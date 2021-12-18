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
				<div class="FDdiv" style="height: auto;">
					<form action="deleteHopebook">
						<input type="hidden" name="num" value="${hopes.num}">
						<p>희망 도서 작성자 : ${hopes.id}</p>
						<p>희망 도서 이름 : ${hopes.title}</p>
						<p>
							희망 도서 신청 날짜 :
							<fmt:formatDate value="${hopes.datetime}" pattern="yyyy-MM-dd" />
						</p>
						<p>희망 도서 설명:</p>
						<p>${hopes.content}</p>
						<input type="submit" value="삭제">
					</form>
				</div>
				<hr>
				<div class="FDdiv" style="height: auto;">
					<form:form action="add_answer" commandName="answerCommand">
						<form:hidden path="num" value="${hopes.num}"/>
						<p>답변 쓰기</p>
						<hr>
						<form:input path="answer"/> 
						<input type="submit" value="등록">
					</form:form>
				</div>
				<hr>
				<div class="FDdiv" style="height: auto;">
				<c:if test="${empty ans}">
					<p>답변 : 아직 답변이 없습니다.</p>
				</c:if>
				<c:if test="${!empty ans}">
				<form action="delete_answer">
					<input type="hidden" name="num" value="${ans.num}">
					<input type="hidden" name="ano" value="${ans.ano}">
					<p>답변 : ${ans.answer}</p>
					<input type="submit" value="삭제">
				</form>
				<button onclick="location.replace('updateAnsF?ano=${ans.ano}')">수정</button>
				</c:if>
				</div>

			</div>
		</div>
	</main>
	<%@ include file="../aside.jsp"%>
</body>
</html>