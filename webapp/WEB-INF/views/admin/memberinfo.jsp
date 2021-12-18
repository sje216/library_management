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
<link href='https://fonts.googleapis.com/css?family=Alatsi'
	rel='stylesheet'>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/library.css?=ver3">
<title>회원 정보 관리 페이지</title>
</head>
<body class="Bbody">
	<div class="Bdiv">
		<div class="Bdiv_1">
			<form action="<%= request.getContextPath() %>/admin/update" method="GET">
				<table class="Btable">
					<caption class="Bcaption">회원 정보 관리 페이지</caption>
					<thead>
						<tr class="BTT">
							<th  style="width: 70px;">회원번호</th>
							<th class="BTHTT">이름</th>
							<th class="BTHTT">아이디</th>
							<th class="BTHTT">생일</th>
							<th class="BTHTT">이메일</th>
							<th class="BTHTT">전화번호</th>
						</tr>
					</thead>
					<tbody>
						<!-- <tr>
							<th><input type="radio" name="MemberNum" value="">123456789123456</th>
							<th>이종환</th>
							<th>leejonghwan</th>
							<th>11년11월11일</th>
							<th>llllllleeeeeee@naver.com</th>
							<th>010-1234-5678</th>
						</tr> -->
						<c:forEach var="m" items="${members}" varStatus="n">
							<tr>
								<th class="BTBTT"><input class="Binput" type="radio" name="memberNum"
									value="${m.memberNum}">${m.memberNum}</th>
								<th class="BTBTT">${m.name}</th>
								<th class="BTBTT">${m.id}</th>
								<th class="BTBTT"><fmt:formatDate value="${m.birthday}"
										pattern="yyyy-MM-dd" /></th>
								<th class="BTBTT">${m.email}</th>
								<th class="BTBTT">${m.call}</th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="Bdiv2">
					<input type="submit" class="Bbutton" value="수정">
					<a href="<c:url value='/masterHome'/>" class="Bbutton Bbutton1">메뉴</a>
					<a href="<c:url value='/logout'/>" class="Bbutton Bbutton1">로그아웃</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>