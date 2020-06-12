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
    <link rel="stylesheet" href="index3.css" />
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
      integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
      crossorigin="anonymous"
    />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script type="text/javascript">
function doOpenCheck(chk){
    var obj = document.getElementsByName("paymentType");
    for(var i=0; i<obj.length; i++){
        if(obj[i] != chk){
            obj[i].checked = false;
        }
    }
}
</script>
<script type="text/javascript">
function clause(){
	var f=document.forms[0];
	var userSeq=document.<%=session.getAttribute("userSeq")%>;

	
	if(!userSeq){
		alert('로그인 후 이용해 주세요.');
		return false;
	}
	else{
		form.submit();
	}
	return true;
}
</script>
</head>
<body>
    <jsp:include page="IncludePage.jsp"/>
      <div class="main">
      <form >
      <c:forEach items="${buyinfo}" var="dto">
      <input type="hidden" value = "${dto.workbookInfoSeq }" name="workbookInfoSeq">
      <input type="hidden" value = "${dto.subjectSeq }" name="subjectSeq">
      
      	<table>
      		<tr><th>문제 미리 보기</th></tr>
      		<c:forEach items="${Preview }" var="preview">
      			<tr>
      				<td>
      					<textarea rows="7" cols="80" readonly="readonly" style="resize:none; border:none;">${preview }</textarea>
      				</td>
      			</tr>
      		</c:forEach>
      	</table>
      
        <table>
        	<tr>
        		<td>구매과목 정보</td>
        	</tr>
        	<tr>
        		<td><input type="text" name = "" value="${dto.subjectName } ${dto.workbookNo}" style="border:none;width:500px;height:100px;font-size:30px;text-align:center;"></td>
        	</tr>
        </table>
        <table>
        	<tr>
        		<td>과목금액</td>
        		<td>총 결제 금액</td>
        	</tr>
        	<tr>
        		<td align="center"><input type="text" name = "subjectprice" value = "${dto.workbookPrice }" style="border:none;text-align:center;font-size:15px;"></td>
        		<td align="center"><input type="text" name = "purchasedprice" value = "${dto.workbookPrice }" style="border:none;text-align:center;font-size:15px;"></td>
        	</tr>
        </table>
        <table>
        	<tr>
        		<td>결제 수단</td>
        	</tr>
        	<tr>
        		<td><input  style = " margin-right:10px; "type="radio" name="paymentType" value="신용카드" onclick="doOpenCheck(this);" required autofocus><i  class="fab fa-cc-visa fa-lg"></i>신용카드
        		<input type="radio" name="paymentType" value="계좌이체" onclick="doOpenCheck(this);" required style = " margin-left:30px; margin-right:10px;"><i class="fas fa-comment-dollar fa-lg"></i> 계좌이체</td>
        	</tr>
        </table>
        <table>
        	<tr>
        		<td>결제 안내</td>
        	</tr>
        	<tr>
        		<td>
        		- 문제 구입 후 3개월 뒤에 자동으로 사용이 중지됩니다.<br>
        		- 문제 구입 후 7일 이내 환불요청시에 환불 가능합니다.<br>
        		- 문제 구입 후 7일이 지나면 전액 환불 불가.
        		</td>
        	</tr>
        </table>
        <br>
      </c:forEach>
      	<div style = "text-align:center; margin-top : 60px;]">
        <input type="checkbox" name="ch" style="margin-left: 0px" required="required" autofocus="autofocus"> 본인은 위의 내용을 모두 읽어 보았으며 모두 동의합니다.<br><br>
        <input type="submit" formaction="SelectSubject.do" value="돌아가기" class="button" style="margin-left:10px" style = "height:500px" style = "margin-top:380px">
        <input type="submit" formaction="Buyinfoinsert.do" onclick="return clause();" name="btn" value="구매하기" class="button">
       </div>
      </form>
      </div>
       <jsp:include page="IncludeDownPage.jsp"/>
</body>
</html>