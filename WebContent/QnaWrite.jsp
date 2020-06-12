<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>기출문제 은행</title>
    <link rel="stylesheet" href="index.css" />
     <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
      integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
      crossorigin="anonymous"
    />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
request.getAttribute("contentView");

%>
<jsp:include page="IncludePage.jsp"/>
<div class="main">
          <h1 class = "h1" style="margin-left:-120px;"><i class="far fa-edit" style ="margin-right:15px;"></i>Q / A</h1>
          <form>
        <table style = "align-items: center;">
        	<tr>
	            <th id = "no" colspan = "5">제목</th>
	        </tr>   
          <tr>
             <td class = "no" colspan = "5"><input type = "text" name = "title" style="width:98%; border: 0; resize: none; height:40px;" placeholder="제목을 입력해주세요." required autofocus></td>
          </tr>
          <tr>
      		<td colspan = "5">
      		<%-- <textarea name="content" rows="10" style="width:98%; height: 400px; border: 0; resize: none; " oninvalid="this.setCustomValidity('내용을 입력해주세요!')">${contentView.content }</textarea> --%>
      		<textarea name="content" rows="10" style="width:98%; height: 400px; border: 0; resize: none; " required autofocus >${contentView.content }</textarea>
      		</td>
      		</tr>
      		<tr>
	      	<td colspan ="5">
	      		<input type = "submit" id="btn-Yes" class="btn btn-lg btn-primary btn-block" formaction="QnaWrite.do" value = "글작성" >
	      		<input type = "button" id="btn-Yes" class="btn btn-lg btn-primary btn-block" onclick="back();" value = "취소" >
	      	</td>
      		</tr>
      	</table>
      	</form>
      </div>

       <jsp:include page="IncludeDownPage.jsp"/>
       <script type="text/javascript">
       
       function back() {
    	   window.history.back();
       }
  
       </script>
</body>
</html>