package com.gec.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.gec.domain.Dept;
import com.gec.domain.User;
import com.github.pagehelper.Page;

public abstract class BaseController {
	//包装分页对象
	protected String toJSON(Page pBean){
		JSONObject jsObj = new JSONObject();
		jsObj.put("code", 0);
		jsObj.put("msg", "");
		jsObj.put("count", pBean.getTotal());
		jsObj.put("data", pBean.toPageInfo().getList());
		return jsObj.toString();
	}
	
	// 设置文件下载的位置 
	private String basePath = "F://upload/";
	protected void download(String file, HttpServletResponse resp)
			throws FileNotFoundException, IOException {
		InputStream is = new FileInputStream(basePath + file);
		file = URLEncoder.encode(file, "UTF-8");
		int size = is.available();
		resp.setContentLengthLong(size);
		resp.setContentType("application/x-msdownload");
		resp.addHeader("Content-Disposition", "attachment;filename=\"" + file + "\"");
		byte[] buff = new byte[8192];
		int count = 0;
		OutputStream os = resp.getOutputStream();
		while (is.available() > 0) {
			count = is.read(buff);
			os.write(buff, 0, count);
		}
		is.close();
	}

	protected String setErrorMsg(Exception e) {
		e.printStackTrace();
		JSONObject jsObj = new JSONObject();
		jsObj.put("result", "failed");
		jsObj.put("cause", e.getMessage());
		return jsObj.toJSONString();
	}

	// {3} now 方法
	// 功能: 获取当前时间。
	protected String now() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date d = new Date();
		return sdf.format(d);
	}

	//"id", "username", "password", "nickname", "email",
	//"sex", "deptId", "no"
	public User makeUser(HttpServletRequest req){
		User user = new User();
		//{1}修改时用使用。
		user.setId( req.getParameter("id") );
		//{2}新增时用使用。
		user.setUsername( req.getParameter("username") ); 
		user.setNickname( req.getParameter("nickname") );
		user.setPassword( req.getParameter("password") );
		user.setDeptid(req.getParameter("deptid") );
		user.setEmail(req.getParameter("email"));
		user.setNo( req.getParameter("no") );
		user.setSex( req.getParameter("sex") );
		return user;
	}
}
