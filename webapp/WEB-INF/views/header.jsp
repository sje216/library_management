<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body class="mainbody">
<header class="mainheader">
		<div class="bg-image"></div>
		<div class="bg-text">
			<h2>책과 지식이 있는곳</h2>
			<h1 style="font-size: 50px">그린 도서관</h1>
				<form id="searchForm" action="<%= request.getContextPath() %>/search/book" method="get">
			<div class="dborder">
					<label for="SearchForBooks" class="label1">도서 검색 <input
						class="ipty" type="radio" id="SearchForBooks"
						name="searchFor" value="book" checked="checked">
					</label> <label for="Author" class="label2">저자 검색 <input
						class="ipty" type="radio" id="Author" name="searchFor" value="author">
					</label>

					<div class="search-flex">
						<input class="hdinput" type="text" id="search" name="search">
						<button class="hdbutton" type="submit" id="submit">
							<i class="fa fa-search"></i>
						</button>
					</divx>
			</div>
				</form>
		</div>
	</header>
</body>