package com.jsplec.exam.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsplec.exam.command.BuyInfoCheckCommand;
import com.jsplec.exam.command.BuyInfoCommand;
import com.jsplec.exam.command.BuyInfoInsertCommand;
import com.jsplec.exam.command.BuyInfoListCommand;
import com.jsplec.exam.command.BuyInfoListManagementCommand;
import com.jsplec.exam.command.CBTCommand;
import com.jsplec.exam.command.CommentaryCommand;
import com.jsplec.exam.command.ExamListCommand;
import com.jsplec.exam.command.ExamProb_InsertCmd;
import com.jsplec.exam.command.ExamProb_ModifyCmd;
import com.jsplec.exam.command.ExamRefundCommand;
import com.jsplec.exam.command.ExamViewCommand;
import com.jsplec.exam.command.LoginCommand;
import com.jsplec.exam.command.NoticeContentCommand;
import com.jsplec.exam.command.NoticeDropCommand;
import com.jsplec.exam.command.NoticeInsertCommand;
import com.jsplec.exam.command.NoticeModifyCommand;
import com.jsplec.exam.command.PagingCommand;
import com.jsplec.exam.command.QnAContentCommand;
import com.jsplec.exam.command.QnADropCommand;
import com.jsplec.exam.command.QnAInsertCommand;
import com.jsplec.exam.command.QnAListCommand;
import com.jsplec.exam.command.QnAModifyCommand;
import com.jsplec.exam.command.QnaPagingCommand;
import com.jsplec.exam.command.ScoreCommand;
import com.jsplec.exam.command.SelectedSubjectCommand;
import com.jsplec.exam.command.SignupCommand;
import com.jsplec.exam.command.TotalSubjectCommand;
import com.jsplec.exam.command.UserDropCommand;
import com.jsplec.exam.command.UserIdFindCommand;
import com.jsplec.exam.command.UserInfoCommand;
import com.jsplec.exam.command.UserManageDropCommand;
import com.jsplec.exam.command.UserManagementDropCancleCommand;
import com.jsplec.exam.command.UserManagementInfo;
import com.jsplec.exam.command.UserModifyCommand;
import com.jsplec.exam.command.UserModifyUpdateCommand;
import com.jsplec.exam.command.UserPwCheck;
import com.jsplec.exam.command.UserPwFindCommand;
import com.jsplec.exam.command.WrongListCommand;
import com.jsplec.exam.command.subjectInfoInsertCommand;
import com.jsplec.exam.command.subjectInfoListCommand;
import com.jsplec.exam.command.subjectInfoUpdateCommand;
import com.jsplec.exam.command.subjectInfoUpdateViewCommand;
import com.jsplec.exam.command.subjectManagementCommand;
import com.jsplec.exam.command.subjectNameInsertCommand;
import com.jsplec.exam.command.subjectNameUpdateCommand;
import com.jsplec.exam.command.subjectNameUpdateviewCommand;
import com.jsplec.exam.command.userManagementAuthorizationCancelCommand;
import com.jsplec.exam.command.userManagementAuthorizationCommand;
import com.jsplec.exam.command.userManagementListCommand;

/**
 * Servlet implementation class CBT_Controller
 */
@WebServlet("*.do")
public class CBTcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int error = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CBTcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("doGet()");
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("doPost()");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		// 겟이나 포스트나 여기서 실행하는거임.
//		System.out.println("actionDo()");
		request.setCharacterEncoding("utf-8");

		String uri = request.getRequestURI();
		String conPath = request.getContextPath(); 
		String com = uri.substring(conPath.length());
		
		String viewPage = null;
		CBTCommand command = null;
		
		HttpSession session = request.getSession(true); // 세션을 가져온다. (가져올 세션이 없다면 생성한다.)
		switch(com) {
		
		////////////////////// 문제 관련 ////////////////////////
		case("/again.do"):
			viewPage = "ExamList.do";
			break;
			
		case("/ExamView.do"):
			command = new ExamViewCommand();
			command.execute(request, response, session);
			viewPage = "ExamView.jsp";
			break;
			
		case("/ExamList.do"):
			command = new ExamListCommand();
			command.execute(request, response, session);
			viewPage = "ExamList.jsp";
			break;
			
		case("/ExamSubmit.do"):
			command = new ScoreCommand();
			boolean result_Submit = command.execute_boolean(request, response, session);
			if(result_Submit) viewPage = "Score.jsp";
			else viewPage = "error.jsp";
			break;
			
		case("/MyWrong_ListView.do"):
			if(session.getAttribute("userSeq") != null) {
				command = new WrongListCommand();
				command.execute_session(request, response, session);
				viewPage = "MyWrongList.jsp";
			}else {
				error = 1;
				viewPage = "purchasedlistResult.jsp";
			}
			break;
			
		case("/MyWrong.do"):
			command = new WrongListCommand();
			command.execute(request, response, session);
			viewPage = "MyWrong.jsp";
			break;
			
		case("/Commentary.do"):
			command = new CommentaryCommand();
			command.execute(request, response, session);
			viewPage = "Commentary.jsp";
			break;
			
		//////////////////////// 회원 정보 관련 /////////////////////	
		// 회원가입 화면 보기
		case("/SignUp.do"):
			System.out.println("SignUp.do");
			viewPage = "SignUp.jsp";				
			break;
		
		// 회원가입 데이터 입력
		case("/UserInsert.do"):
			System.out.println("UserInsert.do");
			command = new SignupCommand();
			boolean checkInsert = command.execute_boolean(request, response, session);
			if(checkInsert) {
				viewPage = "sessionInval.jsp";				
			}else {
				viewPage = "sessionInval.jsp";				
			}
		break;	
		
		// 메인 화면 가기
//		case("/main.do"):
//			viewPage = "main.jsp";				
//			break;
		
		// 로그인 화면 보기
		case("/login_view.do"):
			viewPage = "login.jsp";				
		break;
		
		// 로그인 데이터 맞는지 확인
		case("/login.do"):
			System.out.println("login.do");
		
			command = new LoginCommand();
			boolean result_Login = command.execute_boolean(request, response, session);
			
			System.out.println("in Controller to login.do : " + session.getAttribute("userSeq"));
			
			if(result_Login || request.getAttribute("userId") != null || request.getParameter("userPw") != null) {
				session.setAttribute("result", result_Login);
				viewPage = "loginResult.jsp";
			}else {
				viewPage = "loginResult.jsp";
				session.setAttribute("result", result_Login);
			}
		break;
		
		// 로그아웃 하기
		case("/logout.do"):
			session.invalidate();
			viewPage = "list.do";
			break;
		
		// 내 정보 눌렀을 때 비번 입력 하는 화면
		case("/userPwCheck_view.do"):
			viewPage = "userPwCheck.jsp";				
		break;	
		
		// 내 정보 눌렀을 때 입력한 비번 데이터 맞는지 확인
		case("/userPwCheck.do"):
			System.out.println("userPwCheck.do");
		
			command = new UserPwCheck();
			boolean pwCheckResult = command.execute_boolean(request, response, session);
			
			System.out.println("in Controller to login.do : " + session.getAttribute("userSeq"));
			
			if(pwCheckResult || request.getParameter("userPw") != null) {
				session.setAttribute("pwCheckResult", pwCheckResult);
				viewPage = "pwCheckResult.jsp";
			}else {
				viewPage = "pwCheckResult.jsp";
				session.setAttribute("pwCheckResult", pwCheckResult);
			}
		break;	
		
		// 회원 정보 화면에 띄우기
		case("/userInfo.do"):
			System.out.println("userInfo.do");
			command = new UserInfoCommand();
			command.execute(request, response, session);
			viewPage = "userModify.jsp";				
		break;
		
		// 회원 정보 화면에서 수정 하기
		case("/userModify.do"):
			System.out.println("userModify.do");
			command = new UserModifyCommand();
			boolean check = command.execute_boolean(request, response, session);
			if(check) {
				System.out.println("수정완료");
				viewPage = "list.do";
			}else{
				System.out.println("수정에러");
			}	
			break;
		
		// 회원 정보 화면에서 회원 탈퇴하기
		case("/userDrop.do"):
			command = new UserDropCommand();
			boolean checkDelete = command.execute_boolean(request, response, session);
			if(checkDelete) {
				session.setAttribute("userDeleteResult", checkDelete);
				System.out.println("삭제완료1");
				viewPage = "userdelete.jsp";	
			}else{
				session.setAttribute("userDeleteResult", checkDelete);
				System.out.println("삭제에러");
				viewPage = "userdelete.jsp";	
		}	
			break;
		// 아이디 찾기 화면 가기
		case("/userIdFind_view.do"):
			viewPage = "userIdFind.jsp";				
		break;	
		
		// 비밀번호 찾기 화면 가기
		case("/find_view.do"):
			viewPage = "find.jsp";				
		break;
		
		// 아이디 찾기 실행
		case("/userIdFind.do"):
			System.out.println("userIdFind.do");
		
			command = new UserIdFindCommand();
			boolean idResult = command.execute_boolean(request, response, session);
			
			System.out.println("in Controller to userPwFind.do : " + session.getAttribute("userId"));
			
			if(idResult || request.getParameter("userName") != null|| request.getParameter("userTelno") != null) {
				session.setAttribute("idFindResult", idResult);
				viewPage = "IdFindSessionInval.jsp";			
			}else {
				session.setAttribute("idFindResult", idResult);
				viewPage = "IdFindSessionInval.jsp";			
			}
		break;	
		
		// 비번 찾기 실행
		case("/userPwFind.do"):
			System.out.println("userPwFind.do");
		
			command = new UserPwFindCommand();
			boolean pwResult = command.execute_boolean(request, response, session);
			
			System.out.println("in Controller to userPwFind.do : " + session.getAttribute("userPw"));
			
		
			if(pwResult || request.getParameter("userId") != null || request.getParameter("userName") != null
					|| request.getParameter("userTelno") != null || request.getParameter("userHintQuiz") != null
					|| request.getParameter("userHint") != null) {
				session.setAttribute("pwFindResult", pwResult);
				viewPage = "findSessionInval.jsp";			
			}else {
				session.setAttribute("pwFindResult", pwResult);
				viewPage = "findSessionInval.jsp";			
			}
			System.out.println(session.getAttribute("pwFindResult"));
		break;
			
		////////////////////////// 공지사항, QnA 부분 ///////////////////////
		case("/list.do"):  //공지사항보기
//			command = new NoticeListCommand();
//			command.execute(request, response);
			command = new PagingCommand();
			command.execute_session(request, response, session);
			viewPage = "Notice.jsp";
			break;
		case("/qna.do"): //qna보기
			command = new QnaPagingCommand();
			command.execute_session(request, response, session);
			viewPage = "QnA.jsp";
			break;
		case("/contentView.do"): //공지사항 내용보기
			command = new NoticeContentCommand();
			command.execute(request, response, session);
			viewPage = "NoticeContent.jsp";
			break;
		case("/NoticWrite.do"): //공지사항쓰기
			command = new NoticeInsertCommand();
			command.execute(request, response, session);
			viewPage = "list.do";
			break;
		case("/NoticeModify.do"): //공지사항 수정
			command = new NoticeModifyCommand();
			command.execute(request, response, session);
			viewPage = "list.do";
			break;
		case("/NoticeDelete.do"): //공지사항 삭제
			command = new NoticeDropCommand();
			command.execute(request, response, session);
			viewPage = "list.do";
			break;
		case("/QnaWrite.do"): // 큐엔에이입력!
			command = new QnAInsertCommand();
			command.execute(request, response, session);
			viewPage = "qna.do";
			break;
		case("/QnaList.do"): // 큐엔에이입력!
			command = new QnAListCommand();
			command.execute(request, response, session);
			viewPage = "QnA.jsp";
			break;
		case("/QnaView.do"): //큐엔에이 상세내용불러오기
			command = new QnAContentCommand();
			command.execute(request, response, session);
			viewPage = "QnAContent.jsp";
			break;
		case("/QnAModify.do"): //큐엔에이 수정
			command = new QnAModifyCommand();
			command.execute(request, response, session);
			viewPage = "qna.do";
			break;
		case("/QnaDelete.do"): //큐엔에 삭제
			command = new QnADropCommand();
			command.execute(request, response, session);
			viewPage = "qna.do";
			break;	
			
		/////////////////////// 구매 관련 ///////////////////////
			// 현재 선택한 과목 어떤 문제 구매 할 수 있는지 보는 페이지.
		case("/SelectExamList.do"):
			command = new SelectedSubjectCommand();
			command.execute(request, response, session);
			viewPage = "SelectExamList.jsp";
			break;
			
		case("/SelectSubject.do"):
			command = new TotalSubjectCommand();
			command.execute(request, response, session);
			viewPage = "SelectSubject.jsp";
			break;
		case("/Buyinfoinsert.do"):
			command = new BuyInfoInsertCommand();
			command.execute_session(request, response, session);
			viewPage = "BuyComplete.do";
			break;
		case("/BuyComplete.do"):
			viewPage = "BuyComplete.jsp";
			break;
		case("/main.do"):
			viewPage = "list.jsp";
			break;
			
			// 구매하기 버튼 눌렀을때 
		case("/BuyInfoCheck.do"):
			command = new BuyInfoCheckCommand();
			boolean BuyInfoCheck =command.execute_boolean(request, response, session);
			if(BuyInfoCheck) {
				session.setAttribute("BuyInfoCheck", BuyInfoCheck);
				viewPage = "BuyInfoCheck.jsp";
			}else {
				session.setAttribute("BuyInfoCheck", BuyInfoCheck);
				viewPage = "BuyInfoCheck.jsp";
			}
			break;
		case("/BuyInfo.do")	:
			command = new BuyInfoCommand();
			command.execute_session(request,response,session);
			viewPage = "BuyExam.jsp";
			break;
		
			//내가 구매한 문제 목록들 보기.
		case("/purchasedlist.do"):
			if(session.getAttribute("userSeq") != null) {
				command = new BuyInfoListCommand();
				command.execute_session(request, response, session);
				viewPage = "mycourse.jsp";
			}
			else {
				error = 1;
				viewPage = "purchasedlistResult.jsp";
			}
			break;
			/////////////////////// 회원 정보 관리 ///////////////////////	
		case("/userManagementList.do"):
			command = new userManagementListCommand();
			command.execute_session(request,response,session);
			viewPage = "UserManagement.jsp";
			break;		
		case("/userManagementSelect.do"):
			command = new UserManagementInfo();
			command.execute(request,response,session);
			viewPage = "UserInfoContent.jsp";
			break;	
		
				///////관리자 선택한 회원 정보 수정//////////
		case("/userManagementUpdate.do"):
			System.out.println("userModify.do");
			command = new UserModifyUpdateCommand();
			boolean check2 = command.execute_boolean(request, response, session);
			if(check2) {
				System.out.println("수정완료");
				viewPage = "userManagementList.do";
			}else{
				System.out.println("수정에러");
			}	
			break;
			///////관리자 선택한 회원 정보 탈퇴//////////
		case("/userManagementDrop.do"):
			command = new UserManageDropCommand();
			boolean checkDelete2 = command.execute_boolean(request, response, session);
			if(checkDelete2) {
				session.setAttribute("deleteResult", checkDelete2);
				System.out.println("삭제완료");
				viewPage = "userManagementList.do";	
			}else{
			session.setAttribute("deleteResult", checkDelete2);
			System.out.println("삭제에러");
			viewPage = "userManagementList.do";	
			}	
			break;
			///////관리자 선택한 회원 정보 탈퇴복원//////////
		case("/userManagementDropCancle.do"):
			command = new UserManagementDropCancleCommand();
			boolean checkDelete3 = command.execute_boolean(request, response, session);
			if(checkDelete3) {
				session.setAttribute("deleteResult", checkDelete3);
				System.out.println("복원완료");
				viewPage = "userManagementList.do";	
			}else{
			session.setAttribute("deleteResult", checkDelete3);
			System.out.println("복원에러");
			viewPage = "userManagementList.do";	
			}	
			break;
			//관리자 권한부여
		case("/userManagementAuthorization.do"):
			command = new userManagementAuthorizationCommand();
			boolean checkDelete4 = command.execute_boolean(request, response, session);
			if(checkDelete4) {
				session.setAttribute("deleteResult", checkDelete4);
				System.out.println("권한 부여완료");
				viewPage = "userManagementList.do";	
			}else{
			session.setAttribute("deleteResult", checkDelete4);
			System.out.println("권한부여 에러");
			viewPage = "userManagementList.do";	
			}		
			break;
			////관리자 권한취소
		case("/userManagementAuthorizationCancel.do"):
			command = new userManagementAuthorizationCancelCommand();
			boolean checkDelete5 = command.execute_boolean(request, response, session);
			if(checkDelete5) {
				session.setAttribute("deleteResult", checkDelete5);
				System.out.println("복원완료");
				viewPage = "userManagementList.do";	
			}else{
				session.setAttribute("deleteResult", checkDelete5);
				System.out.println("복원에러");
				viewPage = "userManagementList.do";	
			}	
			break;
		////////// 환불처리 ///////////////////////
		case("/userPurchasedList.do"):
			command = new BuyInfoListManagementCommand();
			command.execute_session(request, response, session);
			viewPage = "usermycourse.jsp";
			break;
		case("/ExamRefund.do"):
			System.out.println("ExamRefund.do");
			command = new ExamRefundCommand();
			command.execute_session(request, response, session);
			viewPage = "userPurchasedList.do";
			break;
		////////// 과목 관리 파트 ///////////////////////
		case("/subjectManagement.do"): // 과목관리
			command = new subjectManagementCommand();
			command.execute(request, response, session);
			viewPage = "subjectModify.jsp";
			break;
		case("/subjectNameInsert.do"): // 과목명 입력
			command = new subjectNameInsertCommand();
			boolean subjectInsert = command.execute_boolean(request, response, session);
			if(subjectInsert) {
				viewPage = "subjectInsertResult.jsp";				
			}else {
				viewPage = "subjectInsertResult.jsp";				
			}
			break;
		case("/subjectNameUpdate_view.do"): // 과목명 정보 보기
			command = new subjectNameUpdateviewCommand();
			command.execute(request, response, session);
			viewPage = "subjectNameUpdate_view.jsp";
			break;	
		case("/subjectNameUpdate.do"): // 과목명 수정
			command = new subjectNameUpdateCommand();
			boolean check6 = command.execute_boolean(request, response, session);
			if(check6) {
				System.out.println("수정완료");
				viewPage = "subjectManagement.do";
			}else{
				System.out.println("수정에러");
				viewPage = "subjectManagement.do";
			}	
			break;
		case("/subjectInfo.do"): // 과목 정보 보기
			command = new subjectInfoListCommand();
			command.execute(request, response, session);
			viewPage = "subjectInfoModify.jsp";
			break;
		case("/subjectInfoInsert.do"): // 과목 정보 입력
			System.out.println("subjectInfoInsert.do");
			command = new subjectInfoInsertCommand();
			boolean subjectInfoInsert = command.execute_boolean(request, response, session);
			if(subjectInfoInsert) {
				viewPage = "subjectInfoInsertResult.jsp";				
			}else {
				viewPage = "subjectInfoInsertResult.jsp";				
			}
			break;
		case("/subjectInfoUpdate_view.do"): // 선택 과목 정보 보기
			command = new subjectInfoUpdateViewCommand();
			command.execute(request, response, session);
			viewPage = "subjectInfoUpdate_view.jsp";
			break;		
		case("/subjectInfoUpdate.do"): // 과목 정보 수정
			System.out.println("subjectInfoUpdate.do");
			command = new subjectInfoUpdateCommand();
			boolean check7 = command.execute_boolean(request, response, session);
			if(check7) {
				System.out.println("수정완료");
				viewPage = "subjectManagement.do";
			}else{
				System.out.println("수정에러");
				viewPage = "subjectManagement.do";
			}	
			break;
		
		///////////////// 기출 문제 입력, 수정 ,삭제 부분 //////////////
		// 문제 입력 후 데이터베이스에 입력 할 데이터들 확인하는 페이지.
		case "/ExamProb_PreviewList.do":
			command = new ExamProb_InsertCmd();
			command.execute(request, response, session);
			viewPage = "ExamProb_InsertPreviewPage.jsp";
			break;
			
		// 나중에 입력했던 데이터 가지고 이전 페이지로 돌아가기 기능 추가하기
		// 현재는 사용 안 함.
		case "/ExamProb_ComeBackInsert.do":
			command = new ExamProb_InsertCmd();
			command.execute(request, response, session);
			viewPage = "ExaProb_InsertPage.jsp";
			break;
		
			// 새로운 문제들 데이터베이스에 입력.
		case "/ExamProb_InsertSql.do":
			command = new ExamProb_InsertCmd();
			boolean result = command.execute_boolean(request, response, session);
			if(result)
				request.setAttribute("result", "1");
			else
				request.setAttribute("result", "0");
			viewPage = "ExamProb_InsertResultView.jsp";
			break;
			
			// 문제들 수정, 삭제 페이지로 이동.
		case "/ExamProb_ModifyView.do":	
			command = new ExamListCommand();
			command.execute(request, response, session);
			viewPage = "ExamProb_ModifyPage.jsp";
			break;	
			
			// 데이터 베이스에 넣기 전 수정 데이터 확인 페이지.
		case "/ExamProb_ModifyPreview.do":	
			command = new ExamProb_ModifyCmd();
			command.execute(request, response, session);
			viewPage = "ExamProb_ModifyPreview.jsp";
			break;		
			
			// 수정 데이터들을 데이터베이스에서 수정한다.
		case "/ExamProb_ModifySql.do":	
			command = new ExamProb_ModifyCmd();
			boolean Modify = command.execute_boolean(request, response, session);
			if(Modify)
				request.setAttribute("result", "1");
			else
				request.setAttribute("result", "0");
			viewPage = "ExamProb_ModifyResultView.jsp";
			break;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
				
	}
	
}
