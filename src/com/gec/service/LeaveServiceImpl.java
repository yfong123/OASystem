package com.gec.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gec.mapper.DeptMapper;
import com.gec.mapper.LeaveMapper;
import com.gec.mapper.ProcessConfigMapper;
import com.gec.domain.Leave;
import com.gec.domain.PageBean;
import com.gec.domain.ProcessConfig;

//{ps}把当前类配置为组件(Bean)
@Service
@Transactional
public class LeaveServiceImpl 
	extends BaseService implements LeaveService {
	
	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private LeaveMapper leaveMapper;
	@Autowired
	private ProcessConfigMapper procConfigMapper;

	
	//{2}自动装配 (工作流相关的Service)
	@Autowired
	private RepositoryService repoService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private RuntimeService rtService;

	@Override
	public String getDeptLeader(String deptId) {
		return deptMapper.findDeptLeaderId(deptId);
	}
	@Override
	public String getHigherDeptLeader(String deptId) {
		//{1}根据当前部门id, 获取上级部门的 id
		String highDept = deptMapper.findHignerLevelDept(
							deptId );
		//{2}根据此 ID, 得到这个上级部门的领导。
		return deptMapper.findDeptLeaderId(highDept);
	}
	@Override
	public void updateAsigneeAndTask(String insId, 
		String taskId, String assignee) {
		int count = leaveMapper.updateAssigneeAndTask(
					insId, taskId, assignee );
		if(count!=1) {
			throw new RuntimeException("更新办理人和任务Id失败");
		}
	}
	
	//{a.1}包装流程变量..
	private Map<String,Object> setVariables( Leave leave ){
		Map<String,Object> vars = new HashMap<String, Object>();
		vars.put("inputUser", leave.getInitiator()); //发起人
		vars.put("startDate", dateToStr(leave.getStartdate())); //开始日期
		vars.put("endDate", dateToStr(leave.getEnddate())); //结束日期
		vars.put("reason", leave.getReason()); //请假原因
		vars.put("days", leave.getDays());    //请假天数
		vars.put("leaveType", leave.getLeavetype()); //请假类型
		vars.put("cancel", "false");  //取消任务
		return vars;
	}
	
	//a.2-提交申请
	private void commitLeave(
			String initiator, String insID){
		//{1}查询任务 ID
		Task task = taskService.createTaskQuery()
			.taskAssignee( initiator )
			.processInstanceId( insID )
			.singleResult();
		//{2}提交任务..
		taskService.complete( task.getId() );
	}
	
	//a.3-开启流程与提交申请
	@Override
	public void startProcess(Leave leave) {
		//{1}计算天数
		int days = calDays(leave.getStartdate(), 
						leave.getEnddate());
		leave.setDays(days);  //设置天数.
		//{2}包装流程变量
		Map<String,Object> procVars = setVariables(leave);
		//{3}查询系统的流程字典表 - 流程定义 ID
		ProcessConfig config = procConfigMapper
							  .getProcConfigByCategory("请假流程");
		String procDefId = config.getProcdefid();
		//{4}开启流程实例(存入流程变量)
		ProcessInstance instance = rtService.startProcessInstanceById(
									procDefId, procVars );
		//{5}补全 leave 信息
		//如: 流程定义ID, 流程实例ID, 创建时间, status
		leave.setProcdefid( procDefId );
		leave.setProcinstid( instance.getId() );
		leave.setCreatetime( dateToStr(new Date()) );
		leave.setStatus("办理中");
		
		//{6}保存到 t_leave 表
		leaveMapper.addLeave( leave );
		
		//{7}提交任务 [发起人,流程实例ID]
		commitLeave( leave.getInitiator(),
					 instance.getId() );
	}
	
	@Override
	public PageBean<Leave> queryMyInitiate(String initiator, int page, int limit) {
		List<Leave> list = null;
		int offset=(page-1)*limit;
		list = leaveMapper.queryMyInitiate(initiator,offset,limit);
		int count = leaveMapper.queryMyInitiateCount(initiator,offset,limit);
		PageBean<Leave> pageBean = new PageBean<Leave>();
		pageBean.setCount(count);
		pageBean.setList( list );
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		return pageBean;
	}

	@Override
	public PageBean<Leave> queryWaitMyApprove(String assigneeId, int page, int limit) {
		int offset=(page-1)*limit;
		List<Leave> list = null;
		list = leaveMapper.queryWaitMyApprove(assigneeId,offset,limit);
		int count = leaveMapper.queryWaitMyApproveCount(assigneeId,offset,limit);
		PageBean<Leave> pageBean = new PageBean<Leave>();
		pageBean.setCount(count);
		pageBean.setList( list );
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		return pageBean;
	}
	
	@Override
	public Leave queryMyTaskByTaskId(String taskId) {
		return leaveMapper.queryMyTaskByTaskId(taskId);
	}
	
	@Override
	public void updateStatusByINstance(String insID, String status) {
		int updateCnt = leaveMapper.updateStatus(insID, status);
		if (updateCnt!=1) {
			throw new RuntimeException("更新任务状态失败");
		}
	}
	
	@Override
	public void completeMyApprove(Map<String, Object> data) {
		String deptId = (String) data.get("deptId");
		String taskId = (String) data.get("taskId");
		String appFlag = (String) data.get("appFlag");
		//获取更高一级部门id
		String pDeptId = deptMapper.findHignerLevelDept(deptId);	
		if (pDeptId.equals("top")) {//我是老板
			data.put("bossApp", appFlag);//处理意见
		}else {
			data.put("deptApp", appFlag);//处理意见
		}
		//提交任务
		taskService.complete(taskId, data);
	}
	
	@Override
	public PageBean<Leave> queryMyApproved(String assigneeId, Integer page, Integer limit) {
		int offset=(page-1)*limit;
		List<Leave> list = null;
		list = leaveMapper.queryMyApproved(assigneeId,offset,limit);
		int count = leaveMapper.queryMyApprovedCount(assigneeId,offset,limit);
		PageBean<Leave> pageBean = new PageBean<Leave>();
		pageBean.setCount(count);
		pageBean.setList( list );
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		return pageBean;
	}


}
