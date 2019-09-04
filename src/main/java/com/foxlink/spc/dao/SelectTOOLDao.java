package com.foxlink.spc.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.foxlink.spc.pojo.SPECTool;
import com.foxlink.spc.service.UploadTOOLService;

import oracle.net.aso.e;

@Repository("SelectTOOLDao")
public class SelectTOOLDao {
	private JdbcTemplate jdbcTemplate;
	private static Logger logger = Logger.getLogger(SelectTOOLDao.class);
	
	@Autowired
	public SelectTOOLDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<SPECTool> ShowToolProName(String ProName) {
		// TODO Auto-generated method stub
		String Ssql = "SELECT INSPECTION_ITEM,INSPECTION_TYPE,DEVICE_NUM,INSPECTION_CONTENT,FREQUENCY,DATETIME,PERSONNEL_ID FROM SPC.SPEC_TOOL WHERE PROJECT_NAME=?";
		List<SPECTool> ProList = new ArrayList<>();
		try {
			RowMapper<SPECTool> mapper = new BeanPropertyRowMapper<>(SPECTool.class);
			ProList = jdbcTemplate.query(Ssql, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement arg0) throws SQLException {
					// TODO Auto-generated method stub
					arg0.setString(1, ProName);
				}
			}, mapper); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("SpecList查找所有Tool規格書時發生錯誤："+e);
		}
		return ProList;
	}

	public List<SPECTool> ShowToolProName() {
		// TODO Auto-generated method stub
		String Ssql = "select distinct PROJECT_NAME from SPC.SPEC_TOOL";
		List<SPECTool> ProList = new ArrayList<>();
		try {
			RowMapper<SPECTool> mapper = new BeanPropertyRowMapper<>(SPECTool.class);
			ProList = jdbcTemplate.query(Ssql, mapper);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("SpecList查找所有Tool規格書專案名稱時發生錯誤："+e);
		}
		return ProList;
	}
}
