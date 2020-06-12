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
    <link rel="stylesheet" href="index2.css" />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
   
<jsp:include page="IncludePage.jsp"/>
      <div class="main">
        <table>
          <tr>
            <td><img src="img/cart.png" width = "100" height ="100"></td>
          </tr>
          <tr>
             <td><h1>결제가 완료 되었습니다.</h1></td>
          </tr>
          <tr>
              <td>문제 구매 목록은 -> 문제 구매 페이지에서 확인 하실 수 있습니다.</td>
           </tr>
           <tr>
              <td><a href="list.do" class="button">메인으로</a>    
               	  <a href="purchasedlist.do" class="button">나의 문제 보기</a></td>
           </tr>
        </table>
      </div>
<jsp:include page="IncludeDownPage.jsp"/>
</body>
</html>