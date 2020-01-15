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
	src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script>
	// 편집 버튼 클릭
	$(document).ready(function() {
		$("#btnEdit").click(function() {
			location.href = '${path}/board/view/edit?bbsID=${boardvo.bbsID}'
		});
	});
	
	// 댓글 등록 POST
	$(document).ready(function() {
		$("#btnEnrollcomment").click(function() {
			var bbsID = ${boardvo.bbsID};
			var commentContent = $('#commentContent').val();	
			$.ajax({
				type : "POST",
				data : JSON.stringify({
					bbsID : bbsID,
					commentContent : commentContent
				}),
				url : "${path}/comment",
				contentType : 'application/json;charset=utf-8',
				dataType : 'json',
				success : function(response) {
					if (response.result == true) {
						window.location.replace('${path}/board/view/detail?bbsID=${boardvo.bbsID}')	
					}	
				},
				error : function(error) {
					alert(error);				
				}
			})
		});
	});
	
	// 댓글 수정 PUT
	function updatecomment(commentID) {
		var bbsID = ${boardvo.bbsID};
		var commentID = commentID;
		var userID = '<c:out value="${userID}"/>';	
		var commentContent = $('#commentContent').val();	
		$.ajax({
			type : "PUT",
			data : JSON.stringify({
				bbsID : bbsID,
				commentID : commentID,
				userID : userID,
				commentContent : commentContent
			}),
			url : "${path}/comment",
			contentType : 'application/json;charset=utf-8',
			dataType : 'json',
			success : function(response) {
				if (response.result == true) {
					window.location.replace('${path}/board/view/detail?bbsID=${boardvo.bbsID}')
				}
			},
			error : function(error) {
				alert(error);
			}
		});	
	}
	
	// 댓글 삭제 DELETE
	function deletecomment(commentID) {
		var commentID = commentID;
		$.ajax({
			type : "DELETE",
			url : "${path}/comment/" + commentID,
			contentType : 'application/json;charset=utf-8',
			dataType : 'json',
			success : function(response) {
				if (response.result == true) {
					window.location.replace('${path}/board/view/detail?bbsID=${boardvo.bbsID}')
				}
			},
			error : function(error) {
				alert(error);
			}
		});
	}
	
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
							<td><input type="text" class="form-control"
								value="${boardvo.bbsID}" placeholder="글 번호" name="bbsID"
								maxlength="50" disabled></td>
						</tr>
						<tr>
							<td><input type="text" class="form-control"
								value="${boardvo.bbsTitle}" placeholder="글 제목" name="bbsTitle"
								maxlength="50" disabled></td>
						</tr>
						<tr>
							<td><input type="text" class="form-control"
								value="${boardvo.bbsContent}" placeholder="글 내용"
								name="bbsContent" maxlength="2048" style="height: 100px"
								disabled></td>

						</tr>
					</thead>
				</table>
				<input type="button" id="btnEdit" class="btn btn-primary pull-right"
					value="게시물 편집">
					

				--------------------------------------------------------------------------전체${countcomment}개의
				댓글이 있습니다.
				--------------------------------------------------------------------------<br>
				
				<table class="table table-striped"
					style="text-align: center; border: 1px;">
					<br>
					<thead>
						<tr>
							<th style="background-color: #eeeeee; text-align: center;">작성자</th>
							<th style="background-color: #eeeeee; text-align: center;">내용</th>
							<th style="background-color: #eeeeee; text-align: center;">작성일</th>
							<th style="background-color: #eeeeee; text-align: center;"></th>
						</tr>
					</thead>
					<c:forEach var="row" items="${commentlist}">
						<tr>
							<td>${row.userID}</td>
							<td>${row.commentContent}</td>
							<td>${row.commentDate}</td>
							<td>
								<div style="height: 40px; overflow: hidden;">
									<c:if test="${row.userID == sessionScope.userID}">
										<a type="button" id="btnUpdatecomment" onClick="javascript:updatecomment('${row.commentID}');"
											class="btn btn-primary btn-arraw-right">댓글 수정</a>
										<a type="button" id="btnDeletecomment" onClick="javascript:deletecomment('${row.commentID}');"
											class="btn btn-primary btn-arraw-right">댓글 삭제</a>
									</c:if>
								</div>
							</td>
						</tr>
					</c:forEach>
				</table>
				<c:if test="${userID ne null}">
					<input type="text" class="form-control" value=""
						placeholder="댓글 입력" id="commentContent" name="commentContent" maxlength="50">
					<input type="button" id="btnEnrollcomment"
						class="btn btn-primary pull-right" value="댓글 등록">
				</c:if>
			
		</div>
	</div>

	<script src="${path}/resources/js/bootstrap.min.js"></script>
</body>
</html>