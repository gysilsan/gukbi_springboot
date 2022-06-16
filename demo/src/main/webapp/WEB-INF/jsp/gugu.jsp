<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 보기</title>
<style>
	main{margin:0 auto; width:fit-content;}
	main>h3 {text-align: center;}
	footer {margin: 0 auto; width:fit-content;}
	footer>a {text-align: center; margin:1em auto; text-decoration:none; color:blue;}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function req() {
		var obj = {};
		$.ajax({
			url:'/index/res',
			method:'post',
			cache:false,
			dataType:'json',
			success:function(res) {
				alert(res.saved ? "저장성공" : "Failed!");
			},
			error:function(xhr,status,err) {
				alert("Error:" + err);
			}
		});
	}
	function req2(num) {
		$.ajax({
			url:'/index/res/dan/5',
			method:'post',
			cache:false,
			dataType:'json',
			success:function(res) {
				alert("res.num: " + res.num);
			},
			error:function(xhr,status,err) {
				alert("Error:" + err);
			}
		});
	}
	$(function(){
		$('#fruit').change(function(evt) {
			$.ajax({
				url: '/index/res/fruit/sel',
				method: 'post',
				cache: false,
				data: {"fruit":evt.target.value},
				dataType: 'json',
				success:function(res) {
					alert(res.selection);
				},
				error:function(xhr,status,err) {
					alert("Error: " + err);
				}
			});
		});
	});
</script>
</head>
<body>
	<main>
		<h3>구구단 보기</h3>
		<c:forEach var="guguStr" items="${list}">
			<div>${guguStr}</div>
		</c:forEach>
	</main>
	<footer>
		<hr>
		<c:forEach var="dan" begin="2" end="9">
			<a href="/index/gugu/${dan}">${dan}</a>
		</c:forEach>
	</footer>
	<hr>
	<button type="submit" onclick="req();">res</button>
	<button type="submit" onclick="req2();">res2</button>
	<hr>
	<select name="fruit" id="fruit">
		<option value="apple">사과</option>
		<option value="orange">오렌지</option>
		<option value="peach">복숭아</option>
		<option value="melon">멜론</option>
	</select>
</body>
</html>