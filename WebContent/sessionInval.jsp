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

boolean insertcheck = (boolean)session.getAttribute("insertcheck");

%>



<%if(insertcheck == true){  %>

<script>
	alert('회원 가입 완료. 로그인 후 이용해 주세요.');
	location.href = 'list.do';

</script>

<%}else{%>
<script>
	alert('중복된 아이디가 있습니다. 다른 아이디로 가입해주세요.');
	location.href = 'SignUp.jsp';
</script>

<%}     %>

<%
	// 세션 초기화
	session.invalidate();
%>
</body>
</html>