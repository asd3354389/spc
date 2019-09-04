package com.foxlink.spc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.foxlink.spc.pojo.ResetPassword;
import com.foxlink.spc.pojo.Assistantinfo;;
@Repository("RetrievePasswordDao")
public class RetrievePasswordDao {
	private JdbcTemplate jdbcTemplate;
	private static Logger logger = Logger.getLogger(ResetPasswordDao.class);
	//工號
	private String accountId;
	
	//郵箱
	private String Assistantemail;
	
	//郵箱服務器地址
	private static String smtpServer="dp-notes.foxlink.com.tw";
	//發件人
	private static String emailID="MIS_SFC@foxlink.com.tw";
	//發件人暱稱
	private static String userName="sfc_mis";
	//發件人密碼
	private static String password ="21250Cw";

	@Autowired
	public RetrievePasswordDao(JdbcTemplate jdbcTemplate) {
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
				 RowMapper<ResetPassword> rowMapper=new BeanPropertyRowMapper<>(ResetPassword.class);
				 SpecList = jdbcTemplate.query(strSQL, rowMapper);			
			} catch (Exception e) {
				// TODO: handle exception
			}
			 return SpecList;
		}
		//查詢郵箱
		public String CheckEmail() {
			//SELECT ASSISTANTEMAIL FROM ASSISTANTINFO WHERE ASSISTANTID =
			String eMailStr = "SELECT ASSISTANTEMAIL FROM SWIPE.ASSISTANTINFO WHERE ASSISTANTID = '"+accountId+"'";
			 List<Assistantinfo> SpecList = new ArrayList<>();
			System.out.println(eMailStr);
			 try {
				 RowMapper<Assistantinfo> rowMapper=new BeanPropertyRowMapper<>(Assistantinfo.class);
				 SpecList = jdbcTemplate.query(eMailStr, rowMapper);	
				 for(int i = 0; i < SpecList.size();i++){

					 Assistantinfo assistantInfo = SpecList.get(i); 
					 Assistantemail = assistantInfo.getASSISTANTEMAIL();
				 
					 System.out.println("郵箱===:"+Assistantemail);
				 }
				 
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("SELECT_Email_Error", e);
			}
			return Assistantemail;
		}
		//SendMailPassword
		//發送郵件
		public boolean SendMailPassword() {
			Properties props=System.getProperties();
			props.put("mail.smtp.host", smtpServer);
			props.put("mail.smtp.auth", "true");
			Session session=Session.getInstance(props,new javax.mail.Authenticator(){
				protected PasswordAuthentication getPasswordAuthentication() {
					if ((userName != null) && (userName.length() > 0) && (password != null) 
					    && (password.length() > 0)) {
					       return new PasswordAuthentication(userName, password);
					}
					return null;
		        }
			});
			MimeMessage msg=new MimeMessage(session);
			try{

				String userEmail = CheckEmail();
				 InternetAddress fromAddress = new InternetAddress(emailID,
						 userName, "utf-8");
			        // 设置发送邮件方
			        msg.setFrom(fromAddress);
			        InternetAddress receiveAddress = new InternetAddress(
			        		userEmail, userEmail, "utf-8");
			        // 设置邮件接收方
			        msg.setRecipient(RecipientType.TO, receiveAddress);
			        // 设置邮件标题
			        msg.setSubject("隨機密碼,收到郵箱后請及時修改密碼", "utf-8");
			        //msg.setText("密碼:12324");
			        //獲取隨機數
			        String RandomPassword = getRandomPassword(6);
			        msg.setContent(SetMailContent(RandomPassword),"text/html;charset=UTF-8");
			        //修改數據庫密碼
			        SetNewPassword(RandomPassword);
			        // 设置显示的发件时间
			        msg.setSentDate(new Date());
			        // 保存设置
			        msg.saveChanges();
			        Transport.send(msg);
				return true;
			}
			catch(Exception ex){
				ex.printStackTrace();
				return false;
			}

		}
		//郵箱發送的內容
		private static String SetMailContent(String password){
			return "<h1><font color='#404040'>隨機密碼</font></h1>"+
					"<p><font color='#F24C27'> 密碼:<span style=\"color: blue;font-size: 26px;\">"+password+"</span></p>"+
					"<p style=\"color: black;font-size: 20px;\" >收到郵件后,前往網頁修改密碼</p>";
		}
		
		/**
	     * 生成随机数当作getItemID
	     * n ： 需要的长度
	     * @return
	     */
	    private static String getRandomPassword( int n )
	    {
	        String val = "";
	        Random random = new Random();
	        for ( int i = 0; i < n; i++ )
	        {
	            String str = random.nextInt( 2 ) % 2 == 0 ? "num" : "char";
	            if ( "char".equalsIgnoreCase( str ) )
	            { // 产生字母
	                int nextInt = random.nextInt( 2 ) % 2 == 0 ? 65 : 97;
	                // System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
	                val += (char) ( nextInt + random.nextInt( 26 ) );
	            }
	            else if ( "num".equalsIgnoreCase( str ) )
	            { // 产生数字
	                val += String.valueOf( random.nextInt( 10 ) );
	            }
	        }
	        return val;
	    }
	    
	    public void SetNewPassword(String newPassword) {
	 	   System.out.println("新密碼=====>"+newPassword);
	 		
	 	int iCount = -1;
	 	String strSQL = "UPDATE SWIPE.USER_DATA SET PASSWORD  = '"+newPassword+"' WHERE USERNAME = '"+accountId+"' ";
	 		
	 		System.out.println(strSQL);
	 		
	 		try {

	 			 iCount = jdbcTemplate.update(strSQL);
	 			System.out.println("設置新密碼返回值======>"+iCount);

	 		} catch (Exception e) {
	 			// TODO: handle exception
	 			logger.error("Update_Password_Error", e);
	 		} 	
	 	}
	
}
