<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 입력 폼</title>
</head>
<body>
	<main>
		<h3>게시판 입력 화면</h3>
		<form id="insert_form" action="/jpaboard/insert" method="post">
			<div>제목: <input type="text" name="title"></div>
			<div>작성자: <input type="text" name="author"></div>
			<div>내용: <textarea name="contents" rows="6" cols="20"></textarea></div>
			<div>
				<button type="reset">취소</button>
				<button type="submit">제출</button>
			</div>
		</form>
	</main>
</body>
</html>