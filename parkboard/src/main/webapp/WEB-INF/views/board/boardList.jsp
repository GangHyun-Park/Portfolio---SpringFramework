<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/resources/layout/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Park Board</title>

	<!-- 중복 url 처리 -->
	<c:url var = "getBoardListURL" value="/board/getBoardList"></c:url>
	<script>
	
		//글쓰기처리
		$(document).on('click', '#Write', function(e){
			if(${sessionId == null}){
				alert("로그인이 필요한 기능입니다.");
				location.href = "${pageContext.request.contextPath}/login/loginForm";
			} else{
				e.preventDefault();
				location.href = "${pageContext.request.contextPath}/board/boardWrite";
			}
		});
		
		//검색처리
		$(document).on('click','#search', function(e){
			e.preventDefault();
			var url = "${getBoardList}";
			url = url + "?searchType=" + $('#searchType').val();
			url = url + "&keyword=" + $('#keyword').val();
			location.href = url;
		});
		
		//글 자세히보기
		function fn_boardDetail(board_num){
			var url = "${pageContext.request.contextPath}/board/getBoardDetail";
			url = url + "?board_num=" + board_num;
			location.href = url;
		}
		
		//페이징 처리
		function fn_pagination(page, range, rangeSize, searchType, keyword){
			var url = "${getBoardList}";
			url = url + "?page=" + page;
			url = url + "&range=" + range;
			url = url + "&searchType=" + searchType;
			url = url + "&keyword=" + keyword;
			console.info(page, range, rangeSize);

			location.href = url;
		}
		//페이지 이전버튼
		function fn_prev(page, range, rangeSize, searchType, keyword){
				var page = ((range - 2) * rangeSize) + 1;
				var range = range - 1;
			
				var url = "${getBoardList}";
				url = url + "?page=" + page;
				url = url + "&range=" + range;
				url = url + "&searchType=" + $('#searchType').val();
				url = url + "&keyword=" + keyword;
				console.info(page, range, rangeSize);
				location.href = url;	
		}
		
		//페이지 다음버튼
		function fn_next(page, range, rangeSize, searchType, keyword){
				var page = parseInt((range * rangeSize)) + 1;
				var range = parseInt(range) + 1;
			
				var url = "${getBoardList}";
				url = url + "?page=" + page;
				url = url + "&range=" + range;
				url = url + "&searchType=" + $('#searchType').val();
				url = url + "&keyword=" + keyword;
				console.info(page, range, rangeSize);

				location.href = url;
		}
		
	</script>
</head>
<body>
	<article>
	<div class = "container">
	<div class = "table-responsive">
	<h2><strong>게시판</strong></h2>
		<table class="table table-striped table-sm">
			<colgroup>
				<col style="width:5%" />
				<col style="width:auto" />
				<col style="width:15%" />
				<col style="width:10%" />
				<col style="width:10%" />
			</colgroup>
			<thead>
				<tr>
					<th>NO</th>
					<th>글제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody><!-- 게시판 목록 -->
				<c:choose>
					<c:when test="${empty boardList}" >
						<tr><td colspan="5" align="center">데이터가 없습니다.</td></tr>
					</c:when>
					<c:when test="${!empty boardList}">
						<c:forEach var="list" items="${boardList}">
						<tr>
							<td><c:out value="${list.board_num}" /></td>
							<td>
								<a href="#" onClick = "fn_boardDetail(<c:out value="${list.board_num}"/>)">
								<c:out value="${list.board_title}" /></a></td>
							<td><c:out value="${list.board_writter}" /></td>
							<td><c:out value="${list.board_cnt}" /></td>
							<td><c:out value="${list.board_date}" /></td>
						</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		</table>
		</div>
			<div>
				<button type="button" class="btn btn-sm btn-primary" id="Write" style = "float:right">글쓰기</button>
			</div>
			
			<!-- 검색후 페이징 처리, 페이징처리 -->
			<div id="paginationBox">
				<ul class="pagination">
					<c:if test="${pagination.prev}">
						<li class="page-item"><a class="page-link" href="#" onClick="fn_prev('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}','${search.searchType}', '${search.keyword}')">이전</a></li>
					</c:if>
					<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="idx">
						<li class="page-item <c:out value="${pagination.page == idx ? 'active' : ''}"/> "><a class="page-link" href="#" onClick="fn_pagination('${idx}', '${pagination.range}', '${pagination.rangeSize}', '${search.searchType}', '${search.keyword}' )"> ${idx} </a></li>
					</c:forEach>
					<c:if test="${pagination.next}">
						<li class="page-item"><a class="page-link" href="#" onClick="fn_next('${pagination.range}', '${pagination.range}', '${pagination.rangeSize}','${search.searchType}', '${search.keyword}')" >다음</a></li>
					</c:if>
				</ul>
			</div>
			
			<!-- 검색기능 -->
			<div class="form-group row justify-content-center">
				<div class="w100" style="padding-right:10px">
					<select class="form-control form-control-sm" name="searchType" id="searchType">
						<option value="board_title">제목</option>
						<option value="board_content">내용</option>
						<option value="board_writter">작성자</option>
					</select>
				</div>
				<div class="w300" style="padding-right:10px">
					<input type="text" class="form-control form-control-sm" name="keyword" id="keyword" >
				</div>
				<div>
					<button class="btn btn-sm btn-primary" name="search" id="search">검색</button>
				</div>
			</div>
		</div>
		</article>
</body>
</html>