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

<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script>
	//로그인
	$(document).ready(function() {
		$("#btnLogin").click(function() {
			if ($('#id').val().length < 1)
				alert('아이디 미입력');
			else if ($('#pw').val().length < 1)
				alert('페스워드 미입력');
			else {
				var id = $('#id').val();
				var pw = $('#pw').val();
				$.ajax({
					type : "POST",
					data : JSON.stringify({
						id : id,
						pw : pw
					}),
					url : "${path}/login",
					contentType : 'application/json;charset=utf-8',
					dataType : 'json',
					success : function(response) {
						if (response.result == true)
							window.location.replace('${path}/board/view/home')
						if (response.result == false)
							alert(response.message);
					},
					error : function(error) {
						alert(error);
					}
				});
			}
		});
	});
	
	// 회원 가입으로 이동
	$(document).ready(function() {
		$("#btnJoin").click(function() {
			location.href = '${path}/board/view/join';
		});
	});
</script>
</head>

<body>
	<nav class="navbar navbar-default">
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<c:import url="board_menu_left.jsp" charEncoding="UTF-8"></c:import>
	</div>
	</nav>

	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<form name="form2" method="post">
					<h3 style="text-align: center;">로그인 화면</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" id="id"
							name="id" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호"
							id="pw" name="pw" maxlength="20">
					</div>
					<div class="form-group">
						<input type="button" id="btnLogin"
							class="btn btn-primary form-control" value="로그인">
					</div>
					<div class="form-group">
						<input type="button" id="btnJoin"
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