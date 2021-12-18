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
<title>관리자 회원 정보 수정 페이지</title>
<!--관리자 회원이 개인정보를 확인,수정 하고 싶을때 확인하는곳-->
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
		<div class="EAdiv">
			<h2>관리자 정보</h2>
			<form:form commandName="updateCommand" action="changeMaster" method="post">
				<p>회원 번호</p>
				<input class="ATinput" type="name" name="masterNum" value="${master.masterNum}" readonly="readonly">
				<p>아이디</p>
				<input class="ATinput" type="name" name="id" value="${master.id}" readonly="readonly">
				<p>이름</p>
				<form:input class="ATinput"  path="name" value="${master.name}"/>
				<p>비밀번호</p>
				<form:password class="ATinput"  path="pw"/>
				<p>생년월일</p>
				<fmt:formatDate value="${master.birthday}" pattern="yyyyMMdd" var="birth" />
				<form:input class="ATinput" path="birthday" value="${birth}" />
				<p>이메일</p>
				<form:input class="ATinput" path="email" value="${master.email}"/>
				<p>전화번호</p>
				<form:input class="ATinput" path="call" value="${master.call}"/>
				
				<hr>
				<input class="ASinput" type="submit" value="수정하기" onclick="alert('수정이 완료되었습니다.')">
				<input class="ASinput ASinputred" type="reset" value="취소하기">
				<input class="ASinput ASinputred" type="button" value="탈퇴하기" onclick="secession()">
				<%-- <a href="<c:url value='/secession'/>" onclick="secession()">탈퇴하기</a> --%>
			</form:form>
		</div>
	</main>
	<script type="text/javascript">
	var testV =1;
		 function secession() {
			var pw = prompt("※ 탈퇴한 뒤에는 데이터를 복구할 수 없으니 신중히 진행해 주시기 바랍니다.정말 탈퇴하시겠습니까?(비밀번호를 입력해주세요)","");
			while(testV<3){
				if(${master.pw}!=pw){
					alert('비밀번호가 틀립니다.');
					history.go(0);
					break;
				}
				if(${master.pw}==pw){
					alert('탈퇴가 완료되었습니다.');
					window.location.replace('secession_master');
					break;
				}
				testV+=1;
			}
		} 
	</script>
	<%@ include file="../aside.jsp"%>
</body>
</html>