package com.workflow.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

import com.gec.service.ExpenseService;
import com.gec.utils.MyWebUtils;

public class ExpenseEndHandler implements ExecutionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ExpenseService expenseService;
	public ExpenseEndHandler(){
		//{2}我可以通过Spring上下文对象获取到这个 Bean
		expenseService = MyWebUtils.getBean(
				ExpenseService.class );
	}
	
	@Override
	public void notify(DelegateExecution exe) throws Exception {
		String isCancel = (String) exe.getVariable("cancel");
		String _status = "已办结";
		if ("true".equals(isCancel)) {
			_status = "已取消";
		}
		String insID = exe.getProcessInstanceId();
		//更新流程的状态
		expenseService.updateStatusByInstance(insID, _status);
		//更新办理人和任务ID为null
		expenseService.updateAsigneeAndTask(insID, null, null);
	}

	 

}
