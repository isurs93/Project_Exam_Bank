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

boolean BuyInfoCheck = (boolean)session.getAttribute("BuyInfoCheck");
%>

<%if(BuyInfoCheck == true){  %>

	<script>
		alert('이미 구매한 문제 입니다.');
		location.href = 'purchasedlist.do';
	</script>

<%}else{
	
	if(session.getAttribute("userSeq") != null){%>
	<script>
		<%int workbookInfoSeq = (int)(request.getAttribute("workbookInfoSeq"));%>
		location.href = 'BuyInfo.do?workbookInfoSeq=<%=workbookInfoSeq%>';		
	</script>
	<%}else{%>
	<script>
		<%int subjectSeq = Integer.parseInt(request.getParameter("subjectSeq"));%>
		alert('로그인을 해주십시오.');
		location.href = 'SelectExamList.do?subjectSeq=<%=subjectSeq%>';
		</script>
	<%}
	}%>

</body>
</html>