package com.foxlink.spc.controller;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foxlink.spc.service.SelectOPTestService;


@Controller
@RequestMapping("/SelectSpcOPTest")
public class SelectSpcOPTestController {
	private static Logger logger = Logger.getLogger(SelectSpcOPTestController.class);
	private ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
	private SelectOPTestService selectOPTestService;
	
	@RequestMapping(value="/ShowSelectSpcOPTest",method=RequestMethod.GET)
	public String ShowAllAccountPage(){
		return "OPTestFileSelect";
	}
	
	@RequestMapping(value="/ShowProName",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String ShowProName(@RequestParam(value="ProName")String ProName) {
		selectOPTestService = (SelectOPTestService)context.getBean("SelectOPTestService");
		return selectOPTestService.ShowOPTestProName(ProName);
	}
	
	@RequestMapping(value="ShowProNameList",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String ShowProNameList() {
		selectOPTestService=(SelectOPTestService)context.getBean("SelectOPTestService");
		return selectOPTestService.ShowProNameList();
	}
}
