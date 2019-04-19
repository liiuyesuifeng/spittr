package com.mvc.dao;

import com.mvc.eitity.User;
import com.mvc.utils.DaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
public class UserDao {
    @Resource
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String execQuerySQL(String sql,String ... params){
        return jdbcTemplate.queryForObject(sql, params, String.class);
    }

    /**
     * 查询实体集合
     * @param tClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public <T> List<T> execQuerySQLForEntityList(Class<T> tClass,String sql,String ... params) throws InstantiationException, IllegalAccessException {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, params);
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
        Map <String, Object> maps = jdbcTemplate.queryForMap(sql, params);
        return DaoUtil.convertSQLResultForEntity(tClass, maps);
    }


}
