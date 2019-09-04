package com.foxlink.spc.pojo;

public class SPECMeasure {
	private String WorkShop;
	private String Inspection_Item;
	private String Inspection_Content;
	private String Inspection_Method;
	private String Nominal_Dim;
	private String Upper_Dim;
	private String Lower_Dim;
	private String Frequency;
	private String Status;
	private String Remark1;
	private String SPC_NUM;
	private String DIM_Location;
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
	public String getInspection_Content() {
		return Inspection_Content;
	}
	public void setInspection_Content(String inspection_Content) {
		Inspection_Content = inspection_Content;
	}
	public String getInspection_Method() {
		return Inspection_Method;
	}
	public void setInspection_Method(String inspection_Method) {
		Inspection_Method = inspection_Method;
	}
	public String getNominal_Dim() {
		return Nominal_Dim;
	}
	public void setNominal_Dim(String nominal_Dim) {
		Nominal_Dim = nominal_Dim;
	}
	public String getUpper_Dim() {
		return Upper_Dim;
	}
	public void setUpper_Dim(String upper_Dim) {
		Upper_Dim = upper_Dim;
	}
	public String getLower_Dim() {
		return Lower_Dim;
	}
	public void setLower_Dim(String lower_Dim) {
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
	public String getSPC_NUM() {
		return SPC_NUM;
	}
	public void setSPC_NUM(String sPC_NUM) {
		SPC_NUM = sPC_NUM;
	}
	public String getDIM_Location() {
		return DIM_Location;
	}
	public void setDIM_Location(String dIM_Location) {
		DIM_Location = dIM_Location;
	}
	@Override
	public String toString() {
		return "SPECMeasure [WorkShop=" + WorkShop + ", Inspection_Item=" + Inspection_Item + ", Inspection_Content="
				+ Inspection_Content + ", Inspection_Method=" + Inspection_Method + ", Nominal_Dim=" + Nominal_Dim
				+ ", Upper_Dim=" + Upper_Dim + ", Lower_Dim=" + Lower_Dim + ", Frequency=" + Frequency + ", Status="
				+ Status + ", Remark1=" + Remark1 + ", SPC_NUM=" + SPC_NUM + ", DIM_Location=" + DIM_Location + "]";
	}
	
	
	
}
