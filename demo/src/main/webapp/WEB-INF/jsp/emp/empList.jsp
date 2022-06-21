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
	table, td, tr, th {border:1px solid black; width:fit-content; text-align:center;}
	table {border-spacing:0; border-collapse:collapse;}
	td, th{padding: 0.5em;}
	#sal {text-align:right;}
</style>
</head>
<body>
<main>
	<h3>사원 목록</h3>
	<table>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>입사</th>
			<th>부서</th>
			<th>급여</th>
		</tr>
		<c:forEach items="${list}" var="emp">
			<tr>
				<td>${emp.empno}</td>
				<td><a href="/jdbc/emp/one/${emp.empno}">${emp.ename}</a></td>
				<td>${emp.hiredate}</td>
				<td>${emp.deptno}</td>
				<td id="sal">${emp.sal}</td>
			</tr>
		</c:forEach>
	</table>
</main>
</body>
</html>