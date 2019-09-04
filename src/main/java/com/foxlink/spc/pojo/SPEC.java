package com.foxlink.spc.pojo;

public class SPEC {
	private String PROJECT_NAME;//專案名稱
	private String PART_NUMBER_V;//料號
	private String WorkShop;//工站名稱
	private String Inspection_Item;//檢驗項目
	private String INSPECTION_CONTENT;//檢驗內容
	private String INSPECTION_METHOD;//檢驗方法
	private int Nominal_Dim;//尺寸
	private int Upper_Dim;//上限
	private int Lower_Dim;//下限
	private String Frequency;//頻率
	private String Status;//檢驗階段
	private String Remark1;//備註
	private String SPC_NUM;//spc尺寸
	private String DIM_LOCATION;//尺寸位置
	private String DATE_TIME;//上傳時間
	private String PERSONNEL_ID;//上傳者
	
	public String getPART_NUMBER_V() {
		return PART_NUMBER_V;
	}
	public void setPART_NUMBER_V(String pART_NUMBER_V) {
		PART_NUMBER_V = pART_NUMBER_V;
	}
	public String getPROJECT_NAME() {
		return PROJECT_NAME;
	}
	public void setPROJECT_NAME(String pROJECT_NAME) {
		PROJECT_NAME = pROJECT_NAME;
	}
	public String getINSPECTION_CONTENT() {
		return INSPECTION_CONTENT;
	}
	public void setINSPECTION_CONTENT(String iNSPECTION_CONTENT) {
		INSPECTION_CONTENT = iNSPECTION_CONTENT;
	}
	public String getINSPECTION_METHOD() {
		return INSPECTION_METHOD;
	}
	public void setINSPECTION_METHOD(String iNSPECTION_METHOD) {
		INSPECTION_METHOD = iNSPECTION_METHOD;
	}
	public String getSPC_NUM() {
		return SPC_NUM;
	}
	public void setSPC_NUM(String sPC_NUM) {
		SPC_NUM = sPC_NUM;
	}
	public String getDIM_LOCATION() {
		return DIM_LOCATION;
	}
	public void setDIM_LOCATION(String dIM_LOCATION) {
		DIM_LOCATION = dIM_LOCATION;
	}
	public String getDATE_TIME() {
		return DATE_TIME;
	}
	public void setDATE_TIME(String dATE_TIME) {
		DATE_TIME = dATE_TIME;
	}
	public String getPERSONNEL_ID() {
		return PERSONNEL_ID;
	}
	public void setPERSONNEL_ID(String pERSONNEL_ID) {
		PERSONNEL_ID = pERSONNEL_ID;
	}
	public String getWorkShop() {
		return WorkShop;
	}
	public void setWorkShop(String workShop) {
		WorkShop = workShop;
	}
	public String getInspection_Item() {
		return Inspection_Item;
	}
	public void setInspection_Item(String inspection_Item) {
		Inspection_Item = inspection_Item;
	}
	public int getNominal_Dim() {
		return Nominal_Dim;
	}
	public void setNominal_Dim(int nominal_Dim) {
		Nominal_Dim = nominal_Dim;
	}
	public int getUpper_Dim() {
		return Upper_Dim;
	}
	public void setUpper_Dim(int upper_Dim) {
		Upper_Dim = upper_Dim;
	}
	public int getLower_Dim() {
		return Lower_Dim;
	}
	public void setLower_Dim(int lower_Dim) {
		Lower_Dim = lower_Dim;
	}
	public String getFrequency() {
		return Frequency;
	}
	public void setFrequency(String frequency) {
		Frequency = frequency;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getRemark1() {
		return Remark1;
	}
	public void setRemark1(String remark1) {
		Remark1 = remark1;
	}
	
	@Override
	public String toString() {
		return "SPEC [WorkShop=" + WorkShop + ", Inspection_Item=" + Inspection_Item + ", Nominal_Dim=" + Nominal_Dim
				+ ", Upper_Dim=" + Upper_Dim + ", Lower_Dim=" + Lower_Dim + ", Frequency=" + Frequency + ", Status="
				+ Status + ", Remark1=" + Remark1 + "]";
	}
}
