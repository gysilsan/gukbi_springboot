<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세정보 화면</title>
</head>
<body>
	<main>
		<h3>상세정보 화면</h3>
		<div>이름: ${emp.empno}</div>
		<div>사번: ${emp.ename}</div>
		<div>업무: ${emp.job}</div>
		<div>mgr: ${emp.mgr}</div>
		<div>입사일: ${emp.hiredate}</div>
		<div>급여: ${emp.sal}</div>
		<div>comm: ${emp.comm}</div>
		<div>부서번호: ${emp.deptno}</div>
		<div><a href="jdbc/emp/update?empno=${emp.empno}">수정</a></div>
	</main>
</body>
</html>