package com.gec.service;

import java.util.Map;

import com.gec.domain.PageBean;
import com.gec.domain.User;

public interface UserService {
	// 任务(1) - 获取用户列表
	PageBean<User> getUserList(Integer page, Integer limit, Map<String, Object> keywords);
	
	// 任务(2) - 新增或更新用户
	void saveUser(User user,String roleId);

	// 任务(3) - 根据用户 id 获取用户
	User getUserById(String id);
	
	void delUser(String id);

	
}
