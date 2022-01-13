package com.gec.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.gec.domain.Dept;
import com.gec.domain.Option;
import com.gec.domain.PageData;
import com.gec.service.DeptService;
import com.github.pagehelper.Page;



@Controller
@RequestMapping("/Dept")
public class DeptController extends BaseController {

	@Autowired
	private DeptService deptService;

	@RequestMapping("/viewAdd")
	public String viewAdd() {
		return "dept/addDept";
	}

	@RequestMapping("/viewList")
	public String viewList() {
		return "dept/list";
	}
	
	@RequestMapping(
			value="/jsonDeptOptions",
			produces="text/html;charset=UTF-8"
		)
	@ResponseBody
	public String jsonDeptOptions(@RequestParam(value="id",defaultValue="") String id){
		List<Option> list =null;
		JSONObject jsObj = new JSONObject();
		try {
			list = deptService.getDeptOptions(id);
			jsObj.put("result", "success");
			jsObj.put("deptJson", list);
		} catch (Exception e) {
			e.printStackTrace();
			jsObj.put("result", "failed");
		}
		return jsObj.toString();
	}
	
	/*
	 * 映射地址: /Dept/jsonList 提交参数: page, limit, ...
	 */
	@RequestMapping(
			value="/jsonList",
			produces="text/html;charset=UTF-8"
		)
	@ResponseBody
	public String jsonList(@RequestParam Map<String,String> data){
		PageData pageData = new PageData( data );
		//PageBean<Dept> pBean = null;
		Page<Dept> pBean = null;
		try{
			pBean = deptService.getDeptList(
							pageData.getPage(), 
							pageData.getLimit(), 
							pageData.getKeywords() );
			String json = toJSON(pBean);
			return json;
		}catch(Exception e){
			e.printStackTrace();
			return setErrorMsg( e );
		}
	}

	// 映射地址: /Dept/jsonInfo
	@RequestMapping(
			value="/jsonInfo",
			produces="text/html;charset=UTF-8"
		)
	@ResponseBody
	public String jsonInfo(@RequestParam("id") String id){
		Dept dept = null;
		JSONObject jsObj = new JSONObject();
		String respTxt = "";
		try {
			dept = deptService.getDeptById(id);
			jsObj.put("result", "success");
			jsObj.put("dept", dept);
			respTxt = jsObj.toString();
		} catch (Exception e) {
			respTxt = setErrorMsg(e);
		}
		return respTxt;
	}
	
	@RequestMapping(
			value="/save",
			produces="text/html;charset=UTF-8"
		)
	@ResponseBody
	public String save(Dept dept){
		System.out.println(dept);
		JSONObject jsonObject = new JSONObject();
		String respTxt = "";
		try {
			deptService.saveDept(dept);
			jsonObject.put("result", "success");
			respTxt = jsonObject.toString();
		} catch (Exception e) {
			respTxt = setErrorMsg(e);
		}
		return respTxt;
	}
	
	// 映射地址: /Dept/delete
	@RequestMapping(value="/delete",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delete(@RequestParam("id")String id) {
		JSONObject jsonObject = new JSONObject();
		String respTxt = "";
		try {
			deptService.delDept(id);
			jsonObject.put("result", "success");
			respTxt = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
			respTxt = setErrorMsg(e);
		}
		return respTxt;
	}
}
