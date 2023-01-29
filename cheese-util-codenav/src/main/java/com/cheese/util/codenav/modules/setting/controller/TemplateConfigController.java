package com.cheese.util.codenav.modules.setting.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.cheese.core.base.domain.Query;
import com.cheese.core.common.Condition;
import com.cheese.core.tool.util.BeanUtil;
import com.cheese.util.codenav.modules.setting.entity.TemplateConfig;
import com.cheese.util.codenav.modules.setting.entity.dto.TemplateConfigDTO;
import com.cheese.util.codenav.modules.setting.entity.vo.TemplateConfigVO;
import com.cheese.util.codenav.modules.setting.service.ITemplateConfigService;
import com.cheese.util.codenav.modules.setting.wrapper.TemplateConfigWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Module: TemplateConfig.java
 *  前端控制器
 * @author sobann
 */
@Api(tags = " 前端控制器")
@RestController
public class TemplateConfigController {
    @Autowired
    private ITemplateConfigService templateConfigService;

    /**
     * -获取全部数据
     *
     * @return
     */
    @ApiOperation("-获取全部数据")
    @GetMapping("/templateConfig/list")
    public R<List<TemplateConfigVO>> list() {
        List<TemplateConfig> entityList = templateConfigService.list();
        return R.ok(TemplateConfigWrapper.build().listVO(entityList));
    }

    /**
    * -列表查询
    *
    * @param templateConfigDTO
    * @return
    */
    @ApiOperation("-列表查询")
    @PostMapping("/templateConfig/list/query")
    public R<List<TemplateConfigVO>> list(@RequestBody TemplateConfigDTO templateConfigDTO) {
        List<TemplateConfig> entityList = templateConfigService.selectList(templateConfigDTO);
        return R.ok(TemplateConfigWrapper.build().listVO(entityList));
    }

    /**
    * -分页查询
    *
    * @param templateConfigDTO
    * @return
    */
    @ApiOperation("-分页查询")
    @PostMapping("/templateConfig/page/query")
    public R<IPage<TemplateConfigVO>> page(@RequestBody TemplateConfigDTO templateConfigDTO, Query query) {
        IPage<TemplateConfig> entityPage = templateConfigService.selectPage(Condition.getPage(query),templateConfigDTO);
        return R.ok(TemplateConfigWrapper.build().pageVO(entityPage));
    }

    /**
    * -根据id获取
    *
    * @param id
    * @return
    */
    @ApiOperation("-根据id获取")
    @GetMapping("/templateConfig/{id}/get")
    public R<TemplateConfigVO> getById(@PathVariable("id") Long id) {
        TemplateConfig entity = templateConfigService.selectById(id);
        return R.ok(TemplateConfigWrapper.build().entityVO(entity));
    }

    /**
    * -新增或修改
    *
    * @param templateConfigDTO
    * @return
    */
    @ApiOperation("-新增或修改")
    @PostMapping("/templateConfig/addOrUpdate")
    public R<Boolean> addOrUpdate(@RequestBody TemplateConfigDTO templateConfigDTO) {
        return R.ok(templateConfigService.saveOrUpdate(BeanUtil.copy(templateConfigDTO, TemplateConfig.class)));
    }

    /**
    * -根据id逻辑删除
    *
    * @param id
    * @return
    */
    @ApiOperation("-根据id逻辑删除")
    @GetMapping("/templateConfig/{id}/del")
    public R<Boolean> logicDel(@PathVariable("id") Long id) {
        return R.ok(templateConfigService.removeById(id));
    }
}