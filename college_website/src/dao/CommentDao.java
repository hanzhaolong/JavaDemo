package dao;

import java.sql.Date;
import java.util.List;

import entity.Comment;

public interface CommentDao {

	// 增加评论
	public boolean addComment(int userId, int artId, String commentValue, Date commentDate);

	// 查询所有评论
	public List<Comment> getCommentAll();

	// 查询某用户的所有评论
	public List<Comment> getCommentOfUser(int userId);

	// 查询某文章的所有评论
	public List<Comment> getCommentOfArt(int artId);

	// 根据评论ID查看评论
	public Comment getCommentOfId(int commentId);

	// 删除评论
	public boolean delete(int commentId);

	// 修改评论信息
	public boolean update(int commentId, String commentValue);
}
