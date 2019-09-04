package com.foxlink.spc.pojo;

public class SPECTool {
	private String PROJECT_NAME;
	private String INSPECTION_ITEM;
	private String INSPECTION_TYPE;
	private String DEVICE_NUM;
	private String INSPECTION_CONTENT;
	private String FREQUENCY;
	private String DATETIME;
	private String PERSONNEL_ID;
	public String getPROJECT_NAME() {
		return PROJECT_NAME;
	}
	public void setPROJECT_NAME(String pROJECT_NAME) {
		PROJECT_NAME = pROJECT_NAME;
	}
	public String getINSPECTION_ITEM() {
		return INSPECTION_ITEM;
	}
	public void setINSPECTION_ITEM(String iNSPECTION_ITEM) {
		INSPECTION_ITEM = iNSPECTION_ITEM;
	}
	public String getINSPECTION_TYPE() {
		return INSPECTION_TYPE;
	}
	public void setINSPECTION_TYPE(String iNSPECTION_TYPE) {
		INSPECTION_TYPE = iNSPECTION_TYPE;
	}
	public String getDEVICE_NUM() {
		return DEVICE_NUM;
	}
	public void setDEVICE_NUM(String dEVICE_NUM) {
		DEVICE_NUM = dEVICE_NUM;
	}
	public String getINSPECTION_CONTENT() {
		return INSPECTION_CONTENT;
	}
	public void setINSPECTION_CONTENT(String iNSPECTION_CONTENT) {
		INSPECTION_CONTENT = iNSPECTION_CONTENT;
	}
	public String getFREQUENCY() {
		return FREQUENCY;
	}
	public void setFREQUENCY(String fREQUENCY) {
		FREQUENCY = fREQUENCY;
	}
	
	public String getDATETIME() {
		return DATETIME;
	}
	public void setDATETIME(String dATETIME) {
		DATETIME = dATETIME;
	}
	public String getPERSONNEL_ID() {
		return PERSONNEL_ID;
	}
	public void setPERSONNEL_ID(String pERSONNEL_ID) {
		PERSONNEL_ID = pERSONNEL_ID;
	}
	@Override
	public String toString() {
		return "SPECTool [PROJECT_NAME=" + PROJECT_NAME + ", INSPECTION_ITEM=" + INSPECTION_ITEM + ", INSPECTION_TYPE="
				+ INSPECTION_TYPE + ", DEVICE_NUM=" + DEVICE_NUM + ", INSPECTION_CONTENT=" + INSPECTION_CONTENT
				+ ", FREQUENCY=" + FREQUENCY + ", DATETIME=" + DATETIME + ", PERSONNEL_ID=" + PERSONNEL_ID + "]";
	}
		
}
