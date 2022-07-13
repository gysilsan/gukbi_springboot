<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Upload Form</title>
</head>
<body>
<h3>Spring boot 파일 업로드 테스트</h3>
<form action="/files/upload" method="post" enctype="multipart/form-data">
	<div><label>작성자</label><input type="text" name="writer" value="smith"></div>
	<div><label>File</label><input type="file" name="files" multiple="multiple"></div>
	<div><label>설명</label><input type="text" name="description" value="업로드 테스트"></div>
	<div>
		<button type="reset">취소</button>
		<button type="submit">업로드</button>
	</div>
</form>
</body>
</html>