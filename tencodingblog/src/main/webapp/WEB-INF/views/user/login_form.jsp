
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container m-5">
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">username : </label> <input name="username" type="text" value="tenco" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">password :</label> <input name="password" type="password" value="asd1234" class="form-control" placeholder="Enter password" id="password">
		</div>
	<button type="submit"  id="btn--login" class="btn btn-primary">signIn</button>
	</form>
</div>
<%@ include file="../layout/footer.jsp"%>