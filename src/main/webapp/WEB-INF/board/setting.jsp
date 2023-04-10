<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD</title>
<link rel="stylesheet" href="/resource/css/style.css?${millis }">
</head>
<body>
	<h1>글 수정</h1>
	<c:if test="${param.cause eq 'error' }">
		글의 양식이 맞지 않습니다. 다시 작성해주세요
	</c:if>
	<form action="/board/modify-task">
	<input type="hidden" name="boardId" value="${board.boardId }"/>
		<div align="right">
			<button>작성하기</button>
		</div>
		<div>
		</div>
		<div>
			<input type="text" name="title" placeholder="제목" value="${board.title }"/>
		</div>
		<div>
			<input type="text" name="content" value="${board.content }" placeholder="내용">
		</div>
	</form>
</body>
</html>