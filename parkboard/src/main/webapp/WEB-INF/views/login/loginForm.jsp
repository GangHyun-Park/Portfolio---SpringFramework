<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/resources/layout/header.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ParkBoard</title>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
	<div style="text-align: center">
		&nbsp;&nbsp;
		<h1><strong>Park Board</strong></h1>
		&nbsp;
	</div>
	<div class="container">
		<div class="table-responsive">
			<div class="jumbotron">
    			<div class="container">
					<div style="text-align: center">
						&nbsp;&nbsp;
						<h4><strong>통합 로그인</strong></h4>
						&nbsp;&nbsp;
					</div>
					<!-- 네이버 로그인 -->
					<div id="naver_id_login" style="text-align:center"><a href="${url}">
						<img width="223" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/></a></div>
					
					<!-- 카카오 로그인 -->
					<div id="kakao_id_login" style="text-align: center"> 
    				<a href="${kakao_url}"> 
    				<img width="245" src="/resources/images/kakaoButton.png" /></a></div>
 	
 					<!-- 구글로그인 -->
 					<div id="google_id_login" style="text-align: center">
 					<a href="${google_url}"> 
    				<img width="245" src="/resources/images/googleButton.png" /></a></div>
 				</div>
 			</div>
 		</div>
 	</div>
</body>

</html>