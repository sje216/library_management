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
<title>계정 관리 페이지</title>
</head>
<body class="mainbody">
	<header class="mainheader">
		<div class="bg-image"></div>
		<div class="bg-text">
			<h2>책과 지식이 있는곳</h2>
			<h1 style="font-size: 50px">그린 도서관</h1>
		</div>
		</div>
	</header>
	<main>
		<div id="findinganaccount" class="Tlogin">
			<!--계정찾기-->
			<div>
				<form:form action="findId" method="get">
					<h2>아이디 찾기</h2>
					<!--아이디 찾기-->
					<p>성함</p>
					<input class="ATinput" type="text" placeholder="성함" name="name">
					<p>이메일</p>
					<input class="AEinput" type="email" placeholder="이메일" name="email">
					<hr style="border: white;">
					<input class="ASinput" type="submit" value="아이디 찾기">
					<hr>

				</form:form>
				<c:if test="${!empty member}">
					<p>${member.id}</p>
				</c:if>
				<c:if test="${empty member}">
					<p>해당되는 아이디가 없습니다.</p>
				</c:if>
				<a href="<c:url value='/findPwForm'/>">비밀번호 찾기</a>
			</div>
		</div>
	</main>
	<%@ include file="../aside.jsp"%>
</body>
</html>