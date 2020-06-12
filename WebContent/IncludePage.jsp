<%@page import="com.jsplec.exam.dto.SubjectDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jsplec.exam.dao.SubjectDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>기출문제 은행</title>
    <link rel="stylesheet" href="index.css" />
    <link rel="stylesheet" href="mycourse.css" />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<style>
    *{margin:0;padding:0;}
    ul,li{list-style:none;}
    .slide{height:170px; width:600px; overflow:hidden; background-size:cover;}
    .slide ul{position:relative;height:170px;}
    .slide li{position:absolute;left:0;right:0;top:0;bottom:0;opacity:0;animation:fade 8s infinite;}
    .slide li:nth-child(1){background-image: url(https://daegu.koreapolice.co.kr/gnu/data/cheditor4/1910/20191029212415_1bce6068737e7123e2bec89c5b9f2034_25mx.jpg);animation-delay:0s;background-size:cover;border-radius:1%;}
    .slide li:nth-child(2){background-image: url(http://www.djlawgosi.net/imgaes/theo_proc1_open.jpg);animation-delay:2s;background-size:cover; border-radius:1%;}
    .slide li:nth-child(3){background-image: url(http://www.jeil21.com/eyoom/theme/pc_basic2/image/mobile_slider/m_munpleKichul.jpg);animation-delay:4s;background-size:cover; border-radius:1%;}
    .slide li:nth-child(4){background-image: url(http://www.dsan.co.kr/data/file/18114171893e54135d7986c14097c149.jpg);animation-delay:6.5s;background-size:cover; border-radius:1%;}
     /* 100 / 8 = 12.5 */
    @keyframes fade {
      5% {opacity:0;}
      15% {opacity:1;}
      25% {opacity:1;}
      30% {opacity:0;}
      100% {opacity:0;}
    }
  </style>


</head>
<body>
<% 
	SubjectDao sql = new SubjectDao();
	ArrayList<SubjectDto> list = sql.Load_SubjectName();
	
	///
	int adminSeq = 0;
	if(session.getAttribute("adminSeq") != null)
		adminSeq = (int)session.getAttribute("adminSeq");
	///변경된 부분
	
%>
<div style = "display:flex; text-align:center; justify-content: center; margin-top:50px; margin-bottom:50px;">
    <header
     class="header" style = "margin-right:20px;">
     <%request.setCharacterEncoding("UTF-8");
     	if(session.getAttribute("userSeq") == null){
     		%><div>
            <ul class = "login-line" >
                <li><a href="login_view.do">로그인</a></li>
                <li><a href="SignUp.do">회원가입</a></li>
                <li><a href="userIdFind_view.do">ID/PW찾기</a></li>
            </ul>
     	<%}else{%>
     		<div>
            <ul class = "login-line" >
                <li><a href="logout.do">로그아웃</a></li>
                <li><a href="userPwCheck_view.do">내 정보</a></li>
            </ul>
     	<%}%>
        <p class = "gi">기출문제은행</p>
        </div>
    </header>
     <div class="slide">
	    <ul>
	      <li></li>
	      <li></li>
	      <li></li>
	      <li></li>
	    </ul>
 	 </div>
 	</div> 
 	 <!--정훈 수정부분-->
 	  <% if(1 != adminSeq){%>
    <div class="menubar" class="container">
              <ul class="nav-pills">
                <li class="nav-item">
                  <a class="nav-link"href="list.do">공지사항</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="SelectSubject.do">과목선택</a>
                  <ul>
                  	  <%for(int index=0; index<list.size(); index++){ %>
                      <li><a href="SelectExamList.do?subjectSeq=<%=list.get(index).getSubjectSeq() %>"><%=list.get(index).getSubjectName() %></a></li>
                      <%}%>
                   </ul>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="purchasedlist.do">나의문제보기</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" class="nav-link active" href="MyWrong_ListView.do">나의오답보기</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="qna.do">Q/A</a>
                  </li>
              </ul>              
      </div>
            <%}else{ %>
            <div class="menubar" class="container">
              <ul class="nav-pills">
                <li class="nav-item">
                  <a class="nav-link"href="list.do">공지사항관리</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="subjectManagement.do">과목관리</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="ExamProb_InsertPage.jsp">문제관리</a>
                </li>
                  <li class="nav-item">
                    <a class="nav-link" href="qna.do">Q/A관리</a>
                  </li>
                    <li class="nav-item">
                    <!-- 재관 수정  -->
                    <a class="nav-link" href="userManagementList.do">회원정보관리</a>
                  </li>
              </ul>              
      </div>
      <%} %>
       <!--정훈 수정부분-->      
</body>
</html>