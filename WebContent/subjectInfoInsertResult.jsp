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

boolean subjectInfoInsertCheck = (boolean)session.getAttribute("subjectInfoInsertCheck");

%>



<%if(subjectInfoInsertCheck == true){%>

<script>
	alert('과목 정보 등록 완료!');
	location.href = 'subjectManagement.do';

</script>

<%}else{%>
<script>
	alert('중복된 과목이 있습니다. 다른 과목명을 입력해주세요.');
	location.href = 'subjectManagement.do';
</script>

<%}     %>

<%
	// 세션 초기화
	session.invalidate();
%>
</body>
</html>