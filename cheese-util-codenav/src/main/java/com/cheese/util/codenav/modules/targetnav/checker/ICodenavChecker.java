package com.cheese.util.codenav.modules.targetnav.checker;

import com.cheese.util.codenav.common.domain.GenerateEntity;
import com.cheese.util.codenav.modules.targetnav.common.Order;

/**
 * 校验器顶层设计：目前已知
 * 1.1 业务参数校验：针对普通导出和特殊导出(后续设计中定义)的导出参数进行校验
 *
 * @author sobann
 */
public interface ICodenavChecker extends Order {

    /**
     * 执行申请检查
     *
     * @param uid 系统用户id
     * @param generateEntity 输入参数
     * @return 检查结果
     */
    boolean check(Long uid, GenerateEntity generateEntity);


    /**
     * 规则匹配
     *
     * @param type
     * @return
     */
    boolean match(String type);
}
