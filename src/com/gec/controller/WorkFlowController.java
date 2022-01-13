package com.gec.controller;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.gec.domain.PageBean;
import com.gec.domain.ProcessConfig;
import com.gec.service.WorkFlowService;
import com.gec.utils.BaseUtils;

@Controller
@RequestMapping("/WorkFlow")
public class WorkFlowController extends BaseController {

	@Autowired
	private WorkFlowService workFlowService;

	// 前往流程布署表单
	@RequestMapping("/viewUploadProc")
	public String viewUploadProc() {
		return "workFlow/deployForm";
	}

	// 布署流程
	@RequestMapping("/deployProc")
	public String deployProc(@RequestParam("zipFile") MultipartFile file,
			@RequestParam("procCategory") String procCategory, @RequestParam("note") String note) {
		ProcessConfig cfg = new ProcessConfig();
		cfg.setId(BaseUtils.makeUUID());
		cfg.setProccategory(procCategory);
		cfg.setNote(note);
		String deployId = null;
		try {
			InputStream is = file.getInputStream();
			deployId = workFlowService.createDeployment(cfg, is);
			return "redirect:/WorkFlow/viewProcList?deployId=" + deployId;
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/WorkFlow/viewUploadProc?errMsg=DeployFailed";
		}
	}

	// 前往流程列表
	@RequestMapping("/viewProcList")
	public String viewProcList() {
		return "workFlow/list";
	}

	// /WorkFlow/jsonList
	@RequestMapping(value = "/jsonList", 
					produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String jsonList(@RequestParam("page") int page, 
						   @RequestParam("limit") int limit, 
						   HttpServletRequest req) {
		//获取前端提交的关键字
		String deploymentId = req.getParameter("deploymentId");
		String procCategory = req.getParameter("procCategory");
		String respTxt = "";
		PageBean<ProcessConfig> pBean = null;
		try {
			// 调用 service 方法查询数据(传入关键字)
			pBean = workFlowService.getProcessConfigList(deploymentId, procCategory, page, limit);
			// 转成 json 格式数据.
			respTxt = pBean.toJSON();
		} catch (Exception e) {
			e.printStackTrace();
			// 包装错误信息..
			respTxt = setErrorMsg(e);
		}
		return respTxt;
	}
	
	// /WorkFlow/delete
	@RequestMapping(value="/delete",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delete(@RequestParam("id")String id) {
		JSONObject jsonObject = new JSONObject();
		String respTxt = "";
		try {
			workFlowService.delProcessConfig(id);
			jsonObject.put("result", "success");
			respTxt = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
			respTxt = setErrorMsg(e);
		}
		return respTxt;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
