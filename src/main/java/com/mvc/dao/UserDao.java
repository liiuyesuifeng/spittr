package com.mvc.dao;

import com.mvc.eitity.User;
import com.mvc.utils.DaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.ContextLoader;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao{
    @Autowired
   private JdbcTemplate jdbcTemplate;

    private JdbcTemplate getJdbcTemplate(){
        return jdbcTemplate;
    }

    /**
     * 查询实体集合
     * @param tClass 实体类
     * @param sql 执行SQL
     * @param params SQL参数
     * @param <T>
     * @return
     */
    public <T> List<T> execQuerySQLForEntityList(Class<T> tClass,String sql,String ... params) throws InstantiationException, IllegalAccessException {
        List<Map<String, Object>> maps = getJdbcTemplate().queryForList(sql, params);
        return DaoUtil.convertSQLResultForEntityList(tClass, maps);
    }

    /**
     * 查询实体
     * @param tClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T> T execQuerySQLForEntity(Class<T> tClass,String sql,String ... params)throws InstantiationException, IllegalAccessException {
        Map <String, Object> maps = getJdbcTemplate().queryForMap(sql, params);
        return DaoUtil.convertSQLResultForEntity(tClass, maps);
    }

    /**
     * 保存方法
     * @param user
     * @throws IllegalAccessException
     */
    public void saveUser(User user) throws IllegalAccessException {
        String sql = DaoUtil.convertEntityForSQL(user);
        jdbcTemplate.execute(sql);
    }
    /**
     * 保存方法
     * @param user
     * @throws IllegalAccessException
     */
    public void save(User user) throws IllegalAccessException {
        String sql = DaoUtil.convertEntityForSQL(user);
        jdbcTemplate.execute(sql);
    }

}
