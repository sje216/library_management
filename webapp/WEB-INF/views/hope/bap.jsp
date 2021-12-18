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
			<div class="FBdiv">
				<form action="regist_book">
					<h3>희망 도서 신청</h3>
					<!--일반 회원이 원하는 도서를 신청하는 곳-->
					<p>희망 도서 이름</p>
					<input type="text" name="title">
					<!--이곳에 희망도서 이름 적는곳-->
					<p>희망 도서 설명</p>
					<!--이곳에 희망도서 에 대한 설명 적는곳-->
					<textarea name="content" id="" cols="50px" rows="10px"></textarea>
					<hr style="border: black;">
					<input type="submit" onclick="alert('희망도서 신청이 완료되었습니다.')"
						value="신청"> <input type="reset" value="취소">
				</form>
			</div>
			<div class="FCdiv">
				<h3>희망 도서 리스트</h3>
				<!--희망 도서 신청후 현황 등록-->
				<div class="FFdiv">
					<div class="FGdiv">희망 도서 신청현황</div>
				</div>
				<c:if test="${!empty hopebooks}">
					<c:forEach var="books" items="${hopebooks}" varStatus="n">
						<div class="FDdiv" style="height: auto;">
							<form action="deleteHopeBook">
								<input type="hidden" name="num" value="${books.num}">
								<p>희망 도서 이름 : ${books.title}</p>
								<p>
									희망 도서 신청 날짜 :
									<fmt:formatDate value="${books.datetime}" pattern="yyyy-MM-dd" />
								</p>
								<p>희망 도서 설명:</p>
								<p>${books.content}</p>
								<input type="submit" value="삭제">
							</form>
							<button
								onclick="location.replace('updateHopeBookF?num=${books.num}')">수정</button>
						</div>
							<c:if test="${empty ans}">
								<div class="FDdiv" style="height: auto;">
									<p>답변 : 아직 답변이 없습니다.</p>
								</div>
							</c:if>
						<c:if test="${!empty ans}">
						<c:set var="loop_flag" value="false" />
							<c:forEach var="a" items="${ans}" varStatus="n">
								<c:if test="${a.num == books.num}">
									<div class="FDdiv" style="height: auto;">
										<p>답변 : ${a.answer}</p>
									</div>
									 <c:set var="loop_flag" value="true" />
								</c:if>
								
								<c:if test="${a.num != books.num}">
									<div class="FDdiv" style="height: auto;">
									</div>
									 <c:set var="loop_flag" value="true" />
								</c:if>
							</c:forEach>
						</c:if>
						<hr>

					</c:forEach>
				</c:if>
				<c:if test="${empty hopebooks}">
					<div class="FDdiv">
						<p>희망 도서 이름</p>
						<!--요구한 희망도서 이름-->
						<hr>
						<p>희망 도서 설명</p>
						<!--요구한 희망도서 설명-->
					</div>
				</c:if>
			</div>

		</div>
	</main>
	<%@ include file="../aside.jsp"%>
</body>
</html>