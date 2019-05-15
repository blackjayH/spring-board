<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원등록 페이지</title>
<%@ include file="user_list_header.jsp"%>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script>
	// 아이디 중복 체크 버튼
	$(document).ready(function() {
		$("#btnCheck").click(function() {
			var form = {
				id : $('#id').val()
			}
			$.ajax({
				type : "GET",
				data : form,
				url : "user_checkId",
				success : function(data) {
					if (data == "no")
						alert("사용불가");
					else {
						alert("사용가능");
						var con_test = confirm("이 아이디를 사용하시겠습니까?");
						if (con_test == true) {
							alert("가입 버튼을 눌러주세요.")
						} else {
							alert("다른 id를 입력해주세요.")
						}
					}
				},
				error : function(error) {
					alert(error);
				}
			})
		});
	});

	// 리셋 버튼 
	$(document).ready(function() {
		$("#btnReset").click(function() {
			if (confirm("입력사항을 초기화하시겠습니까?")) {
				document.form1.reset();
			}

		});
	});

</script>
</head>
<body>
	<%@ include file="user_list_menu.jsp"%>
	<h2>회원등록</h2>
	<form name="form1" method="post" action="${path}/user/user_add">
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
				<td colspan="2" align="center">
				<input type="button"
					value="아이디 중복 체크" id="btnCheck"> 
					<input type="button"
					value="리셋" id="btnReset">  
					<input type="submit" value="가입"> <input
					type="reset" value="취소">
			</tr>
		</table>
	</form>
</body>
</html>