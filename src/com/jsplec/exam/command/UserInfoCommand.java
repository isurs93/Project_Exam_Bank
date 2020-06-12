package com.jsplec.exam.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsplec.exam.dao.UserDao;
import com.jsplec.exam.dto.UserDto;


public class UserInfoCommand implements CBTCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		System.out.println("in userinfoCmd:" + session.getAttribute("userSeq"));
		
		int userSeq = (int) session.getAttribute("userSeq");
		
		UserDao dao = new UserDao();
		
		UserDto list = dao.userInfo(userSeq);
		
		System.out.println("userinfoCommand seqno :"+userSeq);
		
		request.setAttribute("info", list);
		
		session.setAttribute("userId", list.getUserId());
		session.setAttribute("userPw", list.getUserPw());
		session.setAttribute("userName", list.getUserName());
		
		String tel1 =  list.getUserTelno().substring(0,3);
		String tel2 =  list.getUserTelno().substring(3,7);
		String tel3 =  list.getUserTelno().substring(7,11);
		
		session.setAttribute("userTelnoOne", tel1);
		session.setAttribute("userTelnoTwo", tel2);
		session.setAttribute("userTelnoThree", tel3);
		
		session.setAttribute("userNickName", list.getUserNickName());
		session.setAttribute("userHintQuiz", list.getUserHintQuiz());
		session.setAttribute("userHint", list.getUserHint());
		session.setAttribute("userjoinDate", list.getJoinDate());
		
		System.out.println(list.getUserId());
		System.out.println(list.getUserPw());
		System.out.println(list.getUserName());
		
		System.out.println(tel1 + tel2 + tel3);
		
		System.out.println(list.getUserNickName());
		System.out.println(list.getUserHintQuiz());
		System.out.println(list.getUserHint());
		System.out.println(list.getJoinDate());
		
		
	}

	@Override
	public boolean execute_boolean(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		return false;
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
