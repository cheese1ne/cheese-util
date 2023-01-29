package com.cheese.util.codenav.modules.targetnav.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.cheese.core.base.domain.Query;
import com.cheese.core.common.Condition;
import com.cheese.util.codenav.modules.targetnav.entity.TableInfo;
import com.cheese.util.codenav.modules.targetnav.service.ITargetTableService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author sobann
 */
@RestController
public class TargetTableController {

    @Autowired
    private ITargetTableService targetTableService;

    /**
     * -列表查询
     *
     * @param params
     * @return
     */
    @ApiOperation("-列表查询")
    @PostMapping("/targetTable/list/query")
    public R<List<Map<String,Object>>> list(@RequestBody Map params) {
        List<Map<String,Object>> entityList = targetTableService.selectAllTableInfo();
        return R.ok(entityList);
    }

    /**
     * -分页查询
     *
     * @param params
     * @return
     */
    @ApiOperation("-分页查询")
    @PostMapping("/targetTable/page/query")
    public R<IPage<TableInfo>> page(@RequestBody Map params, Query query) {
        IPage<TableInfo> entityPage = targetTableService.selectPage(Condition.getPage(query),params);
        return R.ok(entityPage);
    }
}
