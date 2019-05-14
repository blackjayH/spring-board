<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
<%@ include file="user_list_header.jsp"%>
</head>
<body>
	<%@ include file="user_list_menu.jsp"%>
	<h2>회원 목록</h2>
	<a href="${path}/user/user_join">회원가입</a>
	<table border="1" width="700">
		<tr>
			<th>아이디</th>
			<th>이름</th>
		</tr>
		<c:forEach var="row" items="${list}">
			<tr>
				<td><a href="${path}/user/user_detail?id=${row.id}">${row.id}</a></td>
				<td>${row.pw}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>