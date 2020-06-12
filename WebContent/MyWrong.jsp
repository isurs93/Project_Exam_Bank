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
	    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
      integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
      crossorigin="anonymous"
    />
	
</head>
<body>
    <jsp:include page="IncludePage.jsp"/>
    <%
   	request.setCharacterEncoding("utf-8");
    int buySeq = Integer.parseInt(request.getParameter("buySeq"));
	int subjectSeq = Integer.parseInt(request.getParameter("subjectSeq"));
	String workbookNo = request.getParameter("workbookNo");
    %>
      <div class="main">
        <h1 class = "h1"><i class="far fa-grin-tears" style = "margin-right:15px;"></i>나의 오답 보기</h1>
        <form action="Commentary.do">
        <input type="hidden" name="buySeq" value="<%=buySeq %>">
        <input type="hidden" name="subjectSeq" value="<%=subjectSeq %>">
        <input type="hidden" name="workbookNo" value="<%=workbookNo %>">
		<table>
		<c:forEach items="${WrongList }" var="list">
			<tr>
				<td><textarea rows="7" cols="80" readonly="readonly" style="resize:none; border:none;">${list }</textarea></td>
			</tr>
		</c:forEach>		
			<tr>
				<td><input type="submit" style="align:right" value="오답 해설 보기" ></td>
			</tr>		
		</table>
		</form>
      </div>
      <jsp:include page="IncludeDownPage.jsp"/>
</body>
</html>