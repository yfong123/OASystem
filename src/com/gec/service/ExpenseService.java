package com.gec.service;

import java.util.Map;

import com.gec.domain.Expense;
import com.gec.domain.PageBean;

public interface ExpenseService {

	//获取某部门某角色的一个用户Id
	String getUidByRoleDept( String roleName,String deptName);
	
	void startProcess(Expense expense);

	PageBean<Expense> queryMyInitiate(String initiator, int page, int limit);

	PageBean<Expense> queryWaitMyApprove(String assigneeId, Integer page, Integer limit);

	Expense queryMyTaskByTaskId(String taskId);

	void completeMyApprove(Map<String, Object> map);

	PageBean<Expense> queryMyApproved(String assigneeId, Integer page, Integer limit);

	void updateAsigneeAndTask(String procInsID, String taskID, String leaderId);

	void updateStatusByInstance(String insID, String _status);

}
