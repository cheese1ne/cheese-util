package com.cheese.util.mybatis.plugin.core;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

/**
 * 自定义mapper-基类IBaseService
 * 调用插入方法时候请添加事务进行控制，推荐spring的声明式事务注解
 * @author sobann
 */
public interface CustomBaseMapper<T> extends BaseMapper<T> {
    /**
     * 批量插入 仅适用于mysql
     * insertBatchSomeColumn是mybatis-plus底层集成AbstractMethod的关于处理mysql批量插入的方法
     * tip：只能适用于 mysql数据库
     *
     * @param entityList 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(Collection<T> entityList);
}
