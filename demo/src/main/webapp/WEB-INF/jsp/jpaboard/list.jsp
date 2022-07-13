<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
</head>
<body>
	<main>
		<h3>게시판 목록</h3>
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:forEach items="${list}" var="board">
				<tr>
					<td>${board.num}</td>
					<td><a href="/jpaboard/detail/${board.num}">${board.title}</a></td>
					<td>${board.author}</td>
					<td>${board.wdate}</td>
				</tr>
			</c:forEach>
		</table>
	</main>
</body>
</html>