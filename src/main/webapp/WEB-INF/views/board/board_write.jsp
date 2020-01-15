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
<script>
	// 게시물 작성 POST 
	$(document).ready(function() {
		$("#btnSubmit").click(function() {
			var bbsTitle = $('#bbsTitle').val();
			var bbsContent = $('#bbsContent').val();
			if ($('#bbsTitle').val().length < 1) 
				alert('제목 미입력');
			if ($('#bbsContent').val().length < 1) 
				alert('입려된 내용이 없습니다.');
			$.ajax({
				type : "POST",
				data : JSON.stringify({
					bbsTitle : bbsTitle,
					bbsContent : bbsContent
				}),
				url : "${path}/board",
				contentType : 'application/json;charset=utf-8',
				dataType : 'json',
				success : function(response) {
					if(response.result == true){
						alert("게시물 작성했습니다. 메인화면으로 이동합니다.");
						window.location.replace('${path}/board/view/paging?nowPage=1')	
					}									
				},
				error : function(error) {
					alert(error);
				}
			});
		});
	});
</script>
</head>

<body>
	<nav class="navbar navbar-default">
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<c:import url="board_menu_left.jsp" charEncoding="UTF-8"></c:import>
	</div>
	</nav>

	<div class="container">
		<div class="row">
			<form method="post" >
				<table class="table table-striped"
					style="text-align: center; border: 1px;">
					<thead>
						<tr>
							<th colspan="1"
								style="background-color: #eeeeee; text-align: center;">게시판
								글쓰기 양식</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control"
								placeholder="글제목" name="bbsTitle" id="bbsTitle" maxlength="20"></td>
						</tr>
						<tr>
							<td><textarea class="form-control" placeholder="글 내용"
									name="bbsContent" id="bbsContent" maxlength="1000"
									style="height: 100px"></textarea></td>
						</tr>
					</tbody>
				</table>
				<input type="button" id="btnSubmit"
					class="btn btn-primary pull-right" value="게시물 저장 ">
			</form>
		</div>
	</div>

	<script src="${path}/resources/js/bootstrap.min.js"></script>
</body>
</html>