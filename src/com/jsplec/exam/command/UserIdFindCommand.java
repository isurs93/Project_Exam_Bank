package com.jsplec.exam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsplec.exam.dao.UserDao;

public class UserIdFindCommand implements CBTCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean execute_boolean(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String userName = request.getParameter("userName");
		String userTelno = request.getParameter("userTelno");
		
		boolean result = false;
		
		UserDao dao = new UserDao();
		
		if(userName.length() != 0 || userTelno.length() != 0) {
			
			
			if(dao.userIdCheck(userName, userTelno) == true) {
				result = true;
			}
		}						
		
		session.setAttribute("userId", dao.userIdSave(userName, userTelno));
		System.out.println("in UserIdFindCommand : " + dao.userIdSave(userName, userTelno));
		
		return result;
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
