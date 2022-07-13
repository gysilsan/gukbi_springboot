<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실 목록보기</title>
<style>
	main {width:fit-contents; margin:0 auto;}
	h3 {text-align: center;}
	table, tr, td, th {border: 1px solid black; width:fit-content;}
	table {border-collapse: collapse; border-spacing: 0; margin:0 auto;}
	#num, #writer {text-align: center;}
	a {text-decoration: none; color:blue;}
</style>
</head>
<body>
	<main>
		<h3>자료실 목록보기</h3>
		<table>
			<tr>
				<th>글번호</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>설명</th>
				<th>파일명</th>
			</tr>
			<c:forEach items="${list}" var="vo">
				<tr>
					<td id="num"><a href="/files/detail/${vo.num}">${vo.num}</a></td>
					<td id="writer">${vo.writer}</td>
					<td>${vo.udate}</td>
					<td>${vo.description}</td>
					<td>
						<c:forEach var="aVo" items="${vo.attach}">
							${aVo.fname}
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</table>
	</main>
</body>
</html>