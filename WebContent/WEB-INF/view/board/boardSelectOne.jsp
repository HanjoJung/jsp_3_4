<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../../../temp/bootStrap.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../../../temp/header.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="row" align="center">
			<table class="table table-bordered table-hover"
				style="max-width: 1500px">
				<tr>
					<td style="width: 10%">번호</td>
					<td style="width: 50%">제목</td>
					<td style="width: 15%">작성자</td>
					<td style="width: 15%">작성날짜</td>
					<td style="width: 10%">조회수</td>
				</tr>
				<tr>
					<td>${dto.num}</td>
					<td>${dto.title}</td>
					<td>${dto.writer}</td>
					<td>${dto.reg_date}</td>
					<td>${dto.hit}</td>
				</tr>
				<tr>
					<td colspan="5" align="center">내용</td>
				</tr>
				<tr>
					<td colspan="5" align="center">${dto.contents}</td>
				</tr>
				<tr>
					<td colspan="5">
					<c:forEach items="${files}" var="fileDTO">
							<h3 style="display: inline-block;">
								<a href="../upload/${fileDTO.fname}"><img
									style="max-width: 50px; max-height: 50px;" alt=""
									src="../upload/${fileDTO.fname}"></a>
							</h3>
					</c:forEach></td>
				</tr>

			</table>
			<a href="${board}List.do"><button>목록으로</button></a>
			<c:if test="${not empty member and member.id eq dto.writer}">
				<a
					href="./${board}Update.do?num=${dto.num}&writer=${dto.writer}"><button>수정</button></a>
				<a
					href="./${board}Delete.do?num=${dto.num}&writer=${dto.writer}"><button>삭제</button></a>
			</c:if>
		</div>
	</div>
	<jsp:include page="../../../temp/footer.jsp"></jsp:include>
</body>
</html>