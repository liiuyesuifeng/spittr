package com.mvc.eitity.base;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class TableStructure {
    private String dataSource; //数据源地址
    private String tableName; // 表名
    private Set<ColumnStructure> columnSet = new LinkedHashSet<>();//列属性
    public TableStructure(String dataSource,String tableName,Set<ColumnStructure> columnSet){
        this.dataSource = dataSource;
        this.tableName = tableName;
        this.columnSet = columnSet;
    }
    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Set<ColumnStructure> getColumnSet() {
        return columnSet;
    }

    public void setColumnSet(Set<ColumnStructure> columnSet) {
        this.columnSet = columnSet;
    }
}
