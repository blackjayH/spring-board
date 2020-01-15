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
	
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script>
	function deleteuser(id) {
		var id = id;
		var userid = '<c:out value="${userID}"/>'
		if (id == 'admin')
			alert('admin을 삭제할 수 없습니다.');
		else {
			$.ajax({
				type : "DELETE",
				url : "${path}/user/" + id,
				success : function(response) {
					if (response.result == true) {
						if (userid == 'admin') {
							alert('관리자 권한으로 삭제했습니다.');
							window.location.replace('${path}/board/view/admin')
						} else {
							alert('삭제하였습니다. 홈화면으로 이동합니다.');
							window.location.replace('${path}/board/view/home')
						}
					} else {
						alert('삭제 실패하였습니다.');
					}
				},
				error : function(error) {
					alert(error);
				}
			});
		}
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
				<br>
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">유저명
						</th>
						<th style="background-color: #eeeeee; text-align: center;">버튼</th>
					</tr>
				</thead>
				<c:forEach var="row" items="${list}">
					<tr>
						<td id="id" name="id">${row.id}</td>
						<td width="15"><input type="button" id="btnDelete"
							class="btn btn-primary btn-arraw-right"
							onClick="javascript:deleteuser('${row.id}');" value="삭제"></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<script src="${path}/resources/js/bootstrap.min.js"></script>
</body>
</html>