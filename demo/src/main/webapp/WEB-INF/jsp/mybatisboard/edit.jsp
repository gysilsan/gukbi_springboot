<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mybatis/board 수정폼</title>
<style type="text/css">
	main {width: fit-content; margin:0 auto;}
	main>h3 {text-align: center;}
	label {display: inline-block; width:5em; text-align:right; margin:0.5em;}
	input[readonly] {background-color:#ddd;}
	.btns {text-align: center; margin:1em;}
	button {margin: 0 1em;}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function edit() {
		if(!confirm('입력한 내용으로 수정하시겠습니까?')) return;
		$.ajax({
			url:'/mybatis/board/update',
			method:'post',
			cache: false,
			data: $('#edit_form').serialize(),
			dataType:'json',
			success:function(res){
				alert(res.updated ? '수정 성공' : 'Failed');
				location.href='/mybatis/board/list/page/1';
			}
		});
		return false;
	}
</script>
</head>
<body>
	<main>
		<h3>수정 화면</h3>
		<form id="edit_form" onsubmit="return edit();">
			<input type="hidden" name="pcode" id="pcode" value="${board.pcode}">
			<div><label for="num">번호</label><input type="text" name="num" id="num" value="${board.num}" readonly></div>
			<div><label for="title">제목</label><input type="text" name="title" id="title" value="${board.title}"></div>
			<div><label for="contents">내용</label><textarea name="contents" id="contents" cols="15" rows="10">${board.contents}</textarea></div>
			<div><label for="author">작성자</label><input type="text" name="author" id="author" value="${board.author}" readonly></div>
			<div><label for="wdate">작성일</label><input type="text" name="wdate" id="wdate" value="${board.wdate}" readonly></div>
			<div class="btns">
				<button type="reset">취소</button>
				<button type="submit">수정</button>
			</div>
		</form>
	</main>
</body>
</html>