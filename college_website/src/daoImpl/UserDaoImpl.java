package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.UserDao;
import entity.User;
import util.DBconn;

public class UserDaoImpl implements UserDao{
	
	public boolean register(User user) {
		boolean flag = false;
		DBconn.init();
		int i =DBconn.addUpdDel("insert into user(userName,password,information) " +
				"values('"+user.getUserName()+"','"+user.getPassword()+"','"+user.getInformation()+"')");
		if(i>0){
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}
    public User login(String userName, String password) {
		User u = null;
		try {
			    DBconn.init();
				ResultSet rs = DBconn.selectSql("select * from user where userName='"+userName+"' and password='"+password+"'");
				while(rs.next()){
					// 给变量赋值
					u = new User();
					u.setUserId(rs.getInt("userId"));
					u.setUserName(rs.getString("userName"));
					u.setPassword(rs.getString("password"));
					u.setInformation(rs.getString("information"));
				}
				DBconn.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	public List<User> getUserAll() {
		List<User> list = new ArrayList<User>();
    	try {
		    DBconn.init();
			ResultSet rs = DBconn.selectSql("select * from user");
			while(rs.next()){
				User user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setInformation(rs.getString("information"));
				list.add(user);
			}
			DBconn.closeConn();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public boolean changePwd(int userId,String newPwd) {
		boolean flag = false;
		DBconn.init();
		String sql ="update user set password = "+newPwd+" where userId = "+userId;
		int i =DBconn.addUpdDel(sql);
		if(i>0){
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}
	public boolean changeInfo(int userId,String newInfo) {
		boolean flag = false;
		DBconn.init();
		String sql ="update user set information = "+newInfo+"' where userId = "+userId;
		int i =DBconn.addUpdDel(sql);
		if(i>0){
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}
	public boolean update(int userId,String userName, String password,String information) {
		boolean flag = false;
		DBconn.init();
		String sql ="update user set userName = "+userName+", password ='"+password
				+"' , information ='"+information+"' where userId = "+userId;
		int i =DBconn.addUpdDel(sql);
		if(i>0){
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}
	public boolean delete(int userId) {
		boolean flag = false;
		DBconn.init();
		String sql = "delete  from user where userId="+userId;
		int i =DBconn.addUpdDel(sql);
		if(i>0){
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}
	
	public User getUserOfId(int userId) {
		User user = new User();
    	try {
		    DBconn.init();
			ResultSet rs = DBconn.selectSql("select * from user where userId = "+userId);
			while(rs.next()){
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setInformation(rs.getString("information"));
			}
			DBconn.closeConn();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
		
	}

	
    
}
