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
    <link rel="stylesheet" href="mycourse.css" />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

</head>
<body>
    <jsp:include page="IncludePage.jsp"/>
      <div class="main">
             <h6 class = "h1" style = "margin-left:100px; font-size:25px;">※ <%=session.getAttribute("userId") %> 님의 구매내역 </h6>
        <table>
          <tr>
            <th>제목</th>
            <th>구매날짜</th>
            <th>종료날짜</th>
            <th>환불</th>
          </tr>
           <c:forEach items="${buyinfolist}" var="dtos">
          <tr>
             <td>${dtos.subjectName} ${dtos.workbookNo}</td>
             <td>${dtos.buyDate}</td>
             <td>${dtos.endDate}</td>
             <td><a href = "ExamRefund.do?buySeq=${dtos.buySeq }&userSeq=${dtos.userSeq }">환불하기</a></td>
          </tr>
          </c:forEach>
        </table>
      </div>
        <jsp:include page="IncludeDownPage.jsp"/>
</body>
</html>