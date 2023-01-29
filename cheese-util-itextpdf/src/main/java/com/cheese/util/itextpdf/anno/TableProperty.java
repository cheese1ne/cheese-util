package com.cheese.util.itextpdf.anno;

import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表格属性
 * tip:
 *  1.name属性是pdf表格的文本，可以填充占位符例如 姓名:${name}
 *  2.表格一般分为表头、数据行以及表尾，此注解暂处理表头表尾部分，后续会添加数据行的定义属性，目前isHeader和isFooter一般互斥
 *  3.rowSpan和colSpan是合并行和合并列，align为样式属性默认为居中样式，border为边框属性，默认创建全部边框
 *
 *  复杂表格单元格合并测试时会出现单元格位置不对的情况，一般是当前属性的单元格位置定义错误，
 *  itextpdf表格添加单元格的顺序与表格第一列的上边界平齐!!!
 *
 * @author sobann
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableProperty {
    /**
     * 是否是头
     */
    boolean isHeader() default false;

    /**
     * 是否是尾
     */
    boolean isFooter() default false;

    /**
     * 是否是填充数据
     */
    boolean isContent() default false;

    /**
     * 名称
     */
    String name() default "";

    /**
     * 合并行
     */
    int rowSpan() default 1;

    /**
     * 合并列
     */
    int colSpan() default 1;

    /**
     * 居中样式
     */
    int align() default Element.ALIGN_CENTER;

    /**
     * 边框
     */
    int border() default Rectangle.BOX;
}
