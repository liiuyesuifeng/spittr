package com.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserDao {
    @Resource
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String execQuerySQL(String sql,String ... params){
        return jdbcTemplate.queryForObject(sql, params, String.class);
    }

}
