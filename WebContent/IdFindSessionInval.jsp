<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

request.setCharacterEncoding("utf-8");

boolean idFindResult = (boolean)session.getAttribute("idFindResult");

%>



<%if(idFindResult == true){  %>

<script>
	alert('아이디는  <%=session.getAttribute("userId")%>  입니다.');
	location.href = 'userIdFind_view.do';

</script>

<%}else{%>
<script>
	alert('일치하는 정보가 없습니다. 다시 입력 해주세요.');
	location.href = 'userIdFind_view.do';
</script>

<%}     %>

<%
	// 세션 초기화
	session.invalidate();
%>
</body>
</html>