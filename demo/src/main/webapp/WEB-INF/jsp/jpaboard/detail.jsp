<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세보기 화면</title>
</head>
<body>
	<main>
		<h3>게시물 상세보기 페이지</h3>
		<div>번호: ${board.num}</div>
		<div>제목: ${board.title}</div>
		<div>작성자: ${board.author}</div>
		<div>내용: ${board.contents}</div>
		<div>작성일: ${board.wdate}</div>
		<div>
			<a href="/jpaboard/updateForm/${board.num}">[수정]</a> || <a href="/jpaboard/delete/${board.num}">[삭제]</a>
		</div>
	</main>
</body>
</html>