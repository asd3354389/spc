package com.foxlink.spc.controller;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foxlink.spc.service.SelectCTPService;
import com.foxlink.spc.service.UploadCTPService;
@Controller
@RequestMapping("/SelectSpcCTP")
public class SelectSpcCTPController {
	private static Logger logger=Logger.getLogger(SelectSpcCTPController.class);
	private ApplicationContext context=new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
	private SelectCTPService selectCTPService;
	
	@RequestMapping(value="/ShowSelectSpcCTP",method=RequestMethod.GET)
	public String ShowAllAccountPage(){
		return "CTPFileSelect";
	}
	
	@RequestMapping(value="/ShowCTPNameSpec",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String ShowProjectName(@RequestParam(value="str2V")String str2V ){
		selectCTPService = (SelectCTPService) context.getBean("SelectCTPService");
		//System.out.println("專案號"+str2V);
		return selectCTPService.ShowCTPNameSpec(str2V);
	}
	@RequestMapping(value="/ShowCTPSpec",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String ShowAllLink(@RequestParam(value="str2V")String str2V ){
		selectCTPService = (SelectCTPService) context.getBean("SelectCTPService");
		//System.out.println("專案號"+str2V);
		return selectCTPService.ShowCTPSpec(str2V);
	}
}
