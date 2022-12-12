
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container m-5">
	<form action="/action_page.php">
		<div class="form-group">
			<label for="username">username : </label> <input type="text" value="tenco" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password" value="asd1234" class="form-control" placeholder="Enter password" id="pwd">
		</div>
		<button type="button" id="btn--login" class="btn btn-primary">signin</button>
	</form>
</div>
<script type="text/javascript" src="js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>