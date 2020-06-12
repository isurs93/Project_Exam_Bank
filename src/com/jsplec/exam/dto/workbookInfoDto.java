package com.jsplec.exam.dto;

public class workbookInfoDto {

	int workbookInfoSeq;
	int subjectSeq;
	String workbookNo;
	String workbookPrice;

	public workbookInfoDto(int workbookInfoSeq, int subjectSeq, String workbookNo, String workbookPrice) {
		super();
		this.workbookInfoSeq = workbookInfoSeq;
		this.subjectSeq = subjectSeq;
		this.workbookNo = workbookNo;
		this.workbookPrice = workbookPrice;
	}
	
	public int getWorkbookInfoSeq() {
		return workbookInfoSeq;
	}
	public void setWorkbookInfoSeq(int workbookInfoSeq) {
		this.workbookInfoSeq = workbookInfoSeq;
	}
	public int getSubjectSeq() {
		return subjectSeq;
	}
	public void setSubjectSeq(int subjectSeq) {
		this.subjectSeq = subjectSeq;
	}
	public String getWorkbookNo() {
		return workbookNo;
	}
	public void setWorkbookNo(String workbookNo) {
		this.workbookNo = workbookNo;
	}
	public String getWorkbookPrice() {
		return workbookPrice;
	}
	public void setWorkbookPrice(String workbookPrice) {
		this.workbookPrice = workbookPrice;
	}
}
