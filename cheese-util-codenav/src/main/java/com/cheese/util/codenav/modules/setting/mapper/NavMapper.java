package com.cheese.util.codenav.modules.setting.mapper;



import java.util.List;
import java.util.Map;

/**
 * 目标数据库创建一个demo实体
 * @author sobann
 */
public interface NavMapper {

    Map<String, Object> queryTable(String tableName);

    List<Map<String, Object>> queryColumns(String tableName);

    List<String> queryAllTable();
}
