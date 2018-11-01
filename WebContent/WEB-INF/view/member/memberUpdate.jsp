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
			<form action="./memberUpdate.do" method="post"
				enctype="multipart/form-data">
				<div class="form-group">
					<label for="id">ID:</label> <input type="text" class="form-control"
						id="id" readonly="readonly" name="id" value="${member.id}">
				</div>
				<div class="form-group">
					<label for="pw1">PASSWORD:</label> <input type="password"
						class="form-control" id="pw1" placeholder="Enter password"
						name="pw1">
				</div>
				<div class="form-group">
					<label for="pw2">PASSWORD:</label> <input type="password"
						class="form-control" id="pw2" placeholder="Enter password"
						name="pw2">
				</div>
				<div class="form-group">
					<label for="name">이름:</label> <input type="text"
						class="form-control" id="name" placeholder="Enter name"
						name="name">
				</div>
				<div class="form-group">
					<label for="email">E-mail:</label> <input type="email"
						class="form-control" id="email" placeholder="Enter E-mail"
						name="email">
				</div>
				<div class="form-group">
					선생님<input type="radio" id="kind" name="kind" value="T"> 학생<input
						type="radio" id="kind" name="kind" value="S">
				</div>
				<div class="form-group">
					<p>
						1학년<input type="radio" id="grade" name="grade" value="1">
						1반<input type="radio" id="ban" name="ban" value="1">
					</p>
					<p>
						2학년<input type="radio" id="grade" name="grade" value="2">
						2반<input type="radio" id="ban" name="ban" value="2">
					</p>
					<p>
						3학년<input type="radio" id="grade" name="grade" value="3">
						3반<input type="radio" id="ban" name="ban" value="3">
					</p>
				</div>
				<div class="form-group">
					<label for="file">프로필 사진:</label> <input type="file"
						class="form-control" id="file" name="f">
				</div>

				<button type="submit" class="btn btn-default">수정</button>
			</form>
		</div>
	</div>

	<c:import url="../../../temp/footer.jsp"></c:import>
</body>
</html>