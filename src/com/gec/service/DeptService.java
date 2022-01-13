package com.gec.service;

import java.util.List;
import java.util.Map;

import com.gec.domain.Dept;
import com.gec.domain.Option;
import com.github.pagehelper.Page;

public interface DeptService {

	List<Option> getDeptOptions(String id);

	//PageBean<Dept> getDeptList(Integer page, Integer limit, Map<String, Object> keywords);

	Page<Dept> getDeptList(Integer page, Integer limit, Map<String, Object> keywords);
	
	Dept getDeptById(String id);

	void saveDept(Dept dept);

	void delDept(String id);
}
