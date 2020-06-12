package com.jsplec.exam.dto;

public class SubjectDto {
	
	// 이 곳은 기출문제 관련 변수들.
	int workbookSeq;
	int subjectSeq;
	String workbookNo;
	String workbookExam;
	String workbookAnswer;
	String workbookReview;
	
	// 이 곳은 과목 카테고리 불러오는 변수들.
	String subjectName;
	
	// hojoon's Data.
	int workbookInfoSeq;
	String workbookPrice;
	
	int checkDelete;
	
	boolean trash;
	
	public SubjectDto(int subjectSeq, String subjectName) {
		super();
		this.subjectSeq = subjectSeq;
		this.subjectName = subjectName;
	}
	
	public SubjectDto(int workbookInfoSeq, String workbookNo, String workbookPrice) {
		super();
		this.workbookInfoSeq = workbookInfoSeq;
		this.workbookNo = workbookNo;
		this.workbookPrice = workbookPrice;
	}

	public SubjectDto(String workbookExam, String workbookAnswer, String workbookReview) {
		super();
		this.workbookExam = workbookExam;
		this.workbookAnswer = workbookAnswer;
		this.workbookReview = workbookReview;
	}
	
	public SubjectDto(int workbookSeq, String workbookExam, String workbookAnswer, String workbookReview, int checkDelete, boolean trash) {
		super();
		this.workbookSeq = workbookSeq;
		this.workbookExam = workbookExam;
		this.workbookAnswer = workbookAnswer;
		this.workbookReview = workbookReview;
		this.checkDelete = checkDelete;
	}

	public SubjectDto(int workbookSeq, String workbookNo, String workbookExam, String workbookAnswer,
			String workbookReview) {
		super();
		this.workbookSeq = workbookSeq;
		this.workbookNo = workbookNo;
		this.workbookExam = workbookExam;
		this.workbookAnswer = workbookAnswer;
		this.workbookReview = workbookReview;
	}
	
	public SubjectDto(int workbookSeq, int subjectSeq, String workbookNo, String workbookExam, String workbookAnswer,
			String workbookReview) {
		super();
		this.workbookSeq = workbookSeq;
		this.subjectSeq = subjectSeq;
		this.workbookNo = workbookNo;
		this.workbookExam = workbookExam;
		this.workbookAnswer = workbookAnswer;
		this.workbookReview = workbookReview;
	}
	
	// 과목 수정을 하기 위해서 과목정보 불러오기
	public SubjectDto(int workbookInfoSeq, String workbookNo, String workbookPrice, String subjectName, int subjectSeq ) {
		super();
		this.subjectSeq = subjectSeq;
		this.workbookNo = workbookNo;
		this.subjectName = subjectName;
		this.workbookPrice = workbookPrice;
		this.workbookInfoSeq = workbookInfoSeq;
	}
	
	// 과목정보 불러오기
	public SubjectDto(int workbookInfoSeq, String workbookNo, String subjectName, String workbookPrice) {
		super();
		this.workbookNo = workbookNo;
		this.subjectName = subjectName;
		this.workbookInfoSeq = workbookInfoSeq;
		this.workbookPrice = workbookPrice;
	}


	public int getCheckDelete() {
		return checkDelete;
	}

	public void setCheckDelete(int checkDelete) {
		this.checkDelete = checkDelete;
	}

	public int getWorkbookInfoSeq() {
		return workbookInfoSeq;
	}


	public void setWorkbookInfoSeq(int workbookInfoSeq) {
		this.workbookInfoSeq = workbookInfoSeq;
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

	public int getWorkbookSeq() {
		return workbookSeq;
	}

	public void setWorkbookSeq(int workbookSeq) {
		this.workbookSeq = workbookSeq;
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

	public String getWorkbookExam() {
		return workbookExam;
	}

	public void setWorkbookExam(String workbookExam) {
		this.workbookExam = workbookExam;
	}

	public String getWorkbookAnswer() {
		return workbookAnswer;
	}

	public void setWorkbookAnswer(String workbookAnswer) {
		this.workbookAnswer = workbookAnswer;
	}

	public String getWorkbookReview() {
		return workbookReview;
	}

	public void setWorkbookReview(String workbookReview) {
		this.workbookReview = workbookReview;
	}
}
