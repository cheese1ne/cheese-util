package com.cheese.util.codenav.modules.setting.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cheese.util.codenav.modules.setting.entity.TemplateConfig;
import com.cheese.util.codenav.modules.setting.entity.dto.TemplateConfigDTO;

import java.util.List;

/**
 * TemplateConfig.java
 *  业务接口
 * @author sobann
 */
public interface ITemplateConfigService extends IService<TemplateConfig> {

    /**
     * 列表查询
     * @param templateConfigDTO
     * @return
     */
    List<TemplateConfig> selectList(TemplateConfigDTO templateConfigDTO);

    /**
     * 自定义分页查询
     * @param templateConfigDTO
     * @param page
     * @return
     */
    IPage<TemplateConfig> selectPage(IPage<TemplateConfig> page, TemplateConfigDTO templateConfigDTO);

    /**
     * 根据id获取视图
     * @param id
     * @return
     */
    TemplateConfig selectById(Long id);

    /**
     * 根据模板类型获取模板文件路径列表
     * @param types
     * @return
     */
    List<String> selectTemplatePathByTypes(List<String> types);

    /**
     * 根据模板类型获取模板文件路径列表
     * @param types
     * @return
     */
    List<TemplateConfig> selectListByTypes(List<String> types);
}