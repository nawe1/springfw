<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>B강의장 프로젝트 입니다.</title>
<style>
	.innerOuter{
		height: 800px;
	}
</style>
</head>
<body>

	<jsp:include page="common/menubar.jsp"/>
	
	
	<div id="innerOuter">
	
		<h3>게시글 조회 TOP 5</h3>
		<br>
		<a href ="Lisy.board" style="float:right; color: lightgray; fon-weight:800, font-size=14px">더보기</a>
		<br></br>
	
		<table class="table table-hover" align="center" id="boardList">
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
					<th>첨부파일</th>
				</tr>
			</thead>
			<tbody>
				<!-- BOARD테이블에서 count컬럼의 값이 높은 상위 5개의 게시글을 조회해서 뿌려줄 것 -->
				
			</tbody>
		</table>
		<br><br>
		<table id="board-detail" class="table">
		
		</table>
	</div>
	
	<script>
		
		$(document).on('click','#boardList > tbody >tr', e => {
		    const boardNo = $(e.currentTarget).children().eq(0).text(); // Typo fixed: e.currentTaget -> e.currentTarget
		    
		    $.ajax({
		        url: 'boards/' + boardNo,
		        type: 'get',
		        success: result => {
		            // console.log(result);
	
		            let value = "<tr><td colspan='3'>게시글 상세보기~~</td></tr>";
	
		            value += '<tr>'
		                  + ' <td width="300">' + result.boardTitle + '</td>'
		                  + ' <td width="600">' + result.boardContent + '</td>'
		                  + ' <td width="200">' + result.boardWriter + '</td>'
		                  + '</tr>';
	
		            const boardDetailElement = document.getElementById('board-detail');
		            if (boardDetailElement) {
		                boardDetailElement.innerHTML = value;
		            } else {
		                console.error('Element with ID "board-detail" not found');
		            }
		        }
		    });
		});

			
		/*
			상세보기
			
			조회된 게시글 목록에서 게시글을 클릭하면 선택한 글번호를 가지고 하나의 게시글 정보를 AJAX
			요청을 통해 응답받아서 id속성값이 board-detail인 table에 자식요소를 만들어서 출력
		*/
		
		
		
	
	
	
	
	
	
	
		$(() => {
			findTopFiveBoard();
		})
	
		function findTopFiveBoard(){
			
			$.ajax({
				url: 'boards',
				type: 'get' ,
				success: boards => {
				
					//console.log(boards);
					let value= " ";
				
					for(let i in boards){
					
						value += '<tr>'
						      + '<td>' + boards[i].boardNo + '</td>'
						      + '<td>' + boards[i].boardTitle + '</td>'
						      + '<td>' + boards[i].boardWriter + '</td>'
						      + '<td>' + boards[i].count + '</td>'
						      + '<td>' + boards[i].createDate + '</td>'
						      + '</td>';      
						      if(boards[i].originName != null){
						    	  value += '💌';
						      }
						      + '</td></tr>';
					}
					
					$('#boardList tbody').html(value);
				}
			});
			
		}
	
	
	</script>
	
	

	<jsp:include page="common/footer.jsp"/>
</body>
</html>