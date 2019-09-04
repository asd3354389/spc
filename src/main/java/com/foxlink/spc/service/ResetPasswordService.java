package com.foxlink.spc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxlink.spc.dao.ResetPasswordDao;
import com.foxlink.spc.dao.SelectCTPDao;
import com.foxlink.spc.pojo.ResetPassword;
import com.foxlink.spc.pojo.uploadSPCCTP;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


@Service("ResetPasswordService")
@Transactional
public class ResetPasswordService {

	private ResetPasswordDao resetPasswordDao;
	private static Logger logger = Logger.getLogger(ResetPasswordService.class);
	
	
	@Autowired
	public ResetPasswordService(ResetPasswordDao resetPasswordDao) {
		this.resetPasswordDao = resetPasswordDao;
	}
	//查詢工號
	public String CheckAccount(String strPartNumber) {
		
		JsonObject result = new JsonObject();
		List<ResetPassword>SpecList = resetPasswordDao.CheckAccount(strPartNumber);
		//System.out.println("專案號"+strProNumber2V);
		Gson gson = new GsonBuilder().serializeNulls().create();
		if(SpecList.size()==0||SpecList==null) {
			result.addProperty("StatusCode", "500");
			result.addProperty("message", "賬戶輸入錯誤,請重新輸入!!");
		}else {
			result.addProperty("StatusCode", "200");
			result.addProperty("message", gson.toJson(SpecList));
		}
		System.out.println("返回值==:"+result.toString());
		return result.toString();
		
	}
	

	//設置新密碼 CheckNewPassword
   public String SetNewPassword(String newPassword,String newPassword1) {
	
	   JsonObject result = new JsonObject();
	   int i = resetPasswordDao.SetNewPassword(newPassword,newPassword1);
		if(i<0){
			result.addProperty("StatusCode", "500");
			result.addProperty("message", "更新密碼發生錯誤");
		}else if (i == 0){
			result.addProperty("StatusCode", "500");
			result.addProperty("message", "更新密碼失敗");
		}else{
			result.addProperty("StatusCode", "200");
			result.addProperty("message", "更新密碼成功");
		}
		System.out.println("更新密碼返回值==:"+result.toString());
	 return result.toString();
		//return resetPasswordDao.SetNewPassword(newPassword);
	}
}
