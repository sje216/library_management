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
<title>도서 수정 페이지</title>
</head>
<body class="Abody">
	<div class="Adiv">
		<div class="Acontainer">
			<h1>도서 수정 페이지</h1>
			<form:form action="book_modify" commandName="updatecommand" method="POST">
			<label for="fname">책 번호</label> 
				<input type="text" id="fname" class="ATinput" name="bookNum" value="${book.bookNum}">
				<label for="fname">제목</label> 
				<form:input id="fname" class="ATinput" path="title" value="${book.title}"/>
				<label for="lname">저자</label> 
				<form:input id="lname" class="ATinput" path="author" value="${book.author}"/>
				<label for="lname">출판사</label> 
				<form:input id="lname" class="ATinput" path="company" value="${book.company}"/>
				<label for="lname">대출 여부(1이면 대출 가능/0이면 대출 불가)</label> 
				<form:input id="lname" class="ATinput" path="condition" value="${book.condition}"/>
				<label for="lname">도서 이미지</label> 
				<img id="lname" class="AFinput" alt="이미지" src="${coverImg}" style="width: 200px;height: 300px;">
				<form:hidden  path="coverImg" value="${book.coverImg}" />
				<br>
				<label for="subject">도서 내용</label>
				<br>
				<form:textarea path="content" placeholder="${book.content}" value="${book.content}" id="subject" class="Atextarea" rows="20" cols="20" style="resize: none;width: 500px;"/>
				<input class="ASinput" type="submit" value="수정"> 
				<a style="background-color: red" href="<c:url value='/admin_book/book_delete?bookNum=${book.bookNum}'/>" class="ASinput">삭제</a>
			</form:form>
		</div>
	</div>

</body>
</html>