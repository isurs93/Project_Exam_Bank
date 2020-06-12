package com.jsplec.exam.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsplec.exam.dao.SubjectDao;
import com.jsplec.exam.dto.SubjectDto;

public class ExamListCommand implements CBTCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub

		int subjectSeq = Integer.parseInt((String)request.getParameter("subjectSeq"));
		String workbookNo = (String)request.getParameter("workbookNo");

		SubjectDao sql = new SubjectDao();
		
		ArrayList<SubjectDto> data = sql.Load_Exam(subjectSeq, workbookNo);
				
		String subjectName = sql.Load_QuerySubjectName(subjectSeq);
		
		if(data.size() != 0) {
			request.setAttribute("ExamDataList", data);
			request.setAttribute("Title", subjectName + "[" + data.get(0).getWorkbookNo() + "]");
		}
		
		request.setAttribute("subjectSeq", subjectSeq);
		request.setAttribute("workbookNo", workbookNo);
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
