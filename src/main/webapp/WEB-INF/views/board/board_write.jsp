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
<meta name="viewport" content="width=device-width" , initial-scale="1.0">
<link rel="stylesheet" href="${path}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${path}/resources/css/custorm.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Spring Framework 게시판 만들기</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	// 게시물 작성 Restful POST 
	$(document).ready(function() {
		$("#btnSubmit").click(function() {
			var bbsTitle = $('#bbsTitle').val();
			var bbsContent = $('#bbsContent').val();
			$.ajax({
				type : "POST",
				data : JSON.stringify({
					bbsTitle : bbsTitle,
					bbsContent : bbsContent
				}),
				url : "${path}/board2",
				contentType : 'application/json;charset=utf-8',
				dataType : 'json',
				success : function(response) {
					if(response.result == true){
						alert("성공");
						location.href = '${path}/board/view/paging?nowPage=1'	
					}
										
				},
				error : function(error) {
					alert(error);
				}
			});
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
			<li><a href="${path}/board/view/home">메인</a></li>
			<li class="active">
			<li><a href="${path}/board/view/paging?nowPage=1">게시판</a>
			<li><a href="${path}/board/view/join">회원가입</a>
		</ul>
		<c:if test="${userID eq null}">
			<%@ include file="board_menu_logout.jsp"%>
		</c:if>
		<c:if test="${userID ne null}">
			<%@ include file="board_menu_login.jsp"%>
		</c:if>
	</div>
	</nav>

	<div class="container">
		<div class="row">
			<form method="post" action="${path}/board/action/add">
				<table class="table table-striped"
					style="text-align: center; border: 1px;">
					<thead>
						<tr>
							<th colspan="1"
								style="background-color: #eeeeee; text-align: center;">게시판
								글쓰기 양식</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control"
								placeholder="글제목" name="bbsTitle" id="bbsTitle" maxlength="50"></td>
						</tr>
						<tr>
							<td><textarea class="form-control" placeholder="글 내용"
									name="bbsContent" id="bbsContent" maxlength="2048"
									style="height: 100px"></textarea></td>
						</tr>
					</tbody>
				</table>
				<input type="submit" class="btn btn-primary pull-right" value="저장">

				<input type="button" id="btnSubmit"
					class="btn btn-primary form-control" value="저장 REST">

			</form>
		</div>
	</div>

	<script src="${path}/resources/js/bootstrap.min.js"></script>
</body>
</html>