package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import dao.ArticleDao;
import entity.Article;
import util.DBconn;

public class ArticleDaoImpl implements ArticleDao{

	public boolean addArticle(Article art) {
		boolean flag = false;
		DBconn.init();
		
		
		int i =DBconn.addUpdDel("insert into article(title,imageUrl,createDate,content) " +
				"values('"+art.getTitle()+"','"+art.getImageUrl()+"','"+art.getCreateDate()+"','"+art.getContent()+"')");
		System.out.println(i);
		if(i>0){
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}

	public List<Article> getArticleAll(){
		List<Article> list = new ArrayList<Article>();
    	try {
		    DBconn.init();
		    ResultSet rs = DBconn.selectSql("select * from article");
//			ResultSet rs = DBconn.selectSql("select * from article Order By createDate Desc");
			while(rs.next()){
				Article art = new Article();
				art.setArtId(rs.getInt("artId"));
				art.setTitle(rs.getString("title"));
				art.setImageUrl(rs.getString("imageUrl"));
				art.setCreateDate(rs.getDate("createDate"));
				art.setContent(rs.getString("content"));
				list.add(art);
			}
			DBconn.closeConn();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public Article getArticle(int artId) {
		Article art = new Article();
		try {
		    DBconn.init();
			ResultSet rs = DBconn.selectSql("select * from article where artId="+artId);
			while(rs.next()){
				art.setArtId(artId);
				art.setArtId(rs.getInt("artId"));
				art.setTitle(rs.getString("title"));
				art.setImageUrl(rs.getString("imageUrl"));
				art.setCreateDate(rs.getDate("createDate"));
				art.setContent(rs.getString("content"));
			}
			DBconn.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return art;
	}
	public boolean update(int artId, String title, String imageUrl,Date createDate,String content) {
		boolean flag = false;
		DBconn.init();
		String sql ="update article set title ='"+title
				+"' , imageUrl ='"+imageUrl
				+"' , createDate ='"+createDate
				+"' , content ='"+content+"' where artId = '"+artId+"'";
		int i =DBconn.addUpdDel(sql);
		if(i>0){
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}
	public boolean delete(int artId) {
		boolean flag = false;
		DBconn.init();
		String sql = "delete  from article where artId="+artId;
		int i =DBconn.addUpdDel(sql);
		if(i>0){
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}


}
