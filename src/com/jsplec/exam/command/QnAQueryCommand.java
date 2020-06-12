package com.jsplec.exam.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsplec.exam.dao.NoticDao;
import com.jsplec.exam.dto.NoticDto;

public class QnAQueryCommand implements CBTCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		
		NoticDao dao = new NoticDao();
		
		ArrayList<NoticDto> dtos = null;
		
		String select = request.getParameter("select");
		String query = request.getParameter("query");
		
		System.out.println("큐엔에이 : "+query);
		
		dtos = dao.QnaSearch(select, query);
		
		request.setAttribute("AList", dtos);

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
