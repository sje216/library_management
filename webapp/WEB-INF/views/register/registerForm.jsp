<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
		<div id="joinmembership" class="TTlogin">
			<form:form action="register" commandName="reg" method="POST">
				<!--회원가입-->
				<label>관리자 <input type="radio" name="masterOrMember"
					value="0">
				</label>
				<label>일반 회원 <input type="radio" name="masterOrMember"
					value="1">
				</label>
				<p>성함</p>
				<form:input class="ATinput" path="name"/>
				<form:errors path="name"/>
				<p>ID</p>
				<form:input class="ATinput" path="id" />
				<form:errors path="id" />
				<p>P/W</p>
				<form:password class="ATinput" path="pw"/>
				<form:errors path="pw"/>
				<p>P/W 확인</p>
				<form:password class="ATinput" path="confirmPw"/>
				<form:errors path="confirmPw"/>
				<p>생일</p>
				<form:input class="ATinput" path="birthday"/>
				<form:errors path="birthday"/>
				<p>이메일</p>
				<form:input class="AEinput" path="email"/>
				<form:errors path="email"/>
				<p>전화번호</p>
				<form:input class="ATinput" path="call"/>
				<form:errors path="call"/>
				<hr>
				<input class="ASinput" type="submit" value="가입하기">
			</form:form>
		</div>
	</main>
	<%@ include file="../aside.jsp"%>
</body>
</html>