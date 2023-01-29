package com.cheese.util.codenav.modules.setting.entity.vo;

import com.cheese.util.codenav.modules.setting.entity.TemplateConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Module: TemplateConfig.java
 * TemplateConfig的视图类
 *
 * @author sobann
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateConfigVO extends TemplateConfig {

    private static final long serialVersionUID = 1L;

    /***/
    private Long id;

}
