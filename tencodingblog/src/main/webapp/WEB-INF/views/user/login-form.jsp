
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container m-5">
	<form action="/action_page.php">
		<div class="form-group">
			<label for="email">Email address:</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="pwd">
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>