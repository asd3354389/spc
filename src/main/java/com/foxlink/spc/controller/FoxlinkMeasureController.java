package com.foxlink.spc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foxlink.spc.pojo.Measure;
import com.foxlink.spc.pojo.SaveMeasure;
import com.foxlink.spc.service.FoxlinkMeasureService;
import com.foxlink.spc.service.LinkManageService;

@Controller
@RequestMapping("/Measure")
public class FoxlinkMeasureController {
	private static Logger logger=Logger.getLogger(FoxlinkMeasureController.class);
	private ApplicationContext context=new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
	private FoxlinkMeasureService foxlinkMeasureService;
	
	
	
	@RequestMapping(value="/ShowMeasure",method=RequestMethod.GET)
	public String ShowMeasurePage(){
		return "FoxLinkMeasure";
	}
	
	@RequestMapping(value="/ShowAllFactoryAndProjectName",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String ShowAllFactory(){
		foxlinkMeasureService = (FoxlinkMeasureService) context.getBean("foxlinkMeasureService");
		return foxlinkMeasureService.ShowAllFactoryAndProjectName();
	}
	
	@RequestMapping(value="/ShowLink",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String ShowLink(@RequestParam("Factory")String Factory){
		foxlinkMeasureService = (FoxlinkMeasureService) context.getBean("foxlinkMeasureService");
		return foxlinkMeasureService.ShowLink(Factory);
	}
	
	@RequestMapping(value="/ShowPartVerion",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String ShowPartVerion(@RequestParam("ProjectName")String ProjectName){
		foxlinkMeasureService = (FoxlinkMeasureService) context.getBean("foxlinkMeasureService");
		return foxlinkMeasureService.ShowPartVerion(ProjectName);
	}
	
	@RequestMapping(value="/ShowStutas",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String ShowStutas(@RequestParam("PartVersion")String PartVersion){
		foxlinkMeasureService = (FoxlinkMeasureService) context.getBean("foxlinkMeasureService");
		return foxlinkMeasureService.ShowStutas(PartVersion);
	}
	
	@RequestMapping(value="/ShowFrequency",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String ShowFrequency(@RequestParam("ProjectName")String pro_Name,@RequestParam("PartVersion")String part_version,
			@RequestParam("Stutas")String Stutas){
		foxlinkMeasureService = (FoxlinkMeasureService) context.getBean("foxlinkMeasureService");
		return foxlinkMeasureService.ShowFrequency(pro_Name,part_version,Stutas);
	}
	
	@RequestMapping(value="/ShowMeasureTable",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String ShowMeasureTable(SaveMeasure saveMeasure,HttpSession session){
		foxlinkMeasureService = (FoxlinkMeasureService) context.getBean("foxlinkMeasureService");
		session.setAttribute("wanhaoMeasure", saveMeasure);
		System.out.println(saveMeasure);
		return foxlinkMeasureService.ShowMeasureTable(saveMeasure);
	}
	
	@RequestMapping(value="/sumbitMeasureTable",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String sumbitMeasureTable(@RequestBody Measure[] faultList,HttpSession session){
		foxlinkMeasureService = (FoxlinkMeasureService) context.getBean("foxlinkMeasureService");
		SaveMeasure saveMeasure = (SaveMeasure) session.getAttribute("wanhaoMeasure");
		System.out.println(faultList.length);
		for(int i = 0 ;i<faultList.length;i++){
			System.out.println(faultList[i]);
		}
		System.out.println(saveMeasure);
		return foxlinkMeasureService.sumbitMeasureTable(faultList,saveMeasure);
	}
	
	@RequestMapping(value="/getMeasureData",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getMeasureData(HttpServletRequest req){
		foxlinkMeasureService = (FoxlinkMeasureService) context.getBean("foxlinkMeasureService");
		String remoteAddr = req.getRemoteAddr();
		System.out.println(remoteAddr);
		return foxlinkMeasureService.getMeasureData(remoteAddr);
	}
}
