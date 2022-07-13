<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색결과 페이지</title>
<style type="text/css">
	main{width:fit-content; margin:0 auto;}
	main>h3 {text-align: center;}
	table, td, tr, th {border:1px solid black; width:fit-content;}
	table {border-spacing:0; border-collapse:collapse;}
	td, th{padding: 0.5em;}
	a {text-decoration:none; color:blue;}
	footer {width: fit-content; margin:0 auto;}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
function search(page) {
	$('#page').val(page);
	$('#search_form').submit();
}
</script>
</head>
<body>
<main>
	<h3>검색결과 게시물 목록</h3>
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
	<div>
		[<a href="/mybatis/board/list/page/1">목록보기</a>]
	</div>
	<footer>
	<form id="search_form" method="post" action="/mybatis/board/search">
		<input type="hidden" name="psize" id="psize" value="5">
		<input type="hidden" id="page" name="page" value="1">
		<div>
			<label for="category">검색항목</label>
		<select name="category">
			<option value="title" <c:if test="${category=='title'}"> selected</c:if>>글제목</option>
			<option value="author" <c:if test="${category=='author'}"> selected</c:if>>작성자</option>
		</select>
		</div>
		<div id="btn">
			<label for="searchBox">검색어</label><input type="text" name="key" id="searchBox" value="${keyword}">
			<button type="reset">취소</button>
			<button type="submit">검색</button>
		</div>
	</form>
	</footer>
	<div class="pages">	
		<c:forEach var="i" items="${pageInfo.navigatepageNums}">
			<c:choose>
				<c:when test="${i==pageInfo.pageNum}">
					[${i}]
				</c:when>
				<c:otherwise>
					[<a href="javascript:search(${i});">${i}</a>]
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
</main>
</body>
</html>