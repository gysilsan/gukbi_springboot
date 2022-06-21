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
</style>
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
		<c:forEach items="${list}" var="board">
			<tr>
				<td>${board.num}</td>
				<td><a href="/jdbcboard/detail/${board.num}">${board.title}</a></td>
				<td>${board.author}</td>
				<td>${board.wdate}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<a href="/jdbcboard/add_form">글쓰기</a>
	</div>
</main>
</body>
</html>