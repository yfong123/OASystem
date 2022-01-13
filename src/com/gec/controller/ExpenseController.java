package com.gec.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.gec.domain.Expense;
import com.gec.domain.PageBean;
import com.gec.service.ExpenseService;

@Controller
@RequestMapping("/Expense")
public class ExpenseController extends BaseController{
	
	@Autowired
	private ExpenseService expenseService;

	//----------------------[用户申请报销]-----------------------
	/*
	   {2}展示用户申请页
	   {a}映射地址:/showExpenseForm [入口]
	*/
	@RequestMapping("/showExpenseForm")
	public String showExpenseForm(){
		//内部转发: expense/ExpenseForm.jsp
		return "expense/expenseForm";
	}
	
	@RequestMapping(
		value="/submitExpense",
		produces="text/html;charset=UTF-8"
	)
	@ResponseBody
	public String submitExpense(Expense expense){
		System.out.println("====================");
		System.out.println(expense);
		System.out.println("====================");
		String respTxt = "";
		JSONObject jsObj = new JSONObject();
		try{
			//{1}发起申请
			expenseService.startProcess( expense );
			jsObj.put("result", "success");
			jsObj.put("expense", expense);
			respTxt = jsObj.toString();
		}catch(Exception e){
			//{2}调用父类的方法包装错误信息。
			respTxt = setErrorMsg(e);
		}
		return respTxt;
	}
	
	
	//----------------------[A.查询我发起的任务]-------------------------
	@RequestMapping("/myInitiate")
	public String myInitiate(){
		//{ps}内部转发: myInitiate.jsp
		return "expense/myInitiate";
	}
	
	@RequestMapping(
		value="/jsonMyInitiate",
		produces="text/html;charset=UTF-8"
	)
	@ResponseBody
	public String jsonMyInitiate(
		@RequestParam("initiator")String initiator,
		@RequestParam("page")int page,
		@RequestParam("limit")int limit
		){
		PageBean<Expense> pBean = null;
		String respTxt = "";
		try{
			pBean = expenseService.queryMyInitiate(
				initiator,page,limit );
			respTxt = pBean.toJSON();
		}catch(Exception e){
			e.printStackTrace();
			//{2}打包出错信息。
			respTxt = setErrorMsg(e);
		}
		return respTxt;
	}
	
	@RequestMapping("waitMyApprove")
	public String waitMyApprove() {
		return "expense/waitMyApprove";
	}
	
	@RequestMapping(
			value="/jsonWaitMyApprove",
			produces="text/html;charset=UTF-8"
		)
	@ResponseBody
	public String jsonWaitMyApprove(@RequestParam("assigneeId")String assigneeId,
									@RequestParam(value="page")Integer page,
									@RequestParam(value="limit")Integer limit) {
		PageBean<Expense> pageBean = null;
		try {
			pageBean = expenseService.queryWaitMyApprove(assigneeId, page, limit);
			return pageBean.toJSON();
		} catch (Exception e) {
			return setErrorMsg(e);
		}
	}
	
	@RequestMapping("taskDetail")
	public String taskDetail(@RequestParam("taskId") String taskId,Model model) throws Exception {
		Expense expense = null;
		try {
			expense = expenseService.queryMyTaskByTaskId(taskId);
			System.out.println("========================");
			System.out.println(expense.getOrderdate());
			System.out.println("========================");
			model.addAttribute("expense", expense);
			return "expense/taskDetail";
		} catch (Exception e) {
			e.printStackTrace();
			String msg = e.getMessage();
			msg = URLEncoder.encode(msg, "UTF-8");
			return "redirect:/error.jsp?msg="+msg;
		}	
	}
	
	@RequestMapping(
			value="/doApprove",
			produces="text/html;charset=UTF-8"
		)
	@ResponseBody
	public String doApprove(@RequestParam Map<String, Object> map) {
		String respTxt = "";
		JSONObject jsObj = new JSONObject();
		try{
			//{1}提交审批
			expenseService.completeMyApprove(map);
			jsObj.put("result", "success");
			jsObj.put("operation", "doApprove");
			respTxt = jsObj.toString();
		}catch(Exception e){
			//{2}调用父类的方法包装错误信息。
			respTxt = setErrorMsg(e);
		}
		return respTxt;
	}
	
	@RequestMapping("myApproved")
	public String myApproved() {
		return "expense/myApproved";
	}
	
	@RequestMapping(
			value="/jsonMyApproved",
			produces="text/html;charset=UTF-8"
		)
	@ResponseBody
	public String jsonMyApproved(@RequestParam("assignee")String assigneeId,
									@RequestParam(value="page")Integer page,
									@RequestParam(value="limit")Integer limit) {
		PageBean<Expense> pageBean = null;
		try {
			pageBean = expenseService.queryMyApproved(assigneeId, page, limit);
			return pageBean.toJSON();
		} catch (Exception e) {
			return setErrorMsg(e);
		}
	}
}


























