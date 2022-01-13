package com.workflow.listener;

import java.util.Set;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.gec.domain.Dept;
import com.gec.domain.Role;
import com.gec.domain.User;
import com.gec.service.LeaveService;
import com.gec.utils.MyWebUtils;

public class LeaveTaskHandler 
	implements TaskListener {
	
	//{1}这个侦听器是由  Activiti 自动创建
	//   也就是说 Spring 在这里无法注入对象。
	//   唯一办法: 从 IOC 容器中获取该 service 对象。	
	//{2}装配 1 个 service 进来
	private LeaveService leaveService;
	public LeaveTaskHandler(){
		//{2}我可以通过Spring上下文对象获取到这个 Bean
		leaveService = MyWebUtils.getBean(
						LeaveService.class );
	}

	@Override
	public void notify(DelegateTask task) {	
		//{1}取出当前登录系统的操作用户
		//   可能的人: andy{职员}, candy{主管}
		//   从会话域中取出用户
		User curUser = MyWebUtils.getCurrentUser();

		System.out.println("==============================");
		System.out.println(curUser);
		System.out.println("==============================");
		//{2.1}获取当前用户的角色(雇员 or 经理)
		//{2.2}一个员工有多个角色, 这里暂时当他有一个
		//   以后真有多个，修改以下代码。
		Role role = curUser.getPrimaryRole();
		System.out.println("==============================");
		System.out.println(role);
		System.out.println("==============================");
		//{3}获取任务 ID, 流程实例 ID
		String taskID = task.getId();
		String procInsID = task.getProcessInstanceId();
		
		String deptid = curUser.getDeptid();
		String leaderId = null;
		
		//{PS}leader / employee
		String roleName = role.getRolename();
		if(roleName.equals("leader")){
			//{4.1}找上一级领导 ID
			leaderId = leaveService.getHigherDeptLeader( deptid );	
		}else{  //{5.0}如果是员工
			//{5.1}调用 service 去查当前部门的Leader
			leaderId = leaveService.getDeptLeader(deptid);
		}
		//{6.1}设置新建节点的办理人(即是经理)
		task.setAssignee( leaderId );
		//{6.2}更新 t_leave 表 (办理人+任务ID)
		leaveService.updateAsigneeAndTask(
				procInsID, taskID, leaderId );
	}

}







