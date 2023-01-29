package com.cheese.util.easyexcel.common.strategy;

import com.alibaba.excel.metadata.Head;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 默认的自定义excel表格处理策略
 *
 * @author sobann
 */
public class DefaultCustomCellStyleStrategy extends CustomCellStyleStrategy {

    @Override
    protected void initCellStyle(Workbook workbook) {
        super.initCellStyle(workbook);
    }

    @Override
    protected void setHeadCellStyle(Cell cell, Head head, Integer integer) {
        cell.setCellStyle(super.headStyle);
    }

    @Override
    protected void setContentCellStyle(Cell cell, Head head, Integer integer) {
        cell.setCellStyle(super.contentStyle);
    }
}
