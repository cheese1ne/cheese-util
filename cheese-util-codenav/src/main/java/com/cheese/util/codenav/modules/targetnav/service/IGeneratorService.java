package com.cheese.util.codenav.modules.targetnav.service;


import com.cheese.util.codenav.common.domain.GenerateEntity;

import javax.servlet.http.HttpServletResponse;

/**
 * 代码生成核心接口
 *
 * @author Administrator
 */
public interface IGeneratorService {

    /**
     * 管理者模式，创建代码包
     * @param generateEntity
     * @param response
     */
    void generateCode(GenerateEntity generateEntity, HttpServletResponse response);
}
