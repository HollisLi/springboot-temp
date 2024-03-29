package com.temp.context;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Spring上下文, 通过此类从上下文中获取对象
 *
 * @author Hollis
 * @since 2024-03-03 11:10
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    public SpringContextHolder() {
    }

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    public static Object getBean(String beanName) {
        assertContextInjected();
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(String beanName, Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(beanName, requiredType);
    }

    public static String getProperty(String key) {
        return applicationContext.getEnvironment().getProperty(key);
    }

    private static void assertContextInjected() {
        Assert.notNull(applicationContext, "ApplicationContext属性未注入, 请在Application中定义SpringContextHolder");
    }

    public static void clearHolder() {
        applicationContext = null;
    }
}
