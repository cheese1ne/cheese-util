package com.cheese.util.codenav.modules.setting.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cheese.util.codenav.modules.setting.entity.FieldTypeMapConfig;
import com.cheese.util.codenav.modules.setting.entity.dto.FieldTypeMapConfigDTO;

import java.util.List;
import java.util.Map;

/**
 * FieldTypeMapConfig.java
 *  业务接口
 * @author sobann
 */
public interface IFieldTypeMapConfigService extends IService<FieldTypeMapConfig> {


    /**
     * 数据库实体字段映射
     * @return
     */
    Map<String, String> getTypeMap();

    /**
     * 列表查询
     * @param fieldTypeMapConfigDTO
     * @return
     */
    List<FieldTypeMapConfig> selectList(FieldTypeMapConfigDTO fieldTypeMapConfigDTO);


    /**
     * 自定义分页查询
     * @param fieldTypeMapConfigDTO
     * @param page
     * @return
     */
    IPage<FieldTypeMapConfig> selectPage(IPage<FieldTypeMapConfig> page, FieldTypeMapConfigDTO fieldTypeMapConfigDTO);


    /**
     * 根据id获取视图
     * @param id
     * @return
     */
    FieldTypeMapConfig selectById(Long id);

    /**
     * 根据数据库数据类型获取实体
     * @param dataType
     * @return
     */
    FieldTypeMapConfig selectByDataType(String dataType);
}