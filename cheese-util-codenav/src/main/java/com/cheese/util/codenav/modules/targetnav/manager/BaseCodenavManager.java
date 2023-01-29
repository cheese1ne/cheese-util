package com.cheese.util.codenav.modules.targetnav.manager;

import com.cheese.core.tool.util.Func;
import com.cheese.util.codenav.common.domain.GenerateEntity;
import com.cheese.util.codenav.modules.targetnav.checker.ICodenavChecker;
import com.cheese.util.codenav.modules.targetnav.common.enums.CheckErrorEnum;
import com.cheese.util.codenav.modules.targetnav.handler.ICodenavHandler;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 导出任务管理者
 * 对于检查器结果进行处理，错误信息通过TLM进行保存
 *
 * @author sobann
 */
public abstract class BaseCodenavManager<C extends ICodenavChecker, H extends ICodenavHandler> implements ICodenavManager {
    public static ThreadLocal<Map<String, Object>> ERROR_TL = new ThreadLocal<>();

    private List<C> checkerList;
    private List<H> handlerList;

    public void setCheckerList(List<C> checkerList) {
        checkerList.sort(Comparator.comparing(C::order));
        this.checkerList = checkerList;
    }

    public void setHandlerList(List<H> handlerList) {
        handlerList.sort(Comparator.comparing(H::order));
        this.handlerList = handlerList;
    }


    /**
     * 执行申请检查
     *
     * @param uid            系统用户id
     * @param generateEntity 输入参数
     * @return 检查结果
     */
    @Override
    public boolean check(Long uid, GenerateEntity generateEntity) {
        boolean check = true;
        for (C checker : checkerList) {
            if (checker.match(generateEntity.getBusinessType())) {
                check = checker.check(uid, generateEntity);
                if (!check){ break; }
            }
        }
        if (!check) {
            //检查结果出现问题，获取TL中存储的错误信息，保存通过异常抛出
            StringBuilder sb = new StringBuilder();
            Map<String, Object> errorMap = ERROR_TL.get();
            for (Map.Entry<String, Object> entry : errorMap.entrySet()) {
                //获取错误描述
                String describe = CheckErrorEnum.parseType(entry.getKey()).getDescribe();
                Object value = entry.getValue();
                String errorMsg = String.format(describe, value);
                sb.append(errorMsg).append("\n");
            }
            //清理当前线程中保存的错误信息
            ERROR_TL.remove();
            throw new RuntimeException("导出检查发现错误：" + sb.toString());
        }
        return true;
    }

    /**
     * 执行数据处理
     *
     * @param uid            系统用户id
     * @param generateEntity 输入参数
     * @return 数据字节流
     */
    @Override
    public byte[] handle(Long uid, GenerateEntity generateEntity) {
        byte[] dataArr = null;
        for (H handler : handlerList) {
            if (handler.match(generateEntity.getBusinessType())) {
                dataArr = handler.handle(uid, generateEntity);
                if (Func.isNotEmpty(dataArr)){ return dataArr; }
            }
        }
        return new byte[0];
    }

}
