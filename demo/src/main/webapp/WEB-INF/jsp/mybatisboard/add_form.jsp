<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mybatis/board 글 작성폼</title>
<style>
	main{width:fit-content; margin:0 auto;}
	main>h3 {text-align: center;}
	.btns {margin:0 auto; text-align:center;}
	
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function add() {
		$.ajax({
			url:'/mybatis/board/add',
			method:'post',
			cache: false,
			data: $('#add_form').serialize(),
			dataType:'json',
			success:function(res){
				alert(res.added ? '추가 성공' : 'Failed');
				location.href='/mybatis/board/list/page/1';
			}
		});
		return false;
	}
</script>
</head>
<body>
	<main>
		<h3>추가 화면</h3>
		<form id="add_form" onsubmit="return add();">
			<input type="hidden" name="pcode" value="${board.pcode}">
			<div><label for="title">제목</label><input type="text" name="title" id="title"></div>
			<div><label for="contents">내용</label><textarea name="contents" id="contents" cols="15" rows="10" placeholder="글 내용..."></textarea></div>
			<div class="btns">
				<button type="reset">취소</button>
				<button type="submit">작성</button>
			</div>
		</form>
	</main>
</body>
</html>