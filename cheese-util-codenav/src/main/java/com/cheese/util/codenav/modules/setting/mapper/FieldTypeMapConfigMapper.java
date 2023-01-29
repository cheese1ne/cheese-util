package com.cheese.util.codenav.modules.setting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cheese.util.codenav.modules.setting.entity.FieldTypeMapConfig;
import com.cheese.util.codenav.modules.setting.entity.dto.FieldTypeMapConfigDTO;
import org.apache.ibatis.annotations.Param;

/**
 * Module: FieldTypeMapConfig.java
 *
 * @author sobann
 */
public interface FieldTypeMapConfigMapper extends BaseMapper<FieldTypeMapConfig> {
    /**
     * 自定义分页查询
     * @param page
     * @param fieldTypeMapConfigDTO
     * @return
     */
    IPage<FieldTypeMapConfig> queryPage(IPage<FieldTypeMapConfig> page, @Param("param") FieldTypeMapConfigDTO fieldTypeMapConfigDTO);
}