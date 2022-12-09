<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container m-5">
	<form action="/action_page.php">
		<div class="form-group">
			<label for="username">UserName :</label> <input type="text" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<div class="form-group">
			<label for="email">Email :</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
		<button type="submit" class="btn btn-primary">signUp</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>