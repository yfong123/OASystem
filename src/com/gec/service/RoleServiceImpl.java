package com.gec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gec.domain.Option;
import com.gec.mapper.RoleMapper;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Option> getRoleOptions() {
		return roleMapper.queryRoleOptions();
	}

}
