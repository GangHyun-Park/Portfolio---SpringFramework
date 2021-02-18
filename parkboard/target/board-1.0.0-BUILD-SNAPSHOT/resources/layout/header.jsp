<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
	  
<!-- bootstrap -->
<link rel="stylesheet" 
href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" 
crossorigin="anonymous">

<!-- common.css -->
<link rel = "stylesheet" href="<c:url value='/resources/css/common.css'/>" >

<script>
$(document).on('click', '#list', function(){
	location.href = "${pageContext.request.contextPath}/board/getBoardList";
});
$(document).on('click', '#skill', function(){
	location.href = "${pageContext.request.contextPath}/board/skill";
});
$(document).on('click', '#start', function(){
	location.href = "/";
});
$(document).on('click', '#login', function(){
	location.href = "${pageContext.request.contextPath}/login/loginForm";
});
$(document).on('click', '#logout', function(){
	var result = confirm('로그아웃 하시겠습니까?');
	if(result){
	alert("로그아웃을 했습니다 !");
	location.href = "${pageContext.request.contextPath}/login/logoutForm";
	}
	else{
		
	}
});
</script>
<!-- nabvar -->
<nav class="navbar navbar-expand-md py-3 navbar-dark bg-dark fixed-top">
  <a class="navbar-brand" href="#" id="start">Park Board</a>
  <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="navbar-collapse collapse" id="navbarsExample03" style="">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="#" id="skill">개발자 소개
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#" id="list">게시판</a>
      </li>
    </ul>
    <c:choose>
   		<c:when test="${sessionId == null }">
    		<a class="nav-link" href="#" id="login">로그인</a>
   		</c:when>
		<c:otherwise>
		<span class="navbar-text">환영합니다! &nbsp;</span>
		<span class="navbar-text" id="user_name" style="color:lime">${sessionId}</span>
		<span class="navbar-text">님&nbsp;&nbsp;</span>
			<a class="nav-link" href="#" id="logout">로그아웃</a>
		</c:otherwise>
    </c:choose>
  </div>
</nav>
