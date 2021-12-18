<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<aside>
    <table class="table1">
    <c:if test="${empty memberinfo and empty masterinfo}">
        <tr><th class="att"><a href="<c:url value='/'/>">메인화면</a></th></tr>
    	<tr><th class="att"><a href="<c:url value='/regist/regist'/>">회원가입</a></th></tr>
        <tr><th class="att"><a href="<c:url value='/findIdPw'/>">아이디 찾기 /비밀번호 찾기</a></th></tr>
        <tr><th class="att"><a href="<c:url value='/login'/>">로그인</a></th></tr>
     	<c:if test="${empty hopeBook}">
        <tr><th class="att"><a href="<c:url value='/hopebook'/>" onclick="alert('로그인을 하셔야 신청할 수 있습니다.')">희망도서</a></th></tr>
     	</c:if>
     	<c:if test="${!empty hopeBook}">
        <tr><th class="att"><a href="<c:url value='/hopebook'/>">희망도서</a></th></tr>
     	</c:if>
    </c:if>
    <c:if test="${!empty memberinfo or !empty masterinfo}">
    	<c:if test="${!empty memberinfo}">
    	<tr><th class="att"><p>${memberinfo.name}님(${memberinfo.id}) </p></th></tr>
        <tr><th class="att"><a href="<c:url value='/memberinfo'/>">회원정보</a></th></tr>
        <tr><th class="att"><a href="<c:url value='/hopebook'/>">희망도서</a></th></tr>
        <tr><th class="att"><a href="<c:url value='/reserve'/>">예약도서</a></th></tr>
    	</c:if>
    	<c:if test="${!empty masterinfo}">
    	<tr><th class="att"><p>${masterinfo.name}님(${masterinfo.id}) </p></th></tr>
        <tr><th class="att"><a href="<c:url value='/masterHome'/>">관리자홈</a></th></tr>
        <tr><th class="att"><a href="<c:url value='/masterinfo'/>">관리자정보</a></th></tr>
        <tr><th class="att"><a href="<c:url value='/hopelist'/>">희망도서</a></th></tr>
       <%--  <tr><th class="att"><a href="<c:url value='/bookinglist'/>">예약도서</a></th></tr> --%>
    	</c:if>
        <tr><th class="att"><a href="<c:url value='/'/>">메인화면</a></th></tr>
        <tr><th class="att"><a href="<c:url value='/logout'/>">로그아웃</a></th></tr>
    </c:if>
    </table>
</aside>
