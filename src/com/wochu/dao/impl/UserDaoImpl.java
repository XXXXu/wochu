package com.wochu.dao.impl;

import com.wochu.dao.UserDao;
import com.wochu.enity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */
public class UserDaoImpl implements UserDao {

    BaseDao baseDao = new BaseDao();

    public User login(String name) {
        User user = null;
        String sql = "select * from t_user where user_name=?";
        List<Object> list=new ArrayList<Object>();
        list.add(name);
        List<User> users = null;
        try {
            users = baseDao.operQuery(sql, list, User.class);
            if(users.size() > 0) user = users.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public int register(String name,String password){
        String sql = "insert into t_user (user_name,user_password,user_type) values(?,?,1)";
        List<Object> list = new ArrayList<Object>();
        list.add(name);
        list.add(password);

        return baseDao.havaRsInsert(sql,list);
    }

    public boolean deleteUser(int userId){
        String sql = "delete from t_user where user_id = ?";
        List<Object> list = new ArrayList<Object>();
        list.add(userId);
        return (baseDao.operUpdate(sql,list)>0 ? 1:0) == 1;
    }

    public List<User> queryAllUser() {
        List<User> userList = null;
        String sql = "select * from t_user";
        List<Object> list = new ArrayList<Object>();
        try {
            userList = baseDao.operQuery(sql, list, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public boolean updateUser(User user){
        String sql = "update t_user set user_name=?,user_password=?,user_type=? where user_id=?";
        List<Object> list = new ArrayList<Object>();
        list.add(user.getUserName());
        list.add(user.getUserPassword());
        list.add(user.getUserType());
        list.add(user.getUserId());
        return baseDao.operUpdate(sql,list)>0;
    }

	public User getUserByPrimeryKey(int userId) {
		String sql = "select * from t_user where user_id = "+userId;
		List<User> userList = new ArrayList<User>();
		try {
			userList = baseDao.operQuery(sql, null, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList.get(0);
	}


}
