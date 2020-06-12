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

boolean deletecheck = (boolean)session.getAttribute("userDeleteResult");
%>





<%if(deletecheck == true){  %>

<script>
	alert('회원 탈퇴 완료.');
	<%// 세션 초기화
	session.invalidate();%>
	location.href = 'list.do';

</script>

<%}else{%>
<script>
	alert('회원 정보 삭제에 실패하였습니다 관리자에게 문의해주세요! Tel(102-999-9999)');
	location.href = 'list.do';
	
</script>

<%}     %>


</body>
</html>