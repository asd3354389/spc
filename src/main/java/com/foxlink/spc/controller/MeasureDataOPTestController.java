package com.foxlink.spc.controller;

import com.foxlink.spc.service.MeasureDataOPTestService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/MeasureDataOPTest")
public class MeasureDataOPTestController {
    private static Logger logger = Logger.getLogger(LinkManageController.class);
    private ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
    private MeasureDataOPTestService MeasureDataOPTestService;

    @RequestMapping(value = "/ShowMeasureDataOPTest", method = RequestMethod.GET)
    public String ShowPage() {
        return "MesDataSelectOPTest";
    }

    @RequestMapping(value = "/ShowALLMeasureDataOPTestResult", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowALLMeasureDataOPTestResult() {
        MeasureDataOPTestService = (MeasureDataOPTestService) context.getBean("MeasureDataOPTestService");
        return MeasureDataOPTestService.ShowMeasureDataOPTestResult();
    }

    @RequestMapping(value = "/ShowMeasureDataOPTestResult", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowMeasureDataOPTestResult(@RequestParam("Factory") String Factory,@RequestParam("LINE_NUMBER") String Line,@RequestParam("PART_NUMBER_V") String partVerion,@RequestParam("DATETIME") String date,@RequestParam("PROJECT_NAME") String StrProjectName,@RequestParam("TestType") String testType) {
        System.out.println("xqw!!!!!");
        MeasureDataOPTestService = (MeasureDataOPTestService) context.getBean("MeasureDataOPTestService");
        return MeasureDataOPTestService.ShowMeasureDataOPTestResult(Factory,Line,partVerion,date,StrProjectName,testType);
    }

    @RequestMapping(value = "/ShowMeasureDataOPTestResult1", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowMeasureDataOPTestResult1(@RequestParam("Factory") String Factory,@RequestParam("LINE_NUMBER") String Line,@RequestParam("PART_NUMBER_V") String partVerion,@RequestParam("DATETIME") String date,@RequestParam("PROJECT_NAME") String StrProjectName,@RequestParam("TestType") String testType) {
        System.out.println("cy!!!!!");
        MeasureDataOPTestService = (MeasureDataOPTestService) context.getBean("MeasureDataOPTestService");
        return MeasureDataOPTestService.ShowMeasureDataOPTestResult1(Factory,Line,partVerion,date,StrProjectName,testType);
    }

    @RequestMapping(value = "/FactoryDropdownBoxIf", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String FactoryDropdownBoxIf(@RequestParam("Factory") String Factory) {
        MeasureDataOPTestService = (MeasureDataOPTestService) context.getBean("MeasureDataOPTestService");
        System.out.println(Factory);
        return MeasureDataOPTestService.FactoryDropdownBoxIf(Factory);
    }

    @RequestMapping(value = "/ShowDropdownBoxDay", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxDay(@RequestParam("Factory") String Factory,@RequestParam("LINE_NUMBER") String Line,@RequestParam("PART_NUMBER_V") String partVerion,@RequestParam("DATETIME") String date,@RequestParam("PROJECT_NAME") String proName) {
        MeasureDataOPTestService = (MeasureDataOPTestService) context.getBean("MeasureDataOPTestService");
        return MeasureDataOPTestService.ShowDropdownBoxDay(Factory,Line,partVerion,date,proName);
    }//天數
    @RequestMapping(value = "/ShowDropdownBoxFactory", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxFactory() {
        MeasureDataOPTestService = (MeasureDataOPTestService) context.getBean("MeasureDataOPTestService");
        return MeasureDataOPTestService.ShowDropdownBoxFactory();
    }//廠區

    @RequestMapping(value = "/ShowDropdownBoxLine", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxLine() {
        MeasureDataOPTestService = (MeasureDataOPTestService) context.getBean("MeasureDataOPTestService");
        return MeasureDataOPTestService.ShowDropdownBoxLine();
    }//綫別

    @RequestMapping(value = "/ShowDropdownBoxProjectName", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxProjectName() {
        MeasureDataOPTestService = (MeasureDataOPTestService) context.getBean("MeasureDataOPTestService");
        return MeasureDataOPTestService.ShowDropdownBoxProjectName();
    }//專案名稱

    @RequestMapping(value = "/ShowDropdownBoxProjectNames", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxProjectNames(@RequestParam("Factory") String Factory, @RequestParam("LINE_NUMBER") String Line) {
        MeasureDataOPTestService = (MeasureDataOPTestService) context.getBean("MeasureDataOPTestService");
        return MeasureDataOPTestService.ShowDropdownBoxProjectName(Factory, Line);
    }

    @RequestMapping(value = "/ShowDropdownBoxPartNumber", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxPartNumber() {
        MeasureDataOPTestService = (MeasureDataOPTestService) context.getBean("MeasureDataOPTestService");
        return MeasureDataOPTestService.ShowDropdownBoxPartNumberV();
    }//料號版本

    @RequestMapping(value = "/ShowDropdownBoxPartNumbers", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxPartNumbers(@RequestParam("Factory") String Factory, @RequestParam("LINE_NUMBER") String Line) {
        MeasureDataOPTestService = (MeasureDataOPTestService) context.getBean("MeasureDataOPTestService");
        return MeasureDataOPTestService.ShowDropdownBoxPartNumberV(Factory, Line);
    }

}
