package com.jsplec.exam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsplec.exam.dao.UserDao;

public class UserModifyUpdateCommand implements CBTCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean execute_boolean(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		boolean check = false;
		int userSeq = Integer.parseInt(request.getParameter("userSeq"));
		System.out.println("관리자모드 회원정보 수정 : " + userSeq);
		
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String userName = request.getParameter("userName");
		String userTelnoOne = request.getParameter("userTelnoOne");		
		String userTelnoTwo = request.getParameter("userTelnoTwo");		
		String userTelnoThree = request.getParameter("userTelnoThree");		
		String userNickName = request.getParameter("userNickName");
		String userHintQuiz = request.getParameter("userHintQuiz");
		String userHint = request.getParameter("userHint");
		String userTelno = userTelnoOne + userTelnoTwo + userTelnoThree;
	
		System.out.println("userSeq : "+userSeq);
		UserDao dao = new UserDao();
		
		if(dao.userModifyUpdate(userSeq, userId, userPw, userName, userTelno, userNickName, userHintQuiz, userHint) == 1) {
			System.out.println("userSeq : "+userSeq);
			check = true;
		}
		
		return check;		
	}

	@Override
	public void execute_session(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub

	}

	@Override
	public int execute_int(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		return 0;
	}

}
