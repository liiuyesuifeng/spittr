package com.mvc.utils;

import com.mvc.eitity.base.TableStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class TableUtils {


    private static Lock lock = new ReentrantLock();
    public static TableStructure queryTableStructure(String tableName){
        StringBuffer sql = new StringBuffer("SELECT COLUMN_NAME, COLUMN_TYPE, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH, IS_NULLABLE, COLUMN_DEFAULT, COLUMN_COMMENT FROM INFORMATION_SCHEMA.COLUMNS where");
        sql.append(" table_schema = 'userwork' and table_name = '" + tableName + "'");

        return null;
    }

}
