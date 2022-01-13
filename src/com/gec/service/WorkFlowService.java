package com.gec.service;

import java.io.InputStream;

import com.gec.domain.PageBean;
import com.gec.domain.ProcessConfig;

public interface WorkFlowService {

	//布署流程
	public String createDeployment(ProcessConfig config, InputStream is);

	//获取流程列表
	public PageBean<ProcessConfig> getProcessConfigList(String deploymentId,String procCategory,
											int page,int limit);
	
	//删除流程
	void delProcessConfig(String id);
}
