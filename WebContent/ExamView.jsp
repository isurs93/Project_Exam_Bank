<%@page import="com.jsplec.exam.dto.SubjectDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>기출문제 은행</title>
    <link rel="stylesheet" href="index.css" />
    <link rel="stylesheet" href="mycourse.css" />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<script type="text/javascript">
<%

	if(request.getAttribute("ExamDataList") == null){%>
		alert('문제가 준비되지 않았습니다. 죄송합니다.');
		location.href = 'purchasedlist.do';
<%}%>
</script>

</head>
<body>
<jsp:include page="IncludePage.jsp"/>
      <div class="main">
       <%
			request.setCharacterEncoding("utf-8");
			String title = (String)request.getAttribute("Title");
			
			int subjectSeq = Integer.parseInt((String)request.getParameter("subjectSeq"));
			String workbookNo = (String)request.getAttribute("workbookNo");
		%>
      	<form action="ExamList.do" method="get">
             <h1 class = "h1">※나의 문제 보기</h1>            
		<table border="1">
			<tr>
				<td><input type="submit" style="align-items:right" value="문제 풀기"></td>
			</tr>
			<tr>
				<th><%=title %> 문제</th>
			</tr>
		<c:forEach items="${ExamDataList }" var="list">
			<tr>
				<td><textarea rows="7" cols="80" readonly="readonly" style="resize:none; border:none;">${list.workbookExam }</textarea></td>
			</tr>	
		</c:forEach>
			<tr>
				<td>
					<input type="hidden" name="subjectSeq" value="<%=subjectSeq%>">
					<input type="hidden" name="workbookNo" value="<%=workbookNo%>">
					<input type="hidden" name="where" value="<%=title%>">
					<input type="submit" style="align-items:right" value="돌아가기" formaction="purchasedlist.do">
					<input type="submit" style="align-items:right" value="문제 풀기" formaction="ExamList.do">
				</td>
			</tr>
		</table>
		</form>
      </div>
<jsp:include page="IncludeDownPage.jsp"/>      
</body>
</html> 