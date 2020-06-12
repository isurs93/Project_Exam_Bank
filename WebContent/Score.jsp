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
	String subjectSeq = request.getParameter("subjectSeq");
	String workbookNo = request.getParameter("workbookNo");	
	String buySeq = request.getParameter("buySeq");	
%>

</head>
<body>
<jsp:include page="IncludePage.jsp"/>
      <form>
      <div class="main">
             <h1 class = "h1">※나의 시험 결과</h1>
             <input type="hidden" name="subjectSeq" value="<%=subjectSeq%>">
			 <input type="hidden" name="workbookNo" value="<%=workbookNo%>">
			 <input type="hidden" name="buySeq" value="${buySeq }">	 
        <table>
        	<tr>
        		<td>점수</td>
        		<td>${Score *10} / 100</td>
			</tr>
			<tr>
				<td>틀린 문제들</td>
				<td>${WrongProblem }</td>
			</tr>
			<tr>
				<td>걸린 시간</td>
				<td>${ExamTime }</td>
        	</tr>
        	<tr>
        		<td colspan="3" style="align:center">
        			<input type="submit" style="align:center" value="홈으로" formaction="list.do">
					<input type="submit" style="align:center" value="틀린 문제 보러 가기" formaction="MyWrong.do">
				</td>
        	</tr>
        </table>	
      </div>
      </form>
<jsp:include page="IncludeDownPage.jsp"/>
</body>
</html>