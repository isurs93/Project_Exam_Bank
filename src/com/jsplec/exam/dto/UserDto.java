package com.jsplec.exam.dto;

import java.sql.Timestamp;

public class UserDto {

	public UserDto() {
		// TODO Auto-generated constructor stub
	}
	
	int userSeq;
	String userId;
	String userPw;
	String userName;
	String userTelno;
	String userNickName;
	String userHintQuiz;
	String userHint;
	Timestamp joinDate;
	Timestamp outDate;
	//1 / 3 정훈 수정!   ----여기부
	int adminSeq;
	
	
	
	
	
	
	public UserDto(int userSeq, int adminSeq) {
		super();
		this.userSeq = userSeq;
		this.adminSeq = adminSeq;
	}

	public int getAdminSeq() {
		return adminSeq;
	}

	public void setAdminSeq(int adminSeq) {
		this.adminSeq = adminSeq;
	}

	//// 여기까지 --------정훈 수정 분 
	
	public UserDto(int userSeq, String userId, Timestamp joinDate, Timestamp outDate) {
		super();
		this.userSeq = userSeq;
		this.userId = userId;
		this.joinDate = joinDate;
		this.outDate = outDate;
	}
	

	

	//// 여기까지 --------재관 수정 분 
	
	
	
	

	public UserDto(int userSeq, String userId, String userPw, String userName, String userTelno, String userNickName,
			String userHintQuiz, String userHint, Timestamp joinDate, Timestamp outDate, int adminSeq) {
		super();
		this.userSeq = userSeq;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userTelno = userTelno;
		this.userNickName = userNickName;
		this.userHintQuiz = userHintQuiz;
		this.userHint = userHint;
		this.joinDate = joinDate;
		this.outDate = outDate;
		this.adminSeq = adminSeq;
	}

	// 회원가입 및 회원정보 불러오기
	public UserDto(String userId, String userPw, String userName, String userTelno, String userNickName,
			String userHintQuiz, String userHint, Timestamp joinDate) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userTelno = userTelno;
		this.userNickName = userNickName;
		this.userHintQuiz = userHintQuiz;
		this.userHint = userHint;
		this.joinDate = joinDate;
	}
	
	
	// 로그인 비번
	public UserDto(String userPw) {
		super();
		this.userPw = userPw;
	}


	// 회원 정보 수정
	public UserDto(int userSeq, String userId, String userPw, String userName, String userTelno, String userNickName,
			String userHintQuiz, String userHint) {
		super();
		this.userSeq = userSeq;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userTelno = userTelno;
		this.userNickName = userNickName;
		this.userHintQuiz = userHintQuiz;
		this.userHint = userHint;
	}
	
	// 회원 탈퇴
	public UserDto(int userSeq) {
		super();
		this.userSeq = userSeq;
	}
	
	// 비밀번호 찾기
	public UserDto(String userId, String userPw, String userName, String userTelno, String userHintQuiz,
			String userHint) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userTelno = userTelno;
		this.userHintQuiz = userHintQuiz;
		this.userHint = userHint;
	}
	
	
	// 로그인 유저번호, 비밀번호
	public UserDto(int userSeq, String userPw) {
		super();
		this.userSeq = userSeq;
		this.userPw = userPw;
	}
	
	
	

	public int getUserSeq() {
		return userSeq;
	}



	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserTelno() {
		return userTelno;
	}
	public void setUserTelno(String userTelno) {
		this.userTelno = userTelno;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public String getUserHintQuiz() {
		return userHintQuiz;
	}
	public void setUserHintQuiz(String userHintQuiz) {
		this.userHintQuiz = userHintQuiz;
	}
	public String getUserHint() {
		return userHint;
	}
	public void setUserHint(String userHint) {
		this.userHint = userHint;
	}
	public Timestamp getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}

	public Timestamp getOutDate() {
		return outDate;
	}

	public void setOutDate(Timestamp outDate) {
		this.outDate = outDate;
	}
	
	
	
	
	
}
