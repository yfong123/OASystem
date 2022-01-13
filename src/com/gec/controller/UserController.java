package com.gec.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.gec.domain.PageBean;
import com.gec.domain.PageData;
import com.gec.domain.User;
import com.gec.service.UserService;

@Controller
@RequestMapping("/User")
public class UserController extends BaseController{

	@Autowired
	private UserService userService;
	
	@RequestMapping("/showLogin")
	public String showLogin(HttpSession session) {
		return "forward:/login.jsp";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:/User/showLogin";
	}
	
	
	
	
	@RequestMapping(
			value="/login",
			produces="text/html;charset=UTF-8"
		)
	@ResponseBody
	public String login(@RequestParam("username")String username,
						@RequestParam("password")String password) {
		Md5Hash md5Pwd = new Md5Hash(password, username);
		UsernamePasswordToken token = new UsernamePasswordToken(username, md5Pwd.toString());
		Subject subject = SecurityUtils.getSubject();
		JSONObject jsObj = new JSONObject();
		try {
			subject.login(token);
			Session session = subject.getSession(true);
			User user = (User) subject.getPrincipal();
			session.setAttribute("user", user);
			jsObj.put("result", "success");
		}catch (UnknownAccountException e) {
			e.printStackTrace();
			jsObj.put("result", "failed");
			jsObj.put("cause", "此用户不存在");
		}catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			jsObj.put("result", "failed");
			jsObj.put("cause", "密码错误");
		}
		return jsObj.toString();
	}
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	//  /User/viewList
	@RequestMapping("/viewList")
	public String viewList(Model model){
		model.addAttribute("page", "/User/list");
		return "user/list";
	}
	
	//  /User/jsonList
	@RequestMapping(
			value="/jsonList",
			produces="text/html;charset=UTF-8"
		)
	@ResponseBody
	public String jsonList(@RequestParam Map<String,Object> data){
		//{1}获取 PageData 页信息
		PageData pageData = new PageData( data );
		//{2}调用 userService 获取用户列表
		PageBean<User> pBean = null;
		try{
			pBean = userService.getUserList(
							pageData.getPage(), 
							pageData.getLimit(), 
							pageData.getKeywords() );
			String json = pBean.toJSON();
			return json;
		}catch(Exception e){
			e.printStackTrace();
			return setErrorMsg( e );
		}
	}
	
	//  /User/viewAdd
	@RequestMapping("/viewAdd")
	public String viewAdd(){
		return "user/addUser";
	}
	
	
	// /User/jsonInfo
	@RequestMapping(
			value="/jsonInfo",
			produces="text/html;charset=UTF-8"
		)
	@ResponseBody
	public String jsonInfo(@RequestParam("id") String id){
		User user = null;
		JSONObject jsObj = new JSONObject();
		String respTxt = "";
		try {
			user = userService.getUserById(id);
			jsObj.put("result", "success");
			jsObj.put("user", user);
			respTxt = jsObj.toString();
		} catch (Exception e) {
			respTxt = setErrorMsg(e);
		}
		return respTxt;
	}
	
	//  /User/save
	@RequestMapping(
			value="/save",
			produces="text/html;charset=UTF-8"
		)
	@ResponseBody
	public String save(HttpServletRequest req){
		User user = makeUser(req);
		String roleId = req.getParameter("primaryRole");
		JSONObject jsonObject = new JSONObject();
		String respTxt = "";
		try {
			userService.saveUser(user,roleId);
			jsonObject.put("result", "success");
			respTxt = jsonObject.toString();
		} catch (Exception e) {
			respTxt = setErrorMsg(e);
		}
		return respTxt;
	}
	
	//  /User/delete
	@RequestMapping(
			value="/delete",
			produces="text/html;charset=UTF-8"
		)
	@ResponseBody
	public String delete(@RequestParam("id") String id){
		JSONObject jsonObject = new JSONObject();
		String respTxt = "";
		try {
			userService.delUser(id);
			jsonObject.put("result", "success");
			respTxt = jsonObject.toString();
		} catch (Exception e) {
			respTxt = setErrorMsg(e);
		}
		return respTxt;
	}
}















