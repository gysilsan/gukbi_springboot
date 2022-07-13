<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<script
  src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  crossorigin="anonymous"></script>
<script type="text/javascript">
	function login() {
		$.ajax({
			url:'/ws/login',
			method:'post',
			cache:false,
			data:$('#login_form').serialize(),
			dataType: 'json',
			success:function(res) {
				if(res.login) {
					alert(res.uid + ' 로그인 성공!');
					location.href='/ws/chat';
				} else {
					alert('로그인 실패!');
				}
			},
			error:function(xhr,status,err) {
				alert('에러: ' + err);
			}
		});
		return false;
	}
</script>
</head>
<body>
	<main>
		<h3>로그인 화면</h3>
		<form id="login_form" onsubmit="return login();">
			<div><label for="uid">아이디</label><input type="text" name="uid" id="uid"></div>
			<div><label for="pwd">비밀번호</label><input type="password" name="pwd" id="pwd"></div>
			<input type="submit" value="제출">
			<input type="reset" value="취소">
		</form>
	</main>
</body>
</html>