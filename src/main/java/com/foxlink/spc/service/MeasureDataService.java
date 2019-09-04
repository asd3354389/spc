package com.foxlink.spc.service;

import com.foxlink.spc.dao.MeasureDataDao;
import com.foxlink.spc.pojo.MeasureDataRequireInfo;
import com.foxlink.spc.pojo.MeasureDataResultInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service("MeasureDataService")
public class MeasureDataService {
    private static Logger logger = Logger.getLogger(MeasureDataService.class);
    private MeasureDataDao MeasureDataDao;

    @Autowired
    public void setInkManageDao(MeasureDataDao MeasureDataDao) {
        this.MeasureDataDao = MeasureDataDao;
    }

    public String ShowMeasureDataRequire() {
        JsonObject result = new JsonObject();
        List<MeasureDataRequireInfo> dci_list = MeasureDataDao.ShowMeasureDataRequire();
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

    public String ShowMeasureDataResult() {
        JsonObject result = new JsonObject();
        List<MeasureDataResultInfo> list_dcResult = MeasureDataDao.ShowMeasureDataResult();
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
    public String ShowMeasureDataResult(String StrFactory,String StrLine,String StrPartNumberV,String StrStatus,String date) {
        JsonObject result = new JsonObject();
        List<MeasureDataResultInfo> list_dcResult = MeasureDataDao.ShowMeasureDataResult(StrFactory,StrLine,StrPartNumberV,StrStatus,date);
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
        List<MeasureDataRequireInfo> dci_list = MeasureDataDao.FactoryDropdownBoxIf(StrFactory);
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

    public String ShowDropdownBoxDay(String StrFactory,String StrLine,String StrPartNumberV,String StrStatus,String date) {
        JsonObject result = new JsonObject();
        List<MeasureDataRequireInfo> list_dcResult = MeasureDataDao.ShowDay(StrFactory,StrLine,StrPartNumberV,StrStatus,date);
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
        List<MeasureDataRequireInfo> list_dcResult = MeasureDataDao.ShowFactory();
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
        List<MeasureDataRequireInfo> list_dcResult = MeasureDataDao.ShowLine();
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
        List<MeasureDataRequireInfo> list_dcResult = MeasureDataDao.ShowProjectName();
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
        List<MeasureDataRequireInfo> list_dcResult = MeasureDataDao.ShowProjectName(StrFactory,StrLine);
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
        List<MeasureDataRequireInfo> list_dcResult = MeasureDataDao.ShowPartNumberV();
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
    public String ShowDropdownBoxPartNumberV(String StrFactory,String StrLine,String ProName) {
        JsonObject result = new JsonObject();
        List<MeasureDataRequireInfo> list_dcResult = MeasureDataDao.ShowPartNumberV(StrFactory,StrLine,ProName);
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

    public String ShowDropdownBoxStatus() {
        JsonObject result = new JsonObject();
        List<MeasureDataRequireInfo> list_dcResult = MeasureDataDao.ShowStatus();
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "ShowDropdownBoxStatus-->量測階段查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }//獲取測量階段
    public String ShowDropdownBoxStatus(String StrFactory,String StrLine,String proName,String partVerion) {
        JsonObject result = new JsonObject();
        List<MeasureDataRequireInfo> list_dcResult = MeasureDataDao.ShowStatus(StrFactory,StrLine,proName,partVerion);
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "ShowDropdownBoxStatus-->量測階段查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }//獲取測量階段
}

