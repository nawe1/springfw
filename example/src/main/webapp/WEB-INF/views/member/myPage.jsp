<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    
    <jsp:include page="../common/menubar.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>마이페이지</h2>
            <br>

            <form action="update.do" method="post">
                <div class="form-group">
                    <label for="userId">* ID : </label>
                    <input type="text" class="form-control" id="userId" value="${ sessionScope.loginUser.userId }" name="userId" readonly> <br>
                    
                    <div id="checkResult" style="display:none; font-size:0.7em;"></div><br><br/>
                     <label for="userPwd">* PWD : </label>
                    <input type="text" class="form-control" id="userPwd" value="${ sessionScope.loginUser.userPwd }" name="userPwd"> <br>

                    <label for="userName">* Name : </label>
                    <input type="text" class="form-control" id="userName" value="${ sessionScope.loginUser.userName }" name="userName" required> <br>

                    <label for="email"> &nbsp; Email : </label>
                    <input type="text" class="form-control" id="email" value="${ sessionScope.loginUser.email }" name="email"> <br>

                    <label for="age"> &nbsp; Age : </label>
                    <input type="number" class="form-control" id="age" value="${ sessionScope.loginUser.age }" name="age"> <br>

                    <label for="phone"> &nbsp; Phone : </label>
                    <input type="tel" class="form-control" id="phone" value="${ sessionScope.loginUser.phone }" name="phone"> <br>
                    
                    <label for="address"> &nbsp; Address : </label>
                    <input type="text" class="form-control" id="address" value="${ sessionScope.loginUser.address }" name="address"> <br>
                    
                    <label for=""> &nbsp; Gender : </label> &nbsp;&nbsp;
                    <input type="radio" id="Male" value="M" name="gender">
                    <label for="Male">남자</label> &nbsp;&nbsp;
                    <input type="radio" id="Female" value="F" name="gender">
                    <label for="Female">여자</label> &nbsp;&nbsp;
                    
                    <script>
                    	window.onload = () => {
                    		
                    		 //SessionScope.loginUser.gender
                    		 document.querySelector('input[value=${sessionScope.loginUser.gender}]').checked =true;
                    		
                    	}
                    	//jquery 버전
                    	
                    	// 요소 찍어서 뭐를 포함하고 있는지 확인해보기
                    	//console.dir('input[value =${session.loginUser.gender}]');
                    	
                    	//$(() => {
                    		//$('input[value = ${session.loginUser.gender}]').attr('checked',true);
                    	//})
						
                    </script>
                </div> 
                <br>
                <div class="btns" align="center">
                    <button type="submit" id ="join-btn"class="btn btn-primary">수정하기</button>
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">회원탈퇴</button>
                </div>
            </form>
        </div>
        <br><br>
        
    </div>

    <!-- 회원탈퇴 버튼 클릭 시 보여질 Modal -->
    <div class="modal fade" id="deleteForm">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">회원탈퇴</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <form action="delete.do" method="post">
                	<input type="hidden" value ="${ sessionScope.loginUser.userId }" name="userId"/>
                    <!-- Modal body -->
                    <div class="modal-body">
                        <div align="center">
                            탈퇴 후 복구가 불가능합니다. <br>
                            정말로 탈퇴 하시겠습니까? <br>
                        </div>
                        <br>
                            <label for="userPwd" class="mr-sm-2">Password : </label>
                            <input type="text" class="form-control mb-2 mr-sm-2" placeholder="Enter Password" id="userPwd" name="userPwd"> <br>
                    </div>
                    <!-- Modal footer -->
                    <div class="modal-footer" align="center">
                        <button type="submit" class="btn btn-danger" onclick="deletePrompt();">탈퇴하기</button>
                        
                    </div>
                </form>
                <script>
                	function deletePrompt(){
                		//const value = prompt('탈퇴를 하고 싶으면 "어쩌고저쩌고"를 정확히 입력해주세요');
                		
                		//if(value === '어쩌고저쩌고'){
                		//submint 요청으 보내는것	
                			
                		//}
                		//else{
                		//submint 요청으 보내지 않는 것	
                		//}
                		//console.log(value);
                		//prompt('탈퇴를 하고 싶으면 "어쩌고저쩌고"를 정확히 입력해주세요');
                	
                		return prompt('탈퇴를 하고 싶으면 "어쩌고저쩌고"를 정확히 입력해주세요') === '어쩌고저쩌고' ? true : false;
                		
                	}
                	//const deletePrompt = () => prompt('탈퇴를 하고 싶으면  "어쩌고저쩌고"를 정확히 입력해주세요');
             
               	</script>
               	<script>
               	$(() => {
            		const $pwdInput = $('.form-group #userPwd');
            		const $checkResult = $('#checkResult');
            		const $joinSubmit = $('#join-btn');
            		
            		$idInput.keyup(() =>{
            			
            			//console.log($idInput.val());
            			
            			//불필요한 DV접근을 제한하기 위해 다섯글자 이상으로 입력했을 때만 AJAX요청을 보내서 체크
            			if($pwdInput.val().length >= 5){
            				
            				$.ajax({
            					url:'pwdcheck.do',
            					type: 'get',
            					data: {
            						checkPwd: $pwdInput.val()
            					},
            					success: response =>{
            						
            						//console.log(response);
            						if(response.substr(4) ==='N'){ //증복이다!!
            							$checkResult.show().css('color','crimson').text('중복입니다!');
            							$joinSubmit.attr('disabled',true);
            						}else{//중복이 아니다
            							$checkResult.show().css('color','lightgreen').text('사용 가능합니다!');
            							$joinSubmit.removeAttr('disabled');
            						}
            					},
            					error: ()=>{
            						console.log('아이디 중복체크용 AJAX통신 실패.. ㅠ');
            					}
            				});
            				
            			}
            			else{
            				$checkResult.hide();
            				$joinSubmit.attr('disabled',true);
            			}
            			
            		});
            	});
            	
               	</script>
            </div>
        </div>
    </div>

    <jsp:include page="../common/footer.jsp" />

</body>
</html>