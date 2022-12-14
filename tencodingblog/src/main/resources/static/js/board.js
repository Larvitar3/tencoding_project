
let index = {
	init: function() {
		$("#btn--save").bind("click", () => {
			this.save();
		});
		
		$("#btn--delete").bind("click", () => {
			this.deleteById();
		});
	},
	save: function() {

		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=UTF-8",
			// contentType를 넣지않으면 예외 발생
			dataType: "json"
		}).done(function(data, textStatus, xhr) {
			console.log(data);
			if (data.status == "OK") {
				alert("글 저장 완료");
				location.href = "/";
			}
		}).fail(function(error) {
			alert("저장에 실패했습니다.");
			console.log(error);
		});
	},
	
	deleteById: function(){
		
		let id = $("#board-id").text();
		
		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,			
		}).done(function(data, textStatus, xhr){
			console.log(data);
			if(data.status == "OK") {
				alert("글 삭제 완료했습니다.");
				location.href = "/";
			}
		}).fail(function(){
			alert("글 삭제에 실패했습니다.");
			console.log(error);
		})
		
	}
}

index.init();









