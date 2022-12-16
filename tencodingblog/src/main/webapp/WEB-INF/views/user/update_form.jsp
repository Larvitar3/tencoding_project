<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container m-5">
	<form>
		<input type="hidden" name="id" id="id" value="${principal.user.id}">
		<div class="form-group">
			<label for="username">UserName :</label> <input type="text" class="form-control" readonly="readonly" placeholder="Enter username" id="username"
				name="username" value="${principal.user.username}">
		</div>
		<c:if test="${empty principal.user.oauth}">
			<div class="form-group">
				<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
			</div>
		</c:if>
		<div class="form-group">
			<label for="email">Email :</label> <input type="email" class="form-control" placeholder="Enter email" id="email" value="${principal.user.email}">
		</div>
	</form>
	<button type="submit" class="btn btn-primary" id="btn--update">회원 정보 수정 완료</button>
</div>
<script type="text/javascript" src="../js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
