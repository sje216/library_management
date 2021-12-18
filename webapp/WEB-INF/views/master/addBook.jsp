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
    <title>도서 추가 페이지</title>
</head>
<body class="Abody">
    <div class="Adiv">
        <div class="Acontainer">
            <h1>도서 추가 페이지</h1>
            <form action="addbook" method="post" enctype="multipart/form-data">
              <label for="fname">제목</label>
              <input class="ATinput" type="text" id="fname" name="title" placeholder="제목">
              <label for="lname">저자</label>
              <input class="ATinput" type="text" id="lname" name="author" placeholder="저자">
              <label for="lname">출판사</label>
              <input class="ATinput" type="text" id="lname" name="company" placeholder="출판사">
			  <label for="lname">출판사</label>
              <input class="ATinput" type="text" id="lname" name="condition" placeholder="대출 가능하면 1 대출불가면 0을 입력해주세요.">
              <label for="lname">도서 이미지</label>
              <input class="AFinput" type="file" id="lname" name="coverImg" placeholder="도서 이미지" style="resize: none;">
              <label for="subject">도서 내용</label>
              <textarea class="Atextarea" rows="20" cols="20" id="subject" name="content" placeholder="도서 내용"  style="resize: none;" style="width: 500px;" ></textarea>
              <input class="ASinput" type="submit" value="추가">
            </form>
          </div>          
    </div>
</body>
</html>