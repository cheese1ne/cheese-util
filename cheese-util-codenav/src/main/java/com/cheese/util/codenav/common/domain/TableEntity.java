package com.cheese.util.codenav.common.domain;


import java.util.List;

/**
 * 实际是从information_schema.tables中获取表的参数
 *
 * @author sobann
 */
public class TableEntity {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表备注
     */
    private String comments;
    /**
     * 表主键
     */
    private ColumnEntity primaryKey;
    /**
     * 表的列(不包含主键)
     */
    private List<ColumnEntity> columns;

    /**
     * 类名(第一个字母大写)，如：sys_user => SysUser
     */
    private String className;

    /**
     * 类名(第一个字母小写)，如：sys_user => sysUser
     */
    private String classname;

    /**
     * 是否包含主键
     */
    private Boolean hasPrimaryKey;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ColumnEntity getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ColumnEntity primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<ColumnEntity> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnEntity> columns) {
        this.columns = columns;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Boolean getHasPrimaryKey() {
        return primaryKey != null;
    }

    public void setHasPrimaryKey(Boolean hasPrimaryKey) {
        this.hasPrimaryKey = hasPrimaryKey;
    }
}
