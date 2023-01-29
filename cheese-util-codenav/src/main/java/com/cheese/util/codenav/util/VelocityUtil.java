package com.cheese.util.codenav.util;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.util.Map;
import java.util.Properties;

/**
 * Velocity 工具类
 * @author sobann
 */
public class VelocityUtil {

    static {
        //设置velocity类加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
    }

    /**
     * 初始化Velocity上下文
     * @param context
     * @return
     */
    public static VelocityContext initContext(Map<String,Object> context){
       return new VelocityContext(context);
    }
}
