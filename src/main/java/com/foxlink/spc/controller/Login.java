package com.foxlink.spc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login {
	@RequestMapping(value="/admin",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	public ModelAndView admin(HttpSession session){
		System.out.println("登陆成功");
		SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		String username =  ((UserDetails)securityContext.getAuthentication().getPrincipal()).getUsername();
		Map<String, String> m = new HashMap<>();
		m.put("message", username+"登陆成功");
		
		ModelAndView mv = new ModelAndView("success", m);
		return mv;
	}
	
	@RequestMapping(value="/Login",method=RequestMethod.GET)
	public ModelAndView Login(HttpSession session,
			@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout){
		System.out.println("跳转到登陆界面");
		Map<String, String> m = new HashMap<>();
		if(error != null){
			System.out.println(123);
			m.put("message", "账号或密码错误");
		}
		
		if(logout != null){
			System.out.println(234);
			m.put("message", "登出成功");
		}
		
		ModelAndView mv = new ModelAndView("Login", m);
		return mv;
	}
	
	@RequestMapping(value="/403",method=RequestMethod.GET)
	public String NoLegalPower(HttpSession session){
		
		return "403";
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String ShowIndesx(){
		return "index";
	}
	
}
