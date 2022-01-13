package com.gec.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gec.domain.Leave;
import com.gec.domain.PageBean;

public interface LeaveService {
	// {1}获取当前部门领导
	String getDeptLeader(String deptId);

	// {2}获取上级部门领导
	String getHigherDeptLeader(String deptid);

	// {3}更新办理人与任务ID
	void updateAsigneeAndTask(String insId, String taskid, String assignee);
	
	//开启流程与提交申请
	void startProcess(Leave leave);
	
	//查看我发起的任务
	 PageBean<Leave> queryMyInitiate(String initiator, int page, int limit);
	
	//查看待我审批的业务
	PageBean<Leave> queryWaitMyApprove(String assigneeId,int page,int limit );
	
	//查询待我审批的一个任务
	Leave queryMyTaskByTaskId(String taskId);
	
	void updateStatusByINstance(String insID,String status);
	
	void completeMyApprove(Map<String, Object> data);

	PageBean<Leave> queryMyApproved(String assigneeId, Integer page, Integer limit);
}
