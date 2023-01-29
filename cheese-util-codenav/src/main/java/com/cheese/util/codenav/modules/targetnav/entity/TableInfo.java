package com.cheese.util.codenav.modules.targetnav.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 表实体
 * @author sobann
 */
@Data
@TableName(value = "tables",schema = "information_schema")
public class TableInfo {
    /**
     * 数据库名
     */
    private String tableSchema;
    /**
     * 数据表名
     */
    private String tableName;
    /**
     * 数据表类型
     */
    private String tableType;
    /**
     * 数据表引擎
     */
    private String engine;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
