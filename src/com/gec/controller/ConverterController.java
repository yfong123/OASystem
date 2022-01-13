package com.gec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gec.domain.User;

@Controller
@RequestMapping("/Converter")
public class ConverterController extends BaseController{

	@RequestMapping("/getUser")
	public String getUser(@RequestParam("user") User user) {
		System.out.println(user);
		return "";
		
	}
}
