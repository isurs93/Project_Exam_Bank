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

boolean pwFindResult = (boolean)session.getAttribute("pwFindResult");

%>



<%if(pwFindResult == true){  %>

<script>
	alert('비밀번호는  <%=session.getAttribute("userPw")%>  입니다.');
	location.href = 'list.do';

</script>

<%}else{%>
<script>
	alert('아이디의 정보와 일치하지 않습니다. 다시 입력해 주세요.');
	location.href = 'find.jsp';
</script>

<%}     %>

<%
	// 세션 초기화
	session.invalidate();
%>
</body>
</html>