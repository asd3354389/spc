package com.foxlink.spc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxlink.spc.dao.SelectCTPDao;
import com.foxlink.spc.pojo.uploadSPCCTP;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Service("SelectCTPService")
@Transactional
public class SelectCTPService {
	private SelectCTPDao selectCTPDao;
	private static Logger logger = Logger.getLogger(UploadAllService.class);
	
	@Autowired
	public SelectCTPService(SelectCTPDao selectCTPDao) {
		this.selectCTPDao = selectCTPDao;
	}
	
	public String CheckProName(String strPartNumber) {
		return selectCTPDao.CheckProName(strPartNumber);
	}
	
	//顯示專案名稱
	public String ShowCTPNameSpec(String strProNumber2V) {
		// TODO Auto-generated method stub
		JsonObject result = new JsonObject();
		List<uploadSPCCTP>SpecList = selectCTPDao.ShowCTPSelectSpec(strProNumber2V);
		//System.out.println("專案號"+strProNumber2V);
		Gson gson = new GsonBuilder().serializeNulls().create();
		if(SpecList.size()==0||SpecList==null) {
			result.addProperty("StatusCode", "500");
			result.addProperty("message", "查無數據");
		}else {
			result.addProperty("StatusCode", "200");
			result.addProperty("message", gson.toJson(SpecList));
		}
		System.out.println(result.toString());
		return result.toString();
	}
	//顯示查詢的數據
	public String ShowCTPSpec(String strProNumber2V) {
		// TODO Auto-generated method stub
		JsonObject result = new JsonObject();
		List<uploadSPCCTP>SpecList = selectCTPDao.ShowCTPSpec(strProNumber2V);
		//System.out.println("專案號"+strProNumber2V);
		Gson gson = new GsonBuilder().serializeNulls().create();
		if(SpecList.size()==0||SpecList==null) {
			result.addProperty("StatusCode", "500");
			result.addProperty("message", "查無數據");
		}else {
			result.addProperty("StatusCode", "200");
			result.addProperty("message", gson.toJson(SpecList));
		}
		System.out.println(result.toString());
		return result.toString();
	}

	
}
