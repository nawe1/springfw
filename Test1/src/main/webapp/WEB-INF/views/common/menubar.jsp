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
        div {box-sizing:border-box;} 
        #header {
            width:80%;
            height:100px;
            padding-top:20px;
            margin:auto;
        }
        #header>div {width:100%; margin-bottom:10px;}
        #header_1 {height:80%;}
        #header_2 {height:60%;}

        #header_1>div{
            height:100%;
            float:left;
        }
        #header_1_left {width:30%; position:relative;}
        #header_1_center {width:40%;}
        #header_1_right {width:30%;}

        #header_1_left>img {height:100%; position:absolute; margin:auto; top:0px; bottom:0px; right:0px; left:0px;}
        #header_1_right {text-align:center; line-height:35px; font-size:12px; text-indent:35px;}
        #header_1_right>a {margin:5px;}
        #header_1_right>a:hover {cursor:pointer;}

        #header_2>ul {width:100%; height:100%; list-style-type:none; margin:auto; padding:0;}
        #header_2>ul>li {float:left; width:25%; height:100%; line-height:55px; text-align:center;}
        #header_2>ul>li a {text-decoration:none; color:black; font-size:18px; font-weight:900;}

        #header_2 {border-top:1px solid lightgray;}

        #header a {text-decoration:none; color:black;}

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

    </style>
    
<!-- JavaScript -->
<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.14.0/build/alertify.min.js"></script>

<!-- CSS -->
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.14.0/build/css/alertify.min.css"/>
<!-- Default theme -->
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.14.0/build/css/themes/default.min.css"/>
<!-- Semantic UI theme -->
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.14.0/build/css/themes/semantic.min.css"/>
</head>
<body>

    <div id="header">
        <div id="header_1">
            <div id="header_1_left">
                <img src="https://kh-academy.co.kr/resources/images/main/logo.svg" alt="">
            </div>
            <div id="header_1_center"></div>
            <div id="header_1_right">
               
               <c:choose>
               		<c:when test="${ sessionScope.loginUser eq null}">
               					<%-- ${ empty sessionScope.loginUser } --%> 
                <!-- 로그인 전 -->
                <a href="enroll.do" data-t>회원가입</a>
                <a data-toggle="modal" data-target="#loginModal">로그인</a> <!-- 모달의 원리 : 이 버튼 클릭시 data-targer에 제시되어있는 해당 아이디의 div요소를 띄워줌 -->
                     </c:when>
               <c:otherwise>
                
                <!-- 로그인 후 --> 
                <label>${ sessionScope.loginUser.userName }님 환영합니다</label> &nbsp;&nbsp;
                <a href="myPage.do">마이페이지</a>
                <a href="logout.do">로그아웃</a>
                </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div id="header_2">
            <ul>
                <li><a href="${ requestScope.contextPath }">HOME</a></li>
                <li><a href="notices">공지사항</a></li>
                <li><a href="boardList?page=1">자유게시판</a></li>
                <li><a href="image-board">사진게시판</a></li>
            </ul>
        </div>
    </div>

    <!-- 로그인 클릭 시 뜨는 모달 (기존에는 안보이다가 위의 a 클릭 시 보임) -->
    <div class="modal fade" id="loginModal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Art Spark</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
    
            <form action="login.do" method="post">
                <!-- Modal body -->
                <div class="modal-body">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="아이디를 입력해주세요" id="userId" name="userId">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="비밀번호를 입력해주세요" id="userPwd" name="userPwd">
                    </div>
                    <div class="d-flex justify-content-between mt-3">
                        <a href="#" class="text-muted">아이디 찾기</a>
                        <a href="#" class="text-muted">비밀번호 찾기</a>
                    </div>
                </div>
                       
                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary btn-block">로그인</button>
                    <div class="btn-group mt-3 w-100">
                        <button type="button" class="btn btn-success">네이버</button>
                        <button type="button" class="btn btn-warning">카카오</button>
                        <button type="button" class="btn btn-secondary">구글</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<br clear="both">
</body>
</html>