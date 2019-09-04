package com.foxlink.spc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxlink.spc.dao.SelectOPTestDao;
import com.foxlink.spc.pojo.SPECOPTest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Service("SelectOPTestService")
@Transactional
public class SelectOPTestService {
	private static Logger logger = Logger.getLogger(SelectOPTestService.class);
	private SelectOPTestDao selectOPTestDao;
	
	@Autowired
	public SelectOPTestService(SelectOPTestDao selectOPTestDao) {
		this.selectOPTestDao=selectOPTestDao;
	}
	
	public String ShowOPTestProName(String ProName) {
		// TODO Auto-generated method stub
		JsonObject result = new JsonObject();
		List<SPECOPTest> ProList = selectOPTestDao.ShowOPTestProName(ProName);
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
		List<SPECOPTest>ProList = selectOPTestDao.ShowOPTestProName();
		Gson gson = new GsonBuilder().serializeNulls().create();
		if(ProList.size()==0||ProList==null) {
			result.addProperty("StatusCode", "500");
			result.addProperty("message", "查無數據");
		}else {
			result.addProperty("StatusCode", "200");
			result.addProperty("message", gson.toJson(ProList));
		}
		return result.toString();
	}

}
