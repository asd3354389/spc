package com.foxlink.spc.pojo;

import java.util.List;

public class Measure {
	private String WorkShop;
	private String Inspection_Item;
	private String Nominal_Dim;
	private String Upper_Dim;
	private String Lower_Dim;
	private String Frequency;
	private String Remark1;
	private List<String> Dim1;
	private String Result;
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
	public String getRemark1() {
		return Remark1;
	}
	public void setRemark1(String remark1) {
		Remark1 = remark1;
	}
	public List<String> getDim1() {
		return Dim1;
	}
	public void setDim1(List<String> dim1) {
		Dim1 = dim1;
	}
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}
	@Override
	public String toString() {
		return "Measure [WorkShop=" + WorkShop + ", Inspection_Item=" + Inspection_Item + ", Nominal_Dim=" + Nominal_Dim
				+ ", Upper_Dim=" + Upper_Dim + ", Lower_Dim=" + Lower_Dim + ", Frequency=" + Frequency + ", Remark1="
				+ Remark1 + ", Dim1=" + Dim1 + ", Result=" + Result + "]";
	}
}
