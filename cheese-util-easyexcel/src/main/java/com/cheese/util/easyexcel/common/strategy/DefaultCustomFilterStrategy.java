package com.cheese.util.easyexcel.common.strategy;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * 默认筛选策略
 *
 * @author sobann
 */
public class DefaultCustomFilterStrategy<T> extends CustomFilterStrategy<T> {

    public DefaultCustomFilterStrategy(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        super.beforeSheetCreate(writeWorkbookHolder, writeSheetHolder);
    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        super.afterSheetCreate(writeWorkbookHolder, writeSheetHolder);
    }

    /**
     * 筛选列初始化参数
     */
    @Override
    protected void init() {
        this.filterInit();
    }

    /**
     * 默认筛选列初始化
     */
    private void filterInit() {
        //easyExcel筛选起始行、终止行、以及起始列
        Field[] declaredFields = this.t.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            ExcelProperty annotation = declaredField.getAnnotation(ExcelProperty.class);
            if (Objects.nonNull(annotation)) {
                super.firstRow = ZERO;
                super.lastRow = ZERO;
                super.firstCol = ZERO;
                break;
            }
        }
        //表头最大列数
        for (Field declaredField : declaredFields) {
            ExcelProperty annotation = declaredField.getAnnotation(ExcelProperty.class);
            if (Objects.nonNull(annotation) && !declaredField.equals(declaredFields[declaredFields.length - ONE])) {
                super.lastCol++;
            }
        }
    }
}
