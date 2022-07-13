<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DEPT Test Form</title>
</head>
<body>
	<main>
		<h3>DEPT 테스트용 폼</h3>
		<form id="sampleForm" method="post" action="/mybatis/dept/multi_deptno">
			<select name="deptno" multiple>
				<option>10</option>
				<option>20</option>
				<option>30</option>
				<option>40</option>
			</select>
			<div>
				<button type="reset">취소</button>
				<button type="submit">전송</button>
			</div>
		</form>
	</main>
</body>
</html>