package com.foxlink.spc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxlink.spc.dao.SelectTOOLDao;
import com.foxlink.spc.pojo.SPECTool;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Service("SelectTOOLService")
@Transactional
public class SelectTOOLService {
	private static Logger logger = Logger.getLogger(SelectTOOLService.class);
	private SelectTOOLDao selectTOOLDao;
	
	@Autowired
	public SelectTOOLService(SelectTOOLDao selectTOOLDao) {
		this.selectTOOLDao = selectTOOLDao;
	}
	
	public String ShowToolProName(String ProName) {
		// TODO Auto-generated method stub
		JsonObject result = new JsonObject();
		List<SPECTool> ProList = selectTOOLDao.ShowToolProName(ProName);
		Gson gson = new GsonBuilder().serializeNulls().create();
		if(ProList.size()==0||ProList==null) {
			result.addProperty("StatusCode", "500");
			result.addProperty("message", "查無數據");
		}else {
			result.addProperty("StatusCode", "200");
			result.addProperty("message", gson.toJson(ProList));
		}
		/*System.out.println(result.toString());*/
		return result.toString();
	}

	public String ShowProNameList() {
		// TODO Auto-generated method stub
		JsonObject result = new JsonObject();
		List<SPECTool> ProList = selectTOOLDao.ShowToolProName();
		Gson gson = new GsonBuilder().serializeNulls().create();
		if(ProList.size()==0||ProList==null) {
			result.addProperty("StatusCode", "500");
			result.addProperty("message", "查無數據");
		}else {
			result.addProperty("StatusCode", "200");
			result.addProperty("message", gson.toJson(ProList));
		}
		/*System.out.println(result.toString());*/
		return result.toString();
	}

}
