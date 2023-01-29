package com.cheese.util.codenav.modules.targetnav.checker.normal;

import org.springframework.stereotype.Component;

/**
 * 通用检查器，检查优先级，最先
 * @author sobann
 */
@Component
public class GeneralChecker extends NormalChecker {

    @Override
    public int order() {
        return 0;
    }
}
