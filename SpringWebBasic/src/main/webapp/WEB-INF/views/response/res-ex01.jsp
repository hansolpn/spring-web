<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>Model 객체에 대해 알아보기</h2>
	<a href="/basic/response/test?age=30">테스트 1 페이지로</a>
	
	<hr>
	
	<form action="/basic/response/test2">
		<input type="text" name="userId" placeholder="ID"><br>
		<input type="text" name="userName" placeholder="Name"><br>
		<input type="submit" value="확인">
	</form>
	
	<hr>
	
	<a href="/basic/response/test3">테스트 3 페이지로</a>
</body>
</html>