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

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User queryUserById(String userId) {
        StringBuilder sql = new StringBuilder("select name from userinfo where id =" + userId);
        String s = userDao.execQuerySQL(sql.toString());
        System.out.println(s);
        return null;
    }
}
