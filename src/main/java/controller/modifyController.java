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
import data.User;

@WebServlet("/board/modify")
public class modifyController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession();
		
		String boardId = req.getParameter("boardId");
		Board board = sqlSession.selectOne("boards.findByBoardId",boardId);
		req.setAttribute("board", board);
		
		req.getRequestDispatcher("/WEB-INF/views/board/modify.jsp").forward(req, resp);
	}
}
