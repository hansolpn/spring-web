<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- ${ loginResult } --%>
	
	<%-- 
	<c:choose>
		<c:when test="${ loginResult == 'f-id'}">
			<h2>아이디를 입력하지 않았거나 잘못 입력하셨습니다.</h2>
		</c:when>
		<c:when test="${ loginResult == 'f-pw'}">
			<h2>비밀번호가 틀렸습니다.</h2>
		</c:when>
		<c:otherwise>
			<h2>로그인 성공!</h2>
			로그인 아이디: ${ userId }
		</c:otherwise>
	</c:choose>
	--%>
	<h1 class="result-title"></h1>	
	
	<br><a href="/basic/hw/s-login-form">로그인 화면으로</a>
	
	<script>

		/*
			브라우저가 HTML를 해석하고, css를 해석하고, javascript 코드를 실행해서
			화면에 표현하는 과정을 랜더링이라 합니다.
			지금 우리가 작성하는 파일 형태 -> jsp -> 서버 내에서 클래스로 변환 -> 응답은 HTML
			jsp 파일이 클래스로 변환하는 과정에서 작성한 EL문법은 자바 코드로 변환 -> 값을 표현
			-> 응답은 HTML로 표현.
			EL 표현식이 서버에서 먼저 평가되고 그 결과를 클라이언트에게 전달하여 브라우저로 표현하기 때문에
			script에서도 EL 표현이 가능합니다.
			JavaScript에서 EL 표현식을 작성할 때에는 문자열로 $ { }를 감싸주세요.
			
		*/
		const result = '${ loginResult }';
		const $h1 = document.querySelector('.result-title');
		switch (result) {
		
		case 'f-id':
			$h1.textContent = '아이디가 존재하지 않습니다.';
			break;
		case 'f-pw':
			$h1.textContent = '비밀번호가 틀렸습니다.';
			break;
		case 'success':
			$h1.textContent = '로그인 성공!';
			break;
		}
		//console.log("result: " + result);
	</script>
</body>
</html>