package com.cheese.util.codenav.modules.setting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cheese.core.base.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Module: FieldTypeMapConfig.java
 *
 * @author sobann
 */
@Data
@TableName(value = "code_field_type_map_config")
@EqualsAndHashCode(callSuper = true)
public class FieldTypeMapConfig extends BaseEntity {

    /***/
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;
    /**
     * 数据库类型
     */
    private String dbType;
    /**
     * java类型
     */
    private String javaType;
}