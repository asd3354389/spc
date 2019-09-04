package com.foxlink.spc.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.foxlink.spc.pojo.uploadSPCCTP;

@Repository("SelectCTPDao")
public class SelectCTPDao {
	private JdbcTemplate jdbcTemplate;
	private static Logger logger = Logger.getLogger(UploadCTPDao.class);
	private String projectName;
	@Autowired
	public SelectCTPDao(JdbcTemplate jdbcTemplate) {
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
//			String strSQL = "SELECT count(*) from SPC.SPEC_CTP where PROJECT_NAME=?";
			String strSQL = "SELECT DISTINCT PROJECT_NAME from SPC.SPEC_CTP";
			try {
				Integer iCount = jdbcTemplate.queryForObject(strSQL,Integer.class);
				if (iCount>0) {
					strResult = "Y";
				} else {
					strResult = "N";
				}
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("SelectCTPDao_CheckProName_Error", e);
			}
			return strResult;
		}
		
		//CTP規格書搜尋專案名稱
		public List<uploadSPCCTP> ShowCTPSelectSpec(String strProNumber2V) {
			String strSQL = "SELECT DISTINCT PROJECT_NAME from SPC.SPEC_CTP";
			List<uploadSPCCTP> SpecList = new ArrayList<>();
			try {
				RowMapper<uploadSPCCTP> mapper = new BeanPropertyRowMapper<>(uploadSPCCTP.class); 
				SpecList = jdbcTemplate.query(strSQL, mapper);
			} catch (Exception e) { 
				// TODO: handle exception
				e.printStackTrace();
				logger.error("SpecList查找所有CTP規格書時發生錯誤："+e);
			}

			return SpecList;
		}
		//CTP規格書搜尋
		public List<uploadSPCCTP> ShowCTPSpec(String strProNumber2V) {
			// TODO Auto-generated method stub
			//String strProNumber3V =  strProNumber2V.substring(0, strProNumber2V.indexOf("_"));
			String Ssql = "SELECT PROJECT_NAME,WORKSHOP,WORKSHOP_NAME,MACHINE_NAME,INSPECTION_ITEM,UPPER_DIM,LOWER_DIM,INSPECTION_TYPE,MACHINE_TYPE,REMARK,PERSONNEL_ID,DATE_TIME From SPC.SPEC_CTP where PROJECT_NAME = '"+strProNumber2V +"' order by WORKSHOP";// '"+fileName3 +"%'
			List<uploadSPCCTP> SpecList = new ArrayList<>();
			System.out.println(Ssql);
			System.out.println("專案號"+strProNumber2V);
			try {
				RowMapper<uploadSPCCTP> mapper = new BeanPropertyRowMapper<>(uploadSPCCTP.class); 
//				SpecList = jdbcTemplate.query(Ssql, new PreparedStatementSetter() {
//					
//					@Override
//					public void setValues(PreparedStatement arg0) throws SQLException {
//						// TODO Auto-generated method stub
//						arg0.setString(1, strProNumber3V);
//					}
//					
//				}, mapper);
				SpecList = jdbcTemplate.query(Ssql, mapper);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.error("SpecList查找所有CTP規格書時發生錯誤："+e);
			}
			return SpecList;
		}
}
