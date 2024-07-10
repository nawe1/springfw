<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인이 하고 싶니?</title>
	
	<style>
		a{
			display: inline-block;
		}
	</style>
</head>
<body>
	
	<a id="login-btn">
		<img alt="로그인버튼" src="resources/img/kakao_login_button.png">
	</a>

	<br><br/>
	<button id="logout">로그아웃</button>

	${ loginUser.id }님 환영합니다.
	${ loginUser.nickName }은 없으시네요.
	
	<img src="${loginUser.thumbnailImg }"/>

	<script>
		document.getElementById('login-btn').onclick = () =>{
			
			location.href= 'https://kauth.kakao.com/oauth/authorize'
			             + '?client_id=846ba63b2ad75ed4e03f10ca3ab1ef91'
			             + '&redirect_uri=http://localhost/api/oauth'
			             + '&response_type=code'
			             + '&scope=profile_nickname,profile_image';
			
		};
		
		document.getElementById('logout').onclick = () =>{
			location.href='logout';
		}
	</script>


	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>