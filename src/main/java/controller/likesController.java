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

@WebServlet("/board/likes")
public class likesController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession();
		User logonUser = (User) req.getSession().getAttribute("logonUser");
		
		
		int boardId =Integer.parseInt(req.getParameter("boardId")); 
		Map<String, Object> map = new HashMap<>();
		map.put("userId", logonUser.getId());
		map.put("boardId", boardId);
		sqlSession.insert("recommends.likes", map);
		sqlSession.update("recommends.updateLike",boardId);
		sqlSession.commit();
		sqlSession.close();
		
		resp.sendRedirect("/detail?boardId="+boardId);
	}
}
