
let index = {

	init: function() {
		$("#btn--save").bind("click", () => {
			this.save();
		});

		$("#btn--update").bind("click", () => {
			this.update();
		});

	},

	save: function() {

		let token = $("meta[name='_csrf']").attr("content");
		let csrfHeader = $("meta[name='_csrf_header']").attr("content");
		console.log("token 확인:" + token);
		console.log("csrfHeader 확인:" + csrfHeader);



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
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, token);
			},

			// 회원 가입 요청
			type: "POST",
			url: "/auth/joinProc",
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
	///////////////save/////////////////////

	/////////////////login//////////////


	update: function() {

		let token = $("meta[name='_csrf']").attr("content");
		let csrfHeader = $("meta[name='_csrf_header']").attr("content");

		let data = {
			id: $("#id").val(),
			password: $("#password").val(),
			email: $("#email").val(),
			username: $("#username").val()
		}

		$.ajax({

			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, token);
			},


			type: "PUT",
			url: "/api/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(data, textStatus, xhr) {
			if (data.status == "OK") {
				alert("회원정보 수정을 완료 하였습니다.");
				location.href = "/";
			}
		}).fail(function(error) {
			alert("회원정보 수정을 실패하였습니다. !");
		})
	}



}

index.init();











