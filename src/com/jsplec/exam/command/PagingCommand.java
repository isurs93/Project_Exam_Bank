package com.jsplec.exam.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsplec.exam.dao.NoticDao;
import com.jsplec.exam.dto.NoticDto;

public class PagingCommand implements CBTCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean execute_boolean(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void execute_session(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		NoticDao dao = new NoticDao();	
		// 찾아 올 게시글의 개수를 받아온다.
		
		
		
		int pageCount = 0;
		
		if(request.getParameter("select") != null || request.getParameter("query") != null)
			pageCount = dao.NoticeQueryCount(request.getParameter("select"), request.getParameter("query"));
		else
			pageCount = dao.NoticeCount();
		
		ArrayList<NoticDto> atos = null;
		
		// 선택한 페이지가 있을 경우.
		if(request.getParameter("index") != null) {
			
			int pageNum = Integer.parseInt(request.getParameter("index"));
			atos = dao.listPage(1, pageNum);		
		}			
		else {
			if(request.getParameter("select") != null || request.getParameter("query") != null) {
				atos = dao.listPageQuery(1, 0, request.getParameter("select"), request.getParameter("query"));
			}
			else
				atos = dao.listPage(1, 0);
		}

		request.setAttribute("AList", atos);
		session.setAttribute("ListCount", pageCount);

	}

	@Override
	public int execute_int(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		return 0;
	}

}
