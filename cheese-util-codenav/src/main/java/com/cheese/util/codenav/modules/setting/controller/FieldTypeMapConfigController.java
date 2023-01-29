package com.cheese.util.codenav.modules.setting.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.cheese.core.base.domain.Query;
import com.cheese.core.common.Condition;
import com.cheese.core.tool.util.BeanUtil;
import com.cheese.util.codenav.modules.setting.entity.FieldTypeMapConfig;
import com.cheese.util.codenav.modules.setting.entity.dto.FieldTypeMapConfigDTO;
import com.cheese.util.codenav.modules.setting.entity.vo.FieldTypeMapConfigVO;
import com.cheese.util.codenav.modules.setting.service.IFieldTypeMapConfigService;
import com.cheese.util.codenav.modules.setting.wrapper.FieldTypeMapConfigWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Module: FieldTypeMapConfig.java
 *  前端控制器
 * @author sobann
 */
@Api(tags = " 前端控制器")
@RestController
public class FieldTypeMapConfigController {
    @Autowired
    private IFieldTypeMapConfigService fieldTypeMapConfigService;

    /**
     * -获取全部数据
     *
     * @return
     */
    @ApiOperation("-获取全部数据")
    @GetMapping("/fieldTypeMapConfig/list")
    public R<List<FieldTypeMapConfigVO>> list() {
        List<FieldTypeMapConfig> entityList = fieldTypeMapConfigService.list();
        return R.ok(FieldTypeMapConfigWrapper.build().listVO(entityList));
    }

    /**
    * -列表查询
    *
    * @param fieldTypeMapConfigDTO
    * @return
    */
    @ApiOperation("-列表查询")
    @PostMapping("/fieldTypeMapConfig/list/query")
    public R<List<FieldTypeMapConfigVO>> list(@RequestBody FieldTypeMapConfigDTO fieldTypeMapConfigDTO) {
        List<FieldTypeMapConfig> entityList = fieldTypeMapConfigService.selectList(fieldTypeMapConfigDTO);
        return R.ok(FieldTypeMapConfigWrapper.build().listVO(entityList));
    }

    /**
    * -分页查询
    *
    * @param fieldTypeMapConfigDTO
    * @return
    */
    @ApiOperation("-分页查询")
    @PostMapping("/fieldTypeMapConfig/page/query")
    public R<IPage<FieldTypeMapConfigVO>> page(@RequestBody FieldTypeMapConfigDTO fieldTypeMapConfigDTO, Query query) {
        IPage<FieldTypeMapConfig> entityPage = fieldTypeMapConfigService.selectPage(Condition.getPage(query),fieldTypeMapConfigDTO);
        return R.ok(FieldTypeMapConfigWrapper.build().pageVO(entityPage));
    }

    /**
    * -根据id获取
    *
    * @param id
    * @return
    */
    @ApiOperation("-根据id获取")
    @GetMapping("/fieldTypeMapConfig/{id}/get")
    public R<FieldTypeMapConfigVO> getById(@PathVariable("id") Long id) {
        FieldTypeMapConfig entity = fieldTypeMapConfigService.selectById(id);
        return R.ok(FieldTypeMapConfigWrapper.build().entityVO(entity));
    }

    /**
    * -新增或修改
    *
    * @param fieldTypeMapConfigDTO
    * @return
    */
    @ApiOperation("-新增或修改")
    @PostMapping("/fieldTypeMapConfig/addOrUpdate")
    public R<Boolean> addOrUpdate(@RequestBody FieldTypeMapConfigDTO fieldTypeMapConfigDTO) {
        return R.ok(fieldTypeMapConfigService.saveOrUpdate(BeanUtil.copy(fieldTypeMapConfigDTO, FieldTypeMapConfig.class)));
    }

    /**
    * -根据id逻辑删除
    *
    * @param id
    * @return
    */
    @ApiOperation("-根据id逻辑删除")
    @GetMapping("/fieldTypeMapConfig/{id}/del")
    public R<Boolean> logicDel(@PathVariable("id") Long id) {
        return R.ok(fieldTypeMapConfigService.removeById(id));
    }
}