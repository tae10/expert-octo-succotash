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

import data.Board;
import data.Recommend;
import data.User;

@WebServlet("/detail")
public class DetailController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession();
		User logonUser = (User) req.getSession().getAttribute("logonUser");
		String boardId = req.getParameter("boardId");
		Board board = sqlSession.selectOne("boards.findByBoardId",boardId);
		req.setAttribute("board", board);
		sqlSession.update("boards.updateViews", boardId);
	 if(logonUser !=null) {
		 
		Map<String, Object> map = new HashMap<>();
		map.put("userId", logonUser.getId());
		map.put("boardId", boardId);
		Recommend recommend = sqlSession.selectOne("recommends.findById", map);
		if (recommend != null) {
			req.setAttribute("status", recommend.getStatus());			
		}
	 }
		sqlSession.commit();
		sqlSession.close();
		req.getRequestDispatcher("/WEB-INF/board/detail.jsp").forward(req, resp);
	}
}