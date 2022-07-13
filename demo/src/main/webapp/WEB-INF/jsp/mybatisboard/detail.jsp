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
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function deleteBoard() {
		if(!confirm('정말 삭제하시겠습니까?')) return;
		
		$.ajax({
			url:'/mybatis/board/delete/${board.num}',
			method:'post',
			cache: false,
			dataType:'json',
			success:function(res){
				alert(res.deleted? '삭제 성공' : 'Failed');
				location.href='/mybatis/board/list/page/1';
			}
		});
	}
</script>
</head>
<body>
	<main>
		<h3>상세정보 화면</h3>
		<div><label for="num">번호</label> ${board.num}</div>
		<div><label for="title">제목</label> ${board.title}</div>
		<div><label for="contents">내용</label><textarea name="contents" id="contents" cols="15" rows="10" readonly>${board.contents}</textarea></div>
		<div><label for="author">작성자</label> ${board.author}</div>
		<div><label for="wdate">작성일</label> ${board.wdate}</div>
		<div><label for="pcode">부모글</label> ${board.pcode}</div>
		<div class="links">
			[<a href="/mybatis/board/add_form?pcode=${board.num}">답글 작성</a>]
			[<a href="/mybatis/board/edit?num=${board.num}">수정</a>]
			[<a href="javascript:deleteBoard();">삭제</a>]
		</div>
	</main>
</body>
</html>