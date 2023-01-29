package com.cheese.util.codenav.modules.setting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheese.core.tool.util.Func;
import com.cheese.util.codenav.common.constant.NormalCharConstant;
import com.cheese.util.codenav.modules.setting.entity.FieldTypeMapConfig;
import com.cheese.util.codenav.modules.setting.entity.dto.FieldTypeMapConfigDTO;
import com.cheese.util.codenav.modules.setting.mapper.FieldTypeMapConfigMapper;
import com.cheese.util.codenav.modules.setting.service.IFieldTypeMapConfigService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * FieldTypeMapConfig.java
 * 业务接口实现
 *
 * @author sobann
 */
@Service("fieldTypeMapConfigService")
public class FieldTypeMapConfigServiceImpl extends ServiceImpl<FieldTypeMapConfigMapper, FieldTypeMapConfig> implements IFieldTypeMapConfigService {

    @Override
    public Map<String, String> getTypeMap() {
        Map<String, String> map = new HashMap<>(32);
        List<FieldTypeMapConfig> list = this.list();
        list.stream().peek(item -> map.put(item.getDbType(), item.getJavaType()));
        return map;
    }

    @Override
    public List<FieldTypeMapConfig> selectList(FieldTypeMapConfigDTO fieldTypeMapConfigDTO) {
        //TODO:根据实际条件修改
        LambdaQueryWrapper<FieldTypeMapConfig> wrapper = new LambdaQueryWrapper<>();
        List<FieldTypeMapConfig> entityList = this.baseMapper.selectList(wrapper);
        if (Func.isEmpty(entityList)) {
            return Collections.emptyList();
        }
        return entityList;
    }

    @Override
    public IPage<FieldTypeMapConfig> selectPage(IPage<FieldTypeMapConfig> page, FieldTypeMapConfigDTO fieldTypeMapConfigDTO) {
//        LambdaQueryWrapper<FieldTypeMapConfig> wrapper = Wrappers.lambdaQuery(BeanUtil.copy(fieldTypeMapConfigDTO, FieldTypeMapConfig.class));
//        IPage<FieldTypeMapConfig> entityPage = this.baseMapper.selectPage(page, wrapper);
        IPage<FieldTypeMapConfig> entityPage = this.baseMapper.queryPage(page,fieldTypeMapConfigDTO);
        return entityPage;
    }

    @Override
    public FieldTypeMapConfig selectById(Long id) {
        FieldTypeMapConfig entity = this.getById(id);
        Assert.notNull(entity, "不存在的id!");
        return entity;
    }

    @Override
    public FieldTypeMapConfig selectByDataType(String dataType) {
        if (Func.isEmpty(dataType)){
            throw new RuntimeException("请传入数据类型属性");
        }
        LambdaQueryWrapper<FieldTypeMapConfig> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(FieldTypeMapConfig::getIsDeleted,0);
        wrapper.eq(FieldTypeMapConfig::getDbType,dataType);
        wrapper.last(NormalCharConstant.LIMIT_1);
        return this.baseMapper.selectOne(wrapper);
    }
}