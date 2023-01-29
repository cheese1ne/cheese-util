package com.cheese.util.easyexcel.common.strategy;

import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;

/**
 * 默认冻结策略
 *
 * @author sobann
 */
public class DefaultCustomFreezeStrategy<T> extends CustomFreezeStrategy<T> {

    public  DefaultCustomFreezeStrategy(Class<T> clazz) {
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
     *
     */
    @Override
    protected void init() {
        this.freezeInit();
    }

    /**
     * 默认筛选列初始化
     *
     */
    private void freezeInit() {
        super.colSplit = ZERO;
        super.rowSplit = ONE;
        super.leftmostColumn = ZERO;
        super.topRow = ONE;
    }
}
