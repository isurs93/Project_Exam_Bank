<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
	var nSec = 0;
	var nMin = 0;
	var nHour = 0;
	
	function timer(){

		var currentHours = addZeros(nHour,2); 
	    var currentMinute = addZeros(nMin ,2);
	    var currentSeconds =  addZeros(nSec,2);

		clock.innerHTML = currentHours + ":" + currentMinute + ":" + currentSeconds;
		document.getElementById('clock2').innerTEXT = currentHours + ":" + currentMinute + ":" + currentSeconds;

		nSec++;
		if(nSec==60){
			nMin++;
			nSec = 0;
		} 
		if(nMin==60){
			nHour++;
			nMin = 0;
		}			
		setTimeout("timer()",1000);
	}
	
	function timerReturn(){
		var currentHours = addZeros(nHour,2); 
	    var currentMinute = addZeros(nMin ,2);
	    var currentSeconds =  addZeros(nSec,2);

	    document.getElementById('SendTime').value = currentHours + ":" + currentMinute + ":" + currentSeconds;
	    form.submit();
	    
	    var userId = document.getElementById('uid').value;
	    
	    
	}
	
	function addZeros(num, digit){
		 var zero = '';
		  num = num.toString();
		  if (num.length < digit) {
		    for (i = 0; i < digit - num.length; i++) {
		      zero += '0';
		    }
		  }
		  return zero + num;
	}
	
</script>

<%
	request.setCharacterEncoding("utf-8");
	String title = (String)request.getAttribute("Title");
	int count=0;
	
	int subjectSeq = Integer.parseInt((String)request.getParameter("subjectSeq"));
	String workbookNo = (String)request.getParameter("workbookNo");
%>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>기출문제 은행</title>
    <link rel="stylesheet" href="index.css" />
    <link rel="stylesheet" href="mycourse.css" />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

</head>
<body onload="timer()">
    <jsp:include page="IncludePage.jsp"/>
      <form action="ExamSubmit.do" method="get">
      <div id="clock2">
			<input type="hidden" name="timer" id="SendTime" value="">
		</div>
      <div class="main">
        <h1 class = "h1">※나의 문제 풀기</h1>		
		<table border="1">
			<tr>
				<td colspan="2" style= "display:flex; justify-content: center;">
					<button onclick="back()" style="margin-right:20px;">돌아가기</button>
					<button type="submit"  onclick="timerReturn()">문제 제출</button>
				      
				</td>
				<td>
					<div id="clock" style="font-size:18px;"></div>
				</td>
			</tr>
			<tr>
				<th><%=title %> 문제</th>			
				<th>답 선택</th>
			</tr>
		<c:forEach items="${ExamDataList }" var="list">		
			<tr>
				<td><textarea rows="7" cols="80" readonly="readonly" style="resize:none; border:none ">${list.workbookExam }</textarea></td>			
				<td style="vertical-align: middle" >
					<input type="radio" style="align-items:center" name="Problem_<%=count %>" required autofocus value="1"/>1  <!-- placeholder="전화번호를 입력해주세요!"  -->
					<input type="radio" style="align-items:center" name="Problem_<%=count %>" required value="2"/>2
					<input type="radio" style="align-items:center" name="Problem_<%=count %>" required value="3"/>3
					<input type="radio" style="align-items:center" name="Problem_<%=count %>" required value="4"/>4
				</td>
			</tr>		
			<%count++; %>
		</c:forEach>
			<tr>
				<td colspan="2" style="align-items:right">
					<button onclick="back()" style="margin-right:20px;">돌아가기</button>
					<button type="submit" style="align:right" onclick="timerReturn()">문제 제출</button>
				</td>
			</tr>
		</table>
		<input type="hidden" name="subjectSeq" value="<%=subjectSeq %>">
		<input type="hidden" name="workbookNo" value="<%=workbookNo %>">	
		<input type="hidden" name="Count" value="<%=count %>">
		<input type="hidden" name="where" value="<%=title %>">		
      </div>
      </form>
<jsp:include page="IncludeDownPage.jsp"/>
    <script type="text/javascript">
       
       function back() {
    	   window.history.back();
       }
  
       </script>
</body>
</html>