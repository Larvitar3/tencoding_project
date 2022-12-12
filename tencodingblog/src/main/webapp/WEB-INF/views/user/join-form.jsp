<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container m-5">
	<form>
		<div class="form-group">
			<label for="username">UserName :</label> <input value="이춘식이" type="text" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input value="12341234" type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<div class="form-group">
			<label for="email">Email :</label> <input value="qwer@adasds" type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
	</form>
		<button type="submit" class="btn btn-primary" id="btn--save">signUp</button>
</div>
<script type="text/javascript" src="../js/user.js">
	
</script>

<%@ include file="../layout/footer.jsp"%>