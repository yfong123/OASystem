package com.gec.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.gec.domain.Leave;
import com.gec.domain.PageBean;
import com.gec.service.LeaveService;

@Controller
@RequestMapping("/Leave")
public class LeaveController extends BaseController{
	
	@Autowired
	private LeaveService leaveService;

	//----------------------[用户申请请假]-----------------------
	/*
	   {2}展示用户申请页
	   {a}映射地址:/showLeaveForm [入口]
	*/
	@RequestMapping("/showLeaveForm")
	public String showLeaveForm(){
		//内部转发: leave/leaveForm.jsp
		return "leave/leaveForm";
	}
	
	@RequestMapping(
		value="/submitLeave",
		produces="text/html;charset=UTF-8"
	)
	@ResponseBody
	public String submitLeave(Leave leave){
		String respTxt = "";
		JSONObject jsObj = new JSONObject();
		try{
			//{1}发起申请
			leaveService.startProcess( leave );
			jsObj.put("result", "success");
			jsObj.put("leave", leave);
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
		return "leave/myInitiate";
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
		PageBean<Leave> pBean = null;
		String respTxt = "";
		try{
			pBean = leaveService.queryMyInitiate(
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
		return "leave/waitMyApprove";
	}
	
	@RequestMapping(
			value="/jsonWaitMyApprove",
			produces="text/html;charset=UTF-8"
		)
	@ResponseBody
	public String jsonWaitMyApprove(@RequestParam("assigneeId")String assigneeId,
									@RequestParam(value="page")Integer page,
									@RequestParam(value="limit")Integer limit) {
		PageBean<Leave> pageBean = null;
		try {
			pageBean = leaveService.queryWaitMyApprove(assigneeId, page, limit);
			return pageBean.toJSON();
		} catch (Exception e) {
			return setErrorMsg(e);
		}
	}
	
	@RequestMapping("taskDetail")
	public String taskDetail(@RequestParam("taskId") String taskId,Model model) {
		Leave leave = null;
		try {
			leave = leaveService.queryMyTaskByTaskId(taskId);
			model.addAttribute("leave", leave);
			return "leave/taskDetail";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/erroe.jsp?msg=";
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
			leaveService.completeMyApprove(map);
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
		return "leave/myApproved";
	}
	
	@RequestMapping(
			value="/jsonMyApproved",
			produces="text/html;charset=UTF-8"
		)
	@ResponseBody
	public String jsonMyApproved(@RequestParam("assignee")String assigneeId,
									@RequestParam(value="page")Integer page,
									@RequestParam(value="limit")Integer limit) {
		PageBean<Leave> pageBean = null;
		try {
			pageBean = leaveService.queryMyApproved(assigneeId, page, limit);
			return pageBean.toJSON();
		} catch (Exception e) {
			return setErrorMsg(e);
		}
	}
}


























