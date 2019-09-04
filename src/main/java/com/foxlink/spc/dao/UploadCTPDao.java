package com.foxlink.spc.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import com.foxlink.spc.pojo.uploadSPCCTP;
@Repository("UploadCTPDao")
public class UploadCTPDao {
	private JdbcTemplate jdbcTemplate;
	private static Logger logger = Logger.getLogger(UploadCTPDao.class);
	private String projectName;
	@Autowired
	public UploadCTPDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//插入數據
	public int inser(String sql) {
		Integer integer=	jdbcTemplate.update(sql);
		return integer;
	}
	//刪除數據
	public int delete(String sql) {
		Integer integer=	jdbcTemplate.update(sql);
		return integer;
	}
	//查詢數據
	public Integer selectPartNumber(String sql) {

		return jdbcTemplate.queryForObject(sql, Integer.class);
		
	}
	
	//查詢專案名稱
	public String  CheckProName(String strProName) {
		String strResult = null ;
//		String strSQL = "SELECT count(*) from SPC.SPEC_CTP where PROJECT_NAME=?";
		String strSQL = "SELECT count(*) from SPC.SPEC_CTP where PROJECT_NAME LIKE'"+strProName +"%'";
		try {
			Integer iCount = jdbcTemplate.queryForObject(strSQL,Integer.class);
			if (iCount>0) {
				strResult = "Y";
			} else {
				strResult = "N";
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("UploadCTPDao_CheckProName_Error", e);
		}
		return strResult;
	}
	
	//選擇專案
	public int SelectProName(String fileName3) {
		int totalRecord = -1;
//		String strSelectSql = "SELECT COUNT(*) from SPC.SPEC_CTP where PROJECT_NAME = ?";
		String strSelectSql = "SELECT COUNT(*) from SPC.SPEC_CTP where PROJECT_NAME LIKE '"+fileName3 +"%'";
		//System.out.println(strSelectSql);
		try {
			totalRecord=jdbcTemplate.queryForObject(strSelectSql,Integer.class);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Find ProName error", e);
		}
		System.out.println(totalRecord);
		return totalRecord;
	}
	
	//如果有相同的專案名稱先刪除原來的,再添加新的
	public int DeleteProName(String fileName3) {
		int totalRecord = -1;
		String strDeleteSql = "delete FROM  SPC.SPEC_CTP WHERE PROJECT_NAME LIKE  '"+fileName3 +"%'";
		System.out.println(strDeleteSql);
		try {
//			totalRecord=jdbcTemplate.update(strDeleteSql,new PreparedStatementSetter() {
			totalRecord=jdbcTemplate.update(strDeleteSql) ;
//				@Override
//				public void setValues(PreparedStatement arg0) throws SQLException {
//					// TODO Auto-generated method stub
//					arg0.setString(1, fileName3);
//				}
		
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Delete ProName error", e);
		}
		System.out.println(totalRecord);
		return totalRecord;
	}
	/*
	 * PROJECT_NAME;
	//工站號碼
	private String WORKSHOP;
	//工站名稱
	private String WORKSHOP_NAME;
	//設備名稱
	private String MACHINE_NAME;
	//檢驗項目
	private String INSPECTION_ITEM;
	//上限
	private int UPPER_DIM;
	//下限
	private int LOWER_DIM;
	//檢驗型態
	private String INSPECTION_TYPE;
	//機台型號
	private String  MACHINE_TYPE;
	//備註
	private String REMARK;
	 */
	public int uploadOK(int x,String fileName2,String strUserName,Row row ) {
		int totalRecord = 0;
		String strSQLInsert ="INSERT INTO SPC.SPEC_CTP(ID,PROJECT_NAME,WORKSHOP,WORKSHOP_NAME,MACHINE_NAME,INSPECTION_ITEM,UPPER_DIM,LOWER_DIM,INSPECTION_TYPE,MACHINE_TYPE,REMARK,PERSONNEL_ID,DATE_TIME) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		projectName =  row.getCell(0).getStringCellValue();
		try {
			totalRecord = jdbcTemplate.update(strSQLInsert,new PreparedStatementSetter() {
				//.toString().equals("NA") ? "0.00" : row.getCell(4).toString()
				@Override
				public void setValues(PreparedStatement arg0) throws SQLException {
					// TODO Auto-generated method stub
					arg0.setInt(1, x);
					arg0.setString(2, row.getCell(0).getStringCellValue());
					arg0.setString(3, row.getCell(1).getStringCellValue());
					arg0.setString(4, row.getCell(2).getStringCellValue());
					arg0.setString(5, row.getCell(3).getStringCellValue());
					arg0.setString(6, row.getCell(4).getStringCellValue());
					arg0.setString(7, row.getCell(5).toString().equals("NA")|| row.getCell(5).toString().equals("") ? "0.00" : row.getCell(5).toString());
					arg0.setString(8, row.getCell(6).toString().equals("NA")||row.getCell(6).toString().equals("") ? "0.00" : row.getCell(6).toString());
					arg0.setString(9, row.getCell(7).getStringCellValue());
					arg0.setString(10, row.getCell(8).getStringCellValue());
					arg0.setString(11, row.getCell(9).getStringCellValue());
					arg0.setString(12, strUserName);
					
					
					
//					arg0.setString(1, fileName2);
//					arg0.setString(2, row.getCell(0).getStringCellValue());
//					arg0.setString(3, row.getCell(1).getStringCellValue());
//					arg0.setString(4, row.getCell(2).getStringCellValue());
//					arg0.setString(5, row.getCell(3).getStringCellValue());
//					arg0.setString(6, row.getCell(4).getStringCellValue());
//					arg0.setString(7, row.getCell(5).getStringCellValue());
//					arg0.setString(8, row.getCell(6).getStringCellValue());
//					arg0.setString(9, row.getCell(7).getStringCellValue());
//					arg0.setString(10, row.getCell(8).getStringCellValue());
//					arg0.setString(11, strUserName);
				}
				
			});
			
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Insert ProName error", e);
		}
		
		//System.out.println("插入語句"+strSQLInsert);
		//System.out.println("插入不成功"+totalRecord);
		return totalRecord;
	}
	

	public List<uploadSPCCTP> ShowCTPSpec(String strProNumber2V) {
		// TODO Auto-generated method stub
		String strProNumber3V =  strProNumber2V.substring(0, strProNumber2V.indexOf("_"));
		String Ssql = "SELECT PROJECT_NAME,WORKSHOP,WORKSHOP_NAME,MACHINE_NAME,INSPECTION_ITEM,UPPER_DIM,LOWER_DIM,INSPECTION_TYPE,MACHINE_TYPE,REMARK From SPC.SPEC_CTP where PROJECT_NAME LIKE '"+strProNumber3V +"%' order by WORKSHOP";// '"+fileName3 +"%'
		List<uploadSPCCTP> SpecList = new ArrayList<>();
		System.out.println("專案號"+strProNumber3V);
		try {
			RowMapper<uploadSPCCTP> mapper = new BeanPropertyRowMapper<>(uploadSPCCTP.class); 
//			SpecList = jdbcTemplate.query(Ssql, new PreparedStatementSetter() {
//				
//				@Override
//				public void setValues(PreparedStatement arg0) throws SQLException {
//					// TODO Auto-generated method stub
//					arg0.setString(1, strProNumber3V);
//				}
//				
//			}, mapper);
			SpecList = jdbcTemplate.query(Ssql, mapper);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("SpecList查找所有CTP規格書時發生錯誤："+e);
		}
		return SpecList;
	}
	
	
	
}
