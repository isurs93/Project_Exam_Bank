package com.jsplec.exam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsplec.exam.dao.UserDao;
import com.jsplec.exam.dto.UserDto;

public class LoginCommand implements CBTCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean execute_boolean(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String id = request.getParameter("userId");// 로그인.jsp 텍스트 값
		String pw = request.getParameter("userPw");
			
		boolean result = false;
		
		
		
		if(id.length() != 0 || pw.length() != 0) {
			UserDao dao = new UserDao();
			UserDto dtos = null; // 정훈수정분
			
			dtos = dao.login_Seqno(id, pw); //정훈수정
			
			result = dao.login(id, pw);
			
			if(result) {
				session.setAttribute("userSeq", dtos.getUserSeq());
				session.setAttribute("adminSeq", dtos.getAdminSeq()); //정훈 수정분~
				
				System.out.println("in LoginCmd : " + dao.login_Seqno(id, pw));
				System.out.println("in LoginCmd adminSeq : " + dtos.getAdminSeq());
			}
		}						
		return result;
	}

	@Override
	public void execute_session(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	}

	@Override
	public int execute_int(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		return 0;
	}

}
