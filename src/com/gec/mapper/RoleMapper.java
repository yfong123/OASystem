package com.gec.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.gec.domain.Option;
import com.gec.domain.Role;
import com.gec.domain.RoleExample;

public interface RoleMapper {
	
	Set<String> getRolePermission(@Param("roleId")String roleId);
	
	List<Option> queryRoleOptions();
	
	String findBossId ();
	
	
	
	
	
	
	
	
	
	
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}