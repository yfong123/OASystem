package com.gec.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gec.domain.Dept;
import com.gec.domain.DeptExample;
import com.gec.domain.Option;
import com.gec.domain.PageBean;
import com.gec.mapper.DeptMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptMapper deptMapper;
	
	@Override
	public List<Option> getDeptOptions(String id) {
		return deptMapper.queryDeptOptions(id);
	}

//	@Override
//	public PageBean<Dept> getDeptList(Integer page, Integer limit, Map<String, Object> data) {
//		int offset = (page-1)*limit; 	
//		List<Dept> list = null;
//		list = deptMapper.queryDeptList(offset, limit,data);
//		int count = deptMapper.queryDeptCount(data);
//		PageBean<Dept> pBean = new PageBean<Dept>();
//		pBean.setCount( count );
//		pBean.setList( list );		
//		return pBean;
//	}

	@Override
	public Page<Dept> getDeptList(Integer page, Integer limit,Map<String, Object> data) {
		Page<Dept> pageObj = PageHelper.startPage(page, limit);
		List<Dept> list = null;
		list = deptMapper.queryDeptList(data);
		return pageObj;
	}
	
	@Override
	public Dept getDeptById(String id) {
		Dept dept = deptMapper.selectByPrimaryKey(id);
		if (dept==null) {
			throw new RuntimeException("查询部门失败");
		}
		return dept;
	}

	@Override
	public void saveDept(Dept dept) {
		if (dept.getDeptname()==""||dept.getDeptdesc()==""|dept.getParentid()==null) {
			throw new RuntimeException("部门信息不全");
		}
		if (dept.getId()!=null) {
			int updateCnt = deptMapper.updateByPrimaryKeySelective(dept);
			if (updateCnt!=1) {
				throw new RuntimeException("更新部门失败");
			}
		}else {
			DeptExample deptExample = new DeptExample();
			deptExample.createCriteria().andDeptnameEqualTo(dept.getDeptname());
			List<Dept> depts = deptMapper.selectByExample(deptExample);
			if (depts.size()!=0) {
				throw new RuntimeException("部门名已存在");
			}
			String maxId = deptMapper.queryMaxId();
			if (maxId==null) {
				dept.setId("d01");
			}else {
				String id = maxId.split("d")[1];
				int did = Integer.valueOf(id);
				if (did>=1&&did<9) {
					dept.setId("d0"+(did+1));
				}else {
					dept.setId("d"+(did+1));
				}
			}
			int insertCnt = deptMapper.insertSelective(dept);
			if (insertCnt!=1) {
				throw new RuntimeException("添加部门失败");
			}
		}
	}

	@Override
	public void delDept(String id) {
		List<Dept> childDeptList = deptMapper.queryChildDeptList(id);
		if (childDeptList.size()!=0) {
			throw new RuntimeException("该部门有下层部门");
		}
		int delCnt = deptMapper.deleteByPrimaryKey(id);
		if (delCnt!=1) {
			throw new RuntimeException("删除部门失败");
		}
	}

	

}
