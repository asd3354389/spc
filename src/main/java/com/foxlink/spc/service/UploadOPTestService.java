package com.foxlink.spc.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.foxlink.spc.dao.UploadOPTestDao;
import com.foxlink.spc.pojo.SPECOPTest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Service("UploadOPTestService")
@Transactional
public class UploadOPTestService {
	private static Logger logger = Logger.getLogger(UploadOPTestService.class);
	private UploadOPTestDao uploadOPTestDao;
	
	public UploadOPTestService(UploadOPTestDao uploadOPTestDao) {
		this.uploadOPTestDao = uploadOPTestDao;
	}
	
	public String CheckProName(String strProName) {
		// TODO Auto-generated method stub
		return uploadOPTestDao.CheckProName(strProName);
	}

	public String uploadOK(MultipartFile file, String strUserName) {
		// TODO Auto-generated method stub
		String fileName = file.getOriginalFilename();
		String fileName2 = fileName.substring(0, fileName.indexOf("."));
		String filePath = "D:/ExcelBack/Spec/OPTest/"+fileName;// 存储到服务器上的路径.
		String strResurt="";
		int totalRecord = 0;
		Workbook wb = null;
		int x = 0;
		try {
			InputStream is = file.getInputStream();
			
			if (fileName.endsWith("xls")) {
				// 2003
				wb = new HSSFWorkbook(is);
			} else if (fileName.endsWith("xlsx")) {
				// 2007
				wb = new XSSFWorkbook(is);
			}

			Sheet sheet = wb.getSheetAt(0); // 读取sheet(页) 这里选择第0页
			Integer totoalRows = sheet.getLastRowNum(); // 获取总行数量
			if (uploadOPTestDao.SelectProName(fileName2)>0) {
				if (uploadOPTestDao.DeleteProName(fileName2)>0) {
					for (int rowIndex = 1; rowIndex <= totoalRows; rowIndex++) {
						Row row = sheet.getRow(rowIndex);// 获得当前行
						int lastCellNum = row.getPhysicalNumberOfCells(); // 获得当前行的列数
						if (lastCellNum < 5) {
							return strResurt = "Excel列数应为5，此处为" + lastCellNum;
						}else {
						uploadOPTestDao.uploadOK(fileName2,strUserName, row);
						x++;
						}
					}
				
				}
			}else {
				for (int rowIndex = 1; rowIndex <= totoalRows; rowIndex++) {
					Row row = sheet.getRow(rowIndex);// 获得当前行
					int lastCellNum = row.getPhysicalNumberOfCells(); // 获得当前行的列数
					if (lastCellNum < 5) {
						return strResurt = "Excel列数应为5，此处为" + lastCellNum;
					}else {
					uploadOPTestDao.uploadOK(fileName2,strUserName, row);
					x++;
					}
				}
				
			}
			File targetFile = new File(filePath);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 将前台传过来的file文件写到targetFile中.		
			file.transferTo(targetFile);
		} catch (Exception e) {
			// TODO: handle exception
			totalRecord = 0;
			strResurt += "NG:" + e.toString();
		}
		
		return "规格书已上传，插入了" + x + "行数据 ," + strResurt;
	}

	public String showOPTestSpc(String str2v) {
		// TODO Auto-generated method stub
		JsonObject result = new JsonObject();
		List<SPECOPTest>SpecList = uploadOPTestDao.ShowOPTestSpc(str2v);
		Gson gson = new GsonBuilder().serializeNulls().create();
		if(SpecList.size()==0||SpecList==null) {
			result.addProperty("StatusCode", "500");
			result.addProperty("message", "查無數據");
		}else {
			result.addProperty("StatusCode", "200");
			result.addProperty("message", gson.toJson(SpecList));
		}
		return result.toString();
	}

}
