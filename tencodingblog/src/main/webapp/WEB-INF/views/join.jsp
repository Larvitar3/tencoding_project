<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<!-- <script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script> -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>form 테스트</h1>
		<form action="/blog/dummy/signup2" method="post">
			<div class="form-group">
				<label for="username">UserName:</label> <input type="text"
					class="form-control" placeholder="Enter usermame" id="username"
					name="username" value="qwer124">
			</div>
			<div class="form-group">
				<label for="pwd">Password:</label> <input type="password" value="1234"
					class="form-control" placeholder="Enter password" id="password"
					name="password">
			</div>
			<div class="form-group">
				<label for="email">Email:</label> <input type="email" value="1234@naver.com"
					class="form-control" placeholder="Enter email" id="email"
					name="email">
			</div>
			<!-- <button type="submit" id="join--submit" class="btn btn-primary">회원가입2</button> -->
		</form>
		<button id="join--submit" class="btn btn-primary">회원가입</button>
	</div>

	<script src="/blog/js/join.js"></script>
</body>
</html>