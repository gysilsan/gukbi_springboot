<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록 페이지</title>
<style type="text/css">
	main{width:fit-content; margin:0 auto;}
	main>h3 {text-align: center;}
	table, td, tr, th {border:1px solid black; width:fit-content;}
	table {border-spacing:0; border-collapse:collapse;}
	td, th{padding: 0.5em;}
	a {text-decoration:none; color:blue;}
	.links, .pages {width:fit-content; margin:0 auto;}
	label{display:inline-block; margin:0 1em;}
	#searchBox {width: 9em;}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
</script>
</head>
<body>
<main>
	<h3>게시물 목록</h3>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<c:forEach items="${pageInfo.list}" var="board">
			<tr>
				<td>${board.num}</td>
				<td><a href="/mybatis/board/detail/${board.num}">${board.title}</a></td>
				<td>${board.author}</td>
				<td>${board.wdate}</td>
			</tr>
		</c:forEach>
	</table>
	<div class="links">
		[<a href="/mybatis/board/add_form">글쓰기</a>]
	</div>
	<footer>
	<div>
		<form id="search_form" method="post" action="/mybatis/board/search">
			<select name="category">
				<option value="title" >제목으로 검색
				<option value="author">작성자로 검색
			</select>
				<label for="searchBox">검색어</label><input type="text" name="key" id="searchBox">
				<button type="submit">검색</button>
		</form>
	</div>
	</footer>
	<div class="pages">	
		<c:forEach var="i" items="${pageInfo.navigatepageNums}">
			<c:choose>
				<c:when test="${i==pageInfo.pageNum}">
					[${i}]
				</c:when>
				<c:otherwise>
					[<a href="/mybatis/board/list/page/${i}">${i}</a>]
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
</main>
</body>
</html>