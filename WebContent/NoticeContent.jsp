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
      <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
      integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
      crossorigin="anonymous"
    />
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
          <h1 class = "h1" style="margin-left:-90px;"><i class="far fa-list-alt" style ="margin-right:15px;"></i>공지사항</h1>
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
             <td class = "no"><input type = "text" name = "postSeq" value = "${contentView.postSeq}" style = "border: 0; text-align:center; width:50px;" readonly="readonly"></td>
             <td class = "no">
	             <%if(session.getAttribute("userSeq") != null && (int)session.getAttribute("userSeq") == 1){ %>
	      			<input type = "text" name = "title" value = "${contentView.title}" style = "border: 0; text-align:center; width:500px;">
	      		<%}else{%>
	      			<input type = "text" name = "title" value = "${contentView.title}" style = "border: 0; text-align:center; width:500px;" readonly="readonly">
	      		<%} %>
             </td>
             <td class = "no"><input type = "text" name = "userNickName" value = "${contentView.userNickName }" style = "border: 0; text-align:center;" readonly="readonly"></td>
             <td class = "no"><input type = "text" name = "insertDate" value = "${contentView.insertDate}" style = "border: 0; text-align:center;" readonly="readonly"></td>
             <td class = "no"><input type = "text" name = "hit" value = "${contentView.hit }" style = "border: 0; text-align:center; width:80px;" readonly="readonly" ></td>
          </tr>
          <tr>
      		<td colspan = "5">
      		<%if(session.getAttribute("userSeq") != null && (int)session.getAttribute("userSeq") == 1){ %>
      			<textarea name="content" rows="10" style="width:98%; height: 400px; border: 0; resize: none;">${contentView.content }</textarea>
      		<%}else{%>
      			<textarea name="content" rows="10" style="width:98%; height: 400px; border: 0; resize: none;" readonly="readonly">${contentView.content }</textarea>
      		<%} %>
      		</td>
      		</tr>
      		<tr>
	      	<td colspan ="5"><input type = "submit" value ="돌아가기" formaction="list.do"></td>
      		</tr>
      	</table>
      	<%if(session.getAttribute("userSeq") != null && (int)session.getAttribute("userSeq") == 1){ %>
      	<table style = "align-items: center;">
      		<tr>
      			<td><input type = "submit" value ="수정하기" formaction="NoticeModify.do"></td>
      			<td><input type = "submit" value ="삭제하기" formaction="NoticeDelete.do"></td>
	      		
      		</tr>
      	</table>
      	<%} %>
      	</form>
      </div>
      <jsp:include page="IncludeDownPage.jsp"/>

</body>
</html>