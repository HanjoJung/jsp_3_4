<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../../temp/bootstrap.jsp"></c:import>
</head>
<body>
	<c:import url="../../../temp/header.jsp"></c:import>

	<div class="container-fluid">
		<div class="row">
			<form action="./${board}Join.do" method="post"
				enctype="multipart/form-data">
				<div class="form-group">
					<label for="id">ID:</label> <input type="text" class="form-control"
						id="id" placeholder="Enter id" name="id">
				</div>
				<div class="form-group">
					<label for="writer">Writer:</label> <input type="text"
						class="form-control" id="writer" placeholder="Enter Writer"
						name="writer">
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

				<button type="submit" class="btn btn-default">Write</button>
			</form>
		</div>
	</div>

	<c:import url="../../../temp/footer.jsp"></c:import>
</body>
</html>