package com.cheese.util.easyexcel.common.strategy;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 筛选策略
 *
 * @author sobann
 */
public abstract class CustomFilterStrategy<T> implements SheetWriteHandler {

    protected static final int ONE = 1;
    protected static final int ZERO = 0;

    private static final String DEFAULT_HANDLER_KEY = "defaultHandler";

    private static Map<String, WriteHandler> writeHandlerMap = new ConcurrentHashMap<>();

    protected Class<T> t;

    public CustomFilterStrategy(Class<T> clazz) {
        this.t =clazz;
        init();
    }

    protected int firstRow, lastRow, firstCol, lastCol;

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Sheet sheet = writeSheetHolder.getSheet();
        sheet.setAutoFilter(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }

    /**
     * 初始化参数
     *
     */
    protected abstract void init();

    /**
     * 获取默认的表格处理策略
     *
     * @return
     */
    public static <T> WriteHandler getDefaultInstance(Class<T> clazz) {
        WriteHandler defaultHandler = writeHandlerMap.get(DEFAULT_HANDLER_KEY + clazz.getTypeName());
        if (Objects.nonNull(defaultHandler)) {
            return defaultHandler;
        }
        defaultHandler = new DefaultCustomFilterStrategy<T>(clazz);
        writeHandlerMap.put(DEFAULT_HANDLER_KEY + clazz.getTypeName(), defaultHandler);
        return defaultHandler;
    }
}