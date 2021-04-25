package com.lxq.hotchpotch.common.web.util.component;

import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author luxinqiang
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 根据bean的class获取bean
     * <br/>
     * 2021-02-19 10:46
     *
     * @param clazz
     * @return T
     * @author luxinqiang
     */
    public static <T> T getBean(Class<T> clazz) {
        check();
        return applicationContext.getBean(clazz);
    }

    /**
     * 根据bean的name获取bean
     * <br/>
     * 2021-02-19 10:47
     *
     * @param name
     * @return java.lang.Object
     * @author luxinqiang
     */
    public static Object getBean(String name) {
        check();
        return applicationContext.getBean(name);
    }

    /**
     * 根据bean的name和class获取bean
     * <br/>
     * 2021-02-19 10:51
     *
     * @param name
     * @param clazz
     * @return T
     * @author luxinqiang
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        check();
        return applicationContext.getBean(name, clazz);
    }

    /**
     * 获取环境变量值
     * <br/>
     * 2021-02-19 10:48
     *
     * @param key
     * @return java.lang.String
     * @author luxinqiang
     */
    public static String getProperty(String key) {
        check();
        return applicationContext.getEnvironment().getProperty(key);
    }

    /**
     * 此处写法参照 org.springframework.boot.actuate.context.ShutdownEndpoint （第二种写法）
     * <br/>
     * 2021-03-04 17:43
     *
     * @param
     * @return void
     * @author luxinqiang
     */
    public static void restartApplication() {
        ConfigurableApplicationContext cac = (ConfigurableApplicationContext) applicationContext;
        // 启动类
        String[] beanNamesForAnnotation = cac.getBeanNamesForAnnotation(SpringBootApplication.class);
        Class<?> applicationClazz = cac.getBean(beanNamesForAnnotation[0]).getClass();
        // 启动参数
        ApplicationArguments applicationArgs = cac.getBean(ApplicationArguments.class);
        // 开启一个线程运行
        Thread thread = new Thread(() -> {
            cac.close();
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            SpringApplication.run(applicationClazz, applicationArgs.getSourceArgs());
        });
        thread.setDaemon(false);
        thread.start();
    }

    private static void check() {
        if (applicationContext == null) {
            throw new RuntimeException("ApplicationContext 对象尚未初始化");
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        applicationContext = ac;
    }

}
