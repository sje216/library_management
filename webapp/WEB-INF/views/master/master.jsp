<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/library.css?=ver3">
<title>관리자 페이지</title>
</head>
<body class="Bbody">
	<div class="Bdiv">
		<div class="Bdiv_1">
			<h1>관리자 페이지</h1>
			<div class="Bdiv2">
				<a href="<c:url value='/admin/member'/>" class="Bbutton">회원 정보 관리(수정,삭제)</a> 
				<a href="<c:url value='/admin_book/bookinfo'/>" class="Bbutton">도서 정보 관리(추가,수정,삭제)</a>
				<a href="<c:url value='/'/>" class="Bbutton">홈으로 가기</a>
				<a href="<c:url value='/logout'/>" class="Bbutton Bbutton1">로그아웃</a>
			</div>
		</div>
	</div>
</body>
</html>