<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form action="">
		<input type="hidden" id="id" value="${principal.user.id}">
		<div class="form-group">
			<label for="title" id="board-id" data-id="${board.id}"> 글번호 : ${board.id} </label>
		</div>
		<div class="form-group">
			<label for="username"> username </label> <input readonly="readonly" type="text" name="username" id="username" class="form-control"
				value="${principal.user.username}">
		</div>
		<div class="form-group">
			<label for="title"> title </label> <input type="text" name="title" id="title" class="form-control" value="${board.title}">
		</div>
		<div class="form-group">
			<label for="content"> content </label>
			<textarea rows="5" name="content" id="content" class="form-control content">
			${board.content} 
			 </textarea>
			<br />
			<button type="submit" class="btn btn-primary" id="btn--update">글 수정 완료</button>
		</div>
	</form>
	<script>
		$('.content').summernote(
				{
					placeholder : '내용을 입력해주세요.',
					tabsize : 2,
					height : 400,
					toolbar : [ [ 'style', [ 'style' ] ],
							[ 'color', [ 'color' ] ],
							[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
							[ 'table', [ 'table' ] ],
							[ 'insert', [ 'link', 'picture', 'video' ] ],
							[ 'view', [ 'fullscreen', 'codeview', 'help' ] ] ]
				});
	</script>
	<script type="text/javascript" src="/js/board.js"></script>
	<!-- / -> static을 바라본다.  -->
</div>
<%@ include file="../layout/footer.jsp"%>