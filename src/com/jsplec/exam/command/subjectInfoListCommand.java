package com.jsplec.exam.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsplec.exam.dao.SubjectDao;
import com.jsplec.exam.dto.SubjectDto;

public class subjectInfoListCommand implements CBTCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		int subjectSeq = Integer.parseInt(request.getParameter("subjectSeq"));
		
		SubjectDao dao = new SubjectDao();
		ArrayList<SubjectDto> dtos = null;
		
		dtos = dao.subjectInfoList(subjectSeq);
		
		request.setAttribute("list", dtos);
		request.setAttribute("subjectSeq", dtos.get(0).getSubjectSeq());
		request.setAttribute("subjectName", dtos.get(0).getSubjectName());
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
