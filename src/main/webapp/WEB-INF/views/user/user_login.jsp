<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 페이지</title>
<%@ include file="user_list_header.jsp"%>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script>
	// 아이디 중복 체크 버튼
	$(document).ready(function() {
		$("#btnCheck").click(function() {
			var form = {
				id : $('#id').val(),
				pw : $('#pw').val()
			}
			$.ajax({
				type : "POST",
				data : form,
				url : "user_checkIdPw",
				success : function(data) {
					if (data == "pwno")
						alert("비밀번호 틀림");
					else if (data == "idno")
						alert("없는 아이디");
					else if (data == "yes")
						alert("로그인 성공");
					else
						alert("에러");

				},
				error : function(error) {
					alert(error);
				}
			})
		});
	});
</script>
</head>
<body>
	<%@ include file="user_list_menu.jsp"%>
	<h2>로그인</h2>
	<form name="form1" method="post" action="${path}/user/user_checkIdPw">
		<table border="1" width="400px">
			<tr>
				<td>아이디</td>
				<td><input id="id" name="id"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" id="pw" name="pw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="로그인">
					<input type="reset" value="취소">
			</tr>
		</table>
	</form>
</body>
</html>

