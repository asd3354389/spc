package com.foxlink.spc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxlink.spc.dao.RetrievePasswordDao;
import com.foxlink.spc.pojo.ResetPassword;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
@Service("RetrievePasswordService")
@Transactional
public class RetrievePasswordService {
	private RetrievePasswordDao retrievePasswordDao;
	
private static Logger logger = Logger.getLogger(ResetPasswordService.class);
	
	
	@Autowired
	public RetrievePasswordService(RetrievePasswordDao retrievePasswordDao) {
		this.retrievePasswordDao = retrievePasswordDao;
	}
	    //查詢工號
		public String CheckAccount(String strPartNumber) {
			System.out.println("輸入的工號=========>>:"+strPartNumber);
			JsonObject result = new JsonObject();
			List<ResetPassword>SpecList = retrievePasswordDao.CheckAccount(strPartNumber);
			
			Gson gson = new GsonBuilder().serializeNulls().create();
			if(SpecList.size()==0||SpecList==null) {
				result.addProperty("StatusCode", "500");
				result.addProperty("message", "工號輸入錯誤或者不存在,請重新輸入!!");
			}else {
				result.addProperty("StatusCode", "200");
				result.addProperty("message", gson.toJson(SpecList));
			}
			System.out.println("返回值==:"+result.toString());
			return result.toString();
			
		}
		
		public String SendMailPassword() throws Exception {
			JsonObject result = new JsonObject();
			Gson gson = new GsonBuilder().serializeNulls().create();
			boolean isSuccessful = retrievePasswordDao.SendMailPassword();
			if (isSuccessful) {
				result.addProperty("StatusCode", "200");
				result.addProperty("message", "密碼已重置,前往郵箱查看密碼!!");
			} else {
				result.addProperty("StatusCode", "500");
				result.addProperty("message", "發送郵件失敗!!");
			}
			System.out.println("發送郵件");
			return result.toString();
		}
		
		
}
