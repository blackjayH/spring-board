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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<style>
a, a:hover {
	color: #000000;
	text-decoration: none;
}
</style>
</head>

<body>
	<nav class="navbar navbar-default">
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<c:import url="board_menu_left.jsp" charEncoding="UTF-8"></c:import>
	</div>
	</nav>
	
	<div class="container">
		<div class="row">
			<a href="${path}/board/view/paging?nowPage=1"
				class="btn btn-success btn-arraw-left">처음</a> <a
				href="${path}/board/view/paging?nowPage=${paging.nowPage-1}"
				class="btn btn-success btn-arraw-left">이전</a> <a
				href="${path}/board/view/paging?nowPage=${paging.rootPage * 5 + 1}"
				class="btn btn-success btn-arraw-left">${paging.rootPage * 5 + 1}</a>
			<a href="${path}/board/view/paging?nowPage=${paging.rootPage * 5 + 2}"
				class="btn btn-success btn-arraw-left">${paging.rootPage * 5 + 2}</a>
			<a href="${path}/board/view/paging?nowPage=${paging.rootPage * 5 + 3}"
				class="btn btn-success btn-arraw-left">${paging.rootPage * 5 + 3}</a>
			<a href="${path}/board/view/paging?nowPage=${paging.rootPage * 5 + 4}"
				class="btn btn-success btn-arraw-left">${paging.rootPage * 5 + 4}</a>
			<a href="${path}/board/view/paging?nowPage=${paging.rootPage * 5 + 5}"
				class="btn btn-success btn-arraw-left">${paging.rootPage * 5 + 5}</a>
			<a href="${path}/board/view/paging?nowPage=${paging.nowPage+1}"
				class="btn btn-success btn-arraw-left">다음</a> <a
				href="${path}/board/view/paging?nowPage=${paging.lastPage}"
				class="btn btn-success btn-arraw-left">끝</a> <a
				href="${path}/board/view/write"
				class="btn btn-primary btn-arraw-right">검색</a> <a
				href="${path}/board/view/write" class="btn btn-primary pull-right">글쓰기</a>
			전체 ${count} 개의 게시물이 있습니다. 루트페이지는
			${paging.rootPage} 현재 페이지는 ${paging.nowPage} 마지막페이지${paging.lastPage} <br>

			<table class="table table-striped"
				style="text-align: center; border: 1px;">
				<br>
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
						<th style="background-color: #eeeeee; text-align: center;">조회수</th>
					</tr>
				</thead>
				<c:forEach var="row" items="${list}">
					<tr>
						<td>${row.bbsID}</td>
						<td><a href="${path}/board/${row.bbsID}">${row.bbsTitle}</a></td>
						<td>${row.userID}</td>
						<td>${row.bbsDate}</td>
						<td>${row.click}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<script src="${path}/resources/js/bootstrap.min.js"></script>
</body>
</html>