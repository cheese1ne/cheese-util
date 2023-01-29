package com.cheese.util.codenav.modules.targetnav.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cheese.util.codenav.modules.targetnav.entity.TableInfo;
import com.cheese.util.codenav.modules.targetnav.entity.dto.ColumnInfoDTO;
import com.cheese.util.codenav.modules.targetnav.entity.dto.TableInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 目标数据库创建一个demo实体
 * @author sobann
 */
public interface TargetMapper extends BaseMapper<TableInfo> {

    /**
     * 根据表名获取表详情
     * @param tableName
     * @return
     */
    Map<String, Object> queryTable(String tableName);

    /**
     * 数据列名、数据类型、列注释等数据列表
     * @param tableName
     * @return
     */
    List<Map<String, Object>> queryColumns(String tableName);

    /**
     * 获取数据库中的所有表名称
     * @return
     */
    List<String> queryAllTable();

    /**
     * 获取表详情列表
     * @return
     */
    List<Map<String, Object>> selectTableList();

    /**
     * 表分页查询
     * @param page
     * @param param
     * @return
     */
    IPage<TableInfo> queryPage(IPage<TableInfo> page, @Param("param") Map param);

    /**
     * 根据表名获取表详情
     * @param tableName
     * @return
     */
    TableInfoDTO queryTable2DTO(String tableName);

    /**
     * 数据列名、数据类型、列注释等数据列表，通过传输类包装
     * @param tableName
     * @return
     */
    List<ColumnInfoDTO> queryColumns2DTO(String tableName);
}
