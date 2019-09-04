package com.foxlink.spc.pojo;

public class LineNumber {
	private String FACTORY;
	private String FLOOR;
	private String LINE_NUMBER;
	private String DEPT_CODE;
	private String WECHAT_GROUP;
	private String PRODUCTION_DEPT;
	public String getFACTORY() {
		return FACTORY;
	}
	public void setFACTORY(String fACTORY) {
		FACTORY = fACTORY;
	}
	public String getFLOOR() {
		return FLOOR;
	}
	public void setFLOOR(String fLOOR) {
		FLOOR = fLOOR;
	}
	public String getLINE_NUMBER() {
		return LINE_NUMBER;
	}
	public void setLINE_NUMBER(String lINE_NUMBER) {
		LINE_NUMBER = lINE_NUMBER;
	}
	public String getDEPT_CODE() {
		return DEPT_CODE;
	}
	public void setDEPT_CODE(String dEPT_CODE) {
		DEPT_CODE = dEPT_CODE;
	}
	public String getWECHAT_GROUP() {
		return WECHAT_GROUP;
	}
	public void setWECHAT_GROUP(String wECHAT_GROUP) {
		WECHAT_GROUP = wECHAT_GROUP;
	}
	public String getPRODUCTION_DEPT() {
		return PRODUCTION_DEPT;
	}
	public void setPRODUCTION_DEPT(String pRODUCTION_DEPT) {
		PRODUCTION_DEPT = pRODUCTION_DEPT;
	}
	@Override
	public String toString() {
		return "LineNumber [FACTORY=" + FACTORY + ", FLOOR=" + FLOOR + ", LINE_NUMBER=" + LINE_NUMBER + ", DEPT_CODE="
				+ DEPT_CODE + ", WECHAT_GROUP=" + WECHAT_GROUP + ", PRODUCTION_DEPT=" + PRODUCTION_DEPT + "]";
	}
	
	
}
