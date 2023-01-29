package com.cheese.util.codenav.modules.targetnav.service.impl;

import com.cheese.core.tool.util.Func;
import com.cheese.core.tool.util.IoUtil;
import com.cheese.util.codenav.common.domain.GenerateEntity;
import com.cheese.util.codenav.modules.targetnav.manager.CodenavManagerBuilder;
import com.cheese.util.codenav.modules.targetnav.manager.ICodenavManager;
import com.cheese.util.codenav.modules.targetnav.service.IGeneratorService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 代码生成核心接口
 *
 * @author sobann
 */
@Service("generatorService")
public class GeneratorServiceImpl implements IGeneratorService {

    @Override
    public void generateCode(GenerateEntity generateEntity, HttpServletResponse response) {
        try {
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + generateEntity.getBusinessType() + generateEntity.getType() + ".zip");
            String businessType = Func.toStr(generateEntity.getBusinessType(), "normal");
            ICodenavManager manager = CodenavManagerBuilder.getManager(businessType);
            //TODO：鉴权功能待开发，用户身份信息通过TOKEN解析完成后存入线程中
            manager.check(1L, generateEntity);
            InputStream in = new ByteArrayInputStream(manager.handle(1L, generateEntity));
            IoUtil.copy(in, response.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException("生成表失败!", e);
        }
    }
}
