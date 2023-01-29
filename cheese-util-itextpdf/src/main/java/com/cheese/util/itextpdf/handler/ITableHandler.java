package com.cheese.util.itextpdf.handler;

/**
 * 表格处理器 业务接口
 *
 * @author sobann
 */
public interface ITableHandler {

    /**
     * 判断当前处理器类型是否匹配
     *
     * @param type
     * @return
     */
    boolean match(String type);

    /**
     * 处理数据流
     *
     * @param param 查询参数
     */
    byte[] handle(Object param);

}
