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
     <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
      integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
      crossorigin="anonymous"
    />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<%
	request.setCharacterEncoding("utf-8");
	String subjectSeq = request.getParameter("subjectSeq");
	String workbookNo = request.getParameter("workbookNo");	
%>
</head>
<body>
<jsp:include page="IncludePage.jsp"/>
      <div class="main">
             <h1 class = "h1" style = "margin-left:-40px;"><i class="fas fa-search" style = "margin-right:15px;"></i>나의 오답 해설</h1>
        <form>
        	<input type="hidden" name="subjectSeq" value="<%=subjectSeq%>">
			<input type="hidden" name="workbookNo" value="<%=workbookNo%>">
        <table>
		<tr>
			<th>틀린 문제</th>
			<th>해설</th>
			<th>정답</th>		
		<c:forEach items="${WrongList }" var="list">
		<tr>
			<td><textarea rows="13" cols="80" readonly="readonly" style="resize:none; border:none;" >${list.workbookExam }</textarea></td>
			<td><textarea rows="13" cols="80" readonly="readonly" style="resize:none; border:none;" >${list.workbookReview }</textarea></td>
			<td style="vertical-align: middle">
			<input type="text" style="width:100px; border:none; text-align:center;" value="${list.workbookAnswer }번" readonly="readonly"></td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="3" ><input type="submit" style="align-items:center" value="문제 다시 풀기" formaction="again.do">
							 <input type="submit" style="align-items:center" value="목록으로" formaction="MyWrong_ListView.do">
			</td>
		</tr>
		</table>		
		</form>
      </div>
<jsp:include page="IncludeDownPage.jsp"/>      
</body>
</html>