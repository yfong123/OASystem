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

import com.gec.domain.Dept;
import com.gec.domain.Expense;
import com.gec.domain.Leave;
import com.gec.domain.PageBean;
import com.gec.domain.ProcessConfig;
import com.gec.mapper.DeptMapper;
import com.gec.mapper.ExpenseMapper;
import com.gec.mapper.ProcessConfigMapper;

@Service
@Transactional
public class ExpenseServiceImpl extends BaseService implements ExpenseService {

	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private ExpenseMapper expenseMapper;
	@Autowired
	private ProcessConfigMapper procConfigMapper;

	// {2}自动装配 (工作流相关的Service)
	@Autowired
	private RepositoryService repoService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private RuntimeService rtService;

	// {a.1}包装流程变量..
	private Map<String, Object> setVariables(Expense expense) {
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("inputUser", expense.getInitiator()); // 发起人
		vars.put("orderDate", dateToStr(expense.getOrderdate())); // 开单日期
		vars.put("expenseDate", dateToStr(expense.getExpensedate())); // 提交报销日期
		vars.put("orderDesc", expense.getOrderdesc()); // 报销内容
		vars.put("days", expense.getDays()); // 间隔天数
		vars.put("money", expense.getMoney()); // 报销金额
		vars.put("cancel", "false"); // 取消任务
		return vars;
	}

	// a.2-提交申请
	private void commitLeave(String initiator, String insID) {
		// {1}查询任务 ID
		Task task = taskService.createTaskQuery()
					.taskAssignee(initiator)
					.processInstanceId(insID)
					.singleResult();
		// {2}提交任务..
		taskService.complete(task.getId());
	}

	@Override
	public void startProcess(Expense expense) {
		//设置提交报销日期
		expense.setExpensedate(new Date());
		// {1}计算天数
		int days = calDays(expense.getOrderdate(),expense.getExpensedate());
		expense.setDays(days); // 设置天数.
		// {2}包装流程变量
		Map<String, Object> procVars = setVariables(expense);
		// {3}查询系统的流程字典表 - 流程定义 ID
		ProcessConfig config = procConfigMapper.getProcConfigByCategory("报销流程");
		String procDefId = config.getProcdefid();
		// {4}开启流程实例(存入流程变量)
		ProcessInstance instance = rtService.startProcessInstanceById(procDefId, procVars);
		// {5}补全 expense 信息
		// 如: 流程定义ID, 流程实例ID, 创建时间, status
		expense.setProcdefid(procDefId);
		expense.setProcinstid(instance.getId());
		expense.setCreatetime(dateToStr(new Date()));
		expense.setStatus("办理中");
		
		System.out.println("====================");
		System.out.println(expense);
		System.out.println("====================");
		// {6}保存到 t_expense 表
		expenseMapper.insertSelective(expense);

		// {7}提交任务 [发起人,流程实例ID]
		commitLeave(expense.getInitiator(), instance.getId());
	}

	@Override
	public PageBean<Expense> queryMyInitiate(String initiator, int page, int limit) {
		List<Expense> list = null;
		int offset=(page-1)*limit;
		list = expenseMapper.queryMyInitiate(initiator,offset,limit);
		int count = expenseMapper.queryMyInitiateCount(initiator,offset,limit);
		PageBean<Expense> pageBean = new PageBean<Expense>();
		pageBean.setCount(count);
		pageBean.setList( list );
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		return pageBean;
	}

	@Override
	public PageBean<Expense> queryWaitMyApprove(String assigneeId, Integer page, Integer limit) {
		int offset=(page-1)*limit;
		List<Expense> list = null;
		list = expenseMapper.queryWaitMyApprove(assigneeId,offset,limit);
		int count = expenseMapper.queryWaitMyApproveCount(assigneeId,offset,limit);
		PageBean<Expense> pageBean = new PageBean<Expense>();
		pageBean.setCount(count);
		pageBean.setList( list );
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		return pageBean;
	}

	@Override
	public Expense queryMyTaskByTaskId(String taskId) {
		return expenseMapper.queryMyTaskByTaskId(taskId);
	}

	@Override
	public void completeMyApprove(Map<String, Object> data) {
		String deptId = (String) data.get("deptId");
		String taskId = (String) data.get("taskId");
		String appFlag = (String) data.get("appFlag");
		//获取更高一级部门id
		String pDeptId = deptMapper.findHignerLevelDept(deptId);	
		Dept dept = deptMapper.selectByPrimaryKey(deptId);
		if (pDeptId.equals("top")) {//我是老板
			data.put("bossApp", appFlag);//处理意见
		}else if (dept.getDeptname().equals("财务部")) {//我是财务部主管
			data.put("finanApp", appFlag);//处理意见
		}else{
			data.put("deptApp", appFlag);//处理意见
		}
		//提交任务
		taskService.complete(taskId, data);
	}

	@Override
	public PageBean<Expense> queryMyApproved(String assigneeId, Integer page, Integer limit) {
		int offset=(page-1)*limit;
		List<Expense> list = null;
		list = expenseMapper.queryMyApproved(assigneeId,offset,limit);
		int count = expenseMapper.queryMyApprovedCount(assigneeId,offset,limit);
		PageBean<Expense> pageBean = new PageBean<Expense>();
		pageBean.setCount(count);
		pageBean.setList( list );
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		return pageBean;
	}

	@Override
	public void updateAsigneeAndTask(String procInsID, String taskID, String leaderId) {
		int count = expenseMapper.updateAssigneeAndTask(
				procInsID, taskID, leaderId );
		if(count!=1) {
			throw new RuntimeException("更新办理人和任务Id失败");
		}
	}

	@Override
	public String getUidByRoleDept( String roleName,String deptName){
		return expenseMapper.queryUidByRoleDept(roleName, deptName);
	}

	@Override
	public void updateStatusByInstance(String insID, String status) {
		int updateCnt = expenseMapper.updateStatus(insID, status);
		if (updateCnt!=1) {
			throw new RuntimeException("更新任务状态失败");
		}
	}

}
