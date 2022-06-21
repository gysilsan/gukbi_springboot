<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 화면</title>
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
		$.ajax({
			url:'/jdbc/emp/update',
			method:'post',
			cache: false,
			data: $('#edit_form').serialize(),
			dataType:'json',
			success:function(res){
				alert(res.updated ? '수정 성공' : 'Failed');
				location.href='/jdbc/emp/list';
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
			<div><label for="empno">번호</label><input type="text" name="empno" id="empno" value="${emp.empno}" readonly></div>
			<div><label for="ename">이름</label><input type="text" name="ename" id="ename" value="${emp.ename}" readonly></div>
			<div><label for="deptno">부서</label><input type="text" name="deptno" id="deptno" value="${emp.deptno}"></div>
			<div><label for="sal">급여</label><input type="text" name="sal" id="sal" value="${emp.sal}"></div>
			<div><label for="hiredate">입사</label><input type="text" name="hiredate" id="hiredate" value="${emp.hiredate}" readonly></div>
			<div><label for="mgr">담당</label><input type="text" name="mgr" id="mgr" value="${emp.mgr}" readonly></div>
			<div><label for="job">직무</label><input type="text" name="job" id="job" value="${emp.job}" readonly></div>
			<div><label for="comm">comm</label><input type="text" name="comm" id="comm" value="${emp.comm}" readonly></div>
			<div class="btns">
				<button type="reset">취소</button>
				<button type="submit">수정</button>
			</div>
		</form>
	</main>
</body>
</html>