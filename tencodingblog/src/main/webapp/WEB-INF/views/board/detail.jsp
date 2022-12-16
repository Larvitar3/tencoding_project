<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<button class="btn btn-info" onclick="history.back();">뒤로가기</button>
	<c:if test="${board.user.id == principal.user.id }">
		<a class="btn btn-warning" id="" href="/board/ ${board.id}/update_form">수정</a>
		<button class="btn btn-danger" id="btn--delete">삭제</button>
	</c:if>
	<br /> <br /> <br />
	<div>
		<input type="hidden" id="board-id" value="${board.id}">
		글 번호 | <span id=""><i> ${board.id + 100} </i></span>
	</div>
	<div>
		글 작성자 | <span id=""><i> 『 ${board.user.username} 』 </i></span>
	</div>
	<br /> <br />
	<div class="">
		<h3>${board.title}</h3>
	</div>
	<div class="">${board.content}</div>
	<br />
	<h5 style="margin-bottom: 20px">◈ 댓글 목록</h5>
	<div style="border: 1px solid gray;" class="rounded">
		<ul style="list-style: none; margin-top: 20px;">
			<c:forEach var="reply" items="${board.replys}">
				<li style="padding: 30px 5px;">
					<div class="" style="float: left; line-height: 40px;">${reply.content}</div>
					<button type="button" class="btn btn-danger" style="float: right; margin-right: 30px; height: 40px;">삭제</button>
					<div class="" style="float: right; line-height: 40px;">작성자 | ${reply.user.username} &nbsp&nbsp&nbsp</div>
				</li>
				<div style="height: 1px; background-color: gray; width: 95%; margin-top: 40px;"></div>
			</c:forEach>
		</ul>
		<div class="card-body">
			<textarea rows="1" class="form-control" id="content"></textarea>
		</div>
		<button type="button" class="btn btn-info" id="btn-reply-save"
		style="margin-left: 87%; margin-bottom: 20px;">등록</button>
	</div>
</div>
<script type="text/javascript" src="../js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
