package com.jsplec.exam.dto;

public class ExamProb {

	int proNum; // 문제 번호.
	String proQuestion; // 문제 내용.
	String proComment; // 문제 해설.
	String proAns; // 문제 정답.
	
	public ExamProb(String proQuestion, String proComment, String proAns) {
		super();
		this.proQuestion = proQuestion;
		this.proComment = proComment;
		this.proAns = proAns;
	}

	public ExamProb(int proNum, String proQuestion, String proComment, String proAns) {
		super();
		this.proNum = proNum;
		this.proQuestion = proQuestion;
		this.proComment = proComment;
		this.proAns = proAns;
	}

	public int getProNum() {
		return proNum;
	}

	public void setProNum(int proNum) {
		this.proNum = proNum;
	}

	public String getProQuestion() {
		return proQuestion;
	}

	public void setProQuestion(String proQuestion) {
		this.proQuestion = proQuestion;
	}

	public String getProComment() {
		return proComment;
	}

	public void setProComment(String proComment) {
		this.proComment = proComment;
	}

	public String getProAns() {
		return proAns;
	}

	public void setProAns(String proAns) {
		this.proAns = proAns;
	}
	
	
}
