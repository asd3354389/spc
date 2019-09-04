package com.foxlink.spc.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.foxlink.spc.service.UploadAllService;
import com.foxlink.spc.service.UploadTOOLService;

@Controller
@RequestMapping("/UploadSpcTOOL")
public class UploadSpcTOOLController {
	private static Logger logger = Logger.getLogger(UploadSpcTOOLController.class);
	private ApplicationContext context=new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
	private UploadTOOLService uploadTOOLService;
	
	@RequestMapping(value="/ShowUploadSpcTOOL",method=RequestMethod.GET)
	public String ShowToolUpload(){
		return "TOOLFileUpload";
	}
	
	@RequestMapping("/checkProName.do")
	public @ResponseBody String checkProName(@RequestParam(value="str2V")String str2V )throws IOException {
		uploadTOOLService=(UploadTOOLService)context.getBean("UploadTOOLService");	
		
		return uploadTOOLService.CheckProName(str2V);
	}
	@RequestMapping(value="/ajaxUploadToolSpec.do",method=RequestMethod.POST)
	public @ResponseBody String ajaxUploadToolSpec(HttpSession session,MultipartFile file ) {
		SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		String strUserName =  ((UserDetails)securityContext.getAuthentication().getPrincipal()).getUsername();
		uploadTOOLService=(UploadTOOLService)context.getBean("UploadTOOLService");
		return	uploadTOOLService.uploadOK(file,strUserName);
	}
	
	@RequestMapping(value="/ShowToolSpec",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String ShowAllLink(@RequestParam(value="str2V")String str2V ){
		uploadTOOLService = (UploadTOOLService) context.getBean("UploadTOOLService");
		return uploadTOOLService.ShowToolSpec(str2V);
	}
}
