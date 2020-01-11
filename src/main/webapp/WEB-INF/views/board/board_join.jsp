<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl 코어 태그 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- jstl 시간 출력 태그 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- context 경로 -->
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" initial-scale="1.0">
<link rel="stylesheet" href="${path}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${path}/resources/css/custorm.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Baseball Talk</title>

<script
		src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script>
	//ID 중복 체크 여부 변수
	var flag = false;
	// ID 중복체크 액션 GET
	$(document).ready(function() {
		$("#btnCheck").click(function() {
			if ($('#id').val().length < 1) 
				alert('아이디 미입력');
			var id = $('#id').val();
			$.ajax({
				type : "GET",
				url : "${path}/user/" + id,
				success : function(response) {
					if (response.result == true) {
						var confirmid = confirm("이 아이디를 사용하시겠습니까?");
						if (confirmid == true) {
							alert("회원가입 버튼을 눌러주세요.")
							flag = true;
						} else {
							alert("사용하실 다른 id를 입력해주세요.")
							flag = false;
						}
					} else {
						alert('이미 존재하는 아이디입니다.\n 사용하실 다른 id를 입력해주세요.');
						flag = false;
					}
				},
				error : function(error) {
					alert(error);
				}
			});
		});
	});
	// 회원가입 
	$(document).ready(function() {
		$("#btnSubmit").click(function() {
			if (!flag)
				alert('중복 체크');
			else if ($('#pw').val().length < 1)
				alert('페스워드 미입력');
			else if ($('#pw').val() != $('#pw2').val())
				alert('페스워드 확인해주세요');
			else {
				var id = $('#id').val();
				var pw = $('#pw').val();
				$.ajax({
					type : "POST",
					data : JSON.stringify({
						id : id,
						pw : pw
					}),
					url : "${path}/user",
					contentType : 'application/json;charset=utf-8',
					dataType : 'json',
					success : function(response) {
						if (response.result == true)
							location.href = '${path}/board/view/login'
						else
							alert('회원가입 실패');
					},
					error : function(error) {
						alert(error);
					}
				});
			}
		});
	});
</script>
</head>

<body>
	<nav class="navbar navbar-default">
	<div class="naver-header">
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
			aria-expanded="false">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="${path}/board/view/home">Spring
			Framework 게시판 만들기</a>
	</div>
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li><a href="${path}/board/view/home">메인</a>
			<li><a href="${path}/board/view/paging?nowPage=1">게시판</a>
			<li><a href="${path}/board/view/join">회원가입</a>
			<c:if test="${userID eq 'admin'}">
			<li><a href="${path}/board/view/user">유저관리</a>
		</c:if>
			
		</ul>
		<c:if test="${userID eq null}">
			<c:import url="board_menu_logout.jsp" charEncoding="UTF-8"></c:import>
		</c:if>
		<c:if test="${userID ne null}">
			<c:import url="board_menu_login.jsp" charEncoding="UTF-8"></c:import>
		</c:if>
	</div>
	</nav>

	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<form name="form2" method="post" action="${path}/user/action/add">
					<h3 style="text-align: center;">회원가입 화면</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" id="id"
							name="id" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호"
							id="pw" name="pw" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호확인"
							id="pw2" name="pw2" maxlength="20">
					</div>
					<div class="form-group">
						<input type="button" id="btnCheck"
							class="btn btn-primary form-control" value="아이디 중복 체크">
					</div>
					<div class="form-group">
						<input type="button" id="btnSubmit"
							class="btn btn-primary form-control" value="회원가입">
					</div>
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>

	<script src="${path}/resources/js/bootstrap.min.js"></script>
</body>
</html>