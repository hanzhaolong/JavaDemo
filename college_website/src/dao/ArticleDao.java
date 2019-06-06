package dao;

import java.sql.Date;
import java.util.List;
import entity.Article;

public interface ArticleDao {
	
	public boolean addArticle(Article art);
	public List<Article> getArticleAll();
	public Article getArticle(int artId);
	public boolean update(int artId, String title, String imageUrl,Date createDate,String content);
	public boolean delete(int artId);
	
	
	

}
