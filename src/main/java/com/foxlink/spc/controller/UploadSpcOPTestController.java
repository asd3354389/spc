package com.foxlink.spc.controller;


import java.io.IOException;

import javax.naming.Context;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.foxlink.spc.service.UploadOPTestService;
import com.foxlink.spc.service.UploadTOOLService;
@Controller
@RequestMapping("/UploadSpcOPTest")
public class UploadSpcOPTestController {
	private static Logger logger = Logger.getLogger(UploadSpcOPTestController.class);
	private ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
	private UploadOPTestService uploadOPTestService;
	
	@RequestMapping(value="/ShowUploadSpcOPTest",method=RequestMethod.GET)
	public String ShowAllAccountPage(){
		return "OPTestFileUpload";
	}
	
	@RequestMapping("/checkProName.do")
	public @ResponseBody String checkProName(@RequestParam(value="str2V")String str2V )throws IOException {
		uploadOPTestService=(UploadOPTestService)context.getBean("UploadOPTestService");	
		
		return uploadOPTestService.CheckProName(str2V);
	}
	
	@RequestMapping(value="ajaxUploadOPTestSpec.do" ,method=RequestMethod.POST)
	public @ResponseBody String ajaxUploadOPTestSpec(HttpSession session,MultipartFile file) {
		SecurityContextImpl securityContext = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		String strUserName = ((UserDetails)securityContext.getAuthentication().getPrincipal()).getUsername();
		uploadOPTestService=(UploadOPTestService)context.getBean("UploadOPTestService");
		return uploadOPTestService.uploadOK(file,strUserName);
	}
	
	@RequestMapping(value="showOPTestSpec",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody String showOPTestSpec(@RequestParam(value="str2V")String str2V) {
		uploadOPTestService=(UploadOPTestService)context.getBean("UploadOPTestService");
		return uploadOPTestService.showOPTestSpc(str2V);
		
	}
}
