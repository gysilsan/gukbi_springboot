<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 보기</title>
<style>
	main{margin:0 auto; width:fit-content;}
	main>h3 {text-align: center;}
</style>
</head>
<body>
	<main>
		<h3>구구단 ${dan}단</h3>
		<c:forEach var="n" begin="1" end="9">
			<div>${dan} * ${n} = ${dan*n}</div>
		</c:forEach>
	</main>
</body>
</html>