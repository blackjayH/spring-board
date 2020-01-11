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

<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script>
	// 삭제
	$(document).ready(function() {
		$("#btnWithdrawal").click(function() {
			if ($('#pw').val().length < 1)
				alert('페스워드 미입력');
			else {
				var id = '<c:out value="${userID}"/>';
				var pw = $('#pw').val();
				$.ajax({
					type : "POST",
					data : JSON.stringify({
						id : id,
						pw : pw
					}),
					url : "${path}/check",
					contentType : 'application/json;charset=utf-8',
					dataType : 'json',
					success : function(response) {
						if (response.result == true)
							deleteUser();
						if (response.result == false)
							alert('비밀번호를 확인해주세요');
					},
					error : function(error) {
						alert(error);
					}
				});
			}
		});
	});

	// 회원정보 삭제 액션 DELETE
	function deleteUser() {
		var id = '<c:out value="${userID}"/>';
		var confirmid = confirm('정말로 아이디를 삭제하시겠습니까?');
		if (confirmid == false)
			alert('취소 되었습니다. 홈 화면으로 이동합니다.');
		else {
			$.ajax({
				type : "DELETE",
				url : "${path}/user/" + id,
				success : function(response) {
					if (response.result == true) {
						alert('삭제하였습니다. 홈화면으로 이동합니다.');
						location.href = '${path}/board/view/home'
					} else {
						alert('삭제 실패하였습니다.');
					}
				},
				error : function(error) {
					alert(error);
				}
			});
		}
	}
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
			<li><a href="${path}/board/view/join">회원가입</a> <c:if
					test="${userID eq 'admin'}">
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
					<h3 style="text-align: center;">회원탈퇴 화면</h3>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호"
							id="pw" name="pw" maxlength="20">
					</div>
					<div class="form-group">
						<input type="button" id="btnWithdrawal"
							class="btn btn-primary form-control" value="회원탈퇴">
					</div>
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>

	<script src="${path}/resources/js/bootstrap.min.js"></script>
</body>
</html>