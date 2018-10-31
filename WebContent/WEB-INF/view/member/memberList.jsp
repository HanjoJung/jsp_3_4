<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../../temp/bootStrap.jsp" />
</head>
<body>
	<c:import url="../../../temp/header.jsp" />

	<div class="container-fluid">
		<div class="row">
			<!-- 로그인 전 -->
			<a href="./memberLogin.do">Login</a> 
			<a href="./memberJoin.do">Join</a>
			<!-- 로그인 후 -->
			<a href="./memberLogOut.do">LogOut</a> 
			<a href="./memberMypage.do">MyPage</a>
		</div>
	</div>

	<c:import url="../../../temp/footer.jsp"></c:import>
</body>
</html>