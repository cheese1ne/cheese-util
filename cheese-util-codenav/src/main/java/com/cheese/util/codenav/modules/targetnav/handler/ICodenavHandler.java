package com.cheese.util.codenav.modules.targetnav.handler;

import com.cheese.util.codenav.common.domain.GenerateEntity;
import com.cheese.util.codenav.modules.targetnav.common.Order;

/**
 * 处理器器顶层设计：目前已知
 * 1.1 根据业务，处理导出文件数据，输出字节流数组
 * 1.2 对于全表以及复数表实现，数据表列表通过模板方法获取
 *
 * @author sobann
 */
public interface ICodenavHandler extends Order {

    /**
     * 执行数据处理
     *
     * @param uid 系统用户id
     * @param generateEntity 输入参数
     * @return 数据字节流
     */
    byte[] handle(Long uid, GenerateEntity generateEntity);


    /**
     * 规则匹配
     *
     * @param type
     * @return
     */
    boolean match(String type);
}
