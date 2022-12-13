
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container m-5">
	<form>
		<div class="form-group">
			<label for="username">Username : </label> <input type="text" value="tenco" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="pwd">Password :</label> <input type="password" value="asd1234" class="form-control" placeholder="Enter password" id="password">
		</div>
	</form>
	<button type="button"  id="btn--login" class="btn btn-primary">signIn</button>
</div>
<script type="text/javascript" src="../js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>