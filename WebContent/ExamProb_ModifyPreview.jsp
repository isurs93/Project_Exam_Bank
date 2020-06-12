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
       			<th>유지 여부</th>
       		</tr>   
       		<c:set var="var_index" value="0"/>
       		<c:forEach items="${ExamList }" var="list">
       		<tr>
	       		<td><textarea rows="7" cols="80" name="Exam_${var_index }" style="resize:none;" readonly="readonly">${list.workbookExam } </textarea></td>
	       		<td><textarea rows="7" cols="80" name="Review_${var_index }" style="resize:none;" readonly="readonly">${list.workbookReview }</textarea></td>
	       		
	       		<td style="vertical-align: middle">
	       		<input type="text" name="Answer_${var_index }" value="${list.workbookAnswer }" readonly="readonly">
				<input type="hidden" name="Seq_${var_index }" value="${list.workbookSeq}"></td>

	       		<td style="vertical-align: middle">
				<c:choose>
	       		<c:when test="${list.checkDelete == 2}">
	       		<input type="text" value="삭제 예정" readonly="readonly">
	       		<input type="hidden" name="Delete_${var_index }" value="1"></c:when>
				<c:otherwise>	
				<input type="text" value="유지" readonly="readonly">			
				<input type="hidden" name="Delete_${var_index }" value="2"></c:otherwise>
				</c:choose>
				</td>
	       	</tr>
       		<c:set var="var_index" value="${var_index + 1}"/> 
       		</c:forEach>
		   <tr>
		   		<td colspan="4">
		   		<input type="hidden" name="var_index" value="${var_index }">
		   		<input type="submit" value="메인으로" formaction="list.do">
		   		<input type="submit" value="수정 완료" formaction="ExamProb_ModifySql.do">
		   		</td>
		   	</tr>
       	</table>
       	</form>
      </div>
<jsp:include page="IncludeDownPage.jsp"/>
</body>
</html>