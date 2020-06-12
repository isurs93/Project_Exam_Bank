package com.jsplec.exam.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsplec.exam.dao.SubjectDao;
import com.jsplec.exam.dto.SubjectDto;

public class ExamViewCommand implements CBTCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		SubjectDao sql = new SubjectDao();
		
//		ArrayList<SubjectDto> data = sql.AllData();
		
		int subjectSeq = Integer.parseInt(request.getParameter("subjectSeq"));
		String str = request.getParameter("workbookNo");

		ArrayList<SubjectDto> data = sql.Load_Exam(subjectSeq, str);
		
		if(data.size() != 0) {
			request.setAttribute("ExamDataList", data);		
			request.setAttribute("Title", data.get(0).getWorkbookNo());
		}
		
		request.setAttribute("subjectSeq", subjectSeq);
		request.setAttribute("workbookNo", str);
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
