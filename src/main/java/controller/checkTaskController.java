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

@WebServlet("/board/check-task")
public class checkTaskController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession();
		String caseCheck = req.getParameter("caseCheck");
		String boardId = req.getParameter("boardId");
		Board board = sqlSession.selectOne("boards.findByBoardId",boardId);
		String pass = req.getParameter("pass");
		if (pass.equals(board.getBoardPass())) {
			if (caseCheck.equals("1")) {
				resp.sendRedirect("/board/modify?boardId="+boardId);
			}else {
				resp.sendRedirect("/board/delete?boardId="+boardId);
			}
		}else {
			resp.sendRedirect("/board/check?cause=error&boardId="+boardId+"&caseCheck="+caseCheck);
		}
	}
}
