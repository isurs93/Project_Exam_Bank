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
    
<%
	request.setCharacterEncoding("utf-8");
	int subjectSeq = Integer.parseInt(request.getParameter("subjectSeq"));
	String workbookNo = request.getParameter("workbookNo");

	SubjectDao sql = new SubjectDao();
	ArrayList<SubjectDto> backup = sql.Load_Exam(subjectSeq, workbookNo);
%>

<script type="text/javascript">
<%

	if(request.getAttribute("ExamDataList") == null){%>
		alert('문제가 준비되지 않았습니다. 죄송합니다.');
		location.href = 'subjectInfo.do?subjectSeq=<%=subjectSeq%>'
<%}%>
</script>
    
<script type="text/javascript">

	function setInfoClean(value){
		var index = value;
		alert('index');

		var IdExam = "Exam_" + index;
		var IdReview = "Review" + index;
		var IdAnswer = "Answer" + index;

		document.getElementById("Exam_"+index).value = "삭제 예정";
		document.getElementById("Review_"+index).value = "삭제 예정";
		document.getElementById("Answer_"+index).value = "삭제 예정";	
	}

	function setInfoRollBack(value){
		var index = value;
		
		var Exam = new Array();
		var Review = new Array();
		var Answer = new Array();

		var IdExam = "Exam_" + index;
		var IdReview = "Review" + index;
		var IdAnswer = "Answer" + index;
		
		<%for(int index=0; index<backup.size(); index++){%>
			Exam.push('<%=backup.get(index).getWorkbookExam().trim()%>');
			Review.push('<%=backup.get(index).getWorkbookReview().trim()%>');
			Answer.push('<%=backup.get(index).getWorkbookAnswer().trim()%>');
		<%}%>
		
		document.getElementById("Exam_"+index).value = Exam[index];
		document.getElementById("Review_"+index).value = Review[index];
		document.getElementById("Answer_"+index).value = Answer[index];
	
	}
</script>
</head>
<body>
<jsp:include page="IncludePage.jsp"/>
	<div class="main">
        <h1 class = "h1">※문제 수정 페이지</h1>
		<form name="table" method="post">
       	<table>
       		<tr><td>${Title }</td></tr>
       		<tr>
       			<th>문제 내용</th>
       			<th>문제 해설</th>
       			<th>문제 정답</th>
       			<th>유지</th>
       			<th>삭제</th>
       		</tr>   
       		<c:set var="var_index" value="0"/>
       		<c:forEach items="${ExamDataList }" var="list">
       		<tr>
       		<td><textarea rows="7" cols="80" id="Exam_${var_index }" name="Exam_${var_index }" style="resize:none;" required autofocus>${list.workbookExam } </textarea></td>
       		<td><textarea rows="7" cols="80" id="Review_${var_index }" name="Review_${var_index }" style="resize:none;" required>${list.workbookReview }</textarea></td>
       		<td style="vertical-align: middle">
       		<input type="text" id="Answer_${var_index }" name="Answer_${var_index }" value="${list.workbookAnswer }" required>
       		<input type="hidden" name="Seq_${var_index }" value="${list.workbookSeq}"></td>
       		<td style="vertical-align: middle">유지
       		<input type="radio" name="Delete_${var_index }" style="align-items:center" value="1" checked="checked"></td>
       		<td style="vertical-align: middle">삭제
       		<input type="radio"  name="Delete_${var_index }" style="align-items:center" value="2"></td>
       		</tr>
       		<c:set var="var_index" value="${var_index + 1}"/>      		
       		</c:forEach>
		   <tr>
		   		<td colspan="5">
		   		<input type="hidden" name="var_index" value="${var_index }">
		   		<input type="hidden" name="subjectSeq" value="${subjectSeq }">
		   		<input type="submit" value="메인으로" formaction="list.do">
		   		<input type="submit" value="과목 정보 관리로" formaction="subjectInfo.do">
		   		<input type="submit" value="수정하기" formaction="ExamProb_ModifyPreview.do">
		   		</td>
		   	</tr>
       	</table>
       	</form>
      </div>
<jsp:include page="IncludeDownPage.jsp"/>
</body>
</html>