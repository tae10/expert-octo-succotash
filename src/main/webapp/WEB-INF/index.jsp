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
	<div class="moim-form">
		<c:choose>
			<c:when test="${sessionScope.logon }">
				<div style="padding: 10px 20px; display: flex; align-items: center; gap:10px">
					<b>${logonUser.nick }</b>
				</div>
				<div>
					<a href="/user/logout">로그아웃</a>
				</div>
			</c:when>
			<c:otherwise>
				<div style="padding: 10px 20px;">
					<a href="/user/login">로그인</a>
					<a href="/user/join">가입하기</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="moim-form">
		<h1>게시판</h1>
		<div align="left">
			<a href="/create">글쓰기</a>
		</div>
		<div align="center">
			<a href="/index?sorting=writed">등록순</a> 
			<a href="/index?sorting=views">조회순</a> 
			<a href="/index?sorting=likes">추천순</a>
		</div>
		<br/>
		<div align="center" >
			<table>
				<tr>
				    <th>번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>작성일</th>
					<th>조회</th>
					<th>추천</th>
				</tr>
				<c:forEach items="${list }" var="li">
						<tr>
						    <td style="text-align: center;">${li.boardId }</td>
							<td style="text-align: center;"><a href="/detail?boardId=${li.boardId }">${li.title } </a></td>
							<td style="text-align: center;"width="15%">${li.writer }</td>
							<td style="text-align: center;"width="15%">${li.writed }</td>
							<td style="text-align: center;"width="10%">${li.views }</td>
							<td style="text-align: center;"width="10%">${li.likes }</td>
						</tr>
				</c:forEach>
			</table>
			<br/>
			<div>
			<c:set var="currentPage" value="${empty param.page ? 1: param.page }"/>
			<c:if test="${existPrev }">
					<a href="/index?page=${start -1 }">◁</a>
			</c:if>
			<c:forEach begin="${start }" end="${last }" var="p">
			<c:choose>
				<c:when test="${p eq currentPage }">
					<b style="color:green">${p }</b>
				</c:when>
				<c:otherwise>
					<a href="/index?page=${p }">${p }</a> 
				</c:otherwise>
			</c:choose>
			</c:forEach>
			<c:if test="${existNext }">
					<a href="/index?page=${last + 1 }">▷</a>
			</c:if>
			</div>
		</div>
	</div>
</body>
</html>