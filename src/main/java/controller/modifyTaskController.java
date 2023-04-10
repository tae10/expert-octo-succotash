package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.User;

@WebServlet("/board/modify-task")
public class modifyTaskController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession();
		User logonUser = (User) req.getSession().getAttribute("logonUser");
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String boardId = req.getParameter("boardId");
		Map<String, Object> map = new HashMap<>();
		map.put("title", title);
		map.put("content", content);
		map.put("boardId", boardId);
		
		sqlSession.update("boards.updateBoard", map);
		sqlSession.commit();
		sqlSession.close();
		resp.sendRedirect("/board/detail?boardId="+boardId);
	}
}
