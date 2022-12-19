<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>
<div class="containar">
	<div class="d-flex justify-content-end m-2">
		<form action="/board/search" method="get" class="form-inline">
			<input class="form-control mr-1" type="text" placeholder="검색어를 입력하세요" name="q" value="${q}">
			<button type="submit" class="btn btn-info ">TITLE</button>
		</form>
	</div>
</div>
<c:forEach var="board" items="${boards.content}">
	<div class="containar">
		<div class="card m-2">
			<div class="card-body">
				<h4>${board.title}</h4>
				<a href="/board/${board.id}" class="btn btn-primary">more</a>
			</div>
		</div>
	</div>
</c:forEach>
<br />
<!-- page.first
	 page.last -->
<ul class="pagination justify-content-center">
	<c:set var="isDisabled" value="disabled"></c:set>
	<c:set var="isNotDisabled" value=" "></c:set>
	<li class="page-item ${boards.first ? isDisabled : isNotDisabled}"><a class="page-link" href="?page=${boards.number - 1}">Previous</a></li>
	<c:forEach var="num" items="${pageNumbers}">
		<c:choose>
			<c:when test="${nowPage eq num}">
				<li class="page-item active"><a class="page-link" href="?page=${num-1}"> ${num} </a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${num-1}"> ${num} </a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<!-- 	<li class="page-item active"><a class="page-link" href="#">2</a></li> -->
	<li class="page-item ${boards.last ? isDisabled : isNotDisabled}"><a class="page-link" href="?page=${boards.number + 1}">Next</a></li>
</ul>
<%@ include file="layout/footer.jsp"%>