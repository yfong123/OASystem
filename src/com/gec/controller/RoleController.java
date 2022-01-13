package com.gec.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.gec.domain.Option;
import com.gec.domain.PageBean;
import com.gec.domain.Role;
import com.gec.service.RoleService;



@Controller
@RequestMapping("/Role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	@RequestMapping("/viewUpload")
	public String viewUpload() {
		return "Role/upload";
	}

	@RequestMapping("/viewList")
	public String viewList() {
		return "Role/list";
	}
	
	@RequestMapping(
			value="/jsonRoleOptions",
			produces="text/html;charset=UTF-8"
		)
	@ResponseBody
	public String jsonRoleOptions(){
		List<Option> list = roleService.getRoleOptions();
		JSONObject jsObj = new JSONObject();
		jsObj.put("roleJson", list);
		return jsObj.toString();
	}
	
	/*
	 * 映射地址: /Role/jsonList 提交参数: page, limit, ...
	 */
	@RequestMapping(value="/jsonList",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String jsonList(@RequestParam("page")int page,
						   @RequestParam("limit")int limit,
						   HttpServletRequest req){
		//获取前端提交的关键字
		String RoleName = req.getParameter("RoleName");
		String nickName = req.getParameter("nickName");
		String fileName = req.getParameter("fileName");
		String respTxt = "";
		PageBean<Role> pBean = null;
		try {
			// {2}调用 service 方法查询数据(传入关键字)
			//pBean = RoleService.getRoleList(RoleName,nickName,fileName,page,limit);
			// {3}转成 json 格式数据.
			respTxt = pBean.toJSON();
		} catch (Exception e) {
			e.printStackTrace(); 
			// 包装错误信息..
			respTxt = setErrorMsg(e);
		}
		return respTxt;
	}

	// 映射地址: /Role/delete
	@RequestMapping(value="/delete",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delete(@RequestParam("id")String id) {
		JSONObject jsonObject = new JSONObject();
		String respTxt = "";
		try {
			//RoleService.delRole(id);
			jsonObject.put("result", "success");
			respTxt = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
			respTxt = setErrorMsg(e);
		}
		return respTxt;
	}
}
