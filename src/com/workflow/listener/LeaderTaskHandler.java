package com.workflow.listener;

import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.Task;

import com.gec.domain.Role;
import com.gec.domain.User;
import com.gec.service.ExpenseService;
import com.gec.service.LeaveService;
import com.gec.utils.MyWebUtils;

public class LeaderTaskHandler 
	implements TaskListener {
	
	//{1}这个侦听器是由  Activiti 自动创建
	//   也就是说 Spring 在这里无法注入对象。
	//   唯一办法: 从 IOC 容器中获取该 service 对象。	
	// 装配   service 进来
	private ExpenseService expenseService;
	private LeaveService leaveService;
	private TaskService taskService;
	
	public LeaderTaskHandler(){
 
		expenseService = MyWebUtils.getBean(
						ExpenseService.class );
		leaveService = MyWebUtils.getBean(
				LeaveService.class );
	}

	//taskService.createTaskQuery().taskName("").singleResult();
	@Override
	public void notify(DelegateTask task) {	
		//{1}取出当前登录系统的操作用户
		//   从会话域中取出用户
		User curUser = MyWebUtils.getCurrentUser();
		System.out.println("==============================");
		System.out.println(curUser);
		System.out.println("==============================");
		
		// 获取当前用户的角色
		Role role = curUser.getPrimaryRole();
		System.out.println("==============================");
		System.out.println(role);
		System.out.println("==============================");
		// 获取任务 ID, 流程实例 ID
		String taskID = task.getId();
		String procInsID = task.getProcessInstanceId();
		
		String deptid = curUser.getDeptid();
		String assigneeId = null;
		
//		String roleName = role.getRolename();
//		if(roleName.equals("boss")){
//			assigneeId = expenseService.getUidByRoleDept("leader", "财务部");
//		}else if(roleName.equals("leader")){
//			assigneeId = expenseService.getUidByRoleDept("boss", "总经办");
//		}else{  // 如果是员工
//			// 调用 service 去查当前部门的Leader
//			assigneeId = leaveService.getDeptLeader(deptid);
//		}
		assigneeId = leaveService.getDeptLeader(deptid);
		//{6.1}设置新建节点的办理人
		task.setAssignee( assigneeId );
		// 更新 t_Expense 表 (办理人+任务ID)
		expenseService.updateAsigneeAndTask(procInsID, taskID, assigneeId );
	}

}







