package com.foxlink.spc.controller;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foxlink.spc.service.RetrievePasswordService;;

@Controller
@RequestMapping("/SendMail")
public class SendMailController {
	private static Logger logger=Logger.getLogger(SelectSpcCTPController.class);
	private ApplicationContext context=new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
	private RetrievePasswordService retrievePasswordService;
	@RequestMapping(value="/MailMessage",method=RequestMethod.GET)
	public String ShowSendMail(){
		
		return "Mailsys1";
	}
	
	//查詢賬號是否存在
		@RequestMapping(value="/SelectAccount",method=RequestMethod.POST,produces="application/json;charset=utf-8")
		@ResponseBody
		public String CheckAccount(@RequestParam(value="str2V")String str2V ){
			retrievePasswordService = (RetrievePasswordService) context.getBean("RetrievePasswordService");
			System.out.println(str2V);
			return retrievePasswordService.CheckAccount(str2V);
		}
		//發送郵件
		@RequestMapping(value="/SendMailPassword",method=RequestMethod.POST,produces="application/json;charset=utf-8")
		@ResponseBody
		public String SendMail() throws Exception{
			retrievePasswordService = (RetrievePasswordService) context.getBean("RetrievePasswordService");
			//System.out.println("發送郵件");
			return retrievePasswordService.SendMailPassword();
		}
}
