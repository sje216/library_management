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
	href="${pageContext.request.contextPath}/resources/css/library.css?=ver4">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>도서 정보 검색 페이지</title>
</head>
<!--검색창에 검색후 <도서 정보 검색 페이지> 연결-->
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
		<div class="Babcd">
			<fieldset class="Bfieldset">
				<ol class="Bui">
					<c:if test="${!empty books}">
						<c:forEach var="b" items="${books}" varStatus="n">
							<li class="Bli">
								<div class="Bbook">
									<a href="<c:url value='/book/detail?bookNum=${b.bookNum}'/>">
										<!--이곳에 해당<도서 상세 정보 페이지>로 이동할 링크--> <img class="bisppageimg"
										alt="책" src="${b.coverImg}">
									</a>
									<!--이곳에 사진 -->
								</div>
								<div class="Bcontent">
									<p>${b.title}</p>
									<!--이곳에 도서 이름-->
									<p>${b.author}</p>
									<!--이곳에 작가 이름-->
									<p>${b.company}</p>
									<!--이곳에 출판사-->
								</div>
							</li>
						</c:forEach>
					</c:if>
					<c:if test="${empty books}">
							<h1 class="Th1">자료가 없습니다.</h1>
					</c:if>
			</fieldset>
		</div>
	</main>
	<div class="Bpage">
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
									href="<c:url value='/search/book?searchFor=${searchFor}&search=${search}&section=${section-1}&pageNum=${10}'/>">
									pre </a>
							</c:if>
							<a
								href="<c:url value='/search/book?searchFor=${searchFor}&search=${search}&section=${section}&pageNum=${page}'/>">${(section-1)*10+page}
							</a>
							<c:if test="${page == 10}">
								<a
									href="<c:url value='/search/book?searchFor=${searchFor}&search=${search}&section=${section+1}&pageNum=${1}'/>">next
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
									href="<c:url value='/search/book?searchFor=${searchFor}&search=${search}&section=${section-1}&pageNum=${10}'/>">
									pre </a>
							</c:if>
							<a
								href="<c:url value='/search/book?searchFor=${searchFor}&search=${search}&section=${section}&pageNum=${page}'/>">${(section-1)*10+page}
							</a>
						</c:forEach>
					</c:if>
				</c:when>
				<c:when test="${fullPage == 100}">
					<!-- 1 2 3 4 5 6 7 8 9 10 -->
					<c:forEach var="page" begin="1" end="10" step="1">
						<a
							href="<c:url value='/search/book?searchFor=${searchFor}&search=${search}&section=${section}&pageNum=${page}'/>">${page}</a>
					</c:forEach>
				</c:when>
				<c:when test="${fullPage < 100}">
					<!-- 1 2 3 4 5 6 7 -->
					<c:forEach var="page" begin="1" end="${(fullPage+9)/10}" step="1">
						<a
							href="<c:url value='/search/book?searchFor=${searchFor}&search=${search}&section=${section}&pageNum=${page}'/>">${page}</a>
					</c:forEach>
				</c:when>
			</c:choose>
		</c:if>
	</div>
	<%@ include file="../aside.jsp"%>
</body>
</html>