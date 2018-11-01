<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../../../temp/bootStrap.jsp" />
</head>
<body>
	<jsp:include page="../../../temp/header.jsp" />

	<div class="container-fluid">
		<div class="row" align="center">
			<table class="table table-bordered table-hover"
				style="max-width: 1500px">
				<tr>
					<td style="width: 35%">ID</td>
					<td style="width: 35%">이름</td>
					<td style="width: 30%">프로필</td>
				</tr>
				<tr>
					<td>${member.id}</td>
					<td>${member.name}</td>
					<td><a href="../upload/${member.fname}"> <img
							style="max-width: 50px; max-height: 50px;" alt=""
							src="../upload/${member.fname}">
					</a></td>
				</tr>
				<tr>
					<td colspan="3">E-mail</td>
				</tr>
				<tr>
					<td colspan="3">${member.email}</td>
				</tr>
				<tr>
					<td>직종</td>
					<td>학년</td>
					<td>반</td>
				</tr>
				<tr>
					<td><c:choose>
							<c:when test="${member.kind.equals('T')}">
								선생님
						 	</c:when>
							<c:otherwise>
								학생
							</c:otherwise>
						</c:choose></td>
					<td>${member.grade}</td>
					<td>${member.ban}</td>
				</tr>

			</table>
			<a href="../index.jsp"><button>홈으로</button></a> <a
				href="./memberUpdate.do?id=${member.id}"><button>수정</button></a> <a
				href="./memberDelete.do?id=${member.id}&fname=${member.fname}"><button>삭제</button></a>
		</div>
	</div>
	<jsp:include page="../../../temp/footer.jsp" />
</body>
</html>







