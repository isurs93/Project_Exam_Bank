<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>기출문제 은행</title>
    <link rel="stylesheet" href="index.css" />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
   <jsp:include page="IncludePage.jsp"/>
      <div class="main">
          <h1 class = "h1">※공지사항</h1>
        <table>
            <th>글번호</th>
            <th>글제목</th>
            <th>글작성일자</th>
          <tr>
             <td>1</td>
             <td>제 99회 정보통신기사 시험 관련 공지</td>
             <td>2019 년 19 월 123일</td>
          </tr>
          <tr>
              <td>2</td>
              <td>제 99회 전기기사 시험 관련 공지</td>
              <td>2019 년 21 월 93일</td>
           </tr>
        </table>
      </div>
  <jsp:include page="IncludeDownPage.jsp"/>
</body>
</html>