<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세정보 화면</title>
<style type="text/css">
	main {width:fit-content; margin:0 auto;}
	main>h3 {text-align: center;}
	label {display:inline-block; width:5em; text-align:right; margin:0.3em 1em;}
	div {border-bottom: 1px solid black;}
	.links {margin:1em auto; text-align:center; border:none;}
	.links a {text-decoration: none; color:blue;}
</style>
</head>
<body>
	<main>
		<h3>상세정보 화면</h3>
		<div><label for="empno">사번</label> ${emp.empno}</div>
		<div><label for="ename">이름</label> ${emp.ename}</div>
		<div><label for="job">업무</label> ${emp.job}</div>
		<div><label for="mgr">mgr</label> ${emp.mgr}</div>
		<div><label for="hiredate">입사일</label> ${emp.hiredate}</div>
		<div><label for="sal">급여</label> ${emp.sal}</div>
		<div><label for="comm">comm</label> ${emp.comm}</div>
		<div><label for="deptno">부서번호</label> ${emp.deptno}</div>
		<div class="links"><a href="/jdbc/emp/edit?empno=${emp.empno}">수정</a></div>
	</main>
</body>
</html>