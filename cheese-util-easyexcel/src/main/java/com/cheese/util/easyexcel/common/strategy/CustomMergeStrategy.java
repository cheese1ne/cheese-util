package com.cheese.util.easyexcel.common.strategy;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义单元格合并策略，与ComplexExcel注解联合使用
 * merge方法触发时机：afterCellDispose()调用，此时sheet只初始化一个表头，实际的数值要从list中获取
 *
 * @author sobann
 */
public abstract class CustomMergeStrategy<T> extends AbstractMergeStrategy {

    protected static final int ONE = 1;
    protected static final int ZERO = 0;

    private static final String DEFAULT_HANDLER_KEY = "defaultHandler";

    private static Map<String, WriteHandler> writeHandlerMap = new ConcurrentHashMap<>();

    protected Class<T> t;
    protected List<T> dataList;
    protected int headRow;
    protected int totalRow;

    public CustomMergeStrategy(Class<T> clazz, List<T> dataList) {
        this.t = clazz;
        this.dataList = dataList;
        this.headRow = obtainHeadRow();
        this.totalRow = headRow + dataList.size();
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
    protected abstract int obtainHeadRow();

    /**
     * 合并单元格
     *
     * @param sheet            excel工作簿对象
     * @param head             头对象
     * @param relativeRowIndex 表头角标
     */
    protected abstract void mergeContent(Sheet sheet, Head head, Integer relativeRowIndex);

    /**
     * 获取默认的表格处理策略
     *
     * @return
     */
    public static <T> WriteHandler getDefaultInstance(Class<T> clazz, List<T> dataList) {
        WriteHandler defaultHandler = writeHandlerMap.get(DEFAULT_HANDLER_KEY + clazz.getTypeName() + dataList.size());
        if (Objects.nonNull(defaultHandler)) {
            return defaultHandler;
        }
        defaultHandler = new DefaultCustomMergeStrategy<T>(clazz, dataList);
        writeHandlerMap.put(DEFAULT_HANDLER_KEY + clazz.getTypeName() + dataList.size(), defaultHandler);
        return defaultHandler;
    }
}