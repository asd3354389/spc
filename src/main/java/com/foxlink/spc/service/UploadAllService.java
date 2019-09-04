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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.foxlink.spc.dao.UploadAllDao;
import com.foxlink.spc.pojo.SPEC;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Service("UploadAllService")
@Transactional
public class UploadAllService {
	private UploadAllDao uploadAllDao;
	private static Logger logger = Logger.getLogger(UploadAllService.class);

	@Autowired
	public UploadAllService(UploadAllDao uploadAllDao) {
		this.uploadAllDao = uploadAllDao;
	}

	
	public String CheckPartNumber(String strPartNumber2V) {
		return uploadAllDao.CheckPartNumber(strPartNumber2V);
	}
	
	public String uploadOK(MultipartFile file,String strUserName) {
		String fileName = file.getOriginalFilename();
		String fileName2 = fileName.substring(0, fileName.indexOf("."));
		String filePath = "D:/ExcelBack/Spec/Basic/"+fileName;// 存储到服务器上的路径.
		String strResurt="";
		int totalRecord = 0;
		Workbook wb = null;
		int x = 0 ;
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
			if (uploadAllDao.SelectPartNumber(fileName2)>0) {
				if (uploadAllDao.DeletePartNumber(fileName2)>0) {
					for (int rowIndex = 1; rowIndex <= totoalRows; rowIndex++) {
						Row row = sheet.getRow(rowIndex);// 获得当前行
						int lastCellNum = row.getPhysicalNumberOfCells(); // 获得当前行的列数
						if (lastCellNum < 13) {
							return strResurt = "Excel列数应为13，此处为" + lastCellNum;
						}else {
						uploadAllDao.uploadOK(x, fileName2, strUserName, row);
						x++;
						}
					}
				
				}
			}else {
				for (int rowIndex = 1; rowIndex <= totoalRows; rowIndex++) {
					Row row = sheet.getRow(rowIndex);// 获得当前行
					int lastCellNum = row.getPhysicalNumberOfCells(); // 获得当前行的列数
					if (lastCellNum < 13) {
						return strResurt = "Excel列数应为13，此处为" + lastCellNum;
					}else {
					uploadAllDao.uploadOK(x, fileName2, strUserName, row);
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
		/*return uploadAllDao.uploadOK(file,strUserName);*/
		
	}
	
	public String ShowSpec(String strPartNumber2V) {
		JsonObject result = new JsonObject();
		List<SPEC>SpecList = uploadAllDao.ShowSpec(strPartNumber2V);
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
