
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
	<a href="https://kauth.kakao.com/oauth/authorize?client_id=188c73400922baf0db1a9d0b3dc58279&redirect_uri=http://localhost:9090/auth/kakao/callback&response_type=code">
	<img src="/img/kakao_login.png" height="40"></a>
	</form>
</div>
<%@ include file="../layout/footer.jsp"%>