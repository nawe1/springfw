<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

	<h1>부산의 해수욕장엔 얼마나 많은 대장균이 살까????</h1>



	<table class="table table-success">
		<thead>
			<tr>
				<th>측정지점</th>
				<th>대장균 측정값</th>
				<th>장구균 측정값</th>
				<th>측정일시</th>
			</tr>
		</thead>
		<tbody>
		
		
		</tbody>
	</table>
	<button id="page" class="btn btn-sm btn-info" onclick="getBeachInfo()">다음</button>

	<script>
		var pageNo =1;
		$(() =>{
			getBeachInfo();
		});	
		function getBeachInfo(){
			$.ajax({
				url:'beach',
				type:'get',
				data:{
					pageNo : pageNo
				},
				success : info =>{
					
					//console.log(info.getBeachInfo.body.items.item);
					
					pageNo += 1;
					const items = info.getBeachInfo.body.items.item;
					//여기까지 값 잘 받음 확인
					
					let strEl = '';
					for(let i in items){
						
						const item = items[i];
						
						strEl += '<tr>'
						      + '<td>' +item.inspecArea+ '</td>'
						      + '<td>' +item.water01+ '</td>'
						      + '<td>' +item.water02+ '</td>'
						      + '<td>' +item.inspecYm+ '</td>'
							  + '</tr>';
					};
					$('tbody').html(strEl);
			}
		
			});
		}

	</script>

</body>
</html>