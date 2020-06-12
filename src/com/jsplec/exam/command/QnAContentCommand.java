package com.jsplec.exam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsplec.exam.dao.NoticDao;
import com.jsplec.exam.dto.NoticDto;

public class QnAContentCommand implements CBTCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		
		NoticDao dao = new NoticDao();
		
		NoticDto dtos = null;
		
		int seq = Integer.parseInt(request.getParameter("postSeq"));
		
		dao.hitUpdate(seq);
		
		dao.QnaView(seq);
		
		dtos = dao.QnaView(seq);
		
		request.setAttribute("userSeqq",dtos.getUserSeq());	
		request.setAttribute("contentView", dtos);

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
