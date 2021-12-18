<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href='https://fonts.googleapis.com/css?family=Alatsi'
	rel='stylesheet'>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/library.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>도서 검색 결과 페이지</title>
</head>
<body>
	<%@ include file="../header.jsp"%>
	<!-- <div class="abcd" style="text-align: center"> -->
	<table border="1" class="ttable2">
		<c:forEach var="books" items="${books}" varStatus="n">
			<tr>
				<th>${n.index+1}</th>
				<th><img alt="이미지" src="${books.coverImg}"></th>
				<th>${books.title}</th>
				<th>${books.author}</th>
				<th>${books.company}</th>
				<th>${books.condition}</th>
			</tr>
		</c:forEach>
		
	</table>
	<!-- </div> -->
	<div class="page">
		<c:if test="${fullPage != null}">
	 		<c:choose>
	 			<c:when test="${fullPage > 100}">
	 				<c:if test="${(section)*100 < fullPage}">  <!-- >>(O) -->  <!--  1 2 3 ~~ 9 10 -->
	 					 <c:forEach var="page" begin="1" end="10" step="1">   <!--  pre 11 12 13 14 15~~~19 20  next -->
	 					 	<c:if test="${section > 1 && page == 1}">   
	 					 		<a href="<c:url value='/search/book?searchFor=${searchFor}&search=${search}&section=${section-1}&pageNum=${10}'/>"> pre </a>
	 					 	</c:if>
	 					 		<a href="<c:url value='/search/book?searchFor=${searchFor}&search=${search}&section=${section}&pageNum=${page}'/>">${(section-1)*10+page} </a>
	 					 	<c:if test="${page == 10}"> 
	 					 		<a href="<c:url value='/search/book?searchFor=${searchFor}&search=${search}&section=${section+1}&pageNum=${1}'/>">next </a>
	 					 	</c:if>
	 					 </c:forEach>
	 				</c:if>
	 				<c:if test="${(section)*100 > fullPage}"> <!-- >>(X)-->   <!--764 761~764 //8번섹션 7번페이지       -->  
	 																			<!-- 764+9  (773 - 700) => 73/10 => 7  -->														
	 					<c:forEach var="page" begin="1" end="${((fullPage+9)-((section-1)*100))/10}" step="1">
	 						<c:if test="${section > 1 && page == 1}">   
	 					 		<a href="<c:url value='/search/book?searchFor=${searchFor}&search=${search}&section=${section-1}&pageNum=${10}'/>"> pre </a>
	 					 	</c:if>
	 					 		<a href="<c:url value='/search/book?searchFor=${searchFor}&search=${search}&section=${section}&pageNum=${page}'/>">${(section-1)*10+page} </a>
	 					</c:forEach>
	 				</c:if>
	 			</c:when>
	 			<c:when test="${fullPage == 100}">
	 				<!-- 1 2 3 4 5 6 7 8 9 10 -->
	 				<c:forEach var="page" begin="1" end="10" step="1">
	 					<a href="<c:url value='/search/book?searchFor=${searchFor}&search=${search}&section=${section}&pageNum=${page}'/>">${page}</a>
	 				</c:forEach>
	 			</c:when>
	 			<c:when test="${fullPage < 100}">   
	 				<!-- 1 2 3 4 5 6 7 -->
	 				<c:forEach var="page" begin="1" end="${(fullPage+9)/10}" step="1">
	 					<a href="<c:url value='/search/book?searchFor=${searchFor}&search=${search}&section=${section}&pageNum=${page}'/>">${page}</a>
	 				</c:forEach>
	 			</c:when>
	 		</c:choose>
	 	</c:if>
	 	</div>
	 	<%@ include file="../aside.jsp"%>
</body>
</html>