package com.foxlink.spc.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.foxlink.spc.pojo.ResetPassword;
import com.foxlink.spc.pojo.uploadSPCCTP;

@Repository("ResetPasswordDao")
public class ResetPasswordDao {
	private JdbcTemplate jdbcTemplate;
	private static Logger logger = Logger.getLogger(ResetPasswordDao.class);
	//工號
	private String accountId;
	//舊密碼
	private String oldPassword;
	//姓名
	private String name;
	//部門
	private String deptid;
	@Autowired
	public ResetPasswordDao(JdbcTemplate jdbcTemplate) {
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
		public Integer selectUserName(String sql) {

			return jdbcTemplate.queryForObject(sql, Integer.class);
			
		}
		
		//查詢工號是否存在
	public List<ResetPassword> CheckAccount(String account) {
		accountId = account;
		String strResult = null ;
		String strSQL = "SELECT USERNAME,PASSWORD,CHINESENAME,DEPARTMENTCODE  FROM SWIPE.USER_DATA  WHERE USERNAME = '"+account+"'";
		System.out.println(strSQL);
		 List<ResetPassword> SpecList = new ArrayList<>();
		try {

//			//工號
//			private String USERNAME;
//			//密碼
//			private String PASSWORD;
//			//中文名稱(繁體)
//			private String CHINESENAME;
//			//部門id
//			private String DEPARTMENTCODE;
			//SpecList = jdbcTemplate.query(strSQL, mapper);
			
			 RowMapper<ResetPassword> rowMapper=new BeanPropertyRowMapper<>(ResetPassword.class);
			 SpecList = jdbcTemplate.query(strSQL, rowMapper);
			 for(int i = 0; i < SpecList.size();i++){

				 ResetPassword resetPassword = SpecList.get(i); 
			
				 oldPassword = resetPassword.getPASSWORD();
				 name = resetPassword.getCHINESENAME();
				 deptid = resetPassword.getDEPARTMENTCODE();
				 System.out.println("工號===:"+resetPassword.getUSERNAME()+"密碼===:"+resetPassword.getPASSWORD()+"部門id====:"+resetPassword.getDEPARTMENTCODE());
			 }
			 
	
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("SELECT_Account_Error", e);
		}
		return  SpecList;
		
	}

	//設置新密碼  SetNewPassword
	
public int SetNewPassword(String newPassword,String newPassword1) {
	   System.out.println("新密碼=====>"+newPassword+"確認新密碼===>"+newPassword1);
		//UPDATE USER_DATA SET PASSWORD = '1234' WHERE USERNAME = '133566'
	//AND PASSWORD ='"+oldPassword+"'AND CHINESENAME ='"+name+"' 'AND DEPARTMENTCODE ='"+deptid+"'
	int iCount = -1;
	if (newPassword.equals(newPassword1))  {
	String strSQL = "UPDATE SWIPE.USER_DATA SET PASSWORD  = '"+newPassword+"' WHERE USERNAME = '"+accountId+"' ";
		
		System.out.println(strSQL);
		System.out.println(oldPassword);
		
		try {

			 iCount = jdbcTemplate.update(strSQL);
			System.out.println("設置新密碼返回值======>"+iCount);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("SELECT_Password_Error", e);
		}
	}
	
			return iCount;
	
		
	}
}
