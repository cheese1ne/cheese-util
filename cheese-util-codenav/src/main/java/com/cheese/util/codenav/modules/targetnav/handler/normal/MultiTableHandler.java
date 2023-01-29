package com.cheese.util.codenav.modules.targetnav.handler.normal;

import com.cheese.core.tool.util.IoUtil;
import com.cheese.util.codenav.common.constant.BusinessConstant;
import com.cheese.util.codenav.common.domain.GenerateEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 默认模式下单表处理器
 *
 * @author sobann
 */
@Slf4j
@Component
public abstract class MultiTableHandler extends NormalHandler {
    @Override
    public void recordUserLog(Long uid) {
        //..记录用户操作行为
    }

    @Override
    public byte[] gainDataBytes(GenerateEntity generateEntity) {
        try {
            //在处理器层面对数据流为空的行为作出判断，此处不匹配返回null即可
            if (!BusinessConstant.ALL_TABLE.equals(generateEntity.getType()) &&
                    !BusinessConstant.MATCH_PREFIX.equals(generateEntity.getType())) {
                return null;
            }
            List<String> tables = targetTableNames(generateEntity);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ZipOutputStream zip = new ZipOutputStream(outputStream);
            for (String table : tables) {
                byte[] generate = this.generate(table, generateEntity);
                zip.putNextEntry(new ZipEntry(table + ".zip"));
                zip.write(generate);
                zip.closeEntry();
            }
            //正常关闭zip流
            IoUtil.closeQuietly(zip);
            return outputStream.toByteArray();
        } catch (Exception e) {
            log.error("normal multiTableHandler handling errors", e);
            return new byte[0];
        }
    }

    /**
     * 获取目标表名称列表
     *
     * @param generateEntity
     * @return
     */
    protected abstract List<String> targetTableNames(GenerateEntity generateEntity);
}
