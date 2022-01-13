package com.workflow.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.gec.domain.Role;
import com.gec.domain.User;
import com.gec.service.ExpenseService;
import com.gec.service.LeaveService;
import com.gec.utils.MyWebUtils;

public class BossTaskHandler 
	implements TaskListener {
	
	//{1}这个侦听器是由  Activiti 自动创建
	//   也就是说 Spring 在这里无法注入对象。
	//   唯一办法: 从 IOC 容器中获取该 service 对象。	
	//{2}装配 1 个 service 进来
	private ExpenseService expenseService;
	
	public BossTaskHandler(){
		//{2}我可以通过Spring上下文对象获取到这个 Bean
		expenseService = MyWebUtils.getBean(
						ExpenseService.class );
	}

	@Override
	public void notify(DelegateTask task) {	
		//{3}获取任务 ID, 流程实例 ID
		String taskID = task.getId();
		String procInsID = task.getProcessInstanceId();
		String assigneeId = null;
		assigneeId = expenseService.getUidByRoleDept("boss", "总经办");
		//设置新建节点的办理人(即是老板)
		task.setAssignee( assigneeId );
		//更新 t_Expense 表 (办理人+任务ID)
		expenseService.updateAsigneeAndTask(procInsID, taskID, assigneeId );
	}

}







