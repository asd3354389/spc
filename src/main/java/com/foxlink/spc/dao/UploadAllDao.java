package com.foxlink.spc.dao;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.foxlink.spc.pojo.SPEC;
import com.foxlink.spc.service.UploadAllService;

@Repository("UploadAllDao")
public class UploadAllDao {
	private JdbcTemplate jdbcTemplate;
	private static Logger logger = Logger.getLogger(UploadAllDao.class);
	
	@Autowired
	public UploadAllDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int inser(String sql) {
		Integer integer=	jdbcTemplate.update(sql);
		return integer;
	}
	public int delete(String sql) {
		Integer integer=	jdbcTemplate.update(sql);
		return integer;
	}
	public Integer selectPartNumber(String sql) {

		return jdbcTemplate.queryForObject(sql, Integer.class);
		
	}
	
	public String CheckPartNumber(String strPartNumber2V) {
		String strResult = null;
		String strSQL = "SELECT COUNT(*) from spc.spec where Part_Number_V=?";
		try {
			/*Integer iCount = selectPartNumber(strSQL);*/
			Integer iCount = jdbcTemplate.queryForObject(strSQL,new Object[] { strPartNumber2V },Integer.class);
			if (iCount > 0) {
				strResult="Y";
			} else {
				strResult="N";
			}
		} catch (Exception e) {
			logger.error("UploadALLDao_CheckPartNumber_Error", e);
		}
		return strResult;
	}
	public int SelectPartNumber(String fileName2) {
		int totalRecord = -1;
		String strSelectSql = "SELECT COUNT(*) from spc.spec where Part_Number_V = ?";
		try {
			totalRecord=jdbcTemplate.queryForObject(strSelectSql, new Object[] { fileName2 },Integer.class);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Find PartNumber error", e);
		}
		return totalRecord;
	}
	
	public int DeletePartNumber(String fileName2) {
		int totalRecord = -1;
		String strDeleteSql = "delete FROM  spc.spec WHERE Part_Number_V = ? ";
		try {
			totalRecord=jdbcTemplate.update(strDeleteSql,new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement arg0) throws SQLException {
					// TODO Auto-generated method stub
					arg0.setString(1, fileName2);
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Delete PartNumber error", e);
		}
		return totalRecord;
	}
		
	public int uploadOK(int x,String fileName2,String strUserName,Row row ) {
		int totalRecord = 0;
		String strSQLInsert = "INSERT INTO spc.spec(id,Part_Number_V,Project_Name,Workshop,Inspection_Item,Inspection_Content,Nominal_Dim,Upper_Dim,Lower_Dim,Frequency,Status,Inspection_Method,Remark1,SPC_Num,Dim_Location,Date_Time,Personnel_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?) ";
		try {
			totalRecord = jdbcTemplate.update(strSQLInsert,new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement arg0) throws SQLException {
					// TODO Auto-generated method stub
					arg0.setInt(1, x);
					arg0.setString(2, fileName2);
					arg0.setString(3, row.getCell(0).getStringCellValue());
					arg0.setString(4, row.getCell(1).getStringCellValue());
					arg0.setString(5, row.getCell(2).getStringCellValue());
					arg0.setString(6, row.getCell(3).getStringCellValue());
					arg0.setString(7, row.getCell(4).toString().equals("NA")||row.getCell(4).toString().equals("") ? "0.00" : row.getCell(4).toString());
					arg0.setString(8, row.getCell(5).toString().equals("NA")||row.getCell(5).toString().equals("") ? "0.00" : row.getCell(5).toString());
					arg0.setString(9, row.getCell(6).toString().equals("NA")||row.getCell(6).toString().equals("") ? "0.00" : row.getCell(6).toString());
					arg0.setString(10, row.getCell(7).getStringCellValue());
					arg0.setString(11, row.getCell(8).getStringCellValue());
					arg0.setString(12, row.getCell(9).getStringCellValue());
					arg0.setString(13, row.getCell(10).getStringCellValue());
					arg0.setString(14, row.getCell(11).getStringCellValue());
					arg0.setString(15, row.getCell(12).getStringCellValue());
					arg0.setString(16, strUserName);
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Insert PartNumber error", e);
		}
		return totalRecord;
	}
	
	public List<SPEC> ShowSpec(String strPartNumber2V){
		String Ssql = "SELECT PROJECT_NAME, WORKSHOP,INSPECTION_ITEM,INSPECTION_CONTENT,NOMINAL_DIM,UPPER_DIM,LOWER_DIM,FREQUENCY,STATUS,INSPECTION_METHOD,REMARK1,SPC_NUM,DIM_LOCATION From SPC.SPEC where Part_Number_V=? order by WORKSHOP";
		List<SPEC> SpecList = new ArrayList<>();
		try {
			RowMapper<SPEC> mapper = new BeanPropertyRowMapper<>(SPEC.class); 
			SpecList = jdbcTemplate.query(Ssql, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement arg0) throws SQLException {
					// TODO Auto-generated method stub
					arg0.setString(1, strPartNumber2V);
				}
			}, mapper);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("SpecList查找所有規格書時發生錯誤："+e);
		}
		return SpecList;
	}
	
}
