package entity;

import java.util.Date;

public class Article {

	private int artId;
	private String title;
	private String imageUrl;
	private Date createDate;
	private String content;
	
	public int getArtId() {
		return artId;
	}
	public void setArtId(int artId) {
		this.artId = artId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString() {
		return "Article [artId=" + artId + ", title=" + title + ", imageUrl=" + imageUrl + ", createDate=" + createDate
				+  ", content=" + content + "]";
	}
	
	
}
