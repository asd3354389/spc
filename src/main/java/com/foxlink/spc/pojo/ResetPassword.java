package com.foxlink.spc.pojo;

public class ResetPassword {

	//工號
	private String USERNAME;
	//密碼
	private String PASSWORD;
	//中文名稱(繁體)
	private String CHINESENAME;
	//部門id
	private String DEPARTMENTCODE;
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public String getCHINESENAME() {
		return CHINESENAME;
	}
	public void setCHINESENAME(String cHINESENAME) {
		CHINESENAME = cHINESENAME;
	}
	public String getDEPARTMENTCODE() {
		return DEPARTMENTCODE;
	}
	public void setDEPARTMENTCODE(String dEPARTMENTCODE) {
		DEPARTMENTCODE = dEPARTMENTCODE;
	}
	
	
}
