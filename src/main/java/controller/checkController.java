package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.Board;

@WebServlet("/board/check")
public class checkController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession();
		String caseCheck = req.getParameter("caseCheck");
		String boardId = req.getParameter("boardId");
		Board board = sqlSession.selectOne("boards.findByBoardId",boardId);
		req.setAttribute("boardId", boardId);
		req.setAttribute("caseCheck", caseCheck);
		req.getRequestDispatcher("/WEB-INF/board/check.jsp").forward(req, resp);
		
		sqlSession.commit();
		sqlSession.close();
	}
}
