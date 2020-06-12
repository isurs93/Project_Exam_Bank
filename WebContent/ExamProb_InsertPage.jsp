<%@page import="com.jsplec.exam.dto.SubjectDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jsplec.exam.dao.SubjectDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>기출문제 은행</title>
    <link rel="stylesheet" href="index.css" />
    <link rel="stylesheet" href="mycourse.css" />
     <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
      integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
      crossorigin="anonymous"
    />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<%
	request.setCharacterEncoding("utf-8");
	
	int Pro_Count = 0;
	// 설정한 문제수가 있을 경우 받아서 저장한다.
	
	// 아무 숫자도 안 넣고 설정할 때 버그 수정하기!!!
	if(request.getParameter("Problem_Count") == null)
		Pro_Count = 0;
	else if(request.getParameter("Problem_Count") != null){
		Pro_Count = Integer.parseInt(request.getParameter("Problem_Count"));
	}
	SubjectDao dao = new SubjectDao();
	ArrayList<SubjectDto> SubName = dao.Load_SubjectName();	
	ArrayList<SubjectDto> workbookNo = dao.Load_workbookNo();

%>

<script type="text/javascript">
	
	function setCategory2(){
		form = document.search;
		form.workbookNo.length = 1;
		
		var subjectSeq = new Array();
		var workbookNo = new Array();
		<%for(int index=0; index<workbookNo.size(); index++){%>
			subjectSeq.push('<%=workbookNo.get(index).getSubjectSeq()%>');
			workbookNo.push('<%=workbookNo.get(index).getSubjectName()%>');
		<%}%>
		
		var optionCnt = 0;
		for(var i=0; i<=subjectSeq.length;i++){
			if(document.search.subjectSeq.value == subjectSeq[i]){
				form.workbookNo.options[optionCnt+1] = new Option(workbookNo[i]);
				console.log(subjectSeq[i]);
				console.log(workbookNo[i]);
				form.workbookNo.options[optionCnt+1].value = workbookNo[i];
				optionCnt++;
			}			
		}
		
		var SubjectNo = new Array();
		var SubjectName = new Array();
		
		<%for(int index=0; index<SubName.size(); index++){%>
			SubjectNo.push('<%=SubName.get(index).getSubjectSeq()%>');
			SubjectName.push('<%=SubName.get(index).getSubjectName()%>');				
		<%}%>
		
		for(var i=0; i<SubjectNo.length; i++){
			if(document.search.subjectSeq.value == SubjectNo[i]){
				document.getElementById("subjectName").value = SubjectName[i];
			}
		}		
	}
</script>
<script type="text/javascript">
	function indexCheck(){
		
	}
</script>
</head>
</head>
<body>
	<jsp:include page="IncludePage.jsp"/>
	
	<div class="main">
        <h1 class = "h1"> <i class="fas fa-paper-plane" style = "margin-right:15px;"></i>문제 입력 페이지</h1>
		<form method="post">
       	<table>
       		<tr >
       			<td colspan="2">문제 개수 설정</td>
       			<td><input type="text" name="Problem_Count" required autofocus style="width:250px; text-align:right;" placeholder = "입력해주세요..."></td>       			
       			<td><input type="submit" value="설정" formaction="ExamProb_InsertPage.jsp"></td>    			
       		</tr>       		
       	</table>
       	</form>
       	<form name="search">
       	<table>
       		<tr><td colspan="4"><input type="submit" value="문제 입력 완료" formaction="ExamProb_PreviewList.do"></td></tr>
       		<tr>
       			<td colspan="2">
       			<input type="hidden" id="subjectName" name="subjectName" value="0">
       			과목 이름 : <select name="subjectSeq" onchange="setCategory2()" required autofocus>
       					<option>선택하세요</option>
       					<%for(int index=0; index<SubName.size(); index++){ %>
       					<option value="<%=SubName.get(index).getSubjectSeq()%>"><%=SubName.get(index).getSubjectName() %></option>
       					<%} %>
       				</select>
       			</td>
       			<td>회차 정보 : <select name="workbookNo" required>
       						  <option value="">회차 정보 선택 하세요!</option>
       						  </select>
       			</td>      			
       		</tr>
       		<%int Count = 0;%>
			<%if(Pro_Count != 0){ // 설정 한 문제수가 1 이상이면 테이블을 생성한다.%>
			<tr>
       			<th>문제 번호</th><th>문제 내용</th><th>문제 해설</th><th>문제 정답</th>
       		</tr>
				<%for(int index=0; index<Pro_Count; index++){ // 설정한 문제 수 만큼 생성.%>	
		       	<tr>
		       		<td style="vertical-align: middle"><input type="text" name="Prob_Num_<%=index %>" value="<%=index+1 %>" readonly="readonly"></td>
			       	<td style="vertical-align: middle"><textarea rows="7" cols="80" name="Prob_Exam_<%=index %>" style="resize:none; border:none;" required></textarea></td>
			       	<td style="vertical-align: middle"><textarea rows="7" cols="80" name="Prob_Comment_<%=index %>" style="resize:none; border:none; " required></textarea></td>
			       	<td style="vertical-align: middle"><input type="text" name="Prob_Ans_<%=index %>" required></td>
		       	</tr>
			    	<%Count++; %>	
       		<%	}
			}else{%>
			<tr>
       			<th>문제 번호</th><th>문제 내용</th><th>문제 해설</th><th>문제 정답</th>
       		</tr>
			<tr>
				<td style="vertical-align: middle"><input type="text" name="Prob_Num_0" value="1" readonly="readonly"></td>
			    <td style="vertical-align: middle"><textarea rows="7" cols="80" name="Prob_Exam_0" style="resize:none; border:none;" required></textarea></td>
			    <td style="vertical-align: middle"><textarea rows="7" cols="80" name="Prob_Comment_0" style="resize:none; border:none; " required></textarea></td>
			    <td style="vertical-align: middle"><input type="text" name="Prob_Ans_0" required></td>
			</tr>
			<%} %>
			<tr><td colspan="4"><input type="submit" value="문제 입력 완료" formaction="ExamProb_PreviewList.do"></td></tr>
       	</table>      		
       <input type="hidden" name="index" value="<%=Count%>">
       	</form>
      </div>
	
	<jsp:include page="IncludeDownPage.jsp"/>	
</body>
</html>