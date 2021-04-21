package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

import java.util.List;

//用户的持久层接口
public interface UserDao {

    List<User> findAll();
    //保存用户 增加
    void saveUser(User user);
    //更新用户
    void updateUser(User user);
    //根据id删除用户
    void deleteUser(Integer userId);

    User findById(Integer userId);

    List<User> findByName(String userName);

    Integer findTotal();

    //根据queryVo中的条件查询用户
    List<User> findUserByVo(QueryVo vo);
}
