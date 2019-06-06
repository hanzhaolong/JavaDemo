package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import daoImpl.UserDaoImpl;
import entity.User;

@SuppressWarnings("serial")
public class DengluServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取操作符
		String oper = req.getParameter("oper");
		if ("login".equals(oper)) {
			// 调用登录处理方法
			checkUserLogin(req, resp);
		} else if ("out".equals(oper)) {
			// 调用退出功能
			System.out.println("调用退出功能");
			userOut(req, resp);
		} else if ("reg".equals(oper)) {
			// 调用注册功能
			loginUser(req, resp);
		} else {
			System.out.println("没有找到相应的操作符：" + oper);
		}
	}

	// 用户注册
	private void loginUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String information = req.getParameter("information");

		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setInformation(information);

		System.out.println(user + " 正在注册");

		UserDao ud = new UserDaoImpl();

		if (ud.register(user)) {
			req.setAttribute("userName", userName);
			System.out.println(userName+" 注册成功");
			req.getRequestDispatcher("/denglu.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("zhuce.jsp");
		}
	}

	// 用户退出
	private void userOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 获取session对象
		HttpSession hs = req.getSession();
		// 强制销毁session
		hs.invalidate();
		System.out.println("调用退出功能");
		// 重定向到登录页面
		resp.sendRedirect("/college_website/denglu.jsp");
	}

	// 处理登录
	private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// 获取请求信息
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		System.out.println(userName + ":" + password + " 正在登录系统");
		UserDao ud = new UserDaoImpl();
		if (userName.equals("韩") && password.equals("123456")) {
			System.out.println("管理员成功登录系统");
			req.getRequestDispatcher("/main/main.jsp").forward(req, resp);
		} else if (ud.login(userName, password) != null) {
			// 获取session对象
			HttpSession hs = req.getSession();
			// 将用户数据存储到session中
			hs.setAttribute("user", ud.login(userName, password));
			System.out.println("用户" + userName + "登录成功");
			req.getRequestDispatcher("/main2/main.jsp").forward(req, resp);
		} else {
			req.setAttribute("flag", "false");
			req.getRequestDispatcher("/denglu.jsp").forward(req, resp);
			System.out.println("登录失败");
		}

	}

}
