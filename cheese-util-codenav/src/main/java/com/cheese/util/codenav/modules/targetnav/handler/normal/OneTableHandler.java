package com.cheese.util.codenav.modules.targetnav.handler.normal;

import com.cheese.util.codenav.common.constant.BusinessConstant;
import com.cheese.util.codenav.common.domain.GenerateEntity;
import org.springframework.stereotype.Component;

/**
 * 默认模式下单表处理器
 * @author sobann
 */
@Component
public class OneTableHandler extends NormalHandler{
    @Override
    public void recordUserLog(Long uid) {
        //..记录用户操作行为
    }

    @Override
    public byte[] gainDataBytes(GenerateEntity generateEntity) {
        //在处理器层面对数据流为空的行为作出判断，此处不匹配返回null即可
        if (!BusinessConstant.ONE_TABLE.equals(generateEntity.getType())){
            return null;
        }
        return super.generate(generateEntity.getTableName(),generateEntity);
    }

    @Override
    public int order() {
        return 101;
    }
}
