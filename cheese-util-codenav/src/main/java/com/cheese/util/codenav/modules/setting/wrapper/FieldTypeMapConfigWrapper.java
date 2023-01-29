package com.cheese.util.codenav.modules.setting.wrapper;


import com.cheese.core.base.wrapper.BaseEntityWrapper;
import com.cheese.core.tool.util.BeanUtil;
import com.cheese.util.codenav.modules.setting.entity.FieldTypeMapConfig;
import com.cheese.util.codenav.modules.setting.entity.vo.FieldTypeMapConfigVO;

/**
 * 资料中心实体转视图
 *
 * @author sobann
 */
public class FieldTypeMapConfigWrapper extends BaseEntityWrapper<FieldTypeMapConfig, FieldTypeMapConfigVO> {

    public static FieldTypeMapConfigWrapper build() {
        return new FieldTypeMapConfigWrapper();
    }

    @Override
    public FieldTypeMapConfigVO entityVO(FieldTypeMapConfig entity) {
        FieldTypeMapConfigVO vo = BeanUtil.copy(entity,FieldTypeMapConfigVO.class);
        return vo;
    }
}