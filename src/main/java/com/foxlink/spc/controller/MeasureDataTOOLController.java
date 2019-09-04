package com.foxlink.spc.controller;

import com.foxlink.spc.service.MeasureDataTOOLService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/MeasureDataTOOL")
public class MeasureDataTOOLController {
    private static Logger logger = Logger.getLogger(LinkManageController.class);
    private ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
    private MeasureDataTOOLService MeasureDataTOOLService;

    @RequestMapping(value = "/ShowMeasureDataTOOL", method = RequestMethod.GET)
    public String ShowPage() {
        return "MesDataSelectTOOL";
    }

//    @RequestMapping(value = "/ShowMeasureDataTOOLRequire", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
//    @ResponseBody
//    public String ShowMeasureDataTOOLRequire() {
//        MeasureDataTOOLService = (MeasureDataTOOLService) context.getBean("MeasureDataTOOLService");
//        return MeasureDataTOOLService.ShowMeasureDataTOOLRequire();
//    }

    @RequestMapping(value = "/ShowALLMeasureDataTOOLResult", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowALLMeasureDataTOOLResult() {
        MeasureDataTOOLService = (MeasureDataTOOLService) context.getBean("MeasureDataTOOLService");
        return MeasureDataTOOLService.ShowMeasureDataTOOLResult();
    }

    @RequestMapping(value = "/ShowMeasureDataTOOLResult", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowMeasureDataTOOLResult(@RequestParam("Factory") String Factory,@RequestParam("LINE_NUMBER") String Line,@RequestParam("PART_NUMBER_V") String partVerion,@RequestParam("DATETIME") String date,@RequestParam("PROJECT_NAME") String StrProjectName) {
        MeasureDataTOOLService = (MeasureDataTOOLService) context.getBean("MeasureDataTOOLService");
        return MeasureDataTOOLService.ShowMeasureDataTOOLResult(Factory,Line,partVerion,date,StrProjectName);
    }

    @RequestMapping(value = "/FactoryDropdownBoxIf", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String FactoryDropdownBoxIf(@RequestParam("Factory") String Factory) {
        MeasureDataTOOLService = (MeasureDataTOOLService) context.getBean("MeasureDataTOOLService");
        System.out.println(Factory);
        return MeasureDataTOOLService.FactoryDropdownBoxIf(Factory);
    }

    @RequestMapping(value = "/ShowDropdownBoxDay", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxDay(@RequestParam("Factory") String Factory,@RequestParam("LINE_NUMBER") String Line,@RequestParam("PART_NUMBER_V") String partVerion,@RequestParam("DATETIME") String date,@RequestParam("PROJECT_NAME") String proName) {
        MeasureDataTOOLService = (MeasureDataTOOLService) context.getBean("MeasureDataTOOLService");
        return MeasureDataTOOLService.ShowDropdownBoxDay(Factory,Line,partVerion,date,proName);
    }//天數
    @RequestMapping(value = "/ShowDropdownBoxFactory", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxFactory() {
        MeasureDataTOOLService = (MeasureDataTOOLService) context.getBean("MeasureDataTOOLService");
        return MeasureDataTOOLService.ShowDropdownBoxFactory();
    }//廠區

    @RequestMapping(value = "/ShowDropdownBoxLine", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxLine() {
        MeasureDataTOOLService = (MeasureDataTOOLService) context.getBean("MeasureDataTOOLService");
        return MeasureDataTOOLService.ShowDropdownBoxLine();
    }//綫別

    @RequestMapping(value = "/ShowDropdownBoxProjectName", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxProjectName() {
        MeasureDataTOOLService = (MeasureDataTOOLService) context.getBean("MeasureDataTOOLService");
        return MeasureDataTOOLService.ShowDropdownBoxProjectName();
    }//專案名稱

    @RequestMapping(value = "/ShowDropdownBoxProjectNames", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxProjectNames(@RequestParam("Factory") String Factory, @RequestParam("LINE_NUMBER") String Line) {
        MeasureDataTOOLService = (MeasureDataTOOLService) context.getBean("MeasureDataTOOLService");
        return MeasureDataTOOLService.ShowDropdownBoxProjectName(Factory, Line);
    }

    @RequestMapping(value = "/ShowDropdownBoxPartNumber", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxPartNumber() {
        MeasureDataTOOLService = (MeasureDataTOOLService) context.getBean("MeasureDataTOOLService");
        return MeasureDataTOOLService.ShowDropdownBoxPartNumberV();
    }//料號版本

    @RequestMapping(value = "/ShowDropdownBoxPartNumbers", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxPartNumbers(@RequestParam("Factory") String Factory, @RequestParam("LINE_NUMBER") String Line,@RequestParam("ProName") String ProName) {
        MeasureDataTOOLService = (MeasureDataTOOLService) context.getBean("MeasureDataTOOLService");
        return MeasureDataTOOLService.ShowDropdownBoxPartNumberV(Factory, Line,ProName);
    }

}
