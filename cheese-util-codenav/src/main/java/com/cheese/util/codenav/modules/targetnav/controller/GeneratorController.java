package com.cheese.util.codenav.modules.targetnav.controller;

import com.cheese.util.codenav.common.domain.GenerateEntity;
import com.cheese.util.codenav.modules.targetnav.service.IGeneratorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sobann
 */
@Controller
public class GeneratorController {

    @Autowired
    private IGeneratorService generatorService;

    @ApiOperation("逆向生成代码")
    @GetMapping("/generate")
    public void generateCode(GenerateEntity generateEntity, HttpServletResponse response) {
        generatorService.generateCode(generateEntity, response);
    }
}
