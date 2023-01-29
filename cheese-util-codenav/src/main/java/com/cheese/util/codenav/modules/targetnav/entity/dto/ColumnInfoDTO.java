package com.cheese.util.codenav.modules.targetnav.entity.dto;


import java.io.Serializable;

/**
 * 列信息参数传输类
 * @author sobann
 */
public class ColumnInfoDTO implements Serializable {

    /**
     * 列名
     */
    private String columnName;
    /**
     * 列名类型
     */
    private String dataType;
    /**
     * 列备注
     */
    private String columnComment;
    /**
     * 列索引类型
     */
    private String columnKey;
    /**
     * 额外信息说明
     */
    private String extra;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "ColumnInfoDTO{" +
                "columnName='" + columnName + '\'' +
                ", dataType='" + dataType + '\'' +
                ", columnComment='" + columnComment + '\'' +
                ", columnKey='" + columnKey + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
