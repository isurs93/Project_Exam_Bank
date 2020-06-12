package com.jsplec.exam.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsplec.exam.dao.SubjectDao;
import com.jsplec.exam.dto.SubjectDto;

public class ExamProb_ModifyCmd implements CBTCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		
		int var_index = Integer.parseInt(request.getParameter("var_index"));
		
		ArrayList<SubjectDto> ExamList = new ArrayList<SubjectDto>();
		
		for(int index=0; index<var_index; index++) {
			int workbookSeq = Integer.parseInt(request.getParameter("Seq_"+index));
			String workbookExam = request.getParameter("Exam_"+index);
			String workbookReview = request.getParameter("Review_"+index);
			String workbookAnswer = request.getParameter("Answer_"+index);
			
			int checkDelete = Integer.parseInt(request.getParameter("Delete_"+index));
			
			SubjectDto add = new SubjectDto(workbookSeq, workbookExam, workbookAnswer, workbookReview, checkDelete, false);
			ExamList.add(add);
		}
		
		request.setAttribute("ExamList", ExamList);
	}

	@Override
	public boolean execute_boolean(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		int var_index = Integer.parseInt(request.getParameter("var_index"));
		
		ArrayList<SubjectDto> ExamList = new ArrayList<SubjectDto>();
		
		for(int index=0; index<var_index; index++) {
			int workbookSeq = Integer.parseInt(request.getParameter("Seq_"+index));
			String workbookExam = request.getParameter("Exam_"+index);
			String workbookReview = request.getParameter("Review_"+index);
			String workbookAnswer = request.getParameter("Answer_"+index);			
			int checkDelete = Integer.parseInt(request.getParameter("Delete_"+index));
			
			SubjectDto add = new SubjectDto(workbookSeq, workbookExam, workbookAnswer, workbookReview, checkDelete, false);
			ExamList.add(add);
		}
		
		SubjectDao sql = new SubjectDao();
		result = sql.Modify_ExamProblem(ExamList);
		
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
