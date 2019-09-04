package com.foxlink.spc.service;
import com.foxlink.spc.dao.MeasureDataEleScaleDao;
import com.foxlink.spc.pojo.MeasureDataEleScaleRequireInfo;
import com.foxlink.spc.pojo.MeasureDataEleScaleResultInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service("MeasureDataEleScaleService")
public class MeasureDataEleScaleService {
    private static Logger logger = Logger.getLogger(MeasureDataEleScaleService.class);
    private MeasureDataEleScaleDao MeasureDataEleScaleDao;

    @Autowired
    public void setInkManageDao(MeasureDataEleScaleDao MeasureDataEleScaleDao) {
        this.MeasureDataEleScaleDao = MeasureDataEleScaleDao;
    }

    public String ShowMeasureDataEleScaleResult(String StrFactory,String StrLine,String StrPartNumberV,String StrStatus,String date,String proName) {
        JsonObject result = new JsonObject();
        List<MeasureDataEleScaleResultInfo> list_dcResult = MeasureDataEleScaleDao.ShowMeasureDataEleScaleResult(StrFactory,StrLine,StrPartNumberV,StrStatus,date,proName);
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
        List<MeasureDataEleScaleRequireInfo> dci_list = MeasureDataEleScaleDao.FactoryDropdownBoxIf(StrFactory);
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
        List<MeasureDataEleScaleRequireInfo> list_dcResult = MeasureDataEleScaleDao.ShowDay(StrFactory,StrLine,StrPartNumberV,StrStatus,date);
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
        List<MeasureDataEleScaleRequireInfo> list_dcResult = MeasureDataEleScaleDao.ShowFactory();
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
        List<MeasureDataEleScaleRequireInfo> list_dcResult = MeasureDataEleScaleDao.ShowLine();
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
        List<MeasureDataEleScaleRequireInfo> list_dcResult = MeasureDataEleScaleDao.ShowProjectName();
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
        List<MeasureDataEleScaleRequireInfo> list_dcResult = MeasureDataEleScaleDao.ShowProjectName(StrFactory,StrLine);
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
        List<MeasureDataEleScaleRequireInfo> list_dcResult = MeasureDataEleScaleDao.ShowPartNumberV();
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
        List<MeasureDataEleScaleRequireInfo> list_dcResult = MeasureDataEleScaleDao.ShowPartNumberV(StrFactory,StrLine,ProName);
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
        List<MeasureDataEleScaleRequireInfo> list_dcResult = MeasureDataEleScaleDao.ShowStatus();
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
    public String ShowDropdownBoxStatus(String StrFactory,String StrLine,String ProName,String PartVerion) {
        JsonObject result = new JsonObject();
        List<MeasureDataEleScaleRequireInfo> list_dcResult = MeasureDataEleScaleDao.ShowStatus(StrFactory,StrLine,ProName,PartVerion);
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

