package dao;

import java.util.List;

import entity.User;

public interface UserDao {

	//登录
	public User login(String userName,String password);
	//注册
	public boolean  register(User user);
	//查询所有用户
	public List<User> getUserAll();
	//删除用户
	public boolean delete(int userId) ;
	//修改密码
	public boolean changePwd(int userId,String newPwd);
	//修改个人简介
	public boolean changeInfo(int userId,String newInfo);
	//修改用户信息
	public boolean update(int userId, String userName, String password,String information) ;
	//搜索用户
	public User getUserOfId(int userId);
}
