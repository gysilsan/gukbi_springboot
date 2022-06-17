<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입력 폼</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
function save() {
	var serData = $('#input_form').serialize();
	$.ajax({
		url:'/board/save',
		method:'post',
		cache:false,
		data:serData,
		dataType:'json',
		success:function(res) {
			alert(res.saved ? "저장 성공": res.msg);
			if(!res.saved && res.msg) {
				location.href='/user/login';
			}
		},
		error:function(xhr,status,err) {
			alert("Error: " + err);
		}
	});
	return false;
}
</script>
</head>
<body>
	<main>
		<h3>글 입력 폼</h3>
		<form id="input_form" method="post" action="/board/save" onsubmit="return save();">
			<input type="hidden" name="pcode" value="${board.pcode}">
			<label for="title">제목</label><div><input type="text" name="title" id="title"></div>
			<label for="contents">내용</label><div><textarea name="contents" id="contents" rows="5" cols="20" placeholder="내용.."></textarea></div>
			<button type="reset">취소</button>
			<button type="submit">제출</button>
		</form>
	</main>
</body>
</html>