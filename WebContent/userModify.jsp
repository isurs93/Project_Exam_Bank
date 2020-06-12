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
    <title>회원정보수정</title>

  </head>

	<script type="text/javascript">
	<%-- function check() {
		
		var userId = document.getElementById('uid').value;
		var userPw = <%=session.getAttribute("userPw")%>;
		var userPwCheck = document.getElementById('upw').value;
		
		if(userPwCheck == userPw){
			alert("비밀번호를 정확하게 입력해주세요.");
		}
	} --%>
	
	function pwCheck() {
		var pwFirst = document.getElementById('upwFirst').value;
		var pwSecond = document.getElementById('upwSecond').value;
		
		if(pwFirst != pwSecond){
			alert("비밀번호 두 가지가 일치하지 않습니다.")
			return false;
		}else{
			alert("회원 정보가 수정 되었습니다.")
			return true;
		}
		return true;
	}
	</script>


  <body cellpadding="0" cellspacing="0" marginleft="0" margintop="0" width="100%" height="100%">
  
    <header id = "a">
    <div class="card align-middle" style="width:700px; border-radius:20px;">
		<div class="card-title" style="margin-top:30px;">
			<h2 class="card-title text-center" style="color:#113366;">회원 정보</h2>
		</div>
		<div class="card-body">
      <form class="form-signin" method="POST" onSubmit="return pwCheck();">
        <h6 class="form-signin-heading"><%=session.getAttribute("userId") %> 님의 회원 정보 입니다.</h6>
        <label for="inputEmail" class="sr-only">이메일</label>
        <input type="text" id="uid" class="form-control" placeholder="이메일을 입력해주세요!" name = "userId" value="<%=session.getAttribute("userId") %>" readonly="readonly" required autofocus><br>
        <label for="inputPassword" class="sr-only">비밀번호</label>
        <input type="password" id="upwFirst" class="form-control" placeholder="비밀번호" name = "userPw" value="<%=session.getAttribute("userPw")%>" required><br>
        <label for="inputPassword" class="sr-only">비밀번호 확인</label>
        <input type="password" id="upwSecond" class="form-control" placeholder="비밀번호 확인" name = "userPw" value="<%=session.getAttribute("userPw")%>" required><br>
        <label for="" class="sr-only">이름</label>
        <input type="text" id="" class="form-control" placeholder="이름" name = "userName" value="<%=session.getAttribute("userName") %>" required><br>
        <label for="" class="sr-only">닉네임</label>
        <input type="text" id="" class="form-control" placeholder="닉네임" name = "userNickName" value="<%=session.getAttribute("userNickName") %>" required><br> 
       
        <label for="" class="sr-only">전화번호</label>
        <select name="userTelnoOne" id="" class = "select">
            <option value="010" selected="selected">010</option>
        </select>
        <input type="text" name = "userTelnoTwo" class="control" minlength="4" value="<%=session.getAttribute("userTelnoTwo") %>" required>
        <input type="text" name = "userTelnoThree" class="control" minlength="4" value="<%=session.getAttribute("userTelnoThree") %>" required><br>
        <label for="hint" class="sr-only">비밀번호 힌트</label>
       <select name="userHintQuiz" id="" class = "hint" required>
        	<%if(session.getAttribute("userHintQuiz").equals("태어난 고향은?")){ %>
				<option value="태어난 고향은?" selected="selected" >태어난 고향은?</option>
				<option value="졸업한 초등학교는?">졸업한 초등학교는?</option>
				<option value="제일 친한 친구 이름은?">제일 친한 친구 이름은?</option>
				<option value="아버지 성함은?">아버지 성함은?</option>
			<%}else if(session.getAttribute("userHintQuiz").equals("졸업한 초등학교는?")){ %>
				<option value="태어난 고향은?" >태어난 고향은?</option>
				<option value="졸업한 초등학교는?" selected="selected">졸업한 초등학교는?</option>
				<option value="제일 친한 친구 이름은?">제일 친한 친구 이름은?</option>
				<option value="아버지 성함은?">아버지 성함은?</option>
			<%}else if(session.getAttribute("userHintQuiz").equals("제일 친한 친구 이름은?")){ %>
				<option value="태어난 고향은?" >태어난 고향은?</option>
				<option value="졸업한 초등학교는?">졸업한 초등학교는?</option>
				<option value="제일 친한 친구 이름은?" selected="selected">제일 친한 친구 이름은?</option>
				<option value="아버지 성함은?">아버지 성함은?</option>			
			<%}else{ %>
				<option value="태어난 고향은?">태어난 고향은?</option>
				<option value="졸업한 초등학교는?">졸업한 초등학교는?</option>
				<option value="제일 친한 친구 이름은?">제일 친한 친구 이름은?</option>
				<option value="아버지 성함은?" selected="selected">아버지 성함은?</option>
			<%} %>
        </select> 
        <input type="text" id="hint" class="form-control" placeholder="선택한 힌트를 입력해주세요!" name = "userHint" value="<%=session.getAttribute("userHint") %>" required><br>
        <label for="" class="sr-only">회원가입 일자</label>
        <input type="text" id="" class="form-control" placeholder="회원가입 일자" name = "userNickName" value="<%=session.getAttribute("userjoinDate") %>" readonly="readonly" required><br>
        <button id="btn-Yes" class="btn btn-lg btn-primary btn-block" type="submit" name="" formaction = "userModify.do">회원정보 수정</button>
    </form>
    	<br>
        <button id="btn-Yes" class="btn btn-lg btn-primary btn-block" onclick = "location.href='userDrop.do'">회원탈퇴</button>
    	<br>
        <button id="btn-Yes" class="btn btn-lg btn-primary btn-block" onclick = "location.href='list.do'">메인돌아가기</button>
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