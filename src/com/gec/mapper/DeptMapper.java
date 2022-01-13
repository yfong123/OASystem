package com.gec.mapper;

import com.gec.domain.Dept;
import com.gec.domain.DeptExample;
import com.gec.domain.Option;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DeptMapper {

	// {A}功能:获取部门领导ID
	String findDeptLeaderId(@Param("deptId") String deptId);

	// 功能:获取部门经理ID
	String findDeptManagerId(@Param("deptId") String deptId);
		
	// {B}功能:获取上级部门的 ID
	String findHignerLevelDept(@Param("deptId") String deptId);

	List<Option> queryDeptOptions(@Param("id") String id);
	
//	List<Dept> queryDeptList(@Param("offset")Integer offset, 
//							 @Param("limit")Integer limit,
//							 @Param("data")Map<String, Object> data);

	List<Dept> queryDeptList(
			 @Param("data")Map<String, Object> data);
	
	int queryDeptCount(@Param("data")Map<String, Object> data);
	
	
	List<Dept> queryChildDeptList(@Param("id") String id);
	
	String queryMaxId();
	
	
	
	
	
	
	
	
	
	
	long countByExample(DeptExample example);

	int deleteByExample(DeptExample example);

	int deleteByPrimaryKey(String id);

	int insert(Dept record);

	int insertSelective(Dept record);

	List<Dept> selectByExample(DeptExample example);

	Dept selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") Dept record, @Param("example") DeptExample example);

	int updateByExample(@Param("record") Dept record, @Param("example") DeptExample example);

	int updateByPrimaryKeySelective(Dept record);

	int updateByPrimaryKey(Dept record);

	
}