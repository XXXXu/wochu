package com.wochu.dao;

import com.wochu.enity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */
public interface UserDao {
    public User login(String name);
    public int register(String name,String password);
    public boolean deleteUser(int userId);
    public User getUserByPrimeryKey(int userId);
    public List<User> queryAllUser();
    public boolean updateUser(User user);
}
