package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.Board;
import data.User;

@WebServlet("/index")
public class IndexController extends HttpServlet{

	  
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession();
		User user =(User)req.getSession().getAttribute("logonUser");
	
		String sort =req.getParameter("sorting");
		int p;
		if (req.getParameter("page") == null) {
			p = 1;
		}else {
			p = Integer.parseInt(req.getParameter("page"));
		}
		Map<String, Object> map = new HashMap<>();
		map.put("a", p*10-9);
		map.put("b", p*10);
		List<Board> list = sqlSession.selectList("boards.findByWrited", map);
		req.setAttribute("list", list);
		
		if (sort == null) {			
		} else {
			if (sort.equals("views")) {
				list = sqlSession.selectList("boards.findByViews", map);
				req.setAttribute("list", list);
			} else if (sort.equals("Likes")) {
				list = sqlSession.selectList("boards.findByLikes", map);
				req.setAttribute("list", list);
			} else {
				list = sqlSession.selectList("boards.findByWrited", map);
				req.setAttribute("list", list);
			}
		}
		
		int total = sqlSession.selectOne("boards.countBoards");
		int lastPage = total / 10 + (total % 10 > 0 ? 1 : 0);
		int last = (int) Math.ceil(p / 5.0) * 5;
		int start = last -4;
		
		last = last > lastPage ? lastPage : last;
		
		req.setAttribute("start", start); 
		req.setAttribute("last", last); 
		
		boolean existPrev = p >=6;
		boolean existNext = lastPage > last;
		req.setAttribute("existPrev", existPrev);
		req.setAttribute("existNext", existNext);
		sqlSession.commit();
		sqlSession.close();
		
		req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
	}
}

