package com.cheese.util.codenav.modules.targetnav.entity.dto;


import java.io.Serializable;

/**
 * 列信息参数传输类
 *
 * @author sobann
 */
public class TableInfoDTO implements Serializable {

    /**
     * 列名
     */
    private String tableName;
    /**
     * 列名类型
     */
    private String tableComment;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    @Override
    public String toString() {
        return "TableInfoDTO{" +
                "tableName='" + tableName + '\'' +
                ", tableComment='" + tableComment + '\'' +
                '}';
    }
}
