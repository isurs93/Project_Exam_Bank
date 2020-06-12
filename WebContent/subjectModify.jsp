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
    <jsp:include page="IncludePage.jsp"/>
      <div class="main">
          <h1 class = "h1" style="margin-left:-60px;"><i class="fas fa-book-open" style = " margin-right:15px;"></i>과목관리</h1>
        <form action="" method="post">
        <table>
          	<tr>
          		<td><input type="text" name="subjectName" placeholder="추가 할 과목을 입력해주세요." style="width: 400px;  text-align:right;" autofocus="autofocus">
          		<input type="submit" value= "추가하기" formaction="subjectNameInsert.do" style="width: 100px; height: 40px; float:right; margin-right:20px;"></td>
          	</tr>
        </table>
        </form>
        <br><hr><br>
       	<form action="" method="post">
        <table>
          <tr>
            <th>번호</th>
            <th>과목명</th>
            <th>기능</th>
            <th>과목정보</th>
          </tr>
          <c:forEach items="${list }" var="dto">
          <tr>
             <td>${dto.subjectSeq }</td>
             <td>${dto.subjectName }</td>
             <td><input type="submit" name="" value="과목명 변경" formaction="subjectNameUpdate_view.do?subjectSeq=${dto.subjectSeq }"></td>
             <td><input type="submit" name="" value="과목정보" formaction="subjectInfo.do?subjectSeq=${dto.subjectSeq }"></td>
          </tr>
           </c:forEach>
        </table>
        </form>
        
      </div>
   <jsp:include page="IncludeDownPage.jsp"/>
</body>
</html>