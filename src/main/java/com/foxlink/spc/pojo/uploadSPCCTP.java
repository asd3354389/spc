package com.foxlink.spc.pojo;

public class uploadSPCCTP {

	//專案名稱
	private String PROJECT_NAME;
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
	//上傳時間
	private String DATE_TIME;
	//上傳者
	private String PERSONNEL_ID;
	public String getPERSONNEL_ID() {
		return PERSONNEL_ID;
	}
	public void setPERSONNEL_ID(String pERSONNEL_ID) {
		PERSONNEL_ID = pERSONNEL_ID;
	}
	public String getPROJECT_NAME() {
		return PROJECT_NAME;
	}
	public String getDATE_TIME() {
		return DATE_TIME;
	}
	public void setDATE_TIME(String dATE_TIME) {
		DATE_TIME = dATE_TIME;
	}
	public void setPROJECT_NAME(String pROJECT_NAME) {
		PROJECT_NAME = pROJECT_NAME;
	}
	public String getWORKSHOP() {
		return WORKSHOP;
	}
	public void setWORKSHOP(String wORKSHOP) {
		WORKSHOP = wORKSHOP;
	}
	public String getWORKSHOP_NAME() {
		return WORKSHOP_NAME;
	}
	public void setWORKSHOP_NAME(String wORKSHOP_NAME) {
		WORKSHOP_NAME = wORKSHOP_NAME;
	}
	public String getMACHINE_NAME() {
		return MACHINE_NAME;
	}
	public void setMACHINE_NAME(String mACHINE_NAME) {
		MACHINE_NAME = mACHINE_NAME;
	}
	public String getINSPECTION_ITEM() {
		return INSPECTION_ITEM;
	}
	public void setINSPECTION_ITEM(String iNSPECTION_ITEM) {
		INSPECTION_ITEM = iNSPECTION_ITEM;
	}
	public int getUPPER_DIM() {
		return UPPER_DIM;
	}
	public void setUPPER_DIM(int uPPER_DIM) {
		UPPER_DIM = uPPER_DIM;
	}
	public int getLOWER_DIM() {
		return LOWER_DIM;
	}
	public void setLOWER_DIM(int lOWER_DIM) {
		LOWER_DIM = lOWER_DIM;
	}
	public String getINSPECTION_TYPE() {
		return INSPECTION_TYPE;
	}
	public void setINSPECTION_TYPE(String iNSPECTION_TYPE) {
		INSPECTION_TYPE = iNSPECTION_TYPE;
	}
	public String getMACHINE_TYPE() {
		return MACHINE_TYPE;
	}
	public void setMACHINE_TYPE(String mACHINE_TYPE) {
		MACHINE_TYPE = mACHINE_TYPE;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	
	
	
}
