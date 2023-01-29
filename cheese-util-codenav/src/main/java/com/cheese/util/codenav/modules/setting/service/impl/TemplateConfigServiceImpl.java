package com.cheese.util.codenav.modules.setting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheese.core.tool.util.Func;
import com.cheese.util.codenav.modules.setting.entity.TemplateConfig;
import com.cheese.util.codenav.modules.setting.entity.dto.TemplateConfigDTO;
import com.cheese.util.codenav.modules.setting.mapper.TemplateConfigMapper;
import com.cheese.util.codenav.modules.setting.service.ITemplateConfigService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * TemplateConfig.java
 *  业务接口实现
 * @author sobann
 */
@Service("templateConfigService")
public class TemplateConfigServiceImpl extends ServiceImpl<TemplateConfigMapper, TemplateConfig> implements ITemplateConfigService {

    @Override
    public List<TemplateConfig> selectList(TemplateConfigDTO templateConfigDTO) {
        //TODO:根据实际条件修改
        LambdaQueryWrapper<TemplateConfig> wrapper = new LambdaQueryWrapper<>();
        List<TemplateConfig> entityList = this.baseMapper.selectList(wrapper);
        if (Func.isEmpty(entityList)) {
            return Collections.emptyList();
        }
        return entityList;
    }

    @Override
    public IPage<TemplateConfig> selectPage(IPage<TemplateConfig> page, TemplateConfigDTO templateConfigDTO) {
        LambdaQueryWrapper<TemplateConfig> wrapper = new LambdaQueryWrapper<>();
        IPage<TemplateConfig> entityPage = this.baseMapper.selectPage(page, wrapper);
        return entityPage;
    }

    @Override
    public TemplateConfig selectById(Long id) {
        TemplateConfig entity = this.getById(id);
        Assert.notNull(entity, "不存在的id!");
        return entity;
    }

    @Override
    public List<String> selectTemplatePathByTypes(List<String> types) {
        List<TemplateConfig> templateConfigs = this.selectListByTypes(types);
        if (Func.isEmpty(templateConfigs)){
            return Collections.emptyList();
        }
        return templateConfigs.stream().map(TemplateConfig::getTempaltePath).collect(Collectors.toList());
    }

    @Override
    public List<TemplateConfig> selectListByTypes(List<String> types) {
        LambdaQueryWrapper<TemplateConfig> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(TemplateConfig::getIsDeleted,0);
        wrapper.in(TemplateConfig::getType,types);
        List<TemplateConfig> templateConfigs = this.baseMapper.selectList(wrapper);
        return Func.isEmpty(templateConfigs)?Collections.emptyList():templateConfigs;
    }
}