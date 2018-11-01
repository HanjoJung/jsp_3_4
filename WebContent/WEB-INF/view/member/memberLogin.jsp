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
			<h3>${message}</h3>
		</div>
		<div class="row">
			<form action="./memberLogin.do" method="post">
				<div class="form-group">
					<label for="id">ID:</label> <input type="text" class="form-control"
						id="id" placeholder="Enter id" name="id">
				</div>
				<div class="form-group">
					<label for="pw">PASSWORD:</label> <input type="password"
						class="form-control" id="pw" placeholder="Enter password"
						name="pw">
				</div>
				<div class="form-group">
					선생님<input type="radio" id="kind" name="kind" value="T"> 학생<input
						type="radio" id="kind" name="kind" value="S">
				</div>

				<button type="submit" class="btn btn-default">Write</button>
			</form>
		</div>
	</div>

	<c:import url="../../../temp/footer.jsp"></c:import>
</body>
</html>