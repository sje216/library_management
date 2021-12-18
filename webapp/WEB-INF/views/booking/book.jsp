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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/library.css">
<title>희망 도서 신청 페이지</title>
</head>
<body class="mainbody">
	<header class="mainheader">
		<div class="bg-image"></div>
		<div class="bg-text">
			<h2>책과 지식이 있는곳</h2>
			<h1 style="font-size: 50px">그린 도서관</h1>
		</div>
	</header>
	<main>
		<div class="FAdiv">
			<div class="FCdiv">
				<h3>희망 도서 리스트</h3>
				<!--희망 도서 신청후 현황 등록-->
				<div class="FFdiv">
					<div class="FGdiv">예약 도서 현황</div>
				</div>
									<p>${member.name}[${member.id}]님이예약한도서입니다.</p>
					<c:if test="${!empty booking}">
						<c:forEach var="booking" items="${booking}" varStatus="n">
							<c:forEach var="book" items="${book}">
								<c:if test="${booking.bookNum eq book.bookNum}">
								<div class="FDdiv" style="height: auto;">
									<p>${book.title}</p>
									<p>${book.content}</p>
									<a style="background-color: red;text-decoration: none;color: #fff" href="<c:url value='/deletebooking?bookingNum=${booking.bookingNum}'/>">삭제</a>
								</div>
							</c:if>
							</c:forEach>
						</c:forEach>

						<hr>
					</c:if>
				<c:if test="${empty booking}">
					<div class="FDdiv">
						<p>예약한 도서가 없습니다.</p>
					</div>
				</c:if>
			</div>

		</div>
	</main>
	<%@ include file="../aside.jsp"%>
</body>
</html>