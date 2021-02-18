<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/resources/layout/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Park Board</title>
</head>
<script>
$(document).on('click', '#list', function(){
	location.href = "${pageContext.request.contextPath}/board/getBoardList";
});
</script>
<body>
	<article>
		<div class="container">
		<div class="table-responsive">
			<h2><strong>개발자 소개</strong></h2>
			
			<main role="main">
  <!-- Main jumbotron for a primary marketing message or call to action -->
  <div class="jumbotron">
    <div class="container">
      <h3 class="display-7"><strong>안녕하세요 ! 박강현입니다.</strong></h3>
      <p>아직은 많이 부족하지만, 정성껏 만들어본 게시판입니다. 피드백은 언제나 환영합니다.</p>
      <p><a class="btn btn-primary btn-lg" href="#" role="button" id="list">게시판이동 »</a></p>
    </div>
  </div>

  <div class="container">
    <!-- Example row of columns -->
    <div class="row">
      <div class="col-md-4">
        <h2>Development Environment</h2>
        <p>- O/S : Windows 10</p>
        <p>- IED : Eclipse 4.16.0</p>
        <p>- JDK : JAVA SE 14.0.2</p>
        <p>- Tomcat : Tomcat 9.0</p>
        <p>- Maven : Maven 3.6.3</p>
        <p>- DB : MySQL 8.0.21</p>
      </div>
      <div class="col-md-4">
        <h2>Programming Language</h2>
        <p>- JAVA</p>
        <p>- HTML/CSS</p>
        <p>- JavaScript</p>
        <p>- JSP</p>
      </div>
      <div class="col-md-4">
        <h2>Framework & Flatform</h2>
        <p>- Spring</p>
        <p>- Mybatis</p>
        <p>- bootstrap</p>
        <p>- jQuery</p>
        <p>- Ajax</p>
        <p>- CKEditor5</p>
      </div>   
    </div>
    <hr>
  </div> 
</main>
		</div>
		</div>
	</article>
</body>
</html>