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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${path}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${path}/resources/css/custorm.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>Spring Framework 게시판 만들기</title>
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
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3"
							style="background-color: #eeeeee; text-align: center;">게시판
							글보기</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 20%;">글 제목</td>
						<td colspan="2"><%="ㅇㅇ"%></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="2"><%="ㅇㅇ"%></td>
					</tr>
					<tr>
						<td>작성일자</td>
						<td colspan="2"><%="ㅇㅇ"%></td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="2" style="min-height: 200px; text-align: left;"><%="ㅇㅇ".replace("\n", "<br>")%></td>
					</tr>
				</tbody>
			</table>
			<a href="bbs.jsp" class="btn btn-primary">목록</a> <a
				href="${path}/board/board_home" class="btn btn-primary">수정</a> <a
				onclick="return confirm('정말로 삭제하겠습니까?')"
				href="${path}/board/board_home" class="btn btn-primary">삭제</a>


		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${path}/resources/js/bootstrap.min.js"></script>
</body>
</html>