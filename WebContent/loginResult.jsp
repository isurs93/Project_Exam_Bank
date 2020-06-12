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

boolean result = (boolean)session.getAttribute("result");

%>



<%if(result == true){  %>

<script>
	location.href = 'list.do';

</script>

<%}else{%>
<script>
	alert('일치하는 정보가 없습니다. 다시 입력 해주세요.');
	location.href = 'login_view.do';
<%
	// 세션 초기화
	session.invalidate();
%>
</script>

<%}     %>

</body>
</html>