<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 보기</title>
</head>
<body>
	<h3>구구단 보기</h3>
	<%
		for(int i=2; i<=9; i++) {
	%>
		---------------<br>
		[구구단 <%=i %>단]<br>
	<%
			for(int j=1; j<=9; j++) {
	%>
		<%=i%> * <%=j%> = <%=i*j%><br>
	<%
		}
			}
	%>
</body>
</html>