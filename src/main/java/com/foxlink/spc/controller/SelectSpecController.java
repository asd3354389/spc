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
import com.foxlink.spc.service.SelectSpcService;
@Controller
@RequestMapping("/SelectSpec")
public class SelectSpecController {
	
	private static Logger logger=Logger.getLogger(SelectSpcCTPController.class);
	private ApplicationContext context=new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
	private SelectSpcService selectSpcService;
	
	@RequestMapping(value="/ShowSelectSpec", method=RequestMethod.GET)
	public String ShowAllAccountPage(){
		return "SPFileSelect";
	}
	
	//顯示數據庫的專案名稱
	@RequestMapping(value="/ShowSpcNameSpec",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String ShowProjectName(@RequestParam(value="str2V")String str2V ){
		selectSpcService = (SelectSpcService) context.getBean("SelectSpcService");
		//System.out.println("SPC專案號"+str2V);
		return 	selectSpcService.ShowSpcNameSpec(str2V);
	}
	
	//顯示列表
	@RequestMapping(value="/ShowSpcSpec",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String ShowAllLink(@RequestParam(value="str2V")String str2V ){
		selectSpcService = (SelectSpcService) context.getBean("SelectSpcService");
		System.out.println("SPC的專案號"+str2V);
		return selectSpcService.ShowSpcSpec(str2V);
	}
}
