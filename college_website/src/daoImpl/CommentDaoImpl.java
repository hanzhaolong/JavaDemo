package daoImpl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CommentDao;
import entity.Comment;
import util.DBconn;

public class CommentDaoImpl implements CommentDao {

	public boolean addComment(int userId, int artId, String commentValue, Date commentDate) {
		boolean flag = false;
		DBconn.init();
		int i = DBconn.addUpdDel("insert into comment(userId,artId,commentValue,commentDate) " + "values('"
				+ userId + "','" + artId + "','" + commentValue+"','" + commentDate+"')");
		if (i > 0) {
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}

	public List<Comment> getCommentAll() {
		List<Comment> list = new ArrayList<Comment>();
		try {
			DBconn.init();
			ResultSet rs = DBconn.selectSql("select * from comment");
			while (rs.next()) {
				Comment com = new Comment();
				com.setCommentId(rs.getInt("commentId"));
				com.setUserId(rs.getInt("userId"));
				com.setArtId(rs.getInt("artId"));
				com.setCommentValue(rs.getString("commentValue"));
				com.setCommentDate(rs.getDate("commentDate"));
				list.add(com);
			}
			DBconn.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}return list;
	}

	public boolean delete(int commentId) {
		boolean flag = false;
		DBconn.init();
		String sql = "delete  from comment where commentId=" + commentId;
		int i = DBconn.addUpdDel(sql);
		if (i > 0) {
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}

	public boolean update(int commentId, String commentValue) {
		boolean flag = false;
		DBconn.init();
		String sql = "update comment set commentValue ='" + commentValue
				+ "' where commentId = " + commentId;
		int i = DBconn.addUpdDel(sql);
		if (i > 0) {
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}

	public List<Comment> getCommentOfArt(int artId) {
		List<Comment> list = new ArrayList<Comment>();
		try {
			DBconn.init();
			ResultSet rs = DBconn.selectSql("select * from comment where artId = "+artId);
			while (rs.next()) {
				Comment com = new Comment();
				com.setCommentId(rs.getInt("commentId"));
				com.setUserId(rs.getInt("userId"));
				com.setArtId(rs.getInt("artId"));
				com.setCommentValue(rs.getString("commentValue"));
				com.setCommentDate(rs.getDate("commentDate"));
				list.add(com);
			}
			DBconn.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}return list;
	}

	public List<Comment> getCommentOfUser(int userId) {
		List<Comment> list = new ArrayList<Comment>();
		try {
			DBconn.init();
			ResultSet rs = DBconn.selectSql("select * from comment where userId = "+userId);
			while (rs.next()) {
				Comment com = new Comment();
				com.setCommentId(rs.getInt("commentId"));
				com.setUserId(rs.getInt("userId"));
				com.setArtId(rs.getInt("artId"));
				com.setCommentValue(rs.getString("commentValue"));
				com.setCommentDate(rs.getDate("commentDate"));
				list.add(com);
			}
			DBconn.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}return list;
	}

	public Comment getCommentOfId(int commentId) {
		Comment com = new Comment();
		try {
			DBconn.init();
			ResultSet rs = DBconn.selectSql("select * from comment where commentId = "+commentId);
			while (rs.next()) {
				com.setCommentId(rs.getInt("commentId"));
				com.setUserId(rs.getInt("userId"));
				com.setArtId(rs.getInt("artId"));
				com.setCommentValue(rs.getString("commentValue"));
				com.setCommentDate(rs.getDate("commentDate"));
			}
			DBconn.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}return com;
		
	}
}

