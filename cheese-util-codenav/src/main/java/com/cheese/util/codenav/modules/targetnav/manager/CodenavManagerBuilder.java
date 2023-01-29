package com.cheese.util.codenav.modules.targetnav.manager;

import com.cheese.util.codenav.modules.targetnav.manager.normal.NormalCodenavManager;
import com.cheese.util.codenav.util.SpringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理器建造工厂
 *
 * @author sobann
 */
public class CodenavManagerBuilder {

    private static final String NORMAL = "normal";

    /**
     * manager缓存池
     */
    private static Map<String, ICodenavManager> codeNavManagerPool = new ConcurrentHashMap<>();

    static {
        codeNavManagerPool.put(NORMAL, SpringUtil.getBean(NormalCodenavManager.class));
    }

    /**
     * 获取manager
     *
     * @param type 管理器类型
     * @return ICodenavManager
     */
    public static ICodenavManager getManager(String type) {
        ICodenavManager manager = codeNavManagerPool.get(type);
        if (manager == null) {
            throw new RuntimeException("no codenavManager was found");
        }
        return manager;
    }
}
