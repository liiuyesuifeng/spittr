package com.mvc.service.impl;

import com.mvc.dao.UserDao;
import com.mvc.eitity.User;
import com.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User queryUserById(String userId) {
        StringBuilder sql = new StringBuilder("select * from userinfo where id =" + userId);
        try{
            return userDao.execQuerySQLForEntity(User.class, sql.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> queryUserList(User user) {
        StringBuilder sql = new StringBuilder("select * from userinfo ");
        try{
            return userDao.execQuerySQLForEntityList(User.class, sql.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        try {
            userDao.saveUser(user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
