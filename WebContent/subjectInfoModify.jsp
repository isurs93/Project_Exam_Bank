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
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
    <jsp:include page="IncludePage.jsp"/>
      <div class="main">
          <h1 class = "h1">과목정보 관리</h1>
        <form action="subjectInfoInsert.do" method="post">
        <table>
          	<tr>
          		<td><input type="hidden" name="subjectSeq" value="<%=request.getAttribute("subjectSeq") %>" readonly="readonly"></td>
          		<td><%=request.getAttribute("subjectName") %></td>
          		<td><input type="text" name="workbookNo" placeholder="과목의 회차 정보를 입력해주세요."style="width: 300px; height: 25px; text-align:right;" ></td>
          		<td><input type="text" name="workbookPrice" placeholder="회차의 가격을 입력해주세요."style="width: 300px; height: 25px; text-align:right;"></td>
          		<td><input type="submit" value= "추가하기" ></td>
          	</tr>
        </table>
        </form>
        
        <br><hr><br>
       <form action="" method="post">
        <table>
          <tr>
            <th>과목명</th>
            <th>회차 정보</th>
            <th>가격</th>
            <th>기능</th>
            <th>문제</th>
          </tr>
          <c:forEach items="${list }" var="dto">
          <tr>
             <td>${dto.subjectName }</td>
             <td>${dto.workbookNo }</td>
             <td>${dto.workbookPrice }</td>
             <td><input type="hidden" name="subjectSeq" value="<%=request.getAttribute("subjectSeq")%>">
             <input type="submit" name="" value="변경하기" formaction="subjectInfoUpdate_view.do?workbookInfoSeq=${dto.workbookInfoSeq }"></td>
             <td><input type="submit" name="" value="문제보기" formaction="ExamProb_ModifyView.do?workbookNo=${dto.workbookNo}&subjectSeq=<%=request.getAttribute("subjectSeq")%>"></td>
          </tr>
           </c:forEach>
        </table>
        </form>
      </div>
   <jsp:include page="IncludeDownPage.jsp"/>
</body>
</html>