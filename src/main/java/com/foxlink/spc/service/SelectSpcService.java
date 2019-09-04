package com.foxlink.spc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.foxlink.spc.dao.SelectSpcDao;
import com.foxlink.spc.pojo.SPEC;
import com.foxlink.spc.pojo.uploadSPCCTP;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Service("SelectSpcService")
@Transactional
public class SelectSpcService {
	private SelectSpcDao selectSpcDao;
	private static Logger logger = Logger.getLogger(UploadAllService.class);
	
	@Autowired
	public SelectSpcService(SelectSpcDao selectSpcDao) {
		this.selectSpcDao = selectSpcDao;
	}
	
	public String CheckProName(String strPartNumber) {
		return selectSpcDao.CheckProName(strPartNumber);
	}
	
	//顯示專案名稱
		public String ShowSpcNameSpec(String strProNumber2V) {
			// TODO Auto-generated method stub
			JsonObject result = new JsonObject();
			List<SPEC>SpecList = selectSpcDao.ShowSpcSelectSpec(strProNumber2V);
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
		public String ShowSpcSpec(String strProNumber2V) {
			// TODO Auto-generated method stub
			JsonObject result = new JsonObject();
			List<SPEC>SpecList = selectSpcDao.ShowSpcSpec(strProNumber2V);
			System.out.println("spc專案號"+strProNumber2V);
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
