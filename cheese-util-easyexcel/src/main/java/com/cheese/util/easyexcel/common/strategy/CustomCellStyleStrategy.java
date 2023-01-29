package com.cheese.util.easyexcel.common.strategy;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.style.AbstractCellStyleStrategy;
import com.cheese.util.easyexcel.common.constant.ExcelConstant;
import org.apache.poi.ss.usermodel.*;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 自定义导出excel样式策略抽象类
 *
 * @author sobann
 */
public abstract class CustomCellStyleStrategy extends AbstractCellStyleStrategy {

    private static final String DEFAULT_HANDLER_KEY = "defaultHandler";

    private static Map<String, WriteHandler> writeHandlerMap = new ConcurrentHashMap<>();

    protected CellStyle headStyle;
    protected CellStyle contentStyle;

    @Override
    protected void initCellStyle(Workbook workbook) {
        //数据行基本样式
        CellStyle contentStyle = workbook.createCellStyle();
        contentStyle.setFillForegroundColor((short)15);
        contentStyle.setAlignment(HorizontalAlignment.CENTER);
        contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentStyle.setWrapText(Boolean.TRUE);
        // 设置内容边框样式
        contentStyle.setBorderLeft(BorderStyle.THIN);
        contentStyle.setBorderTop(BorderStyle.THIN);
        contentStyle.setBorderRight(BorderStyle.THIN);
        contentStyle.setBorderBottom(BorderStyle.THIN);
        //数据行字体样式
        Font contentFont = workbook.createFont();
        contentFont.setBold(Boolean.FALSE);
        contentFont.setFontName(ExcelConstant.DEFAULT_CONTENT_FONT_NAME);
        contentFont.setFontHeightInPoints(ExcelConstant.DEFAULT_CONTENT_FONT_SIZE);
        this.contentStyle = contentStyle;

        //表头基本样式、边框样式除背景色外拷贝自内容样式
        CellStyle headStyle = workbook.createCellStyle();
        headStyle.cloneStyleFrom(contentStyle);
        headStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
        headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //表头字体样式
        Font headFont = workbook.createFont();
        headFont.setBold(Boolean.TRUE);
        headFont.setFontName(ExcelConstant.DEFAULT_HEAD_FONT_NAME);
        headFont.setFontHeightInPoints(ExcelConstant.DEFAULT_HEAD_FONT_SIZE);
        headStyle.setFont(headFont);
        this.headStyle = headStyle;
    }

    /**
     * 具体的头部样式控制实现方法
     *
     * @param cell    单元格
     * @param head    头对象
     * @param integer 行号
     */
    @Override
    protected abstract void setHeadCellStyle(Cell cell, Head head, Integer integer);

    /**
     * 具体的内容样式控制实现方法
     *
     * @param cell    单元格对象
     * @param head    头对象
     * @param integer 行号
     */
    @Override
    protected abstract void setContentCellStyle(Cell cell, Head head, Integer integer);

    /**
     * 获取默认的表格处理策略
     *
     * @return
     */
    public static WriteHandler getDefaultInstance() {
        WriteHandler defaultHandler = writeHandlerMap.get(DEFAULT_HANDLER_KEY);
        if (Objects.nonNull(defaultHandler)) {
            return defaultHandler;
        }
        defaultHandler = new DefaultCustomCellStyleStrategy();
        writeHandlerMap.put(DEFAULT_HANDLER_KEY, defaultHandler);
        return defaultHandler;
    }
}
