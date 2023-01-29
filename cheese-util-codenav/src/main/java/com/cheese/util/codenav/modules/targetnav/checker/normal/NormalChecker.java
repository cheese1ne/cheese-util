package com.cheese.util.codenav.modules.targetnav.checker.normal;

import com.cheese.core.tool.util.Func;
import com.cheese.util.codenav.common.domain.GenerateEntity;
import com.cheese.util.codenav.modules.targetnav.checker.ICodenavChecker;
import com.cheese.util.codenav.modules.targetnav.handler.ICodenavHandler;
import com.cheese.util.codenav.modules.targetnav.manager.BaseCodenavManager;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * 普通业务类检查器父类实现，子类完成模板方法
 * @author sobann
 */
public abstract class NormalChecker implements ICodenavChecker {

    /**
     * 匹配规则:以normal开头的业务
     */
    private Pattern normalPattern = Pattern.compile("^normal");

    @Override
    public boolean check(Long uid, GenerateEntity generateEntity) {
        boolean uidCheck = true;
        boolean paramCheck = true;
        //检查参数是否存在
        if (Func.isEmpty(uid)){
            uidCheck = false;
            //todo:参数类型保存到CheckErrorEnum中
            BaseCodenavManager.ERROR_TL.get().put("NO_UID","null");
        }
        if (Func.isEmpty(generateEntity)){
            paramCheck = false;
            //todo:参数类型保存到CheckErrorEnum中
            BaseCodenavManager.ERROR_TL.get().put("NO_GENERATE_ENTITY","null");
        }
        return uidCheck && paramCheck;
    }

    @Override
    public boolean match(String type) {
        return normalPattern.matcher(type).matches();
    }

}
