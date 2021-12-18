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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/library.css">
<title>관리자 회원 정보 수정 페이지</title>
</head>
<body class="Abody">
	<div class="Adiv">
		<div class="Acontainer">
			<h1>관리자 회원 정보 수정 페이지</h1>
			<%-- <form action="<%= request.getContextPath() %>/admin/update" method="post">
          <label for="fname">이름</label>
          <input type="text" id="fname" name="name" value="${member.name}" placeholder="${member.name}">

          <label for="fname">회원번호</label>
          <input type="number" id="fname" name="memberNum" placeholder="${member.memberNum}">

          <label for="fname">아이디</label>
          <input type="text" id="fname" name="id" placeholder="${member.id}">

          <label for="fname">비밀번호</label>
          <input type="text" id="fname" name="pw" placeholder="${member.pw}">

          <label for="fname">이메일</label>
          <input type="email" id="fname" name="email" placeholder="${member.email}">

          <label for="fname">전화번호</label>
          <input type="number" id="fname" name="call" placeholder="${member.call}">
          
          <label for="fname">생년월일</label>
          <input type="date" id="fname" name="birthday" style="resize: none;" placeholder="<fmt:formatDate value='${member.birthday}' pattern='yyyy-MM-dd'/>">

          <input type="submit" value="수정">
        </form>  --%>
<%--         <form action="<%= request.getContextPath() %>/admin/update" method="post">
 --%>        <form:form action="update" commandName="updatemember" method="POST">
          <label for="fname">회원번호</label>
          <input class="ATinput" type="text" id="fname" name="memberNum" value="${member.memberNum}" placeholder="${member.memberNum}" readonly>
          <label for="fname">아이디</label>
          <input class="ATinput" type="text" id="fname" name="id" value="${member.id}" placeholder="${member.id}" readonly>
          <%-- <form:input path="memberNum"/> --%>
          <label for="fname">이름</label>
          <form:input class="ATinput" path="name"  value="${member.name}"/>
          <label for="fname">비밀번호</label>
          <form:input class="ATinput" path="pw" value="${member.pw}"/>
          <label for="fname">생년월일</label>
          <fmt:formatDate value="${member.birthday}" pattern="yyyyMMdd" var="birth"/>
          <form:input class="ADinput" path="birthday" value="${birth}"/>
          <label for="fname">이메일</label>
          <form:input class="AEinput" path="email"  value="${member.email}"/>
          <label for="fname">전화번호</label>
          <form:input class="ATinput" path="call"  value="${member.call}"/>
        
          <input class="Bbutton" type="submit" value="수정">
          <a href="<c:url value='/admin/delete?memberNum=${member.memberNum}'/>" class="Bbutton Bbutton1">삭제</a> 
        </form:form>
         <%--  <input type="text" id="fname" name="name" value="${member.name}" placeholder="${member.name}">

          <input type="number" id="fname" name="memberNum" placeholder="${member.memberNum}" readonly>

          <input type="text" id="fname" name="id" placeholder="${member.id}" readonly>

          <input type="text" id="fname" name="pw" placeholder="${member.pw}">

          <input type="email" id="fname" name="email" placeholder="${member.email}">

          <input type="number" id="fname" name="call" placeholder="${member.call}">
          
          <input type="date" id="fname" name="birthday" style="resize: none;" placeholder="<fmt:formatDate value='${member.birthday}' pattern='yyyy-MM-dd'/>">

        </form> --%>
		</div>
	</div>
</body>
</html>