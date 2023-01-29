package com.cheese.util.easyexcel.common.strategy;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.excel.metadata.Head;
import com.cheese.util.easyexcel.common.anno.ComplexExcel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

/**
 * 自定义单元格合并策略，与ComplexExcel注解联合使用
 * merge方法触发时机：afterCellDispose()调用，此时sheet只初始化一个表头，实际的数值要从list中获取
 *
 * @author sobann
 */
public class DefaultCustomMergeStrategy<T> extends CustomMergeStrategy<T> {

    public DefaultCustomMergeStrategy(Class<T> clazz, List<T> dataList) {
        super(clazz,dataList);
    }

    @Override
    protected void merge(Sheet sheet, Cell cell, Head head, Integer relativeRowIndex) {
        mergeContent(sheet, head, relativeRowIndex);
    }

    /**
     * 初始化获取表头行数
     *
     * @return
     */
    @Override
    protected int obtainHeadRow() {
        int headRow = ONE;
        Field[] declaredFields = this.t.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(Boolean.TRUE);
            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
            if (Objects.nonNull(excelProperty) && excelProperty.value().length > headRow) {
                headRow = excelProperty.value().length;
            }
        }
        return headRow;
    }

    @Override
    protected void mergeContent(Sheet sheet, Head head, Integer relativeRowIndex) {
        if (relativeRowIndex != ZERO) {
            return;
        }
        try {
            String fieldName = head.getFieldName();
            Field field = this.t.getDeclaredField(fieldName);
            field.setAccessible(Boolean.TRUE);
            ComplexExcel annotation = field.getAnnotation(ComplexExcel.class);
            if (Objects.nonNull(annotation) && annotation.needColAggregation() && this.totalRow > ONE) {
                //找到了需要合并的列，获取列号
                int cloIndex = annotation.currentCol();
                //获取首行初始比较值:headRow是行数，转换为角标需要-1，获取数据行要+1
                String tempText = this.getCellString(ZERO, fieldName);
                for (int i = headRow, mergeNum = ZERO, rowFrom = headRow; i < totalRow; i++) {
                    String text = this.getCellString(i - headRow, fieldName);
                    if (tempText.equals(text)) {
                        if (i != headRow) {
                            mergeNum++;
                        }
                        if (i == totalRow - ONE && mergeNum != ZERO) {
                            //最后一行如果mergeNum不为0也合并
                            this.mergeCell(sheet, rowFrom, rowFrom + mergeNum, cloIndex, cloIndex);
                        }
                    } else {
                        if (mergeNum != ZERO) {
                            this.mergeCell(sheet, rowFrom, rowFrom + mergeNum, cloIndex, cloIndex);
                        }
                        //如果mergeNum不为0合并，并且重置角标以及被比较元素
                        rowFrom = rowFrom + mergeNum + ONE;
                        mergeNum = ZERO;
                        tempText = text;
                    }
                }
            }
        } catch (Exception e) {
            throw new ExcelAnalysisException(e.getMessage(), e);
        }


    }

    /**
     * 获取单元格内容
     * @param rowIndex 索引
     * @param fieldName 字段文本
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private String getCellString(int rowIndex, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        T t = dataList.get(rowIndex);
        Field field = t.getClass().getDeclaredField(fieldName);
        field.setAccessible(Boolean.TRUE);
        String context = String.valueOf(field.get(t));
        return Objects.isNull(context) ? "" : context;
    }

    /**
     *
     * @param sheet
     * @param firstRow
     * @param lastRow
     * @param firstCol
     * @param lastCol
     */
    private void mergeCell(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        CellRangeAddress region = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        sheet.addMergedRegionUnsafe(region);
    }


}
