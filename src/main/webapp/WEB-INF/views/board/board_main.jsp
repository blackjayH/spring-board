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
<style>
a, a:hover {
	color: #000000;
	text-decoration: none;
}
</style>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="navbar-header">
		<h1>
			<a class="navbar-brand" href="${path}/board/home">Spring
				Framework 게시판 만들기</a>
		</h1>
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
			aria-expanded="false"></button>
	</div>
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li><a href="${path}/board/home">메인</a></li>
			<li class="active"><a href="${path}/board/main">게시판</a></li>
			<li><a href="${path}/board/join">회원가입</a>
		</ul>
		<%@ include file="board_menu_login.jsp"%>
	</div>
	</nav>
	<div class="container">
		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px;">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><%="dd"%></td>
						<td><%="dd"%></td>
						<td><%="dddd"%></td>
						<td><%="dddd"%></td>
					</tr>
				</tbody>
			</table>

			<a href="bbs.jsp?pageNumber=<%="dd"%>"
				class="btn btn-success btn-arraw-left">이전</a> <a
				href="bbs.jsp?pageNumber=<%="dd"%>"
				class="btn btn-success btn-arraw-left">다음</a> <a
				href="${path}/board/write" class="btn btn-primary pull-right">글쓰기</a>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${path}/resources/js/bootstrap.min.js"></script>
</body>
</html>