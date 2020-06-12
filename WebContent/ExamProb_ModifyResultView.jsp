<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <h1 class = "h1">※입력한 문제 결과 페이지</h1>
		<form method="post">
       	<table>
       		<tr>
       			<%if(request.getAttribute("result").equals("1")){%>
       				<td>문제 수정이 완료되었습니다.</td>
       			<%}else{%>
       				<td>문제 입력에 실패하였습니다. 다시 시도 해주세요.</td>
       			<%} %>
       		</tr>
       		<tr>
       		</tr>   		
		   <tr><td colspan="2"><input type="submit" value="메인으로" formaction="list.do">
		   		<input type="submit" value="과목 관리 페이지" formaction="subjectManagement.do"></td>
		   	</tr>
       	</table>
       	</form>
      </div>
<jsp:include page="IncludeDownPage.jsp"/>
</body>
</html>