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
	href="${pageContext.request.contextPath}/resources/css/library.css?=ver3">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>도서 상세 정보 페이지</title>
</head>
<!--원하는 도서 클릭시 <도서 상세 정보 페이지> 이동-->
<body class="mainbody">
	<header class="mainheader">
		<div class="bg-image"></div>
		<div class="bg-text">
			<h2>책과 지식이 있는곳</h2>
			<h1 style="font-size: 50px">그린 도서관</h1>
			<form id="searchForm"
				action="<%=request.getContextPath()%>/search/book" method="get">
				<div class="dborder">
					<label for="SearchForBooks" class="label1">도서 검색 <input
						class="ipty" type="radio" id="SearchForBooks" name="searchFor"
						checked="checked" value="book">
					</label> <label for="Author" class="label2">저자 검색 <input
						class="ipty" type="radio" id="Author" name="searchFor"
						value="author">
					</label>

					<div class="search-flex">
						<input class="hdinput" type="text" name="search">
						<button class="hdbutton" type="submit">
							<i class="fa fa-search"></i>
						</button>
					</div>
				</div>
			</form>
		</div>
	</header>
	<main>
		<div class="Cdiv">
			<div class="CAdiv">
				<div class="CCdiv">
					<img class="bdppageimg" alt="책" src="${coverImg}">
				</div>
				<!--이곳에 책사진을 두는곳-->
				<div class="CEdiv">
					<p>${book.bookNum}</p>
					<!--도서번호-->
					<p>${book.title}</p>
					<!--제목-->
					<p>${book.author}</p>
					<!--저자-->
					<p>${book.company}</p>
					<!--출판사-->
					<c:choose>
						<c:when test="${book.condition==1 or book.condition eq '1'}">
							<!-- 도서 대출 가능 -->
							<p>대출가능</p>
							<!--도서현황-->
						</c:when>
						<c:when test="${book.condition==0 or book.condition eq '0'}">
							<!-- 도서 대출 불가 -->
							<p>대출불가</p>
							<!--도서현황-->
							<c:if test="${empty bookingbook}">
								<input type="button" value="예약하기"
									onclick="location.replace('../reservation?bookNum=${book.bookNum}')"
									onclick="alert('예약이 완료되었습니다.')">
								<c:if test="${! empty msg}">
									<p>${msg}</p>
								</c:if>
								<!--도서현황-->
							</c:if>
							<c:if test="${!empty bookingbook}">
								<input type="button" disabled="disabled" value="예약하기">
								<!--도서현황-->
							</c:if>
						</c:when>
						<c:otherwise>
							<p>대출 현황 파악 불가</p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="CBdiv">
				<p>${book.content}</p>
				<!--이곳에 해당 도서의 상세 내용 설명-->
			</div>
		</div>
	</main>
	<%@ include file="../aside.jsp"%>
</body>
</html>