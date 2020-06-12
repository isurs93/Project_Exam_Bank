package com.jsplec.exam.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsplec.exam.dao.SubjectDao;
import com.jsplec.exam.dto.ExamProb;

public class ExamProb_InsertCmd implements CBTCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub

		// Prewview List 만드는 곳.
		
		ArrayList<ExamProb> proList = new ArrayList<ExamProb>();
		
		int proCount = Integer.parseInt(request.getParameter("index")); 
		String workbookNo = request.getParameter("workbookNo");
		
		int subjectSeq = Integer.parseInt(request.getParameter("subjectSeq"));
		String subjectName = request.getParameter("subjectName");

		// 리스트 생성.
		for(int index=0; index<proCount; index++) {
			
			int proNum = Integer.parseInt(request.getParameter("Prob_Num_"+index));
			String proQuestion = request.getParameter("Prob_Exam_"+index);
			String proComment = request.getParameter("Prob_Comment_"+index);
			String proAns = request.getParameter("Prob_Ans_"+index);

			ExamProb data = new ExamProb(proNum, proQuestion, proComment, proAns);
			proList.add(data);
		}
		
		request.setAttribute("error", "0");
		request.setAttribute("PreviewList", proList);
		request.setAttribute("subjectSeq", subjectSeq);
		request.setAttribute("subjectName", subjectName);
		request.setAttribute("workbookNo", workbookNo);
	}

	@Override
	public boolean execute_boolean(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		boolean result = false;
		
		ArrayList<ExamProb> proList = new ArrayList<ExamProb>();
		int proCount = Integer.parseInt(request.getParameter("Problem_Count"));
		// 리스트 생성.
		
		for(int index=0; index<proCount; index++) {
			String proQuestion = request.getParameter("Prob_Exam_"+index);
			String proComment = request.getParameter("Prob_Comment_"+index);
			String proAns = request.getParameter("Prob_Ans_"+index);

			ExamProb data = new ExamProb(proQuestion, proComment, proAns);
			proList.add(data);
		}
		
		int subjectSeq = Integer.parseInt(request.getParameter("subjectSeq"));
		String workbookNo = request.getParameter("workbookNo");		
		
		SubjectDao dao = new SubjectDao();		
		result = dao.Insert_ExamProblem(subjectSeq, workbookNo, proList);
		
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
