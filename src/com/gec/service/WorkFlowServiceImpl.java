package com.gec.service;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gec.domain.Document;
import com.gec.domain.PageBean;
import com.gec.domain.ProcessConfig;
import com.gec.domain.ProcessConfigExample;
import com.gec.domain.ProcessConfigExample.Criteria;
import com.gec.mapper.ProcessConfigMapper;

@Service
public class WorkFlowServiceImpl implements WorkFlowService {
	
	@Autowired
	private RepositoryService repositoryService;
	@Autowired 
	private ProcessConfigMapper processConfigMapper;


	@Override
	public String createDeployment(ProcessConfig cfg, InputStream is) {
		ZipInputStream ziplS = new ZipInputStream(is);
		Deployment deploy = repositoryService.createDeployment()
		.addZipInputStream(ziplS)
		.name(cfg.getNote())
		.deploy();
		//获取部署Id
		String deployId = deploy.getId();
		//获取流程定义
		ProcessDefinition procDef = repositoryService.createProcessDefinitionQuery()
				.deploymentId(deployId)
				.singleResult();
		
		cfg.setDeplomentid(deployId);
		cfg.setProcdefid(procDef.getId());
		cfg.setProckey(procDef.getKey());
		cfg.setVersion(procDef.getVersion()+"");
		
		int count = processConfigMapper.addProcessConfig(cfg);
		if (count==1) {
			//返回部署Id
			return deployId;
		}
		return null;
	}


	@Override
	public PageBean<ProcessConfig> getProcessConfigList
					(String deploymentId, String procCategory, int page, int limit) {
		// 查询结果记录偏移量
		int offset = (page - 1) * limit;
		// 创建 PageBean
		PageBean<ProcessConfig> pBean = new PageBean<ProcessConfig>();
		// 获取当前查询到的记录数。
		int count = processConfigMapper.getProcCount(deploymentId,procCategory);
		// 获取当前查询到的记录数据。
		List<ProcessConfig> list = processConfigMapper.getProcList(deploymentId,procCategory,offset, limit);
		// 设置记录数
		pBean.setCount(count);
		// 设置 列表数据
		pBean.setList(list);
		return pBean;
	}


	@Override
	public void delProcessConfig(String id) {
		repositoryService.deleteDeployment(id);
		//使用反向工程的删除方法
		ProcessConfigExample configExample = new ProcessConfigExample();
		Criteria criteria = configExample.createCriteria();
		criteria.andDeplomentidEqualTo(id);
		int count = processConfigMapper.deleteByExample(configExample);
		if (count!=1) {
			throw new RuntimeException("流程删除失败");
		}
	}

}
