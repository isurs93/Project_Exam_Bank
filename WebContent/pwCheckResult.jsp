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

boolean pwCheckResult = (boolean)session.getAttribute("pwCheckResult");

%>



<%if(pwCheckResult == true){  %>

<script>
	location.href = 'userInfo.do';

</script>

<%}else{%>
<script>
	alert('비밀번호가 맞지 않습니다. 다시 입력 해주세요.');
	location.href = 'userPwCheck_view.do';
</script>

<%}     %>

</body>
</html>