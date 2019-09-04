package com.foxlink.spc.pojo;

public class SaveMeasure {
	/*Factory:Factory,Link:Link,ProjectName:pro_Name,PartVersion:part_version,Stutas:Stutas,Frequency:Frequency,
	   Period:Period,TicketNumber:Ticket_Number,MeasurementNumber:Measurement_Number,MachineNumber:Machine_Number*/
	private String Factory;
	private String Link;
	private String ProjectName;
	private String PartVersion;
	private String Status;
	private String Frequency;
	private String Period;
	private String TicketNumber;
	private String MeasurementNumber;
	private String MachineNumber;
	private String Personnel_ID;
	public String getFactory() {
		return Factory;
	}
	public void setFactory(String factory) {
		Factory = factory;
	}
	public String getLink() {
		return Link;
	}
	public void setLink(String link) {
		Link = link;
	}
	public String getProjectName() {
		return ProjectName;
	}
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	public String getPartVersion() {
		return PartVersion;
	}
	public void setPartVersion(String partVersion) {
		PartVersion = partVersion;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getFrequency() {
		return Frequency;
	}
	public void setFrequency(String frequency) {
		Frequency = frequency;
	}
	public String getPeriod() {
		return Period;
	}
	public void setPeriod(String period) {
		Period = period;
	}
	public String getTicketNumber() {
		return TicketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		TicketNumber = ticketNumber;
	}
	public String getMeasurementNumber() {
		return MeasurementNumber;
	}
	public void setMeasurementNumber(String measurementNumber) {
		MeasurementNumber = measurementNumber;
	}
	public String getMachineNumber() {
		return MachineNumber;
	}
	public void setMachineNumber(String machineNumber) {
		MachineNumber = machineNumber;
	}
	public String getPersonnel_ID() {
		return Personnel_ID;
	}
	public void setPersonnel_ID(String personnel_ID) {
		Personnel_ID = personnel_ID;
	}
	@Override
	public String toString() {
		return "SaveMeasure [Factory=" + Factory + ", Link=" + Link + ", ProjectName=" + ProjectName + ", PartVersion="
				+ PartVersion + ", Status=" + Status + ", Frequency=" + Frequency + ", Period=" + Period
				+ ", TicketNumber=" + TicketNumber + ", MeasurementNumber=" + MeasurementNumber + ", MachineNumber="
				+ MachineNumber + ", Personnel_ID=" + Personnel_ID + "]";
	}


}
