package com.wujialong.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wujialong.pojo.PageBean;
import com.wujialong.service.BlogService;

@Controller
public class JumpPageController {
	
	@Autowired
	private BlogService blogService;

	//实验
	//controller中怎么互相跳转；return“forward：方法名”
	//可以转移参数在return的方法名后加？pram=“value”并且可以自动赋参
	//但是用request时不能自动赋参(可能是因为？后是在url后赋参在header中而setAttribute是在body中赋参)
	//使用request.getRequestDispatcher("hello").forward(request, response);和return的效果一样,对于spring都是转发的新请求,重新分配给下一个controller
	@RequestMapping(value="/jumpPage.html")
	public void  jumpPage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String s="abc";
		request.setAttribute("a", s);
//		return "forward:/hello";
		request.getRequestDispatcher("hello").forward(request, response);
	}
	
	@RequestMapping(value="/hello")
	public String  hello(String a,HttpServletRequest request){
		System.out.println(request.getAttribute("a"));
		System.out.println(a);
		return "hello";
	}
	
	
}
