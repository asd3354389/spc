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
import org.springframework.web.multipart.MultipartFile;


//導入包下的類
import com.foxlink.spc.dao.UploadCTPDao;

import com.foxlink.spc.pojo.uploadSPCCTP;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Service("UploadCTPService")
@Transactional
public class UploadCTPService {
	private UploadCTPDao uploadCTPDao;
	private static Logger logger = Logger.getLogger(UploadAllService.class);
	
	@Autowired
	public UploadCTPService(UploadCTPDao uploadCTPDao) {
		this.uploadCTPDao = uploadCTPDao;
	}
	
	public String CheckProName(String strPartNumber) {
		return uploadCTPDao.CheckProName(strPartNumber);
	}
	
	//上傳成功
	public String uploadOK(MultipartFile file,String strUserName) {
		String fileName = file.getOriginalFilename();
		String fileName2 = fileName.substring(0, fileName.indexOf("."));
		String fileName3 = fileName2.substring(0, fileName.indexOf("_"));
		String filePath = "D:/ExcelBack/Spec/CTP/"+fileName;// 存储到服务器上的路径.
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
			System.out.println(fileName3);         
			if (uploadCTPDao.SelectProName(fileName3)>0) {
				if (uploadCTPDao.DeleteProName(fileName3)>0) {
					
					for (int rowIndex = 1; rowIndex <= totoalRows; rowIndex++) {
						Row row = sheet.getRow(rowIndex);// 获得当前行
						int lastCellNum = row.getPhysicalNumberOfCells(); // 获得当前行的列数
						if (lastCellNum < 10) {
							return strResurt = "Excel列数应为10，此处为" + lastCellNum;
						}else {
						
						uploadCTPDao.uploadOK(x,fileName2,strUserName, row);
						x++;
						}
						
					}
				
				}
			}else {
				for (int rowIndex = 1; rowIndex <= totoalRows; rowIndex++) {
					Row row = sheet.getRow(rowIndex);// 获得当前行
					int lastCellNum = row.getPhysicalNumberOfCells(); // 获得当前行的列数
					if (lastCellNum < 10) {
						return strResurt = "Excel列数应为10，此处为" + lastCellNum;
					}else {
					
					uploadCTPDao.uploadOK(x,fileName2,strUserName, row);
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
		//System.out.println("插入的數據"+x);
		return "规格书已上传，插入了" + x + "行数据 ," + strResurt;
	}
	
	public String ShowCTPSpec(String strProNumber2V) {
		// TODO Auto-generated method stub
		JsonObject result = new JsonObject();
		List<uploadSPCCTP>SpecList = uploadCTPDao.ShowCTPSpec(strProNumber2V);
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
