package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticleDao;
import dao.CommentDao;
import dao.UserDao;
import daoImpl.ArticleDaoImpl;
import daoImpl.CommentDaoImpl;
import daoImpl.UserDaoImpl;
import entity.Article;
import entity.Comment;
import entity.User;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取操作符
		String oper = req.getParameter("oper");
		
		if("changepwd".equals(oper)) {
			changePwd(req,resp);
		}else if("changeinfo".equals(oper)) {
			changeInfo(req,resp);
		}else if("getallart".equals(oper)) {
			getAllArt(req,resp);
		}else if("searchart".equals(oper)) {
			searthArt(req,resp);
		}else if("addcomment".equals(oper)) {
			addComment(req,resp);
		}else if("getmycom".equals(oper)) {
			getMyCom(req,resp);
		}else if("deletemycom".equals(oper)) {
			deleteMyCom(req,resp);
		}
		else {
			System.out.println("没有找到相应的操作符：" + oper);
		}
	}

	private void deleteMyCom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String comId = req.getParameter("commentId");
		int commentId = 0;
		if(comId!=null) {
			commentId = Integer.valueOf(comId);
		}
		System.out.println("正在删除评论"+commentId);
		CommentDao cd = new CommentDaoImpl();
		if(cd.delete(commentId)) {
			System.out.println("删除评论成功");
			req.setAttribute("flag",true);
			req.getRequestDispatcher("/comment2/deletemycom.jsp").forward(req, resp);
		}
		
	}

	private void getMyCom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User)req.getSession().getAttribute("user"); 
		int userId = user.getUserId();
		CommentDao cd = new CommentDaoImpl();
		List<Comment> list = cd.getCommentOfUser(userId);
		if(list!=null) {
			req.setAttribute("list", list);
			req.getRequestDispatcher("/comment2/getmycom.jsp").forward(req, resp);
			return ;
		}
	}

	private void addComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String comValue = req.getParameter("commentValue");
		String aId = req.getParameter("artId");
		int artId = 0;
		if(aId!=null) {
			artId = Integer.valueOf(aId); 
		}
		User user = (User)req.getSession().getAttribute("user");
		int userId = user.getUserId();
		CommentDao cd = new CommentDaoImpl();
		if(artId!=0 && user!=null && comValue!=null) {
			System.out.println("用户 "+userId+"正在给文章 "+artId+"添加评论："+comValue);
			Date commentDate = new Date(System.currentTimeMillis());
			if(cd.addComment(userId, artId, comValue, commentDate)) {
				System.out.println("添加评论成功");
				searthArt(req,resp);
				
			}
		}
	}

	private void searthArt(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String aId = req.getParameter("artId");
		int artId = 0;
		if(aId!=null) {
			artId = Integer.valueOf(aId);
			System.out.println("正在查询文章"+artId); 
		}
		ArticleDao ad = new ArticleDaoImpl();
		Article art = new Article();
		art = ad.getArticle(artId);
		CommentDao cd = new CommentDaoImpl();
		List<Comment> list = cd.getCommentOfArt(artId);
		if(art!=null) {
			req.setAttribute("Article", art);
			req.setAttribute("Comment", list);
			req.getRequestDispatcher("/article2/showart.jsp").forward(req, resp);
			return ;
		}
		
	}

	private void getAllArt(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArticleDao ad = new ArticleDaoImpl();
		List<Article> list = ad.getArticleAll();
		if(list!=null) {
			System.out.println(list);
			req.setAttribute("list", list);
			req.getRequestDispatcher("/article2/getallart.jsp").forward(req, resp);
			return ;
		}
		
	}

	private void changeInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String newInfo = req.getParameter("newInfo");
		User user = (User)req.getSession().getAttribute("user");
		int userId = user.getUserId();
		String userName = user.getUserName();
		System.out.println("用户 "+userId+":"+userName+"正在修改个人简介");
		System.out.println("新简介 : "+newInfo);
		UserDao ud = new UserDaoImpl();
		if(ud.changeInfo(userId, newInfo)) {
			System.out.println("修改简介成功");
			resp.getWriter().write("修改简介成功");
		}else {
			System.out.println("修改简介异常");
		}	
		
	}

	private void changePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String newpwd = req.getParameter("newPwd");
		User user = (User)req.getSession().getAttribute("user");
		int userId = user.getUserId();
		String userName = user.getUserName();
		System.out.println("用户 "+userId+":"+userName+"正在修改密码");
		System.out.println("新密码 : "+newpwd);
		UserDao ud = new UserDaoImpl();
		if(ud.changePwd(userId, newpwd)) {
			System.out.println("修改密码成功");
			resp.sendRedirect("/college_website/denglu.jsp");
		}else {
			System.out.println("修改密码异常");
		}
		
		
		
	}

}
