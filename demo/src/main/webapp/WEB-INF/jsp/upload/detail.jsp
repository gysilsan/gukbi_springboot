<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세정보 페이지</title>
<style>
	main {margin:0 auto; width:fit-content;}
	main>h3 {text-align:center;}
	.links{margin:1em auto; text-align:center;}
	input[readonly] {background-color: #ccc;}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous">
</script>
<script type="text/javascript">
function upload(num) {
		
	var form = $('#uploadForm')[0];
	var data = new FormData(form);
	
	$.ajax({
		type: "POST",
		enctype: 'multipart/form-data',
		url: '/files/upload',
		data: data,
		dataType: 'json',	/* text, json, script, html, xml 등 */
		processData: false, /* 디폴트인 Query String 변환 하지 않도록 설정 */
		contentType: false, /* 디폴트 "application/x-www-form-unlencoded; charset=UTF-8" */
		cache: false,
		timeout: 600000, /* 시간 제한 없음 */
		success: function(res) {
			alert(res.inserted? '업로드 성공': 'Failed');
			location.href='/files/detail/'+num;
		},
		error:function(e) {
			alert('error');
		}
	});
};

function deleteThis(num) {
	var voNum = ${vo.num};
	if(!confirm('정말로 삭제하시겠습니까?')) return;
	$.ajax({
		url:'/files/delete/'+num,
		method:'post',
		cache:false,
		dataType: 'json',
		success: function(res) {
			if(res.deleted) alert('삭제 성공');
			location.href='/files/detail/'+voNum;
		},
		error: function(xhr, status, err) {
			alert('에러!');
		}
	});
};

function deleteUpload(num) {
	if(!confirm('정말로 게시물을 삭제하시겠습니까?')) return;
	$.ajax({
		url:'/files/deleteUpload/'+num,
		method:'GET',
		cache:false,
		dataType: 'json',
		success: function(res) {
			if(res.deleted) alert('게시물 삭제 성공');
			location.href='/files/list';
		},
		error: function(xhr, status, err) {
			alert('에러!');
		}
	});
};
</script>
</head>
<body>
	<main>
		<h3>상세정보 보기</h3>
		<table>
			<tr><th>게시물 번호</th><td>${vo.num}</td></tr>
			<tr><th>작성자</th><td>${vo.writer}</td></tr>
			<tr><th>작성일</th><td>${vo.udate}</td></tr>
			<tr><th>설명</th><td>${vo.description}</td></tr>
			<tr><th>첨부파일</th>
				<td>
				<c:forEach items="${vo.attach}" var="attach">
						<span><a href="/files/download/${attach.num}">${attach.fname}</a></span>
						[<a href="javascript:deleteThis(${attach.num});">삭제</a>]</br>
				</c:forEach>
				</td>
			</tr>
			<tr><th> </th>
				<td>
					<form method="post" enctype="multipart/form-data" id="uploadForm">
						<input type="hidden" name="pnum" value="${vo.num}">
						<div style="border-top:3px double black; padding-top:0.5em;">
							<input type="file" name="files" multiple>
						</div>
						<div style="margin-top:0.5em;">
							<button id="btnUpload" onclick="upload(${vo.num});">업로드</button>
							<button id="btnDelete" onclick="deleteUpload(${vo.num});">게시물 삭제</button>
						</div>
					</form>
				</td>
			</tr>
		</table>
		<div class="links"><a href="/files/list">[목록으로]</a></div>
	</main>
</body>
</html>