
let index = {

	init: function() {
		$("#btn--save").bind("click", () => {
			this.save();
		});

		$("#btn--login").bind("click", () =>{
			this.login();
		});

	},
	
	save: function() {
		// form tag에 사용자가 입력한 값들은 가지고 온다. (자바스크립트 변수)
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}

		console.log(data);

		// ajax 통신 구현
		// $.ajax().done().fail();
		$.ajax({
			// 회원 가입 요청
			type: "POST",
			url: "/api/user",
			data: JSON.stringify(data), // JSON으로 언어 변경, http 메세지의 body
			contentType: "application/json; charset=UTF-8", // 보낼 때 데이터 타입
			dataType: "json", // 응답을 받을 시 MIME TYPE 지정
		}).done(function(data, textStatus, xhr) {
			console.log(data);
			if (data.status == "OK") {
				alert("회원가입 완료 !! ");
				location.href = "/";
			}
		}).fail(function(error) {
			alert("회원가입 실패 !! " + error.responseJSON.msg);
			console.log(error);
		});
	},
	
	
	
	login: function(){
		
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		};
		
		$.ajax({
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(data, textStatus, xhr){
			alert("로그인 성공");

			console.log("login" + data);
			location.href = "/";
			
		}).fail(function(error){
			alert("로그인 실패");
		});
		
	}
}

index.init();











