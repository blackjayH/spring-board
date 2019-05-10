<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- jstl 코어 태그 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- context 경로 -->
<c:set var="path" value="${pageContext.request.contextPath}" />
<div style="text-align: center;">
	<a href="${path}/member/list.do">유저확인</a>
	<a href="${path}/member/list.do">로그인</a>
</div>
<hr>