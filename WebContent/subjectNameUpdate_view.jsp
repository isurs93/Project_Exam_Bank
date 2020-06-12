<%@page import="java.util.ArrayList"%>
<%@page import="com.jsplec.exam.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="SignUp.css">
    <title>과목명 수정</title>

  </head>

  <body cellpadding="0" cellspacing="0" marginleft="0" margintop="0" width="100%" height="100%">
  
    <header id = "a">
    <div class="card align-middle" style="width:700px; border-radius:20px;">
		<div class="card-title" style="margin-top:30px;">
			<h2 class="card-title text-center" style="color:#113366;">과목명 변경</h2>
		</div>
		<div class="card-body">
      <form class="form-signin" method="POST" onSubmit="return pwCheck();">
        <h6 class="form-signin-heading">변경 할 과목명을 입력해주세요.</h6>
        <label for="inputEmail" class="sr-only">기존 과목명</label>
        <input type="text" id="uid" class="form-control" name = "" value="<%=request.getAttribute("subjectName") %>" readonly="readonly" required autofocus><br>
        <label for="inputPassword" class="sr-only">변경 할 과목명</label>
        <input type="text" id="upwFirst" class="form-control" placeholder="변경 할 과목명을 입력해주세요." name = "subjectName" required><br>
        <label for="inputPassword" class="sr-only">과목 번호</label>
        <input type="hidden" id="upwSecond" class="form-control" placeholder="비밀번호 확인" name = "subjectSeq" value="<%=request.getAttribute("subjectSeq")%>" required><br>
        <button id="btn-Yes" class="btn btn-lg btn-primary btn-block" type="submit" name="" formaction = "subjectNameUpdate.do">변경</button>
    </form>
    	<br>
        <button id="btn-Yes" class="btn btn-lg btn-primary btn-block" onclick = "location.href='subjectManagement.do'">과목관리로</button>
        <button id="btn-Yes" class="btn btn-lg btn-primary btn-block" onclick = "location.href='list.do'">메인으로</button>
		</div>
    </div>
    </header>

   <jsp:include page="IncludeDownPage.jsp"/>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script> 
  </body>
</html>