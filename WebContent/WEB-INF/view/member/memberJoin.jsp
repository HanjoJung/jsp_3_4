<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../../temp/bootStrap.jsp" />
<script type="text/javascript">
	$(function() {
		$("#join").click(function() {
			var check = $("#idCheck").val();
			if (check == "s") {
				alert("OK");
			} else {
				alert("id 중복 체크");
			}
		})
		$("#id").change(function() {
			$("#idCheck").val('f');
		})
		$("#btn").click(
				function() {
					$("#idCheck").val('f');
					var id = document.frm.id.value;
					window.open("./memberCheckId.do?id=" + id, "",
							"width=300, height=200, top=300, left=500");
				})
	})
</script>
</head>
<body>
	<c:import url="../../../temp/header.jsp" />

	<div class="container-fluid">
		<div class="row">
			<form action="./memberJoin.do" method="post"
				enctype="multipart/form-data" name="frm">
				<input type="hidden" value="f" id="idCheck" name="idCheck">

				<div class="form-group">
					<label for="id">ID:</label> <input type="text" class="form-control"
						id="id" placeholder="Enter id" name="id"> <input
						type="button" id="btn" class="btn btn-default" value="중복확인">
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
					선생님<input type="radio" name="kind" value="T"> 학생<input
						type="radio" name="kind" value="S">
				</div>
				<div class="form-group">
					<p>
						1학년<input type="radio" name="grade" value="1"> 1반<input
							type="radio" name="ban" value="1">
					</p>
					<p>
						2학년<input type="radio" name="grade" value="2"> 2반<input
							type="radio" name="ban" value="2">
					</p>
					<p>
						3학년<input type="radio" name="grade" value="3"> 3반<input
							type="radio" name="ban" value="3">
					</p>
				</div>
				<div class="form-group">
					<label for="file">프로필 사진:</label> <input type="file"
						class="form-control" id="file" name="f">
				</div>

				<input type="button" id="join" class="btn btn-default" value="가입">
			</form>
		</div>
	</div>

	<c:import url="../../../temp/footer.jsp"></c:import>
</body>
</html>