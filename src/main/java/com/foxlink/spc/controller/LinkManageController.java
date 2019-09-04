package com.foxlink.spc.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foxlink.spc.service.LinkManageService;


@Controller
@RequestMapping("/LinkManage")
public class LinkManageController {
	private static Logger logger=Logger.getLogger(LinkManageController.class);
	private ApplicationContext context=new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
	private LinkManageService linkManageService;

	@RequestMapping(value="/ShowLinkManage",method=RequestMethod.GET)
	public String ShowLinkManagePage(){
		return "LinkManage";
	}
	
	@RequestMapping(value="/ShowAllLink",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String ShowAllLink(){
		linkManageService = (LinkManageService) context.getBean("linkManageService");
		return linkManageService.ShowAllLink();
	}
	
	@RequestMapping(value="/AddLink",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String AddLink(@RequestParam("Factory")String Factory,@RequestParam("Floor")String Floor,@RequestParam("Line_Number")String Line_Number,
			@RequestParam("Dept_Code")String Dept_Code,@RequestParam("Production_Dept")String Production_Dept,@RequestParam("Wechat_Group")String Wechat_Group){
		linkManageService = (LinkManageService) context.getBean("linkManageService");
		System.out.println(Factory);
		return linkManageService.AddLink(Factory,Floor,Line_Number,Dept_Code,Production_Dept,Wechat_Group);
	}
	
	@RequestMapping(value="/DeleteLink",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String DeleteLink(@RequestParam("Factory")String Factory,@RequestParam("Line_Number")String Line_Number){
		linkManageService = (LinkManageService) context.getBean("linkManageService");
		System.out.println(Factory);
		return linkManageService.DeleteLink(Factory,Line_Number);
	}

}
