<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri = "http://www.springframework.org/tags/form" %>
<%@ include file="/resources/layout/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글을 써보자</title>
	
	<script>
		$(document).ready(function(){
			var mode = '<c:out value="${mode}"/>';
			if(mode == 'update'){
				$("#board_writter").prop('readonly',true);
				$("input:hidden[name='board_num']").val('<c:out value="${boardDetail.board_num}"/>');
				$("input:hidden[name='mode']").val('<c:out value="${mode}"/>');
				$("#board_writter").val('<c:out value="${boardDetail.board_writter}"/>');
				$("#board_title").val('<c:out value="${boardDetail.board_title}"/>');
				$("#board_content").val('<c:out value="${boardDetail.board_content}"/>');
				$("#board_tag").val('<c:out value="${boardDetail.board_tag}"/>');
				
				console.log("#board_num");
			}
		});
	
		$(document).on('click', '#insert', function(e){
			e.preventDefault();
			$("#form").submit();
		});
		
		$(document).on('click', '#list', function(e){
			e.preventDefault();
			location.href="${pageContext.request.contextPath}/board/getBoardList";
		});
		
	
		
	</script>
</head>
<script src="https://cdn.ckeditor.com/ckeditor5/23.1.0/classic/ckeditor.js"></script>
<body>
	<article>
		<div class="container" role="main">
			<h2>board Write</h2>
			<form:form name = "form" id = "form" role = "form" modelAttribute="boardDto" method = "post"
			action="${pageContext.request.contextPath}/board/insertBoard">
				<form:hidden path="board_num" items="${board_num}"/>
				<input type="hidden" name="mode"/>
				
				<div class="mb-3">
					<label for="board_title">제목</label>
					<form:input path="board_title" id="board_title" class="form-control" placeholder="제목을 입력하세요" />
				</div>
				<div class="mb-3">
					<label for="board_writter">작성자</label>
					<form:input path="board_writter" id="board_writter" value="${sessionId }" class="form-control" placeholder="${sessionId }" readonly="true" />
				</div>
				<div class="mb-3">
					<label for="board_tag">내용</label>
					<form:textarea path="board_content" rows="5" id="board_content" class="form-control" placeholder="내용을 입력하세요" />
				</div>
				
				<div class="mb-3">
					<label for="board_content">태그</label>
					<form:input path="board_tag" id="board_tag" class="form-control" placeholder="태그를 입력하세요" />
				</div>
			</form:form>
			
			<div>
				<button type="button" class="btn btn-sm btn-primary" id="insert">저장</button>
				<button type="button" class="btn btn-sm btn-primary" id="list">목록</button>
			</div>
		</div>	
	</article>
	<!-- <script src="${pageContext.request.contextPath}/resources/js/ckeditor.js"></script>-->
</body>
</html>