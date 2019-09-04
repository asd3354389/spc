package com.foxlink.spc.controller;

import com.foxlink.spc.service.MeasureDataService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/MeasureData")
public class MeasureDataController {
    private static Logger logger = Logger.getLogger(LinkManageController.class);
    private ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
    private MeasureDataService MeasureDataService;

    @RequestMapping(value = "/ShowMeasureData", method = RequestMethod.GET)
    public String ShowPage() {
        return "MesDataSelect";
    }

    @RequestMapping(value = "/ShowMeasureDataRequire", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowMeasureDataRequire() {
        MeasureDataService = (MeasureDataService) context.getBean("MeasureDataService");
        return MeasureDataService.ShowMeasureDataRequire();
    }

    @RequestMapping(value = "/ShowALLMeasureDataResult", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowALLMeasureDataResult() {
        MeasureDataService = (MeasureDataService) context.getBean("MeasureDataService");
        return MeasureDataService.ShowMeasureDataResult();
    }

    @RequestMapping(value = "/ShowMeasureDataResult", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowMeasureDataResult(@RequestParam("Factory") String Factory,@RequestParam("LINE_NUMBER") String Line,@RequestParam("PART_NUMBER_V") String partVerion,@RequestParam("STATUS") String status,@RequestParam("DATETIME") String date) {
        MeasureDataService = (MeasureDataService) context.getBean("MeasureDataService");
        return MeasureDataService.ShowMeasureDataResult(Factory,Line,partVerion,status,date);
    }

    @RequestMapping(value = "/FactoryDropdownBoxIf", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String FactoryDropdownBoxIf(@RequestParam("Factory") String Factory) {
        MeasureDataService = (MeasureDataService) context.getBean("MeasureDataService");
        System.out.println(Factory);
        return MeasureDataService.FactoryDropdownBoxIf(Factory);
    }

    @RequestMapping(value = "/ShowDropdownBoxDay", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxDay(@RequestParam("Factory") String Factory,@RequestParam("LINE_NUMBER") String Line,@RequestParam("PART_NUMBER_V") String partVerion,@RequestParam("STATUS") String status,@RequestParam("DATETIME") String date) {
        MeasureDataService = (MeasureDataService) context.getBean("MeasureDataService");
        return MeasureDataService.ShowDropdownBoxDay(Factory,Line,partVerion,status,date);
    }//天數
    @RequestMapping(value = "/ShowDropdownBoxFactory", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxFactory() {
        MeasureDataService = (MeasureDataService) context.getBean("MeasureDataService");
        return MeasureDataService.ShowDropdownBoxFactory();
    }//廠區

    @RequestMapping(value = "/ShowDropdownBoxLine", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxLine() {
        MeasureDataService = (MeasureDataService) context.getBean("MeasureDataService");
        return MeasureDataService.ShowDropdownBoxLine();
    }//綫別

    @RequestMapping(value = "/ShowDropdownBoxProjectName", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxProjectName() {
        MeasureDataService = (MeasureDataService) context.getBean("MeasureDataService");
        return MeasureDataService.ShowDropdownBoxProjectName();
    }//專案名稱

    @RequestMapping(value = "/ShowDropdownBoxProjectNames", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxProjectNames(@RequestParam("Factory") String Factory, @RequestParam("LINE_NUMBER") String Line) {
        MeasureDataService = (MeasureDataService) context.getBean("MeasureDataService");
        return MeasureDataService.ShowDropdownBoxProjectName(Factory, Line);
    }

    @RequestMapping(value = "/ShowDropdownBoxPartNumber", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxPartNumber() {
        MeasureDataService = (MeasureDataService) context.getBean("MeasureDataService");
        return MeasureDataService.ShowDropdownBoxPartNumberV();
    }//料號版本

    @RequestMapping(value = "/ShowDropdownBoxPartNumbers", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxPartNumbers(@RequestParam("Factory") String Factory, @RequestParam("LINE_NUMBER") String Line, @RequestParam("ProName") String ProName) {
        MeasureDataService = (MeasureDataService) context.getBean("MeasureDataService");
        return MeasureDataService.ShowDropdownBoxPartNumberV(Factory, Line,ProName);
    }

    @RequestMapping(value = "/ShowDropdownBoxStatus", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxStatus() {
        MeasureDataService = (MeasureDataService) context.getBean("MeasureDataService");
        return MeasureDataService.ShowDropdownBoxStatus();
    }//測量階段

    @RequestMapping(value = "/ShowDropdownBoxStatuss", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxStatuss(@RequestParam("Factory") String Factory, @RequestParam("LINE_NUMBER") String Line, @RequestParam("ProName") String proName,@RequestParam("PartVerion") String partVerion) {
        MeasureDataService = (MeasureDataService) context.getBean("MeasureDataService");
        return MeasureDataService.ShowDropdownBoxStatus(Factory, Line,proName,partVerion);
    }

}
