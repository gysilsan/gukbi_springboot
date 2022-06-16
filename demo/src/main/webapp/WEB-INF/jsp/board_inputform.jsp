<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>폼</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<form id="form" method="post" action="/board/res">
		<div>제목<input type="text" name="title"></div>
		<div><textarea name="contents" rows="5" cols="20" placeholder="내용.."></textarea></div>
		<button type="reset">취소</button>
		<button type="submit">제출</button>
	</form>
</body>
</html>