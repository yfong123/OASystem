package com.gec.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.gec.domain.User;

public class MyWebUtils {
	//方法(1):
	public static void print(Object obj) {
		System.out.println("{WebUtils}"+ obj);
	}	
	
	//方法(2):	
	//功能: 获取 Spring ioc 容器中的 Bean
	//{1}获取 Web 应用程序的 ioc 容器对象。
	//   ContextLoader.getCurrentWebApplicationContext();
	public static <T> T getBean( Class<T> type ){
		WebApplicationContext ioc = 
			ContextLoader.getCurrentWebApplicationContext();
		return ioc.getBean( type );
	}
	
	//方法(3):
	//功能: 在其它非控制器方法中获取 请求对象。
	//     在监听器中获取请求。
	public static HttpServletRequest getRequest(){
		//{1} 获取请求属性对象
		RequestAttributes attr = RequestContextHolder
						.getRequestAttributes();
		//{2} 转个类型
		ServletRequestAttributes serAttr = 
				(ServletRequestAttributes)attr;
		//{3} 获取请求对象。
		if( serAttr!=null ){
			return serAttr.getRequest();
		}
		return null;
	}
	
	//方法(4):
	//功能: 获取当前会话域中听用户
	public static User getCurrentUser(){
		HttpServletRequest req = getRequest();
		if( req!=null ){
			return (User)req.getSession()
			.getAttribute("user");
		} 
		return null;
	}
	
}
