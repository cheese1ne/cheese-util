package com.cheese.util.codenav.modules.targetnav.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cheese.util.codenav.modules.targetnav.entity.TableInfo;

import java.util.List;
import java.util.Map;

/**
 * @author sobann
 */
public interface ITargetTableService extends IService<TableInfo> {

    /**
     * 查询所有数据表
     * @return
     */
    List<Map<String,Object>> selectAllTableInfo();

    /**
     * 分页查询
     * @param page
     * @param params 查询参数
     * @return
     */
    IPage<TableInfo> selectPage(IPage<TableInfo> page, Map params);
}
