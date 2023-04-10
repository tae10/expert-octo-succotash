package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.User;

@WebServlet("/user/*")
public class LoginTaskController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String ctxPath = req.getContextPath();
		String uri = req.getRequestURI().substring(ctxPath.length());

		switch (uri) {

		case "/user/logout" -> {
			req.getSession().invalidate();

			resp.sendRedirect("/");
		}

		case "/user/login" -> {

			req.getRequestDispatcher("/WEB-INF/user/login.jsp").forward(req, resp);
		}

		case "/user/login-task" -> {
			String id = req.getParameter("id");
			String pass = req.getParameter("pass");
			String nick = req.getParameter("nick");

			SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");
			SqlSession sqlSession = factory.openSession();
			
		
			
			

			User findUser = (User) sqlSession.selectOne("users.findByUser", id);
			if(findUser==null) {
				resp.sendRedirect("user/login");
				return;
			}
			String dbId = findUser.getId();
			String dbPass = findUser.getPass();

			if (id.equals(dbId) && pass.equals(dbPass)) {
				User user = new User();
				user.setId(id);
				user.setPass(pass);
				user.setNick(nick);
				HttpSession session = req.getSession();
				session.setAttribute("logon", true);
				session.setAttribute("logonUser", user);

				resp.sendRedirect("/");
		
			} else {
				resp.sendRedirect("user/login?cause=error");
		
			}

		}
		case "/user/join" -> {

			req.getRequestDispatcher("/WEB-INF/user/join.jsp").forward(req, resp);
		}
		case "/user/join-task" -> {
			String id = req.getParameter("id");
			String pass = req.getParameter("pass");
			String nick = req.getParameter("nick");

			SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");
			SqlSession sqlSession = factory.openSession();

			Map<String, Object> map = new HashMap<>();
			map.put("userId", id);
			map.put("userPass", pass);
			map.put("userNick", nick);

			sqlSession.insert("users.create", map);

			req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);

			sqlSession.commit();
			sqlSession.close();

		}

		}

	}

}
