package com.jsplec.exam.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsplec.exam.dao.BuyDao;
import com.jsplec.exam.dao.SubjectDao;
import com.jsplec.exam.dto.BuyDto;

public class WrongListCommand implements CBTCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		
		int subjectSeq = Integer.parseInt(request.getParameter("subjectSeq"));
		String workbookNo = request.getParameter("workbookNo");
		int buySeq = Integer.parseInt(request.getParameter("buySeq"));
		
		BuyDao BuyDao = new BuyDao();
		SubjectDao subDao = new SubjectDao();
		
		//int workbookInfoSeq = subDao.Load_workbookInfoSeq(subjectSeq, workbookNo);
		//int buySeq = BuyDao.Load_BuySeq((int)session.getAttribute("userSeq"), subjectSeq, workbookInfoSeq);
		
		String str = BuyDao.Load_WrongSheet(buySeq);

		String[] wrongSheet = str.split(",");

		ArrayList<String> wrongProblem = subDao.Load_WrongProblem(subjectSeq, workbookNo, wrongSheet);
		
		request.setAttribute("WrongList", wrongProblem);		
		request.setAttribute("buySeq", buySeq);
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

//		int userSeq = (int)session.getAttribute("userSeq");
				
		int userSeq = 0;
		
		if(session.getAttribute("userSeq") != null)
			userSeq = (int) session.getAttribute("userSeq");
		
		BuyDao BuyDao = new BuyDao();
		
		// 해당 유저의 모든 오답 리스트를 불러온다.
		ArrayList<BuyDto> wrongProblem = BuyDao.Load_WrongList(userSeq);
		
		request.setAttribute("WrongList", wrongProblem);
	}

	@Override
	public int execute_int(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		return 0;
	}

}
