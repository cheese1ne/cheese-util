package com.cheese.util.codenav.modules.setting.wrapper;


import com.cheese.core.base.wrapper.BaseEntityWrapper;
import com.cheese.core.tool.util.BeanUtil;
import com.cheese.util.codenav.modules.setting.entity.TemplateConfig;
import com.cheese.util.codenav.modules.setting.entity.vo.TemplateConfigVO;

/**
 * 资料中心实体转视图
 *
 * @author sobann
 */
public class TemplateConfigWrapper extends BaseEntityWrapper<TemplateConfig, TemplateConfigVO> {

    public static TemplateConfigWrapper build() {
        return new TemplateConfigWrapper();
    }

    @Override
    public TemplateConfigVO entityVO(TemplateConfig entity) {
        TemplateConfigVO vo = BeanUtil.copy(entity,TemplateConfigVO.class);
        return vo;
    }
}