package com.jsplec.exam.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsplec.exam.dao.BuyDao;
import com.jsplec.exam.dao.SubjectDao;
import com.jsplec.exam.dao.workbookInfoDao;
import com.jsplec.exam.dto.BuyDto;
import com.jsplec.exam.dto.workbookInfoDto;

public class BuyInfoCommand implements CBTCommand {

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
		int workbookInfoSeq = Integer.parseInt(request.getParameter("workbookInfoSeq"));
		System.out.println("바이 인포두에서 들어온거 : "+workbookInfoSeq);
		
		workbookInfoDao InfoSql = new workbookInfoDao();
		workbookInfoDto InfoDto = InfoSql.Load_WhereSeqData(workbookInfoSeq);
	
		BuyDao dao = new BuyDao();
		ArrayList<BuyDto> dtos = dao.buyinfo(workbookInfoSeq);
		
		SubjectDao subSql = new SubjectDao();
		ArrayList<String> PreviewExam = subSql.Preview(InfoDto.getSubjectSeq(), InfoDto.getWorkbookNo());
		
		request.setAttribute("buyinfo", dtos);
		request.setAttribute("Preview", PreviewExam);
	}

	@Override
	public int execute_int(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		return 0;
	}

}
