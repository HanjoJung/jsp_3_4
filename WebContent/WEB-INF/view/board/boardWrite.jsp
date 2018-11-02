<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Theme Made By www.w3schools.com - No Copyright -->
<title>Bootstrap Theme Company Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="../../../temp/bootStrap.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		$("#btn").click(function() {
			if($("#title") == null)
				alert("제목을 입력하세요");
		})
	})
</script>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="60">

	<jsp:include page="../../../temp/header.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="row">
			<form action="./${board}Write.do" method="post"
				enctype="multipart/form-data">
				<div class="form-group">
					<label for="title">Title:</label> <input type="text"
						class="form-control" id="title" placeholder="Enter Title"
						name="title">
				</div>
				<div class="form-group">
					<label for="writer">Writer:</label> <input type="text"
						class="form-control" id="writer" readonly="readonly"
						value="${member.id}" name="writer">
				</div>
				<div class="form-group">
					<label for="contents">Contents:</label>
					<textarea rows="25" cols="" class="form-control" name="contents"></textarea>
				</div>

				<div class="form-group">
					<label for="file">File:</label> <input type="file"
						class="form-control" id="file" name="f1">
				</div>
				<div class="form-group">
					<label for="file">File:</label> <input type="file"
						class="form-control" id="file" name="f2">
				</div>

				<button id="btn" class="btn btn-default">등록</button>
			</form>
		</div>
	</div>


	<jsp:include page="../../../temp/footer.jsp"></jsp:include>

</body>
</html>
