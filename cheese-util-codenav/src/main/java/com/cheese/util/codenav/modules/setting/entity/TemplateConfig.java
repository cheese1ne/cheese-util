package com.cheese.util.codenav.modules.setting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cheese.core.base.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Module: TemplateConfig.java
 *
 * @author sobann
 */
@Data
@TableName(value = "code_template_config")
@EqualsAndHashCode(callSuper = true)
public class TemplateConfig extends BaseEntity {

    /***/
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;
    /**
     * 模板类型
     */
    private String type;
    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 模板路径
     */
    private String tempaltePath;
    /**
     * 生成文件相对路径
     */
    private String fileName;
    /**
     * 备注
     */
    private String remark;
}