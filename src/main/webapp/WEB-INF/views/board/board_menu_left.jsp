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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>

<body>
	<ul class="nav navbar-nav">
		<li><a href="${path}/board/view/home">메인</a></li>
		<li><a href="${path}/board/view/paging?nowPage=1">게시판</a>
		<li><a href="${path}/board/view/join">회원가입</a> <c:if
				test="${userID eq 'admin'}">
				<li><a href="${path}/board/view/admin">유저관리</a>
			</c:if>
	</ul>

		<c:if test="${userID eq null}">
		<c:import url="board_menu_logout.jsp" charEncoding="UTF-8"></c:import>
	</c:if>
	<c:if test="${userID ne null}">
		<c:import url="board_menu_login.jsp" charEncoding="UTF-8"></c:import>
	</c:if>


</body>
</html>