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
	
	System.out.println("error = " + request.getAttribute("error"));

	if(request.getAttribute("error") == null){%>
		<script>
		 alert('문제를 입력 하세요!')
		 location.href = 'ExamProb_InsertPage.jsp';
		</script>
<%	}

%>
</head>
<body>
<jsp:include page="IncludePage.jsp"/>
	
	<div class="main">
        <h1 class = "h1">※입력 문제 확인 페이지</h1>
		<form method="post">
       	<table>
       		<tr>
       			<td colspan="4"><input type="submit" value="문제 입력 하기" formaction="ExamProb_InsertSql.do"></td>
       		</tr>
       		<tr><td colspan="2">과목 이름 : ${subjectName }
       			<input type="hidden" name="workbookNo" value="${workbookNo }">
       			<input type="hidden" name="subjectSeq" value="${subjectSeq }"></td>
       			<td colspan="2">회차 정보 : ${workbookNo }</td>
       		</tr>
			<tr>
       			<th>문제 번호</th><th>문제 내용</th><th>문제 해설</th><th>문제 정답</th>
       		</tr>
       		<c:set var="index" value="0"/>
       		<c:forEach items="${PreviewList }" var="list">
		       	<tr>
	       		<td style="vertical-align: middle"><input type="text" name="Prob_Num_${index}" value="${list.proNum }" readonly="readonly"></td>
		       	<td style="vertical-align: middle"><textarea rows="7" cols="80" name="Prob_Exam_${index}" style="resize:none; border:none;" readonly="readonly">${list.proQuestion }</textarea></td>
		       	<td style="vertical-align: middle"><textarea rows="7" cols="80" name="Prob_Comment_${index}" style="resize:none; border:none;" readonly="readonly">${list.proComment }</textarea></td>
		       	<td style="vertical-align: middle"><input type="text" name="Prob_Ans_${index}" value="${list.proAns }"></td>
		       	<c:set var="index" value="${index + 1}"/>
		       	</tr>
		   </c:forEach>
		   <tr><td colspan="4"><input type="submit" value="문제 입력 하기" formaction="ExamProb_InsertSql.do"></td></tr>
       	</table>
       		<input type="hidden" name="Problem_Count" value="${index}">
       	</form>
      </div>
	
	<jsp:include page="IncludeDownPage.jsp"/>
</body>
</html>