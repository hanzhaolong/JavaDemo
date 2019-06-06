package entity;

import java.sql.Date;

public class Comment {

	private int commentId;
	private String commentValue;
	private Date commentDate;
	private int userId;
	private int artId;
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getCommentValue() {
		return commentValue;
	}
	public void setCommentValue(String commentValue) {
		this.commentValue = commentValue;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getArtId() {
		return artId;
	}
	public void setArtId(int artId) {
		this.artId = artId;
	}

	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentValue=" + commentValue + ", commentDate=" + commentDate
				+ ", userId=" + userId + ", artId=" + artId + "]";
	}
	
}
