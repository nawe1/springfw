<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>var,let,const</h1>
	
	<h2>변수 선언 시 사용할 수 있는 키워드들</h2>
	
	<button onclick= 'defDeclare()'>안녕 나는 버튼이얌</button>
	
	<script>
		function defDeclare(){
			
			
			var userId = 'user01';
			console.log(userId);
			
			var userId = 'user02';
			console.log(userId);
			
			console.log('----------------');
			
			let userName ='홍길동':
			console.log(userName);
			
			userName = "머시기";
			console.log(userName);
			
			console.log('------------------');
			
			const userPwd =123;
			console.log(123);
			
			//const userPwd = 234;
			//userPwd = 234;
			
			//let, const 위주로 사용
			
			const titleE1 = document.getElementById('title');
			
		}
	</script>
	
	<h1 id="title">요소</h1>
	
	<button onclick="keywordScope()">나는 버튼이얌</button>
	<script>
	
		function keywordScope(){
			
			if(1){
				let gender = 'M';
				const hobby = "취미취미당";
			}
			
			console.log(hobby);
			
			//var == functionScope == 변수가 선언된 "함수영역"네에서 사용가능
			
			for(var i = 0; i<5; i++){
				
			}
			
			console.log(i);
		}
		
	</script>
</body>
</html>