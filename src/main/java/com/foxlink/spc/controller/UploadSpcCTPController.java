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

import com.foxlink.spc.service.UploadCTPService;
import com.foxlink.spc.service.UploadTOOLService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/UploadSpcCTP")
public class UploadSpcCTPController {
	
	private static Logger logger=Logger.getLogger(UploadSpecController.class);
	private ApplicationContext context=new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
	private UploadCTPService uploadCTPService;
	
	//跳转到jsp文件处理视图
	@RequestMapping(value="/ShowUploadSpcCTP",method=RequestMethod.GET)
	public String ShowAllAccountPage(){
		return "CTPFileUpload";
	}
	
	@RequestMapping("/checkProName.do")
	public @ResponseBody String checkProName(@RequestParam(value="str2V")String str2V )throws IOException {
		uploadCTPService=(UploadCTPService)context.getBean("UploadCTPService");	
		
		return uploadCTPService.CheckProName(str2V);
	}
	
	@RequestMapping(value="/ajaxUploadSpecCTP.do",method=RequestMethod.POST)
	public @ResponseBody String ajaxUploadSpecCTP(HttpSession session,MultipartFile file ) {
		SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		String strUserName =  ((UserDetails)securityContext.getAuthentication().getPrincipal()).getUsername();
		uploadCTPService=(UploadCTPService)context.getBean("UploadCTPService");
		return	uploadCTPService.uploadOK(file,strUserName);
	}
	
	@RequestMapping(value="/ShowCTPSpec",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String ShowAllLink(@RequestParam(value="str2V")String str2V ){
		uploadCTPService = (UploadCTPService) context.getBean("UploadCTPService");
		//System.out.println("專案號"+str2V);
		return uploadCTPService.ShowCTPSpec(str2V);
	}
}

//SelectSpcCTP