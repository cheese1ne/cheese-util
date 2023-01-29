package com.cheese.util.itextpdf.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表格属性直接，用于存放表名、是否包含表头、是否包含数据列、是否包含表等内容
 * tip:
 * 1.colnums个数要与colWidth的长度相等!!!
 * 2.colnums的数量一般根据表格的最大列数量确定，如果表格中存在很多错位合并表格，则人为取小单元格进行合并处理，此时列数量满足通过合并操作满足所有单元格即可
 *
 *
 * @author sobann
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableModel {

    /**
     * 列数量，一般是表格中单行最多列的列数
     */
    int colnums();

    /**
     * colnums定义的每一列的自定义宽度
     */
    float[] colWidth();

    /**
     * 默认每一页数据行的行数
     * tip：
     * 1.默认数据行不进行合并操作
     * 2.此属性用于计算总页数以及分页
     */
    int dataCountPer() default 25;

    /**
     * 标题
     */
    String title() default "";

    /**
     * 副标题
     */
    String subTitle() default "";

    /**
     * 定制化表头
     */
    boolean diyHeader() default false;

    /**
     * 定制化数据行
     */
    boolean diyContent() default false;

    /**
     * 定制化表尾
     */
    boolean diyFooter() default false;
}
