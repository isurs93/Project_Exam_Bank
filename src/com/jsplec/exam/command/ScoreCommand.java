package com.jsplec.exam.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsplec.exam.dao.BuyDao;
import com.jsplec.exam.dao.SubjectDao;
import com.jsplec.exam.dao.workbookInfoDao;

public class ScoreCommand implements CBTCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub			
	}

	@Override
	public boolean execute_boolean(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
			
		// 사용자가 제출한 정답을 정리.
		ArrayList<String> UserAnswer = new ArrayList<String>();
		int count = Integer.parseInt(request.getParameter("Count"));
		String answerSheet = "";
		
		int subjectSeq = Integer.parseInt((String)request.getParameter("subjectSeq"));
		String workbookNo = (String)request.getParameter("workbookNo");
		
		// 사용자가 입력한 답 분류.
		for(int index=0; index<count; index++) {
			UserAnswer.add(request.getParameter("Problem_"+index));
			answerSheet += request.getParameter("Problem_"+index) + ",";
		}		
		
		// DB에서 정답들을 불러온다.
		SubjectDao dao = new SubjectDao();
		// 어떤 문제를 가져올지 정한다.			
		ArrayList<String> RightAnswer = dao.Load_Answer(subjectSeq, workbookNo);
		String wrongSheet = "";		
		
		// 점수 집계.
		int Score = 0;
		count = RightAnswer.size();
		for(int index=0; index<count; index++) {
			// 맞으면 1점 더하기.
			if(UserAnswer.get(index).equals(RightAnswer.get(index))) Score++;
			
			// 틀리면 문자열에 문제 번호 적기.
			else {
				String input = Integer.toString(index+1);
				wrongSheet += input + ",";
			}				
		}
		
		// 문제 푸는데 걸린시간 받아옴.
		String examTime = request.getParameter("timer");
		
		workbookInfoDao InfoSql = new workbookInfoDao();
		int workbookInfoSeq = InfoSql.Load_InfoSeq(subjectSeq, workbookNo); 
		
		
		
		// 체점 결과창에 표시할 데이터들.
		request.setAttribute("Score", Score);
		request.setAttribute("WrongProblem", wrongSheet);	
		request.setAttribute("ExamTime", examTime);
		request.setAttribute("SubjectSeq", subjectSeq);
		request.setAttribute("workbookNo", workbookNo);
		

		// db로 던져주기 위해 점수 스트링으로 변환.
		String ScoreResult = Integer.toString(Score);
		
		// 입력 후 입력 결과를 불러온다.
		// 테스트용.
		//boolean result = dao.Submit_Answer_Test(examTime, answerSheet, wrongSheet, ScoreResult, paymentType);
		int userSeq = (int)session.getAttribute("userSeq");
		//int workbookInfoSeq = dao.Load_workbookInfoSeq(userSeq, workbookNo);
		
		boolean result = dao.Submit_Answer(userSeq, subjectSeq, workbookInfoSeq, 
				examTime, answerSheet, wrongSheet, ScoreResult);
		// 주문 시퀀스 번호 얻어오기.
		BuyDao buySql = new BuyDao();
		int buySeq = buySql.Load_BuySeq((int)session.getAttribute("userSeq"), subjectSeq, workbookInfoSeq);
				
		System.out.println("buySeq in ScroeCommand = " + buySeq);
		request.setAttribute("buySeq", buySeq);
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
