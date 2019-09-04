package com.foxlink.spc.service;

import com.foxlink.spc.dao.MeasureDataTOOLDao;
import com.foxlink.spc.pojo.MeasureDataTOOLRequireInfo;
import com.foxlink.spc.pojo.MeasureDataTOOLResultInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("MeasureDataTOOLService")
public class MeasureDataTOOLService {
    private static Logger logger = Logger.getLogger(MeasureDataTOOLService.class);
    private MeasureDataTOOLDao MeasureDataTOOLDao;

    @Autowired
    public void setInkManageDao(MeasureDataTOOLDao MeasureDataTOOLDao) {
        this.MeasureDataTOOLDao = MeasureDataTOOLDao;
    }

//    public String ShowMeasureDataTOOLRequire() {
//        JsonObject result = new JsonObject();
//        List<MeasureDataTOOLRequireInfo> dci_list = MeasureDataTOOLDao.ShowMeasureDataTOOLRequire();
//        Gson gson = new GsonBuilder().serializeNulls().create();
//        if (dci_list.size() == 0 || dci_list == null) {
//            result.addProperty("StatusCode", "500");
//            result.addProperty("message", "查無數據");
//        } else {
//            result.addProperty("StatusCode", "200");
//            result.addProperty("message", gson.toJson(dci_list));
//        }
//        System.out.println(result.toString());
//        return result.toString();
//    }

    public String ShowMeasureDataTOOLResult() {
        JsonObject result = new JsonObject();
        List<MeasureDataTOOLResultInfo> list_dcResult = MeasureDataTOOLDao.ShowMeasureDataTOOLResult();
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }
    public String ShowMeasureDataTOOLResult(String StrFactory,String StrLine,String StrPartNumberV,String date,String StrProjectName) {
        JsonObject result = new JsonObject();
        List<MeasureDataTOOLResultInfo> list_dcResult = MeasureDataTOOLDao.ShowMeasureDataTOOLResult(StrFactory,StrLine,StrPartNumberV,date,StrProjectName);
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }

    public String FactoryDropdownBoxIf(String StrFactory) {
        JsonObject result = new JsonObject();
        List<MeasureDataTOOLRequireInfo> dci_list = MeasureDataTOOLDao.FactoryDropdownBoxIf(StrFactory);
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (dci_list.size() == 0 || dci_list == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(dci_list));
        }
        System.out.println(result.toString());
        return result.toString();
    }

    public String ShowDropdownBoxDay(String StrFactory,String StrLine,String StrPartNumberV,String date,String proName) {
        JsonObject result = new JsonObject();
        List<MeasureDataTOOLRequireInfo> list_dcResult = MeasureDataTOOLDao.ShowDay(StrFactory,StrLine,StrPartNumberV,date,proName);
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "ShowDropdownBoxDay-->天數查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }//獲取天數

    public String ShowDropdownBoxFactory() {
        JsonObject result = new JsonObject();
        List<MeasureDataTOOLRequireInfo> list_dcResult = MeasureDataTOOLDao.ShowFactory();
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "ShowDropdownBoxFactory-->廠區查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }//獲取廠區

    public String ShowDropdownBoxLine() {
        JsonObject result = new JsonObject();
        List<MeasureDataTOOLRequireInfo> list_dcResult = MeasureDataTOOLDao.ShowLine();
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "ShowDropdownBoxLine-->綫別查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }//獲取綫別

    public String ShowDropdownBoxProjectName() {
        JsonObject result = new JsonObject();
        List<MeasureDataTOOLRequireInfo> list_dcResult = MeasureDataTOOLDao.ShowProjectName();
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "ShowDropdownBoxProjectName-->專案名稱查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }//獲取專案名稱
    public String ShowDropdownBoxProjectName(String StrFactory, String StrLine) {
        JsonObject result = new JsonObject();
        List<MeasureDataTOOLRequireInfo> list_dcResult = MeasureDataTOOLDao.ShowProjectName(StrFactory,StrLine);
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "ShowDropdownBoxProjectName-->專案名稱查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }

    public String ShowDropdownBoxPartNumberV() {
        JsonObject result = new JsonObject();
        List<MeasureDataTOOLRequireInfo> list_dcResult = MeasureDataTOOLDao.ShowPartNumberV();
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "ShowDropdownBoxPartNumberV-->料號版本查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }//獲取料號版本
    public String ShowDropdownBoxPartNumberV(String StrFactory,String StrLine,String proName) {
        JsonObject result = new JsonObject();
        List<MeasureDataTOOLRequireInfo> list_dcResult = MeasureDataTOOLDao.ShowPartNumberV(StrFactory,StrLine,proName);
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "ShowDropdownBoxPartNumberV-->料號版本查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }//獲取料號版本
}

