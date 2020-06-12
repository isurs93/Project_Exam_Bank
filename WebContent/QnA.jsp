<%@page import="com.jsplec.exam.dao.NoticDao"%>
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
    <link rel="stylesheet" href="qna.css" />
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
	//request.getAttribute("AList");

%>
    <jsp:include page="IncludePage.jsp"/>
      <div class="main">
      <form action ="#">
         <h1 class = "h1" style="margin-left:-120px;"><i class="far fa-edit" style ="margin-right:15px;"></i>Q / A</h1>
        <table style = "align-items: center;">
        	 <tr>
        		<td colspan = "5" style ="height:40px; width:100px; opacity: 75%;" >
	        		<select name = "select" style ="height:30px; width:150px; margin-left:50px;">
		            	<option value = "title">제목</option>
		            	<option value = "userNickName">작성자</option>
	           	    </select>
	           	    <input type = "text" name = "query" style="width:400px; height: 30px; border: 0; text-align:right; background-color: #e2e2e2;" autofocus="autofocus" >
            		<input type = "submit" name = "serachBtn" value = "검색" style="width:100px; height: 80px;  margin-right:50px;" formaction="qna.do" >
				</td>
        	</tr>
        	<tr>
	            <th id = "no" style = "width : 20px;">번호</th>
	            <th class = "title">제목</th>
	            <th>작성자</th>
	            <th class = "day">작성일자</th>
	            <th class = "no" style = "width : 80px;">조회수</th>
	        </tr>   
      	 	<c:forEach items="${AList }" var="dto">
          <tr>
             <td class = "no"><a href="QnaView.do?postSeq=${dto.postSeq}">${dto.postSeq}</a></td>
             <td id = "title"> <a href="QnaView.do?postSeq=${dto.postSeq}">${dto.title}</a></td>
             <td class = "no">${dto.userNickName }</td>
             <td>${dto.insertDate}</td>
             <td id = "hit">${dto.hit }</td>
          </tr>
			</c:forEach>
			<tr>
				<td colspan = "5">
				<%int ctn = (int) session.getAttribute("ListCount"); %>
				<%if(ctn > 10){ %>
				<%if(request.getParameter("index") != null){ // <<-,  <- 버튼 효시 할지 결정 %>
					<%if(Integer.parseInt(request.getParameter("index"))-5 < 0){%>
						<a href = "qna.do?index=0"><i class="fas fa-angle-double-left "></i></a>
					<%}else{%>
						<a href = "qna.do?index=<%=Integer.parseInt(request.getParameter("index"))-5%>"><i class="fas fa-angle-double-left "></i></a>
						
					<%}if(Integer.parseInt(request.getParameter("index"))-1 < 0){%>
						<a href = "qna.do?index=0"><i class="fas fa-caret-left"></i></a>
					<%}else{%>
						<a href = "qna.do?index=<%=Integer.parseInt(request.getParameter("index"))-1%>"><i class="fas fa-caret-left"></i></a>
					<%} %>
				<%}
				  else{%>
					  	<a href = "qna.do?index=0"><i class="fas fa-angle-double-left "></i></a>
					  	<a href = "qna.do?index=0"><i class="fas fa-caret-left"></i></a>
				<%}
				}
				  // 리스트와 인덱스가 있을 경우 페이징을 시작한다.
				  if(session.getAttribute("ListCount") != null && request.getParameter("index") != null){
						// 이거는 리스트 카운트가 몇인지 알기 위한 콘솔 출력.
				  		System.out.println("listCount = " + session.getAttribute("ListCount"));

						// pageNum 은 현재 표시 할 페이지의 번호.
						int pageNum = Integer.parseInt(request.getParameter("index"));
						// countNum 은 전체 몇 페이지까지 있다 인식하는 번호.
						double countNum = (int) session.getAttribute("ListCount");
						
						int LastNum = 0;  // 페이지 표시할 마지막 버튼 번호.
						int StartNum = 0; // 페이지 표시할 시작 버튼 시작 번호.
						
						// 이 for문은 버튼을 몇 까지 표시할지 정한다.
						// 페이지 버튼을 5개씩 나타낼것이기 때문에 50으로 나누어 표시한다.
						// 다르게 표시 할 것이면 나누는 숫자 50을 다른 숫자로 변경해준다.
						for(int i = (int)countNum/50+1; i > 0; i--) {
							// 5 페이지씩 나눠서 보여줄거라 5를 곱한후 계산.
							// 예를 들어 pageNum이 3이면 버튼은 1,2,3,4,5 로 표시할것이다.
							// 5를 넘어 6이면 6,7,8,9,10 으로 버튼을 표시한다.
							if(pageNum >= ((i-1)*5) && pageNum < (i*5)) {
								
								// 최대페이지 넘어가면 i*5 말고 최대페이지까지만 출력 
								// 만약 최대페이지가 7이면 ,6,7,8,9,10,으로 표시 안 하고,
								// 6,7 만 표시한다.
								if(i*5 > (int)countNum/10+1) LastNum = (int) countNum/10 + 1; 
								else LastNum = i*5; 
								StartNum = (i-1)*5;
								break;
							}							
						}
	
						// 이 for문은 위에서 정해진 버튼의 개수만큼 표시한다.
						for(int index=StartNum; index<LastNum; index++){%>
							<a href="qna.do?index=<%=index %>"><%=index+1 %></a>
					  <%}
						
						// 선택 되어진 페이지가 최대 페이지보다 작으면 -> (하나의 페이지이동) 버튼을 생성한다.
						if(pageNum < countNum/10+1){%>
							<%if(pageNum+1 < LastNum-1){%>
								 <a href = "qna.do?index=<%=Integer.parseInt(request.getParameter("index"))+1%>"><i class="fas fa-caret-right"></i></a>
							<%}else{%>
								 <a href = "qna.do?index=<%=LastNum-1%>"><i class="fas fa-caret-right"></i></a>
							<%}
							
							    // 그리고 선택 되어진 페이지에 5를 더했을때 최대 페이지를 넘어가면 
							    // ->> 버튼에 5 페이지 이동 기능을 빼준다.
							  if(pageNum+5 > countNum/10+1){ %>
									 <a href = "qna.do?index=<%=LastNum-1%>" ><i class="fas fa-angle-double-right"></i></a>
						  	  <%}else{%>
						  		 	<a href = "qna.do?index=<%=Integer.parseInt(request.getParameter("index"))+5%>" ><i class="fas fa-angle-double-right"></i></a>					  		 
					  		  <%}
						}						
				  }else{		
					// 리스트와 인덱스가 있을 경우, 즉 맨 처음 해당 리스트 페이지에 들어왔을 경 						
						NoticDao dao = new NoticDao();	
						// 찾아 올 게시글의 개수를 받아온다.		
						double countNum = 0;
						
						if(request.getParameter("select") != null || request.getParameter("query") != null)
							countNum = (double) dao.QnaQueryCount(request.getParameter("select"), request.getParameter("query"));
						else
							countNum = (double) dao.QnaCount();
						
						int pageNum = 1;
						int LastNum = 0;  // 페이지 표시할 마지막 버튼 번호.
						int StartNum = 0; // 페이지 표시할 시작 버튼 시작 번호.

						for(int i = (int)countNum/50+1; i > 0; i--) {
							if(pageNum >= ((i-1)*5) && pageNum < (i*5)) {
								
								if(i*5 > (int)countNum/10+1) LastNum = (int) countNum/10 + 1; 
								else LastNum = i*5; 
								StartNum = (i-1)*5;
								break;
							}							
						}
						// 이 for문은 위에서 정해진 버튼의 개수만큼 표시한다.
						for(int index=StartNum; index<LastNum; index++){%>
							<a href="qna.do?index=<%=index %>"><%=index+1 %></a>
					  <%} // 그리고 >, >> 버튼도 만들어준다.%>
				     	 <%if(countNum > 10){%>
				     <a href = "qna.do?index=1"> <i class="fas fa-caret-right"> </i></a>
				     <a href = "qna.do?index=<%=LastNum%>"> <i class="fas fa-angle-double-right"></i> </a>
				     <%} %>
				  <%}%>
				</td>
			</tr>
      	</table>
			</form>
	<%if(session.getAttribute("userSeq") == null){
      	}else{%>
      	<table style = "align-items: center;">
      		<tr>
      			<td colspan = "5"><button onclick = "location.href='QnaWrite.jsp'">Q/A 입력</button><td>
      		</tr>
      	</table>
      	<%} %>
      </div>
	<jsp:include page="IncludeDownPage.jsp"/>
</body>
</html>