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
				<form action="updateHopeBook">
				<input type="hidden" name="num" value="${book.num}">
					<h3>희망 도서 신청 내용 수정</h3>
					<!--일반 회원이 원하는 도서를 신청하는 곳-->
					<p>희망 도서 이름</p>
					<input type="text" name="title" value="${book.title}">
					<!--이곳에 희망도서 이름 적는곳-->
					<p>희망 도서 설명</p>
					<!--이곳에 희망도서 에 대한 설명 적는곳-->
					<textarea name="content" id="" cols="50px" rows="10px">${book.content}</textarea>
					<hr style="border: black;">
					<input type="submit" onclick="alert('희망도서 수정이 완료되었습니다.')" value="수정">
					<input type="reset" value="취소">
				</form>
			</div>

		</div>
	</main>
<%@ include file="../aside.jsp"%>
</body>
</html>