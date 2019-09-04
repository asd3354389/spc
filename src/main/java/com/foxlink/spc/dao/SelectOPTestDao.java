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

import com.foxlink.spc.pojo.SPECOPTest;

@Repository("SelectOPTestDao")
public class SelectOPTestDao {
	private static Logger logger =Logger.getLogger(SelectOPTestDao.class);
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SelectOPTestDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<SPECOPTest> ShowOPTestProName(String ProName) {
		// TODO Auto-generated method stub
		String strSQL = "SELECT TEST_ITEM,TEST_CLASS,TEST_STATUS,WORK_STATION,TEST_CONTENT,DATETIME,PERSONNEL_ID FROM SPC.SPEC_OPTEST WHERE PROJECT_NAME=?";
		List<SPECOPTest>ProList = new ArrayList<>();
		try {
			RowMapper<SPECOPTest>mapper = new BeanPropertyRowMapper<>(SPECOPTest.class);
			ProList = jdbcTemplate.query(strSQL, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement arg0) throws SQLException {
					// TODO Auto-generated method stub
					arg0.setString(1,ProName);
				}
			}, mapper);
			System.out.println(strSQL);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("SpecList查找所有OPTest規格書時發生錯誤："+e);
		}
		return ProList;
	}

	public List<SPECOPTest> ShowOPTestProName() {
		// TODO Auto-generated method stub
		String strSQL = "SELECT  distinct PROJECT_NAME FROM SPC.SPEC_OPTEST";
		List<SPECOPTest>ProList = new ArrayList<>();
		try {
			RowMapper<SPECOPTest>mapper = new BeanPropertyRowMapper<>(SPECOPTest.class);
			ProList = jdbcTemplate.query(strSQL, mapper);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("SpecList查找所有OPTest規格書專案名稱時發生錯誤："+e);
		}
		return ProList;
	}


}
