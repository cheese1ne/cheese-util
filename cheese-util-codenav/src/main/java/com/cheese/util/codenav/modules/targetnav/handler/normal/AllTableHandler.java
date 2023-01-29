package com.cheese.util.codenav.modules.targetnav.handler.normal;

import com.cheese.util.codenav.common.domain.GenerateEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * normal模式下的全表处理
 * @author sobann
 */
@Component
public class AllTableHandler extends MultiTableHandler {
    @Override
    protected List<String> targetTableNames(GenerateEntity generateEntity) {
        return targetMapper.queryAllTable();
    }

    @Override
    public int order() {
        return 103;
    }
}
