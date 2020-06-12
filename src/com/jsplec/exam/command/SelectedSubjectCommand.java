package com.jsplec.exam.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsplec.exam.dao.SubjectDao;
import com.jsplec.exam.dto.SubjectDto;

public class SelectedSubjectCommand implements CBTCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int subjectSeq = Integer.parseInt(request.getParameter("subjectSeq"));
		
		SubjectDao dao = new SubjectDao();
		ArrayList<SubjectDto> dtos = dao.examlist(subjectSeq);
		
		
		
		String title = dao.Load_QuerySubjectName(subjectSeq);
		
		request.setAttribute("examlist", dtos);
		request.setAttribute("title", title);
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
