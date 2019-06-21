package com.mvc.eitity.base;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 字段结构实体
 */
public class ColumnStructure {
    @Column(name = "COLUMN_NAME")
    private String columnName;//列名
    @Column(name = "COLUMN_TYPE")
    private String columnType;//数据类型
    @Column(name = "DATA_TYPE")
    private String dataType;
    @Column(name = "CHARACTER_MAXIMUM_LENGTH")
    private String columnMaxLength;//字段最大长度
    @Column(name = "IS_NULLABLE")
    private boolean isNull;//是否为空
    @Column(name = "COLUMN_DEFAULT")
    private String columnDefault;//默认值
    @Column(name = "COLUMN_COMMENT")
    private String columnComment;//注释
    public ColumnStructure(String columnName, String columnType, String dataType, String columnMaxLength, boolean isNull, String columnDefault, String columnComment){
        this.columnName = columnName;
        this.columnType = columnType;
        this.dataType = dataType;
        this.columnMaxLength = columnMaxLength;
        this.isNull = isNull;
        this.columnDefault = columnDefault;
        this.columnComment = columnComment;
    }
    public String getColumnName() {
        return columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public String getDataType() {
        return dataType;
    }

    public String getColumnMaxLength() {
        return columnMaxLength;
    }

    public boolean isNull() {
        return isNull;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public String getColumnComment() {
        return columnComment;
    }

}
