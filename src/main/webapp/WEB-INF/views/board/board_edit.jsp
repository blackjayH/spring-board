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
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script>
	// 삭제 버튼 클릭
	$(document).ready(function() {
		$("#btnDelete").click(function() {
			location.href = '${path}/board/delete?bbsID=${boardvo.bbsID}'
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
		<a class="navbar-brand" href="${path}/board/home">Spring Framework
			게시판 만들기</a>
	</div>
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li><a href="${path}/board/home">메인</a></li>
			<li class="active"><a href="${path}/board/main">게시판</a></li>
			<li><a href="${path}/board/join">회원가입</a>
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
			<form method="post" action="${path}/board/board_update">
				<table class="table table-striped"
					style="text-align: center; border: 1px;">
					<thead>
						<tr>
							<th colspan="1"
								style="background-color: #eeeeee; text-align: center;">게시판
								글쓰기 양식</th>
						</tr>
					</thead>
					<thead>
						<tr>
							<td><input type="hidden" class="form-control"
								value="${boardvo.bbsID}" placeholder="글 번호" name="bbsID"
								maxlength="50"></td>
						</tr>
						<tr>
							<td><input type="text" class="form-control"
								value="${boardvo.bbsTitle}" placeholder="글 제목" name="bbsTitle"
								maxlength="50"></td>
						</tr>
						<tr>
							<td><input type="text" class="form-control"
								value="${boardvo.bbsContent}" placeholder="글 내용"
								name="bbsContent" maxlength="2048" style="height: 350px"></td>

						</tr>
					</thead>
				</table>
				<input type="submit" class="btn btn-primary pull-right" value="수정">
				<input type="button" id="btnDelete"
					class="btn btn-primary pull-right" value="삭제">
			</form>
		</div>
	</div>
	<script src="${path}/resources/js/bootstrap.min.js"></script>
</body>
</html>