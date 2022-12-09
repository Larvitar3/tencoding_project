
$('#join--submit').on('click', function() {
	let data = {
		username: $('#username').val(),
		password: $('#password').val(),
		email: $('#email').val(),
		/** val() 이벤트가 일어나면 해당 라벨의 글을 가져온다 */
	};

	console.log("data : " + data.username);
	console.log("data : " + data.password);
	console.log("data : " + data.email);

	// js & http 통신 가능, 동기방식, 비동기방식
	// 대부분 비동기 통신을 많이 사용

	// 아작스 통신 ▼ 문법
	$.ajax({
		type : 'POST',
		url : '/blog/dummy/signup',
		data : JSON.stringify(data),
		contentType : 'application/json; charset=utf-8',
		dataType : 'json'
	}).done( function(response){
			console.log(response);
		}).fail(function(error){
			console.log(error);
		});

	
	
});


