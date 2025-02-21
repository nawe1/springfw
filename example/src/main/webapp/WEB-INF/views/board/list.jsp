<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        .content {
            background-color:rgb(247, 245, 245);
            width:80%;
            margin:auto;
        }
        .innerOuter {
            border:1px solid lightgray;
            width:80%;
            margin:auto;
            padding:5% 10%;
            background-color:white;
        }

        #boardList {text-align:center;}
        #boardList>tbody>tr:hover {cursor:pointer;}

        #pagingArea {width:fit-content; margin:auto;}
        
        #searchForm {
            width:80%;
            margin:auto;
        }
        #searchForm>* {
            float:left;
            margin:5px;
        }
        .select {width:20%;}
        .text {width:53%;}
        .searchBtn {width:20%;}
    </style>
</head>
<body>
    
    <jsp:include page="../common/menubar.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter" style="padding:5% 10%;">
            <h2>게시판</h2>
            <br>
            
            <c:if test="${not empty sessionScope.loginUser }">
            
            <!-- 로그인 후 상태일 경우만 보여지는 글쓰기 버튼 -->
            <a class="btn btn-secondary" style="float:right;" href="boardForm.do">글쓰기</a>
            </c:if>
            <br>
            <br>
            <table id="boardList" class="table table-hover" align="center">
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
 					
 					<c:choose>
                		<c:when test="${empty list }">
                			<tr>
                				<td colspan="6">조회된 결과가 존재하지 않습니다.</td>
                			</tr>
                		</c:when>
                		<c:otherwise>
	                		<c:forEach var="board" items="${ list }" varStatus="status">
			                    <tr class = "board-detail" id="board-${board.boardNo}">
			                        <td>${board.boardNo }</td>
			                        <td>${board.boardTitle }</td>
			                        <td>${board.boardWriter }</td>
			                        <td>${board.count }</td>
			                        <td>${board.createDate }</td>
			                        <td>
			                        	<c:if test="${ not empty board.originName }">
			                        		💌
			                        	</c:if>
 									</td>
 								</tr>
 							
 							</c:forEach>
 						</c:otherwise>
 					</c:choose>
 					
                </tbody>
            </table>
            <br>
			<script>
				$(() => {
					
					$('.board-detail').click(e => {
						//location.href= '${board.boardNo }';  // 클릭시 여기로 이동한다!
						
						//console.log(e.target);
						//console.log(e.currentTaget.id.split('-')[1]); 이런식으로 뽑아서 사용할 수 있다.
						//console.log($(e.currentTaget).children().eq(0).text());
						//find('선택자') <-- 활용도가 가장 높음
						//children() <-- 하나씩 찾아가는 녀석
						location.href ='board-detail?boardNo=' + e.currentTarget.id.split('-')[1];
					}) ;
					
					//alert('하이하이');
					
					//location.href= '리터럴값';
					
					//$('.board-detail').on('click',handler())
					//.0n() --> 권장사항
					//사용시 고려사항 - 1. 어떤 친구들을 == eventTarget , 2.언제 사용할 것인가 == eventType
					//순수 JS로 작성시 .addEventListener() --> 권장사항
					// on이벤트 속성 = 
					// 익명함수 대입~ 도 있고
					//	인라인 방식도 있고~
					
					// $
					// .on()
					// 이벤트타입();
			
				});
			</script>
            <div id="pagingArea">
                <ul class="pagination">
                    <li class="page-item disabled"><a class="page-link" href="#">이전</a></li>
 					
 					<c:forEach begin ="${ pageInfo.startPage }" end="${ pageInfo.endPage }" var="p">
 						
 						<c:choose>
 						<c:when test="${ empty condition }">
 						<li class ="page-item">
 							<a class="page-link" href="boardList?page=${ p }">${ p }</a>
 						</li>
 						</c:when>
 						<c:otherwise>
 							<li class ="page-item">
 								<a class= "page-link"
 									href="search.do?page=${ p }&condition=${condition}&keyword=${keyword }">${ p }</a>
 						</c:otherwise>
 						</c:choose>
 					</c:forEach>
					
					<c:choose>
					<c:when test="${ pageInfo.maxPage eq pageInfo.currentPage }">
	                    <li class="page-item disabled">
	                    	<a class="page-link" href="#">다음</a>
	                   	</li>
                   	</c:when>
                   	<c:otherwise>
	                   	 <li class="page-item">
	                    	<a class="page-link" href="boardList?page=${ pageInfo.currentPage + 1 }">다음</a>
	                   	</li>
 					</c:otherwise>
 					</c:choose>
                </ul>
            </div>

            <br clear="both"><br>

            <form id="searchForm" action="search.do" method="get" align="center">
                <div class="select">
                    <select class="custom-select" name="condition">
                        <option value="writer">작성자</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                    </select>
                </div>
                <div class="text">
                    <input type="text" class="form-control" name="keyword">
                </div>
                <button type="submit" class="searchBtn btn btn-secondary">검색</button>
            </form>
        
            <br><br>
        	<script>
        	$(() => {      	
        		$('#searchForm option[value="${condition}"]').attr('selected', true);
        	});
        	</script>
        </div>
        <br><br>

    </div>

    <jsp:include page="../common/footer.jsp" />

</body>
</html>