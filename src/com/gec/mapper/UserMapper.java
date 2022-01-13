package com.gec.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gec.domain.User;
import com.gec.domain.UserExample;

public interface UserMapper {
	
	User queryUserByName(@Param("username") String username);

	public List<User> queryUserList(@Param("offset") Integer offset,
									@Param("limit") Integer limit,
									@Param("data")Map<String, Object> data);
	

	public int queryUserCount(Map<String, Object> data);
	
	public int deleteUserRole(@Param("id") String id);
	
	public int insertUserRole(@Param("id")String id,@Param("roleId") String roleId);
	
	public int updateUserRole(@Param("id")String id,@Param("roleId") String roleId);
	
	String queryMaxId();
	
	User queryByNo(@Param("no") String no);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	
}