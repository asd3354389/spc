package com.foxlink.spc.pojo;

public class SPECOPTest {
	private String TEST_ITEM;
	private String PROJECT_NAME;
	private String TEST_CLASS;
	private String TEST_STATUS;
	private String WORK_STATION;
	private String TEST_CONTENT;
	private String DATETIME;
	private String PERSONNEL_ID;
	
	
	public String getTEST_ITEM() {
		return TEST_ITEM;
	}
	public void setTEST_ITEM(String tEST_ITEM) {
		TEST_ITEM = tEST_ITEM;
	}
	public String getPROJECT_NAME() {
		return PROJECT_NAME;
	}
	public void setPROJECT_NAME(String pROJECT_NAME) {
		PROJECT_NAME = pROJECT_NAME;
	}
	public String getTEST_CLASS() {
		return TEST_CLASS;
	}
	public void setTEST_CLASS(String tEST_CLASS) {
		TEST_CLASS = tEST_CLASS;
	}
	public String getTEST_STATUS() {
		return TEST_STATUS;
	}
	public void setTEST_STATUS(String tEST_STATUS) {
		TEST_STATUS = tEST_STATUS;
	}
	public String getWORK_STATION() {
		return WORK_STATION;
	}
	public void setWORK_STATION(String wORK_STATION) {
		WORK_STATION = wORK_STATION;
	}
	public String getTEST_CONTENT() {
		return TEST_CONTENT;
	}
	public void setTEST_CONTENT(String tEST_CONTENT) {
		TEST_CONTENT = tEST_CONTENT;
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
		return "SPECOPTest [TEST_ITEM=" + TEST_ITEM + ", PROJECT_NAME=" + PROJECT_NAME + ", TEST_CLASS=" + TEST_CLASS
				+ ", TEST_STATUS=" + TEST_STATUS + ", WORK_STATION=" + WORK_STATION + ", TEST_CONTENT=" + TEST_CONTENT
				+ ", DATETIME=" + DATETIME + ", PERSONNEL_ID=" + PERSONNEL_ID + "]";
	}
	
	
}
