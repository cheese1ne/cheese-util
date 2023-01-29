package com.cheese.util.codenav.modules.targetnav.handler.normal;

import com.cheese.util.codenav.common.domain.GenerateEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * normal模式下匹配表前缀的表
 * @author sobann
 */
@Component
public class MatchPrefixTableHandler extends MultiTableHandler {
    @Override
    protected List<String> targetTableNames(GenerateEntity generateEntity) {
        List<String> tables = targetMapper.queryAllTable();
        //过滤符合条件的表
        return tables.stream().filter(item->item.startsWith(generateEntity.getTablePrefix())).collect(Collectors.toList());
    }

    @Override
    public int order() {
        return 102;
    }
}
