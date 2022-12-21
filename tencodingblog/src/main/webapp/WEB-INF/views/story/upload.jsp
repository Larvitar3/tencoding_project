<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../layout/header.jsp" %>

<div class="container" style=" margin-bottom: 200px; ">

	<form action="/story/image/upload" method="post" enctype="multipart/form-data">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<div class="custom-file">
			<input type="file" class="custom-file-input" id="customFile" required="required" name="file"> 
			<label class="custom-file-label" for="customFile">파일을 선택해주세요.</label>
			
			<div class="input-group">
				<div class="input-group prepend" style="margin-top: 40px">
					<h5>▷ 스토리 설명</h5>
				</div>
				<input class="form-control" type="text" name="storyText">
				<div class="input-group" style="margin-top: 20px">
					<button type="submit" class="btn btn-info">업로드</button>
				</div>
			</div>
		</div>
	</form>
</div>


<%@ include file ="../layout/footer.jsp" %>

<script>
$(".custom-file-input").on("change", function() {
  var fileName = $(this).val().split("\\").pop();
  console.log(fileName);
  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});
</script>