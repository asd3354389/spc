package com.foxlink.spc.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.foxlink.spc.pojo.SPECOPTest;

@Repository("UploadOPTestDao")
public class UploadOPTestDao {
	private static Logger logger = Logger.getLogger(UploadOPTestDao.class);
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UploadOPTestDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	public String CheckProName(String strProName) {
		// TODO Auto-generated method stub
		
		String strResult = null;
		String strSQL = "select count(*) from SPC.SPEC_OPTEST where PROJECT_NAME LIKE '"+strProName+"%'";
		try {
			Integer iCount = jdbcTemplate.queryForObject(strSQL, Integer.class);
			if (iCount>0) {
				strResult="Y";
			} else {
				strResult="N";
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("UploadOPTestDao_CheckProName_Error", e);
		}
		return strResult;
	}

	public int SelectProName(String fileName2) {
		// TODO Auto-generated method stub
		int totalRecord = -1;
		String strSQL = "select count(*) from SPC.SPEC_OPTEST where PROJECT_NAME = ?";
		try {
			totalRecord = jdbcTemplate.queryForObject(strSQL, new Object[] { fileName2 },Integer.class);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Find ProName error", e);
		}
		return totalRecord;
	}

	public int DeleteProName(String fileName2) {
		// TODO Auto-generated method stub
		int totalRecord = -1;
		String strSQL = "delete from SPC.SPEC_OPTEST where PROJECT_NAME=?";
		try {
			totalRecord = jdbcTemplate.update(strSQL,new PreparedStatementSetter() {
				
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

	public int uploadOK(String fileName2, String strUserName, Row row) {
		// TODO Auto-generated method stub
		int totalRecord = -1;
		String strSQL = "INSERT INTO SPC.SPEC_OPTEST(TEST_ITEM,PROJECT_NAME,TEST_CLASS,TEST_STATUS,WORK_STATION,TEST_CONTENT,DATETIME,PERSONNEL_ID)VALUES(?,?,?,?,?,?,sysdate,?)" ;
		try {
			totalRecord = jdbcTemplate.update(strSQL,new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement arg0) throws SQLException {
					// TODO Auto-generated method stub
					arg0.setString(1, row.getCell(0).getStringCellValue());
					arg0.setString(2, fileName2);
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

	public List<SPECOPTest> ShowOPTestSpc(String str2v) {
		// TODO Auto-generated method stub
		List<SPECOPTest>SpecList = new ArrayList<>();
		String strSQL= "select * from SPC.SPEC_OPTEST where PROJECT_NAME=?";
		try {
			RowMapper<SPECOPTest>mapper = new BeanPropertyRowMapper<>(SPECOPTest.class);
			SpecList = jdbcTemplate.query(strSQL, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement arg0) throws SQLException {
					// TODO Auto-generated method stub
					arg0.setString(1, str2v);
				}
			}, mapper);
		} catch (Exception e) {
			logger.error("SpecList查找所有OPTest規格書時發生錯誤："+e);
			// TODO: handle exception
		}
		return SpecList;
	}

}
