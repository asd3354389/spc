package com.foxlink.spc.controller;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foxlink.spc.service.ResetPasswordService;
@Controller
@RequestMapping("/ResetPassword")
public class ResetPasswordController {
	
	private static Logger logger=Logger.getLogger(SelectSpcCTPController.class);
	private ApplicationContext context=new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
	private ResetPasswordService resetPasswordService;
	
	//查詢賬號是否存在
	@RequestMapping(value="/CheckAccount",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String CheckAccount(@RequestParam(value="str2V")String str2V ){
		resetPasswordService = (ResetPasswordService) context.getBean("ResetPasswordService");
		//System.out.println(resetPasswordService.CheckAccount(str2V));
		return resetPasswordService.CheckAccount(str2V);
	}

		//設置新密碼
		@RequestMapping(value="/CheckNewPassword",method=RequestMethod.POST,produces="application/json;charset=utf-8")
		@ResponseBody
		public String CheckNewPassword(@RequestParam(value="str2V")String str2V,@RequestParam(value="newPassword")String newPassword){
			resetPasswordService = (ResetPasswordService) context.getBean("ResetPasswordService");
//			System.out.println("新密碼密碼返回值:" + resetPasswordService.SetNewPassword(str2V));
			System.out.println(str2V+newPassword);
			return resetPasswordService.SetNewPassword(str2V,newPassword);
		}
}
