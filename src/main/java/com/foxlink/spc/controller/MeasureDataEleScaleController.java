package com.foxlink.spc.controller;

import com.foxlink.spc.service.MeasureDataEleScaleService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/MeasureDataEleScale")
public class MeasureDataEleScaleController {
    private static Logger logger = Logger.getLogger(LinkManageController.class);
    private ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
    private MeasureDataEleScaleService MeasureDataEleScaleService;

    @RequestMapping(value = "/ShowMeasureDataEleScale", method = RequestMethod.GET)
    public String ShowPage() {
        return "MesDataSelectEleScale";
    }

    @RequestMapping(value = "/ShowMeasureDataEleScaleResult", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowMeasureDataEleScaleResult(@RequestParam("Factory") String Factory,@RequestParam("LINE_NUMBER") String Line,@RequestParam("PART_NUMBER_V") String partVerion,@RequestParam("STATUS") String status,@RequestParam("DATETIME") String date,@RequestParam("ProName") String proName) {
        MeasureDataEleScaleService = (MeasureDataEleScaleService) context.getBean("MeasureDataEleScaleService");
        return MeasureDataEleScaleService.ShowMeasureDataEleScaleResult(Factory,Line,partVerion,status,date,proName);
    }

    @RequestMapping(value = "/FactoryDropdownBoxIf", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String FactoryDropdownBoxIf(@RequestParam("Factory") String Factory) {
        MeasureDataEleScaleService = (MeasureDataEleScaleService) context.getBean("MeasureDataEleScaleService");
        System.out.println(Factory);
        return MeasureDataEleScaleService.FactoryDropdownBoxIf(Factory);
    }

    @RequestMapping(value = "/ShowDropdownBoxDay", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxDay(@RequestParam("Factory") String Factory,@RequestParam("LINE_NUMBER") String Line,@RequestParam("PART_NUMBER_V") String partVerion,@RequestParam("STATUS") String status,@RequestParam("DATETIME") String date) {
        MeasureDataEleScaleService = (MeasureDataEleScaleService) context.getBean("MeasureDataEleScaleService");
        return MeasureDataEleScaleService.ShowDropdownBoxDay(Factory,Line,partVerion,status,date);
    }//天數
    @RequestMapping(value = "/ShowDropdownBoxFactory", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxFactory() {
        MeasureDataEleScaleService = (MeasureDataEleScaleService) context.getBean("MeasureDataEleScaleService");
        return MeasureDataEleScaleService.ShowDropdownBoxFactory();
    }//廠區

    @RequestMapping(value = "/ShowDropdownBoxLine", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxLine() {
        MeasureDataEleScaleService = (MeasureDataEleScaleService) context.getBean("MeasureDataEleScaleService");
        return MeasureDataEleScaleService.ShowDropdownBoxLine();
    }//綫別

    @RequestMapping(value = "/ShowDropdownBoxProjectName", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxProjectName() {
        MeasureDataEleScaleService = (MeasureDataEleScaleService) context.getBean("MeasureDataEleScaleService");
        return MeasureDataEleScaleService.ShowDropdownBoxProjectName();
    }//專案名稱

    @RequestMapping(value = "/ShowDropdownBoxProjectNames", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxProjectNames(@RequestParam("Factory") String Factory, @RequestParam("LINE_NUMBER") String Line) {
        MeasureDataEleScaleService = (MeasureDataEleScaleService) context.getBean("MeasureDataEleScaleService");
        return MeasureDataEleScaleService.ShowDropdownBoxProjectName(Factory, Line);
    }

    @RequestMapping(value = "/ShowDropdownBoxPartNumber", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxPartNumber() {
        MeasureDataEleScaleService = (MeasureDataEleScaleService) context.getBean("MeasureDataEleScaleService");
        return MeasureDataEleScaleService.ShowDropdownBoxPartNumberV();
    }//料號版本

    @RequestMapping(value = "/ShowDropdownBoxPartNumbers", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxPartNumbers(@RequestParam("Factory") String Factory, @RequestParam("LINE_NUMBER") String Line,@RequestParam("ProName") String ProName) {
        MeasureDataEleScaleService = (MeasureDataEleScaleService) context.getBean("MeasureDataEleScaleService");
        return MeasureDataEleScaleService.ShowDropdownBoxPartNumberV(Factory, Line,ProName);
    }

    @RequestMapping(value = "/ShowDropdownBoxStatus", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxStatus() {
        MeasureDataEleScaleService = (MeasureDataEleScaleService) context.getBean("MeasureDataEleScaleService");
        return MeasureDataEleScaleService.ShowDropdownBoxStatus();
    }//測量階段

    @RequestMapping(value = "/ShowDropdownBoxStatuss", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxStatuss(@RequestParam("Factory") String Factory, @RequestParam("LINE_NUMBER") String Line,@RequestParam("ProName") String ProName, @RequestParam("PartVerion") String PartVerion) {
        MeasureDataEleScaleService = (MeasureDataEleScaleService) context.getBean("MeasureDataEleScaleService");
        return MeasureDataEleScaleService.ShowDropdownBoxStatus(Factory, Line,ProName,PartVerion);
    }

}
