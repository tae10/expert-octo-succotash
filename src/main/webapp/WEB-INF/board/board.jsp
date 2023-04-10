<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div align="left">
			<a href="/index?sorting=writed">등록순</a> 
			<a href="/index?sorting=views">조회순</a> 
			<a href="/index?sorting=likes">추천순</a>
		</div>
		<div class="root">
			<table>
				<tr>
					<th>제목</th>
					<th>글쓴이</th>
					<th>작성일</th>
					<th>조회</th>
					<th>추천</th>
				</tr>
				<c:forEach items="${list }" var="one">
						<tr>
							<td><a href="/detail?boardId=${one.boardId }">${one.title }</a></td>
							<td width="15%">${one.writer }</td>
							<td width="15%">${one.writed }</td>
							<td width="10%">${one.views }</td>
							<td width="10%">${one.likes }</td>
						</tr>
				</c:forEach>
			</table>
		</div>
</body>
</html>