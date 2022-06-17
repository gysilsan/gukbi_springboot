<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
function login() {
	$.ajax({
		url:'/user/login',
		method:'post',
		cache:false,
		data:$('#login_form').serialize(),
		dataType:'json',
		success:function(res){
			alert(res.logined ? '로그인 성공' : 'Failed!');
			if(res.logined) location.href='/board/input';
		},
		error:function(xhr,status,err) {
			alert('Error: ' + err);
		}
	});
	return false;
}
function logout() {
	$.ajax({
		url:'/user/logout',
		method:'post',
		cache:false,
		dataType:'json',
		success:function(res){
			alert(res.logout? '로그아웃 성공' : '로그아웃 실패');
			if(res.logout) location.href='/user/login_form';
		},
		error:function(xhr,status,err) {
			alert("Error: " + err);
		}
	});
}
</script>
</head>
<body>
	<main>
		<h3>로그인 폼</h3>
		<form id="login_form" onsubmit="return login();">
			아이디<input type="text" name="uid">
			비밀번호<input type="password" name="pwd">
			<div>
				<button type="reset">취소</button>
				<button type="submit">로그인</button>
			</div>
		</form>
		<a href="javascript:logout();">로그아웃</a>
	</main>
</body>
</html>