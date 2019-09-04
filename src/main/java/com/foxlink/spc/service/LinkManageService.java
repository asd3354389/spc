package com.foxlink.spc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxlink.spc.dao.LinkManageDao;
import com.foxlink.spc.pojo.LineNumber;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


@Service("linkManageService")
public class LinkManageService {
	private static Logger logger=Logger.getLogger(LinkManageService.class);
	private LinkManageDao linkManageDao;
	@Autowired
	public void setInkManageDao(LinkManageDao linkManageDao) {
		this.linkManageDao = linkManageDao;
	}
	public String ShowAllLink() {
		// TODO Auto-generated method stub
		JsonObject result = new JsonObject();
		
		List<LineNumber> LineNumberList = linkManageDao.ShowAllLink();
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		if(LineNumberList.size() == 0 || LineNumberList == null){
			result.addProperty("StatusCode", "500");
			result.addProperty("message", "查無數據");
		}else{
			result.addProperty("StatusCode", "200");
			result.addProperty("message", gson.toJson(LineNumberList));
		}
		System.out.println(result.toString());
		return result.toString();
	}
	public String AddLink(String factory, String floor, String line_Number, String dept_Code, String production_Dept,
			String wechat_Group) {
		// TODO Auto-generated method stub
		int exist = linkManageDao.checkExist(factory,line_Number);
		JsonObject result = new JsonObject();
		if(exist < 0){
			result.addProperty("StatusCode", "500");
			result.addProperty("message", "查詢線別時發生錯誤");
		}else if(exist == 0){
			int i = linkManageDao.addLink(factory,floor,line_Number,dept_Code,production_Dept,wechat_Group);
			if(i<0){
				result.addProperty("StatusCode", "500");
				result.addProperty("message", "添加線別發生錯誤");
			}else if (i == 0){
				result.addProperty("StatusCode", "500");
				result.addProperty("message", "添加線別失敗");
			}else{
				result.addProperty("StatusCode", "200");
				result.addProperty("message", "添加線別成功");
			}
		}else{
			int i = linkManageDao.updateLink(factory,floor,line_Number,dept_Code,production_Dept,wechat_Group);
			if(i<0){
				result.addProperty("StatusCode", "500");
				result.addProperty("message", "更新線別發生錯誤");
			}else if (i == 0){
				result.addProperty("StatusCode", "500");
				result.addProperty("message", "更新線別失敗");
			}else{
				result.addProperty("StatusCode", "200");
				result.addProperty("message", "更新線別成功");
			}
		}
		return result.toString();
	}
	public String DeleteLink(String factory, String line_Number) {
		// TODO Auto-generated method stub
		int delresult = linkManageDao.DeleteLink(factory,line_Number);
		JsonObject result = new JsonObject();
		if(delresult < 0){
			result.addProperty("StatusCode", "500");
			result.addProperty("message", "刪除線別發生錯誤");
		}else if (delresult == 0){
			result.addProperty("StatusCode", "500");
			result.addProperty("message", "刪除線別失敗");
		}else{
			result.addProperty("StatusCode", "200");
			result.addProperty("message", "刪除線別成功");
		}
		return result.toString();
	}
	
	

}
