package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class ManagerServlet
 */

public class ManagerServlet extends HttpServlet {
	private static final String Article = null;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String oper = req.getParameter("oper");
		System.out.println(oper);
		if("getalluser".equals(oper)) {
			getAllUser(req,resp);
		}else if("addart".equals(oper)) {
			addAtr(req,resp);
		}else if("changeart".equals(oper)) {
			changeArt(req,resp);
		}else if("deleteuser".equals(oper)) {
			deleteUser(req,resp);
		}else if("searchuser".equals(oper)) {
			searchUser(req,resp);
		}else if("getallart".equals(oper)) {
			getAllArt(req,resp);
		}else if("deleteart".equals(oper)) {
			deleteArt(req,resp);
		}else if("searchart".equals(oper)) {
			searchArt(req,resp);
		}else if("getallcom".equals(oper)) {
			getAllCom(req,resp);
		}else if("searchcom".equals(oper)) {
			searchCom(req,resp);
		}else if("getcomofuser".equals(oper)) {
			getComOfUser(req,resp);
		}else if("getcomofart".equals(oper)) {
			getComOfArt(req,resp);
		}else if("deletecom".equals(oper)) {
			deleteCom(req,resp);
		}
		else {
			System.out.println("没有找到相应的操作符：" + oper);
		}
	}
	
	private void searchUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uId = req.getParameter("userId");
		int userId = 0;
		if(uId!=null) {
			userId = Integer.valueOf(uId);
			System.out.println("正在查询用户"+userId); 
		}
		UserDao ud = new UserDaoImpl();
		User user = new User();
		user = ud.getUserOfId(userId);
		if(user!=null) {
			req.setAttribute("User", user);
			req.getRequestDispatcher("/user/searchuser2.jsp").forward(req, resp);
			return ;
		}
		
	}

	private void searchArt(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String aId = req.getParameter("artId");
		int artId = 0;
		if(aId!=null) {
			artId = Integer.valueOf(aId);
			System.out.println("正在查询评论"+artId); 
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

	private void deleteCom(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String comId = req.getParameter("commentId");
		int commentId = 0;
		if(comId!=null) {
			commentId = Integer.valueOf(comId);
			System.out.println("正在删除评论"+commentId); 
		}
		CommentDao cd = new CommentDaoImpl();
		if(cd.delete(commentId)) {
			System.out.println("删除成功");
			resp.getWriter().write("评论删除成功");
		}else {
			System.out.println("删除异常");
			resp.getWriter().write("评论删除失败");
		}
		
	}

	private void getComOfArt(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String aId = req.getParameter("artId");
		int artId = 0;
		if(aId!=null) {
			artId = Integer.valueOf(aId);
			System.out.println("正在查询评论"+artId); 
		}
		CommentDao cd = new CommentDaoImpl();
		List<Comment> list = cd.getCommentOfArt(artId);
		if(list!=null) {
			System.out.println(list);
			req.setAttribute("list", list);
			req.getRequestDispatcher("/comment/getcomofart2.jsp").forward(req, resp);
			return ;
		}
	}

	private void getComOfUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uId = req.getParameter("userId");
		int userId = 0;
		if(uId!=null) {
			userId = Integer.valueOf(uId);
			System.out.println("正在查询评论"+userId); 
		}
		CommentDao cd = new CommentDaoImpl();
		List<Comment> list = cd.getCommentOfUser(userId);
		if(list!=null) {
			System.out.println(list);
			req.setAttribute("list", list);
			req.getRequestDispatcher("/comment/getcomofuser2.jsp").forward(req, resp);
			return ;
		}
	}

	private void searchCom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String comId = req.getParameter("commentId");
		int commentId = 0;
		if(comId!=null) {
			commentId = Integer.valueOf(comId);
			System.out.println("正在查询评论"+commentId); 
		}
		CommentDao cd = new CommentDaoImpl();
		Comment com = new Comment();
		com = cd.getCommentOfId(commentId);
		if(com!=null) {
			req.setAttribute("comment", com);
			req.getRequestDispatcher("/comment/searchcom2.jsp").forward(req, resp);
			return ;
		}
	}

	private void getAllCom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CommentDao ud = new CommentDaoImpl();
		List<Comment> list = ud.getCommentAll();
		if(list!=null) {
			System.out.println(list);
			req.setAttribute("list", list);
			req.getRequestDispatcher("/comment/getallcom.jsp").forward(req, resp);
			return ;
		}
		
	}

	private void deleteArt(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String aId = req.getParameter("artId");
		int artId = 0;
		if(aId!=null) {
			artId = Integer.valueOf(aId);
			System.out.println("正在删除文章"+artId); 
		}
		ArticleDao ad = new ArticleDaoImpl();
		 if(ad.delete(artId)) {
			 System.out.println("文章删除成功");
			 resp.getWriter().write("文章删除成功");
		 }else {
			 System.out.println("文章删除失败");
			 resp.getWriter().write("文章删除失败");
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

	private void changeArt(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String aId = req.getParameter("artId");
//		int artId = 0;
//		if(aId!=null) {
//			artId = Integer.valueOf(aId);
//			System.out.println("正在查询文章"+artId); 
//		}
//		ArticleDao ud = new ArticleDaoImpl();
//		Article art = ud.getArticle(artId);
//		if(art!=null) {
//			System.out.println("查询成功");
//			req.setAttribute("Article", art);
//			req.getRequestDispatcher("/article/changeart.jsp").forward(req, resp);
//		}else {
//			System.out.println("查询失败");
		String aId = req.getParameter("artId");
		int artId = 0;
		if(aId!=null) {
			artId = Integer.valueOf(aId);
			System.out.println("正在修改文章"+artId); 
		}
		String title = req.getParameter("title");
		String imageUrl = req.getParameter("imageUrl");
		String content = req.getParameter("content");
		Date createDate = new Date(0);
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
//		try {
//			createDate = df.parse((df.format(new Date())));// new Date()为获取当前系统时间
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		ArticleDao ad = new ArticleDaoImpl();
		if(ad.update(artId, title, imageUrl, createDate, content)) {
			System.out.println("文章 "+artId+"修改成功");
			resp.getWriter().write("文章修改成功");
		}else {
			System.out.println("文章 "+artId+"修改失败");
			resp.getWriter().write("文章修改失败");
		}
}

	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String uId = req.getParameter("userId");
		int userId = 0;
		if(uId!=null) {
			userId = Integer.valueOf(uId);
			System.out.println("正在删除用户"+userId); 
		}
		UserDao ud = new UserDaoImpl();
		 if(ud.delete(userId)) {
			 System.out.println("删除成功");
			 resp.getWriter().write("删除用户成功");
		 }else {
			 System.out.println("删除异常");
			 resp.getWriter().write("删除用户失败");
		 }
	}

	//添加文章
	private void addAtr(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String imageUrl = req.getParameter("imageUrl");
		String content = req.getParameter("content");
		Date createDate = new Date(System.currentTimeMillis());

		Article art = new Article();
		art.setTitle(title);
		art.setImageUrl(imageUrl);
		art.setCreateDate(createDate);
		art.setContent(content);
		System.out.println(art);
		
		ArticleDao ad = new ArticleDaoImpl();
		if(ad.addArticle(art)){
			System.out.println("文章添加成功");
//			req.setAttribute("flag", "true");
			resp.getWriter().write("文章添加成功");
			
		}else {
			System.out.println("文章添加失败");
//			req.setAttribute("flag", "false");
			resp.getWriter().write("文章添加失败");
		}
//		req.getRequestDispatcher("/article/addart.jsp").forward(req, resp);
	}

	private void getAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDao ud = new UserDaoImpl();
		List<User> list = ud.getUserAll();
		if(list!=null) {
			req.setAttribute("list", list);
			req.getRequestDispatcher("/user/getalluser.jsp").forward(req, resp);
			return ;
		}
	}
}
