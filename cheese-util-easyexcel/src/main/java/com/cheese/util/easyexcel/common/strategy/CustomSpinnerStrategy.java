package com.cheese.util.easyexcel.common.strategy;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.*;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 下拉框策略
 *
 * @author sobann
 */
public abstract class CustomSpinnerStrategy<T> implements SheetWriteHandler {

    protected static final int ONE = 1;
    protected static final int TWO_THOUSAND = 2000;
    protected static final String COLON = ":";

    private static final String DEFAULT_HANDLER_KEY = "defaultHandler";

    private static Map<String, WriteHandler> writeHandlerMap = new ConcurrentHashMap<>();

    protected Map<Integer, String[]> mapDropDown;
    protected int firstRow;
    protected int lastRow;
    protected Class<T> t;

    public CustomSpinnerStrategy(Map<Integer, String[]> mapDropDown, int firstRow, int lastRow) {
        this.mapDropDown = mapDropDown;
        this.firstRow = firstRow;
        this.lastRow = lastRow;
    }

    public CustomSpinnerStrategy(Class<T> clazz) {
        this.t = clazz;
        this.init();
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Sheet sheet = writeSheetHolder.getSheet();
        this.createRangeAddressList(sheet);
    }

    /**
     * 初始化参数
     */
    protected abstract void init();

    /**
     * 设置下拉框
     *
     * @param sheet
     */
    protected abstract void createRangeAddressList(Sheet sheet);

    /**
     * 获取默认的表格处理策略
     *
     * @return
     */
    public static <T> WriteHandler getDefaultInstance(Map<Integer, String[]> mapDropDown, int firstRow, int lastRow) {
        String key = DEFAULT_HANDLER_KEY + mapDropDown.toString() + COLON + firstRow + COLON + lastRow;
        WriteHandler defaultHandler = writeHandlerMap.get(key);
        if (Objects.nonNull(defaultHandler)) {
            return defaultHandler;
        }
        defaultHandler = new DefaultCustomSpinnerStrategy<T>(mapDropDown, firstRow, lastRow);
        writeHandlerMap.put(key, defaultHandler);
        return defaultHandler;
    }

    /**
     * 获取默认的表格处理策略
     *
     * @return
     */
    public static <T> WriteHandler getDefaultInstance(Class<T> clazz){
        WriteHandler defaultHandler = writeHandlerMap.get(DEFAULT_HANDLER_KEY + clazz.getTypeName());
        if (Objects.nonNull(defaultHandler)) {
            return defaultHandler;
        }
        defaultHandler = new DefaultCustomSpinnerStrategy<T>(clazz);
        writeHandlerMap.put(DEFAULT_HANDLER_KEY + clazz.getTypeName(), defaultHandler);
        return defaultHandler;
    }
}