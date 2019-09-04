package com.foxlink.spc.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxlink.spc.dao.FoxlinkMeasureDao;
import com.foxlink.spc.pojo.Measure;
import com.foxlink.spc.pojo.MeasureDataResultInfo;
import com.foxlink.spc.pojo.SPECMeasure;
import com.foxlink.spc.pojo.SaveMeasure;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Service("foxlinkMeasureService")
@Transactional
public class FoxlinkMeasureService {
	private static Logger logger=Logger.getLogger(FoxlinkMeasureService.class);
	Gson gson = new GsonBuilder().serializeNulls().create();
	private FoxlinkMeasureDao foxlinkMeasureDao;
	@Autowired
	public void setFoxlinkMeasureDao(FoxlinkMeasureDao foxlinkMeasureDao) {
		this.foxlinkMeasureDao = foxlinkMeasureDao;
	}
	
	public String ShowAllFactoryAndProjectName() {
		// TODO Auto-generated method stub
		JsonObject result = new JsonObject();
		
		List Factorylist = foxlinkMeasureDao.ShowAllFactory();
		List ProjectNameList = foxlinkMeasureDao.ShowAllProjectName();
		
		String Factoryjson = gson.toJson(Factorylist);
		String ProjectNameJson = gson.toJson(ProjectNameList);
		
		result.addProperty("Factory", Factoryjson);
		result.addProperty("ProjectName", ProjectNameJson);
		
		return result.toString();
	}

	public String ShowLink(String factory) {
		// TODO Auto-generated method stub
		List LinkList = foxlinkMeasureDao.ShowLink(factory);
		String LinkJson = gson.toJson(LinkList);
		System.out.println(LinkJson);
		return LinkJson;
	}

	public String ShowPartVerion(String projectName) {
		// TODO Auto-generated method stub
		List PartVersionList = foxlinkMeasureDao.ShowPartVerion(projectName);
		String PartVersionJson = gson.toJson(PartVersionList);
		System.out.println(PartVersionList);
		
		return PartVersionJson;
	}

	public String ShowStutas(String partVersion) {
		// TODO Auto-generated method stub
		List StutasList = foxlinkMeasureDao.ShowStutas(partVersion);
		String StutasJson = gson.toJson(StutasList);
		System.out.println(StutasJson);
		return StutasJson;
	}

	public String ShowFrequency(String pro_Name, String part_version, String stutas) {
		// TODO Auto-generated method stub
		List FrequencyList = foxlinkMeasureDao.ShowFrequency(pro_Name,part_version,stutas);
		String FrequencyJson = gson.toJson(FrequencyList);
		System.out.println(FrequencyJson);
		return FrequencyJson;
	}

	public String ShowMeasureTable(SaveMeasure saveMeasure) {
		// TODO Auto-generated method stub
		List listout = foxlinkMeasureDao.ShowMeasureTable(saveMeasure);
		List<MeasureDataResultInfo> measuredList = foxlinkMeasureDao.findMeasured(saveMeasure);
		JsonObject result = new JsonObject();
		
		if(listout.size()==0){
			result.addProperty("SPECStatus", "500");
			result.addProperty("Message", "查無測量資料");
		}else{
			result.addProperty("SPECStatus", "200");
			result.addProperty("Message", gson.toJson(listout));
		}
		if(measuredList.size()==0){
			result.addProperty("HISPECStatus", "500");
			result.addProperty("HIMessage", "無歷史測量資料");
		}else{
			result.addProperty("HISPECStatus", "200");
			result.addProperty("HIMessage", gson.toJson(measuredList));
		}
		System.out.println(result.toString());
		return result.toString();
	}

	public String sumbitMeasureTable(Measure[] faultList, SaveMeasure saveMeasure) {
		
		List<SPECMeasure> SPECMeasure = foxlinkMeasureDao.ShowMeasureTable(saveMeasure);
		int deleteResult = foxlinkMeasureDao.deleteMeasureTable(faultList, saveMeasure);
		JsonObject resultJson = new JsonObject();
		if(deleteResult==0){
			int result = foxlinkMeasureDao.sumbitMeasureTable(faultList,saveMeasure,SPECMeasure);
			if(result==0){
				resultJson.addProperty("SPECStatus", "200");
				resultJson.addProperty("Message", "量測數據提交成功");
			}else{
				resultJson.addProperty("SPECStatus", "500");
				resultJson.addProperty("Message", "量測數據提交失敗");
			}
		}else{
			resultJson.addProperty("SPECStatus", "500");
			resultJson.addProperty("Message", "量測數據提交失敗,修改原量測數據失敗");
		}
		
		return resultJson.toString();
	}

	public String getMeasureData(String remoteAddr) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> data = foxlinkMeasureDao.getMeasureData(remoteAddr);
		String MeasureData = "";
		JsonObject resultJson = new JsonObject();
		if(data.size()>0){
			MeasureData = (String) data.get(0).get("Measure_data");
		}
		resultJson.addProperty("Message", MeasureData);
		System.out.println(resultJson.toString());
		return resultJson.toString();
	}
	
	

}
