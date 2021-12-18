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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/library.css?=ver1">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>메인 페이지</title>
</head>
<body class="mainbody">
	<%@ include file="header.jsp"%>
	<main class="middlemain">
		<div class="slideshow-container">
			<div class="mySlides fade">
				<img
					src="${pageContext.request.contextPath}/resources/img/Slide1.jpg"
					style="width: 100%;">
				<div class="text">그린 도서관</div>
			</div>

			<div class="mySlides fade">
				<img
					src="${pageContext.request.contextPath}/resources/img/Slide2.jpg"
					style="width: 100%;">
				<div class="text">그린 도서관</div>
			</div>

			<div class="mySlides fade">
				<img
					src="${pageContext.request.contextPath}/resources/img/Slide3.jpg"
					style="width: 100%;">
				<div class="text">그린 도서관</div>
			</div>
		</div>
		<br>

		<div class="abc" style="text-align: center">
			<span class="dot"></span> <span class="dot"></span> <span class="dot"></span>
		</div>
		<div class="abcd">
			<table class="ttable2">
			<c:forEach var="b" items="${books}">
			<tr>
				<th class="tthh"><a href="<c:url value='/book/detail?bookNum=${b.bookNum}'/>"><img class="mainpageimg" alt="사진" src="${b.coverImg}"></a></th>
				<th class="ttss">${b.content}</th>
			</tr>
			</c:forEach>
			</table>
		</div>
	</main>
	<%@ include file="aside.jsp"%>
	<script
		src="${pageContext.request.contextPath}/resources/js/mainpage.js"></script>
</body>
</html>
