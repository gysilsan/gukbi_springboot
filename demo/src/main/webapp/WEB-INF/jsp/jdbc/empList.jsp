<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록 페이지</title>
</head>
<body>
	<table>
		<tr>
			<th>사번</th>
			<th>이름</th>
			<th>입사일</th>
			<th>급여</th>
			<th>부서</th>
		</tr>
		<c:forEach items="${list}" var="emp">
			<tr>
				<td>${emp.empno}</td>
				<td><a href="/jdbc/emp/detail/${emp.empno}">${emp.ename}</a></td>
				<td>${emp.hiredate}</td>
				<td>${emp.sal}</td>
				<td>${emp.deptno}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>