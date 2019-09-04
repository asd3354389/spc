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

import com.foxlink.spc.pojo.SPECTool;

@Repository("UploadTOOLDao")
public class UploadTOOLDao {
	private JdbcTemplate jdbcTemplate;
	private static Logger logger = Logger.getLogger(UploadTOOLDao.class);
	
	@Autowired
	public UploadTOOLDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public String  CheckProName(String strProName) {
		String strResult = null ;
		String strSQL = "SELECT count(*) from spc.spec_tool where PROJECT_NAME=?";
		try {
			Integer iCount = jdbcTemplate.queryForObject(strSQL, new Object[] {strProName},Integer.class);
			if (iCount>0) {
				strResult = "Y";
			} else {
				strResult = "N";
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("UploadTOOLDao_CheckProName_Error", e);
		}
		return strResult;
	}
	
	public int SelectProName(String fileName2) {
		int totalRecord = -1;
		String strSelectSql = "SELECT COUNT(*) from SPC.SPEC_TOOL where PROJECT_NAME = ?";
		try {
			totalRecord=jdbcTemplate.queryForObject(strSelectSql, new Object[] { fileName2 },Integer.class);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Find ProName error", e);
		}
		return totalRecord;
	}
	
	public int DeleteProName(String fileName2) {
		int totalRecord = -1;
		String strDeleteSql = "delete FROM  SPC.SPEC_TOOL WHERE PROJECT_NAME = ? ";
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
			logger.error("Delete ProName error", e);
		}
		return totalRecord;
	}
		
	public int uploadOK(String fileName2,String strUserName,Row row ) {
		int totalRecord = 0;
		String strSQLInsert = "INSERT INTO SPC.SPEC_TOOL(PROJECT_NAME,INSPECTION_ITEM,INSPECTION_TYPE,DEVICE_NUM,INSPECTION_CONTENT,FREQUENCY,DATETIME,PERSONNEL_ID) VALUES(?,?,?,?,?,?,sysdate,?) ";
		try {
			totalRecord = jdbcTemplate.update(strSQLInsert,new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement arg0) throws SQLException {
					// TODO Auto-generated method stub
					arg0.setString(1, fileName2);
					arg0.setString(2, row.getCell(0).getStringCellValue());
					arg0.setString(3, row.getCell(1).getStringCellValue());
					arg0.setString(4, row.getCell(2).getStringCellValue());
					arg0.setString(5, row.getCell(3).getStringCellValue());
					arg0.setString(6, row.getCell(4).getStringCellValue());
					arg0.setString(7, strUserName);
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Insert ProName error", e);
		}
		return totalRecord;
	}

	public List<SPECTool> ShowToolSpec(String strProNumber2V) {
		// TODO Auto-generated method stub
		String Ssql = "SELECT INSPECTION_ITEM,INSPECTION_TYPE,DEVICE_NUM,INSPECTION_CONTENT,FREQUENCY From SPC.SPEC_TOOL where PROJECT_NAME=? order by INSPECTION_ITEM";
		List<SPECTool> SpecList = new ArrayList<>();
		try {
			RowMapper<SPECTool> mapper = new BeanPropertyRowMapper<>(SPECTool.class); 
			SpecList = jdbcTemplate.query(Ssql, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement arg0) throws SQLException {
					// TODO Auto-generated method stub
					arg0.setString(1, strProNumber2V);
				}
			}, mapper);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("SpecList查找所有Tool規格書時發生錯誤："+e);
		}
		return SpecList;
	}
}
