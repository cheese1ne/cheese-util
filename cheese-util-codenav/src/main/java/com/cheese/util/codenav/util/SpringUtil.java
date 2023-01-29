package com.cheese.util.codenav.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * spring bean实例获取工具类
 * @author sobann
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return (T) applicationContext.getBean(clazz);
    }

    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    public static <T> Map<String, T> getBeans(Class<T> clazz) {
        return BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, clazz, true, false);
    }

}
