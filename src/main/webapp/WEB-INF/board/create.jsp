<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="/create/task" autocomplete="off">
			<div align="center">
				제목 :<input type="text" name="title" placeholder="제목">
			</div>
			<div align="center">
				내용:
				<textarea type="text" name="content" placeholder="내용">
		    </textarea>
			</div>
			<c:if test="${empty sessionScope.logonUser }">
				<div align="center">
					<input type="text" name="writer" placeholder="작성자">
				</div>

				<div align="center">
					<input type="text" name="boardPass" placeholder="비밀번호">
				</div>
			</c:if>
			<button type="submit"  class="">등록</button>
		</form>
	</div>
</body>
</html>