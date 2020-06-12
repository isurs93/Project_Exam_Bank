<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>기출문제 은행</title>
    <link rel="stylesheet" href="index.css" />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
request.getAttribute("contentView");

%>
    <jsp:include page="IncludePage.jsp"/>

		<div class="main">
          <h1 class = "h1">※공지사항</h1>
          <form>
        <table style = "align-items: center;">
        	<tr>
	            <th id = "no">번호</th>
	            <th class = "title">제목</th>
	            <th>작성자</th>
	            <th class = "day">작성일자</th>
	            <th class = "no">조회수</th>
	        </tr>   
          <tr>
             <td class = "no">${contentView.postSeq}</td>
             <td id = "title"> ${contentView.title}</td>
             <td class = "no">${contentView.userNickName }</td>
             <td>${contentView.insertDate}</td>
             <td id = "hit">${contentView.hit }</td>
          </tr>
          <tr>
      		<td colspan = "5">
      		<textarea name="content" rows="10" style="width:98%; height: 400px; border: 0; resize: none;">${contentView.content }</textarea>
      		</td>
      		</tr>
      		<tr>
	      	<td colspan ="5">
	      		<button id="btn-Yes" class="btn btn-lg btn-primary btn-block" onclick = "location.href='.do'">수정</button>
	      		<button id="btn-Yes" class="btn btn-lg btn-primary btn-block" onclick = "location.href='list.do'">삭제</button>
	      	</td>
      		</tr>     	
      	</table>
      	</form>
      </div>

        <jsp:include page="IncludeDownPage.jsp"/>
</body>
</html>