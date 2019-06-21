package com.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TableDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
