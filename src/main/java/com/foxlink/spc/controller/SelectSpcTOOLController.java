package com.foxlink.spc.controller;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foxlink.spc.service.SelectTOOLService;

@Controller
@RequestMapping("/SelectSpcTOOL")
public class SelectSpcTOOLController {
	private static Logger logger = Logger.getLogger(SelectSpcTOOLController.class);
	private ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
	private SelectTOOLService selectTOOLService;
	
	
	@RequestMapping(value="/ShowSelectSpcTOOL",method=RequestMethod.GET)
	public String ShowToolSelect(){
		return "TOOLFileSelect";
	}
	
	@RequestMapping(value="/ShowProName",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String ShowProName(@RequestParam(value="ProName")String ProName ){
		selectTOOLService = (SelectTOOLService) context.getBean("SelectTOOLService");
		return selectTOOLService.ShowToolProName(ProName);
	}
	
	@RequestMapping(value="/ShowProNameList",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String ShowProNameList(){
		selectTOOLService = (SelectTOOLService) context.getBean("SelectTOOLService");
		return selectTOOLService.ShowProNameList();
	}
}
