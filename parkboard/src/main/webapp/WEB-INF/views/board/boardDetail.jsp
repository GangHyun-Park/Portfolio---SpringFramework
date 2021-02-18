<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/resources/layout/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>parkboard Detail</title>
<c:url var="insertReplyURL" value="/restboard/insertReply"></c:url>
<c:url var="updateReplyURL" value="/restboard/updateReply"></c:url>
<c:url var="deleteReplyURL" value="/restboard/deleteReply"></c:url>

</head>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.js"></script>
	<script>
		//JQuery 시작 (댓글부분)
		
		$(document).ready(function(){
			ReplyList();
		});
		
		// 댓글 리스트
		function ReplyList(){
			if(document.readyState == "loading"){
				location.href='/';
			}
			var url = "${pageContext.request.contextPath}/restboard/getReplyList"; // restController에서 url 매핑
			var paramData = {"board_num" : "${boardDetail.board_num}"}; //파라미터로 게시글 번호를 받아온다
			//ajax
			$.ajax({
				type : 'POST', // POST타입
				url : url, 
				data : paramData,
				dataType : 'json', //json 데이터타입으로 통신
				success : function(result){
					var htmls = " ";
					if(result.length < 1){
						//htmls.push("댓글이 비었습니다.");
					} else{
						$(result).each(function(){
							htmls += '<div class="media text-muted pt-3" id="reply_num' + this.reply_num + '">'; // 댓글 번호를 id로 쓴다
		                    htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';
		                    htmls += '<title>Placeholder</title>';
		                    htmls += '<rect width="100%" height="100%" fill="#007bff"></rect>';
		                    htmls += '<text x="50%" fill="#007bff" dy=".3em">32x32</text>';
		                    htmls += '</svg>';
		                    htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
		                    htmls += '<span class="d-block">';
		                    htmls += '<strong class="text-gray-dark">' + this.reply_writter + '</strong>'; // 댓글 작성자 기입부분
		                    htmls += '<span style="padding-left: 7px; font-size: 9pt">';
		                    
		                    if( this.reply_writter == "${sessionId}" && this.reply_mail == "${sessionMail}" ) {
		                    	htmls += '<a href="javascript:void(0)" onclick="updateReply(' + this.reply_num + ', \'' + this.reply_writter + '\', \'' + this.reply_content + '\' )" style="padding-right:5px">수정</a>'; // 댓글 수정을 클릭시 넘겨주는 데이터들과 그 기능
		                    	htmls += '<a href="javascript:void(0)" onclick="deleteReply(' + this.reply_num + ')" >삭제</a>'; // 댓글 삭제 클릭시 작동
		                    }
		                    
		                    htmls += '</span>';
		                    htmls += '</span>';
		                    htmls += this.reply_content; // 댓글 내용
		                    htmls += '</p>';
		                    htmls += '</div>';
						});
					}
					$("#showReply").html(htmls); // ajax완료하면 댓글리스트 보여줌
				}
			})
		}
		
		//댓글 수정창
		function updateReply(reply_num, reply_writter, reply_content){
			var htmls = ""; 
			htmls += '<div class="media text-muted pt-3" id="reply_num' + reply_num + '">'; 
			htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';
			htmls += '<title>Placeholder</title>';
			htmls += '<rect width="100%" height="100%" fill="#007bff"></rect>';
			htmls += '<text x="50%" fill="#007bff" dy=".3em">32x32</text>';
			htmls += '</svg>';
			htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
			htmls += '<span class="d-block">';
			htmls += '<strong class="text-gray-dark">' + reply_writter + '</strong>'; 
			htmls += '<span style="padding-left: 7px; font-size: 9pt">';
			htmls += '<a href="javascript:void(0)" onclick="re_insertReply(' + reply_num + ', \'' + reply_writter + '\')" style="padding-right:5px">저장</a>';
			htmls += '<a href="javascript:void(0)" onClick="ReplyList()">취소<a>';
			htmls += '</span>';
			htmls += '</span>';		
			htmls += '<textarea name="editContent" id="editContent" class="form-control" rows="3">';
			htmls += reply_content;
			htmls += '</textarea>';
			htmls += '</p>';
			htmls += '</div>';
			$('#reply_num' + reply_num).replaceWith(htmls);
			$('#reply_num' + reply_num + ' #editContent').focus();
		}
		
		//댓글 수정 저장
		function re_insertReply(reply_num, reply_writter){
			var replyUpdateContent = $('#editContent').val();
			
			var paramData = JSON.stringify({"reply_content":replyUpdateContent, "reply_num":reply_num});
			var headers = {"Content-Type" : "application/json", "X-HTTP-Method-Override" : "POST"};
			
			$.ajax({
				url : "${updateReplyURL}"
			   ,headers : headers
			   ,data : paramData
			   ,type : 'POST'
			   ,dataType : 'text'
			   ,success:function(result){
				   console.log(result);
				   ReplyList();
			   }
				,error:function(error){
					console.log("에러 : " + error);
				}
			});
		}
		
		//댓글 삭제
		function deleteReply(reply_num){
		
				var paramData = {"reply_num": reply_num};
			
				$.ajax({
					url : "${deleteReplyURL}"
				   ,data : paramData
				   ,type : 'POST'
				   ,dataType : 'text'
				   ,success : function(result){
					   ReplyList();
				   }
					,error : function(error){
						console.log("에러 : " + error);
					}
				});
		
		}
		
		//댓글 입력
		$(document).on('click', '#insertReply', function(){
			if(${sessionId == null}){
				alert("로그인이 필요한 기능입니다.");
				location.href = "${pageContext.request.contextPath}/login/loginForm";
			} else{
			var replyContent = $('#reply_content').val();
			var replyWritter = $('#user_name').text();
			
			var paramData = JSON.stringify({"reply_content":replyContent, "reply_writter": replyWritter, "board_num":'${boardDetail.board_num}', "reply_mail":'${sessionMail}'});
			var headers = {"Content-Type" : "application/json", "X-HTTP-Method-Override" : "POST"};
			
			$.ajax({
				url : "${insertReplyURL}",
				headers : headers,
				data : paramData,
				type : 'POST',
				dataType : 'text',
				success : function(result){
					ReplyList();
					
					$('#reply_content').val('');
					$('#reply_writter').val('');
				}
				, error : function(error){
					console.log("에러 : " + error);
				}
			});
			}
		});
		
		//게시글 목록
		$(document).on('click', '#list', function(){
			location.href = "${pageContext.request.contextPath}/board/getBoardList";
		});
		
		//게시글 수정
		$(document).on("click", "#update", function(){
			var url = "${pageContext.request.contextPath}/board/boardUpdate";
			url = url + "?board_num=" + ${boardDetail.board_num};
			url = url + "&mode=update";
			
			location.href = url;
		});
		
		//게시글 삭제
		$(document).on("click", "#delete", function(){
			
			var result = confirm('게시물을 삭제하시겠습니까?');

			if(result){
				
				alert('게시물이 삭제되었습니다!');
				var url = "${pageContext.request.contextPath}/board/boardDelete";
				url = url + "?board_num=" + ${boardDetail.board_num};
				location.href = url;
			}
			else{
				
			}
		});
		
	</script>
<body>
	<article>
		<div class="container" role="main">
			<h3><strong>게시물 상세</strong></h3>
			
			<!-- 게시글 상세보기 -->
			<div class="bg-white rounded shadow-lg p-1 mb-2">
				<div class="board_title"><c:out value="${boardDetail.board_title}"/></div>
				<div class="board_info">
					<span class="board_writter"><c:out value="${boardDetail.board_writter}"/>,
					</span><span class="board_date"><c:out value="${boardDetail.board_date}"/></span>
				</div>
				<div class="board_content" style="margin-bottom : 100px">${boardDetail.board_content}</div>
				<span class="tag_title"><strong>&nbsp; Tag : </strong>
				</span><span class="board_tag" style="margin-bottom : 10px"><c:out value="${boardDetail.board_tag}"/></span>
			</div>
					
			<!-- 게시글 수정,삭제,목록 -->
			<div style="margin-bottom : 30px">
			<c:set var="text_name"  value="${boardDetail.board_writter}" />
			<c:set var="login_name"  value="${sessionId }" />
   				<c:if test="${boardDetail.board_writter eq sessionId && boardDetail.board_cate eq sessionMail}">
   					<button type="button" class="btn btn-sm btn-primary" id = "update">수정</button>
					<button type="button" class="btn btn-sm btn-primary" id = "delete">삭제</button>
   				</c:if>
				<button type="button" class="btn btn-sm btn-primary" id = "list">목록</button>
			</div>
			
			<!-- 댓글 리스트 -->
			<div class="my-3 p-3 bg-white rounded shadow-lg p-3 mb-5" style="padding-top:10px">
				<form:form name="form" id="form" role="form" modelAttribute="replyDto" method="post">
				<form:hidden path="board_num" id="board_num" />
				<form:hidden path="reply_mail" id="reply_mail" value="${sessionMail }" />
				<div class="row">
					<div class="col-sm-10">
						<form:textarea path="reply_content" id="reply_content" class="form-control" rows="3" placeholder="댓글을 입력하세요"></form:textarea>
					</div>
					<div class="col-sm-2">
						<button type="button" class="btn btn-sm btn-primary" id="insertReply" style="width:100%; margin-top:10px">저장</button>
					</div>
				</div>
				</form:form>
			</div>
			
			<div class="my-3 p-3 bg-white rounded shadow-lg p-3 mb-5" style="padding-top:10px">
				<h6 class="border-bottom pb-2 mb-0">댓글 목록</h6>
				<div id="showReply"></div>
			</div>
		</div>
	</article>
</body>
</html>