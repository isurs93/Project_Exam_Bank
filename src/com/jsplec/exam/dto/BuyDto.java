package com.jsplec.exam.dto;

import java.sql.Timestamp;

public class BuyDto {
	int buySeq;
	int userSeq;
	int subjectSeq;
	int workbookInfoSeq;
	Timestamp buyDate;
	Timestamp endDate;
	Timestamp examDate;
	String examTime;
	String answerSheet;
	String wrongSheet;
	String score;
	String paymentType;
		
	String workbookNo;
	String workbookPrice;
	String subjectName;
	String purchasedprice;
		
	public BuyDto(Timestamp buyDate, Timestamp endDate, String subjectName, String workbookNo) {
		super();
		this.buyDate = buyDate;
		this.endDate = endDate;
		this.workbookNo = workbookNo;
		this.subjectName = subjectName;
	}
	
	public BuyDto(Timestamp buyDate, Timestamp endDate, String subjectName, String workbookNo, int subjectSeq) {
		super();
		this.buyDate = buyDate;
		this.endDate = endDate;
		this.workbookNo = workbookNo;
		this.subjectName = subjectName;
		this.subjectSeq = subjectSeq;
	}
	
	public BuyDto(int buySeq, int subjectSeq, int workbookInfoSeq, Timestamp endDate, Timestamp examDate, String wrongSheet, String workbookNo, String subjectName, String score) {
		super();
		this.buySeq = buySeq;
		this.subjectSeq = subjectSeq;
		this.workbookInfoSeq = workbookInfoSeq;
		this.endDate = endDate;
		this.examDate = examDate;
		this.wrongSheet = wrongSheet;
		this.workbookNo = workbookNo;
		this.subjectName = subjectName;
		this.score = score;
	}

	public BuyDto(int userSeq, int subjectSeq, int workbookInfoSeq, String paymentType, String purchasedprice) {
		super();
		this.userSeq = userSeq;
		this.subjectSeq = subjectSeq;
		this.workbookInfoSeq = workbookInfoSeq;
		this.paymentType = paymentType;
		this.purchasedprice = purchasedprice;
	}

	public BuyDto(int subjectSeq, int workbookInfoSeq, String workbookNo, String workbookPrice, String subjectName) {
		super();
		this.subjectSeq = subjectSeq;
		this.workbookInfoSeq = workbookInfoSeq;
		this.workbookNo = workbookNo;
		this.workbookPrice = workbookPrice;
		this.subjectName = subjectName;
	}
	public BuyDto(int buySeq, Timestamp buyDate, Timestamp endDate, String workbookNo, String subjectName) {
		super();
		this.buySeq = buySeq;
		this.buyDate = buyDate;
		this.endDate = endDate;
		this.workbookNo = workbookNo;
		this.subjectName = subjectName;
	}
	
	
	
	public BuyDto(int userSeq) {
		super();
		this.userSeq = userSeq;
	}

	public BuyDto(int buySeq, int subjectSeq, Timestamp buyDate, Timestamp endDate, String workbookNo,
			String subjectName, int userSeq) {
		super();
		this.buySeq = buySeq;
		this.subjectSeq = subjectSeq;
		this.buyDate = buyDate;
		this.endDate = endDate;
		this.workbookNo = workbookNo;
		this.subjectName = subjectName;
		this.userSeq = userSeq;
		
	}

	public BuyDto(int buySeq, int subjectSeq, Timestamp buyDate, Timestamp endDate, String workbookNo,
			String subjectName) {
		super();
		this.buySeq = buySeq;
		this.subjectSeq = subjectSeq;
		this.buyDate = buyDate;
		this.endDate = endDate;
		this.workbookNo = workbookNo;
		this.subjectName = subjectName;
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

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getPurchasedprice() {
		return purchasedprice;
	}

	public void setPurchasedprice(String purchasedprice) {
		this.purchasedprice = purchasedprice;
	}

	public int getBuySeq() {
		return buySeq;
	}
	public void setBuySeq(int buySeq) {
		this.buySeq = buySeq;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public int getSubjectSeq() {
		return subjectSeq;
	}
	public void setSubjectSeq(int subjectSeq) {
		this.subjectSeq = subjectSeq;
	}
	public int getWorkbookInfoSeq() {
		return workbookInfoSeq;
	}
	public void setWorkbookInfoSeq(int workbookInfoSeq) {
		this.workbookInfoSeq = workbookInfoSeq;
	}
	public Timestamp getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Timestamp buyDate) {
		this.buyDate = buyDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public Timestamp getExamDate() {
		return examDate;
	}
	public void setExamDate(Timestamp examDate) {
		this.examDate = examDate;
	}
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	public String getAnswerSheet() {
		return answerSheet;
	}
	public void setAnswerSheet(String answerSheet) {
		this.answerSheet = answerSheet;
	}
	public String getWrongSheet() {
		return wrongSheet;
	}
	public void setWrongSheet(String wrongSheet) {
		this.wrongSheet = wrongSheet;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	
	
	
	
}
