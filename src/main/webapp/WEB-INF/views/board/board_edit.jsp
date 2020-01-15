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
	// 게시물 수정  PUT 
	$(document).ready(function() {
		$("#btnUpdate").click(function() {
			var bbsTitle = $('#bbsTitle').val();
			var bbsContent = $('#bbsContent').val();
			if ($('#bbsTitle').val().length < 1) 
				alert('제목 미입력');
			if ($('#bbsContent').val().length < 1) 
				alert('입려된 내용이 없습니다.');
			var checkconfirm = confirm("이 게시물을 수정하시겠습니까?");
			if (checkconfirm == true) {
				var bbsID = $('#bbsID').val();
				var bbsTitle = $('#bbsTitle').val();
				var bbsContent = $('#bbsContent').val();
				$.ajax({
					type : "PUT",
					data : JSON.stringify({
						bbsID : bbsID,
						bbsTitle : bbsTitle,
						bbsContent : bbsContent
					}),
					url : "${path}/board",
					contentType : 'application/json;charset=utf-8',
					dataType : 'json',
					success : function(response) {
						if (response.result == true) {
							location.href = '${path}/board/view/paging?nowPage=1'
						}
					},
					error : function(error) {
						alert(error);
					}
				});
			}
			else {
				alert("수정 취소하였습니다.")
			}
		});
	});

	// 게시물 삭제 DELETE 
	$(document).ready(function() {
		$("#btnDelete").click(function() {
			var checkconfirm = confirm("이 게시물을 삭제하시겠습니까?");
			if (checkconfirm == true) {
				var bbsID = $('#bbsID').val();
				$.ajax({
					type : "DELETE",
					data : JSON.stringify({
						bbsID : bbsID
					}),
					url : "${path}/board/"+bbsID,
					contentType : 'application/json;charset=utf-8',
					dataType : 'json',
					success : function(response) {
						if (response.result == true) {
							location.href = '${path}/board/view/paging?nowPage=1'
						}
					},
					error : function(error) {
						alert(error);
					}
				});
			} 
			else {
				alert("삭제 취소하였습니다.")
			}
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
			<form method="post">
				<table class="table table-striped"
					style="text-align: center; border: 1px;">
					<thead>
						<tr>
							<th colspan="1"
								style="background-color: #eeeeee; text-align: center;">게시판
								글쓰기 양식</th>
						</tr>
					</thead>
					<thead>
						<tr>
							<td><input type="hidden" class="form-control"
								value="${boardvo.bbsID}" placeholder="글 번호" name="bbsID"
								id="bbsID" maxlength="50"></td>
						</tr>
						<tr>
							<td><input type="text" class="form-control"
								value="${boardvo.bbsTitle}" placeholder="글 제목" name="bbsTitle"
								id="bbsTitle" maxlength="20"></td>
						</tr>
						<tr>
							<td><input type="text" class="form-control"
								value="${boardvo.bbsContent}" placeholder="글 내용"
								name="bbsContent" id="bbsContent" maxlength="1000"
								style="height: 350px"></td>

						</tr>
					</thead>
				</table>
				<c:if test="${countcomment == 0}">
					<input type="button" id="btnDelete"
						class="btn btn-primary pull-right" value="게시물 삭제 ">
				</c:if>
				<input type="button" id="btnUpdate"
					class="btn btn-primary pull-right" value="게시물 수정 ">

			</form>
		</div>
	</div>

	<script src="${path}/resources/js/bootstrap.min.js"></script>
</body>
</html>