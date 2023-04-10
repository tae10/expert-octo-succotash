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
	<div style="border-bottom: solid 1px black" align="center">
		<h1>${board.title }</h1>
		<c:choose>
			<c:when test="${!empty board.boardPass }">
				<div>
					<a href="/board/check?boardId=${board.boardId }&caseCheck=1">수정</a> |
					<a href="/board/check?boardId=${board.boardId }&caseCheck=2">삭제</a>
				</div>				
			</c:when>
			<c:when test="${empty board.boardPass }">
				<c:if test="${logonUser.id eq board.userId }">
					<div>
						<a href="/setting?boardId=${board.boardId }">수정</a> |
						<a href="/delete?boardId=${board.boardId }">삭제</a>
					</div>		
				</c:if>
			</c:when>
		</c:choose>
		조회수${board.views } | 추천${board.likes }
		<br/>
		${board.writer } | ${board.writed }
		<div>
			<c:if test="${sessionScope.logonUser ne null }">
				<c:if test="${status ne '1' }">
					<a href="/board/likes?boardId=${board.boardId }"><button>추천</button></a>
				</c:if>
			</c:if>
		</div>
	</div>
	<div align="center">
		${board.content }
	</div>
</body>
</html>