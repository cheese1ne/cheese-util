package com.cheese.util.codenav.modules.targetnav.manager;

import com.cheese.util.codenav.common.domain.GenerateEntity;
import com.cheese.util.codenav.modules.targetnav.checker.ICodenavChecker;
import com.cheese.util.codenav.modules.targetnav.handler.ICodenavHandler;

/**
 * 导出任务管理者
 * 对于检查器结果进行处理，错误信息通过TLM进行保存
 *
 * @author sobann
 */
public interface ICodenavManager<C extends ICodenavChecker,H extends ICodenavHandler> {

    /**
     * 执行申请检查
     *
     * @param uid 系统用户id
     * @param generateEntity 输入参数
     * @return 检查结果
     */
    boolean check(Long uid, GenerateEntity generateEntity);

    /**
     * 执行数据处理
     *
     * @param uid 系统用户id
     * @param generateEntity 输入参数
     * @return 数据字节流
     */
    byte[] handle(Long uid, GenerateEntity generateEntity);
}
