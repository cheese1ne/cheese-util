package com.cheese.util.codenav.modules.targetnav.component;

import com.cheese.util.codenav.modules.targetnav.checker.normal.NormalChecker;
import com.cheese.util.codenav.modules.targetnav.handler.normal.NormalHandler;
import com.cheese.util.codenav.modules.targetnav.manager.normal.NormalCodenavManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 管理器实例定义
 * @author sobann
 */
@Configuration
public class ManagerConfiguration {

    @Bean("normalCodenavManager")
    public NormalCodenavManager normalCodenavManager(List<NormalChecker> checkers,
                                                      List<NormalHandler> handlers){
        NormalCodenavManager manager = new NormalCodenavManager();
        manager.setCheckerList(checkers);
        manager.setHandlerList(handlers);
        return manager;
    }
}
