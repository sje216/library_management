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
	href="${pageContext.request.contextPath}/resources/css/library.css?=ver2">
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
					<div class="FGdiv">희망 도서 신청현황</div>
				</div>
				<table class="Bhopelist" border="1">
					<thead>
						<tr class=BTT>
							<th class="BTHTT">게시판 번호</th>
							<th class="BTHTT">이름</th>
							<th class="BTHTT">아이디</th>
							<th class="BTHTT">제목</th>
							<th class="BTHTT">날짜</th>
							<th class="BTHTT"></th>
						</tr>
						<c:forEach var="booking" items="${booking}">
							<c:forEach var="member" items="${member}">
								<c:forEach var="book" items="${book}">
									<th>${booking.bookingNum}</th>
									<th>${member.name}</th>
									<th>${member.id}</th>
									<th>${book.title}</th>
									<th>${booking.bookingDate}</th>
									<th><a href="deleteBookingList?bookingNum=${booking.bookingNum}">삭제</a></th>
								</c:forEach>
							</c:forEach>
						</c:forEach>

					</thead>
				</table>
			</div>
		</div>
	</main>
	<div class="page">
		<c:if test="${fullPage != null}">
			<c:choose>
				<c:when test="${fullPage > 100}">
					<c:if test="${(section)*100 < fullPage}">
						<!-- >>(O) -->
						<!--  1 2 3 ~~ 9 10 -->
						<c:forEach var="page" begin="1" end="10" step="1">
							<!--  pre 11 12 13 14 15~~~19 20  next -->
							<c:if test="${section > 1 && page == 1}">
								<a
									href="<c:url value='/bookinglist?section=${section-1}&pageNum=${10}'/>">
									pre </a>
							</c:if>
							<a
								href="<c:url value='/bookinglist?section=${section}&pageNum=${page}'/>">${(section-1)*10+page}
							</a>
							<c:if test="${page == 10}">
								<a
									href="<c:url value='/bookinglist?section=${section+1}&pageNum=${1}'/>">next
								</a>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${(section)*100 > fullPage}">
						<!-- >>(X)-->
						<!--764 761~764 //8번섹션 7번페이지       -->
						<!-- 764+9  (773 - 700) => 73/10 => 7  -->
						<c:forEach var="page" begin="1"
							end="${((fullPage+9)-((section-1)*100))/10}" step="1">
							<c:if test="${section > 1 && page == 1}">
								<a
									href="<c:url value='/bookinglist?section=${section-1}&pageNum=${10}'/>">
									pre </a>
							</c:if>
							<a
								href="<c:url value='/bookinglist?section=${section}&pageNum=${page}'/>">${(section-1)*10+page}
							</a>
						</c:forEach>
					</c:if>
				</c:when>
				<c:when test="${fullPage == 100}">
					<!-- 1 2 3 4 5 6 7 8 9 10 -->
					<c:forEach var="page" begin="1" end="10" step="1">
						<a
							href="<c:url value='/bookinglist?section=${section}&pageNum=${page}'/>">${page}</a>
					</c:forEach>
				</c:when>
				<c:when test="${fullPage < 100}">
					<!-- 1 2 3 4 5 6 7 -->
					<c:forEach var="page" begin="1" end="${(fullPage+9)/10}" step="1">
						<a
							href="<c:url value='/bookinglist?section=${section}&pageNum=${page}'/>">${page}</a>
					</c:forEach>
				</c:when>
			</c:choose>
		</c:if>
	</div>
	<%@ include file="../aside.jsp"%>
</body>
</html>