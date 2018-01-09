package com.dascom.product.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	//跳转到首页
	@RequestMapping("index")
	public String index(HttpServletRequest request ){

		return "/Index/index";
	}
	
	@RequestMapping("welcome")
	public String welcome(HttpServletRequest request ){
		return "/Index/welcome";
	}
	
	@RequestMapping("400")
	public String to444(HttpServletRequest request ){
		return "/Result/400";
	}
}
