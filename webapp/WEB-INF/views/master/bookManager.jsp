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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/library.css?=ver3">
<title>도서 정보 관리 페이지</title>
</head>
<body class="Bbody">
	<form action="book_modifyForm">
		<div class="Bdiv Bdivdiv">
			<div class="Bdiv_1">
				<table class="Btable">
					<caption class="Bcaption">도서 정보 관리 페이지</caption>
					<thead>
						<tr class="BTT">
							<th class="BTHTT" style="width: 70px;">도서번호</th>
							<th class="BTHTT">제목</th>
							<th class="BTHTT">저자</th>
							<th class="BTHTT">출판사</th>
							<th class="BTHTT">대출여부</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="b" items="${books}" varStatus="n">
							<tr>
								<th class="BTBTT"><input class="Binput" type="radio" name="bookNum" value="${b.bookNum}">${b.bookNum}</th>
								<th class="BTBTT">${b.title}</th>
								<th class="BTBTT">${b.author}</th>
								<th class="BTBTT">${b.company}</th>
								<th class="BTBTT"><c:choose>
										<c:when test="${b.condition==1 or b.condition eq '1'}">
											<!-- 도서 대출 가능 -->
											<a href="<c:url value=''/>">대출가능</a>
											<!--도서현황-->
										</c:when>
										<c:when test="${b.condition==0 or b.condition eq '0'}">
											<!-- 도서 대출 불가 -->
											<p>대출불가</p>
											<!--도서현황-->
										</c:when>
										<c:otherwise>
											<p>대출 현황 파악 불가</p>
										</c:otherwise>
									</c:choose></th>
							</tr>
						</c:forEach>
						<!-- <tr>
                <th><input type="radio" name="MembershipNumber" value="">123456789</th>
                <th>어린왕자</th>
                <th>생텍쥐페리</th>
                <th>그린출판사</th>
                <th>10</th>
            </tr> -->
					</tbody>
				</table>
				<div class="Cpage">
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
												href="<c:url value='/admin_book/bookinfo?section=${section-1}&pageNum=${10}'/>">
												pre </a>
										</c:if>
										<a
											href="<c:url value='/admin_book/bookinfo?section=${section}&pageNum=${page}'/>">${(section-1)*10+page}
										</a>
										<c:if test="${page == 10}">
											<a
												href="<c:url value='/admin_book/bookinfo?section=${section+1}&pageNum=${1}'/>">next
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
												href="<c:url value='/admin_book/bookinfo?section=${section-1}&pageNum=${10}'/>">
												pre </a>
										</c:if>
										<a
											href="<c:url value='/admin_book/bookinfo?section=${section}&pageNum=${page}'/>">${(section-1)*10+page}
										</a>
									</c:forEach>
								</c:if>
							</c:when>
							<c:when test="${fullPage == 100}">
								<!-- 1 2 3 4 5 6 7 8 9 10 -->
								<c:forEach var="page" begin="1" end="10" step="1">
									<a
										href="<c:url value='/admin_book/bookinfo?section=${section}&pageNum=${page}'/>">${page}</a>
								</c:forEach>
							</c:when>
							<c:when test="${fullPage < 100}">
								<!-- 1 2 3 4 5 6 7 -->
								<c:forEach var="page" begin="1" end="${(fullPage+9)/10}"
									step="1">
									<a
										href="<c:url value='/admin_book/bookinfo?section=${section}&pageNum=${page}'/>">${page}</a>
								</c:forEach>
							</c:when>
						</c:choose>
					</c:if>
				</div>
			</div>
		</div> 
		<div class="Bdiv2 Bdiv2div2">
			<input class="Bbutton" type="submit" value="수정">
			<a href="<c:url value='/admin_book/book_insert'/>" class="Bbutton">추가</a>
			 <!-- 매니저 홈 가기 -->
			 <a href="<c:url value='/masterHome'/>" class="Bbutton Bbutton1">메뉴</a>
			 <a href="<c:url value='/logout'/>"	class="Bbutton Bbutton1">로그아웃</a>
		</div>
	</form>
</body>
</html>