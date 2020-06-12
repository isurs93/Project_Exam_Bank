package com.jsplec.exam.dto;

import java.sql.Timestamp;

public class NoticDto {
	
	int boardSeq;
	int postSeq;
	int userSeq;
	String title;
	String content;
	Timestamp insertDate;
	Timestamp deleteDate;
	int hit;
	String userNickName;
	
	public NoticDto() {
		
	}
	
	
	public NoticDto(int boardSeq, int postSeq, int userSeq, String title, String content, Timestamp insertDate,
			Timestamp deleteDate, int hit, String userNickName) {
		super();
		this.boardSeq = boardSeq;
		this.postSeq = postSeq;
		this.userSeq = userSeq;
		this.title = title;
		this.content = content;
		this.insertDate = insertDate;
		this.deleteDate = deleteDate;
		this.hit = hit;
		this.userNickName = userNickName;
	}
	
	
	public int getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
	public int getPostSeq() {
		return postSeq;
	}
	public void setPostSeq(int postSeq) {
		this.postSeq = postSeq;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}
	public Timestamp getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Timestamp deleteDate) {
		this.deleteDate = deleteDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	
	
	

}
