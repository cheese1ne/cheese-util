package com.cheese.util.codenav.modules.targetnav.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheese.util.codenav.modules.targetnav.entity.TableInfo;
import com.cheese.util.codenav.modules.targetnav.mapper.TargetMapper;
import com.cheese.util.codenav.modules.targetnav.service.ITargetTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author sobann
 */
@Service("targetTableService")
public class TargetTableServiceImpl extends ServiceImpl<TargetMapper, TableInfo> implements ITargetTableService {

    @Autowired
    private TargetMapper targetMapper;

    @Override
    public List<Map<String, Object>> selectAllTableInfo() {
        return targetMapper.selectTableList();
    }

    @Override
    public IPage<TableInfo> selectPage(IPage<TableInfo> page, Map param) {
        return targetMapper.queryPage(page, param);
    }
}
