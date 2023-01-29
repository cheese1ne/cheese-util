package com.cheese.util.easyexcel.common.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel处理注解
 *
 * @author sobann
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface ComplexExcel {
    /**
     * 下拉框数据
     * @return
     */
    String[] dropDownArr() default {};

    /**
     * 文本所在列
     */
    int currentCol() default 0;

    /**
     * 所在数据是否需要进行列聚合，一般报表表格中数据的合并单元格都是将列进行合并
     */
    boolean needColAggregation() default false;

    /**
     * 所在列是否需要筛选
     */
    boolean needColAddFilter() default false;
}
