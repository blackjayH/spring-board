<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원등록 페이지</title>
<%@ include file="user_list_header.jsp" %>
</head>
<body>
<%@ include file="user_list_menu.jsp" %>
	<h2>회원등록</h2>
	<form name="form1" method="post" action="${path}/user/user_add">
		<table border="1" width="400px">
			<tr>
				<td>아이디</td>
				<td><input name="id"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="확인">
					<input type="reset" value="취소">
					
				</td>
			</tr>
		</table>
	</form>
</body>
</html>