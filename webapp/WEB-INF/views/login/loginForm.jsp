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

		<div id="login" class="Tlogin">
			<form:form action="loginMemberOrMaster" commandName="loginCommand"
				method="POST">
				<label>관리자 <input type="radio" name="masterOrMember"
					value="0">
				</label>
				<label>일반 회원 <input type="radio" name="masterOrMember"
					value="1" checked="checked">
				</label>
				<!--로그인-->
				<p>아이디</p>
				<form:input class="ATinput" path="id" />
				<form:errors path="id" />
				아이디 기억하기
				<form:checkbox path="rememberId" />
				<form:errors path="rememberId" />
				<p>비밀번호</p>
				<form:password class="ATinput" path="pw" />
				<form:errors path="pw" />
				<hr>
				<input class="ASinput" type="submit" value="로그인">
			</form:form>
		</div>

	</main>
	<%@ include file="../aside.jsp"%>
</body>
</html>