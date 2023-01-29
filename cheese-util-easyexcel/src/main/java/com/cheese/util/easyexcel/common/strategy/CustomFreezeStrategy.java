package com.cheese.util.easyexcel.common.strategy;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 冻结策略
 *
 * @author sobann
 */
public abstract class CustomFreezeStrategy<T> implements SheetWriteHandler {

    protected static final int ONE = 1;
    protected static final int ZERO = 0;

    private static final String DEFAULT_HANDLER_KEY = "defaultHandler";

    private static Map<String, WriteHandler> writeHandlerMap = new ConcurrentHashMap<>();

    protected Class<T> t;
    protected int colSplit, rowSplit, leftmostColumn, topRow;

    public CustomFreezeStrategy(Class<T> clazz) {
        this.t = clazz;
        init();
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Sheet sheet = writeSheetHolder.getSheet();
        sheet.createFreezePane(colSplit, rowSplit, leftmostColumn, topRow);
    }

    /**
     * 筛选列初始化参数
     */
    protected abstract void init();

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
        defaultHandler = new DefaultCustomFreezeStrategy(null);
        writeHandlerMap.put(DEFAULT_HANDLER_KEY, defaultHandler);
        return defaultHandler;
    }
}
