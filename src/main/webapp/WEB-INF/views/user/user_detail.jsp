<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 상세 정보 페이지</title>
<%@ include file="user_list_header.jsp"%>
</head>
<body>
	<%@ include file="user_list_menu.jsp"%>

	<h2>회원정보 상세</h2>
	<form name="form1" method="post">
		<table border="1" width="400px">
			<tr>
				<td>아이디</td>
				<!-- id는 수정이 불가능하도록 readonly속성 추가 -->
				<td><input name="id" value="${dto.id}" readonly="readonly"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pw"></td>
			</tr>

			<td colspan="2" align="center"><input type="button" value="수정"
				id="btnUpdate"> <input type="button" value="삭제"
				id="btnDelete">
				<div style="color: red;">${message}</div></td>
			</tr>
		</table>
	</form>
</body>
</html>