package com.lxq.hotchpotch.common.web.util.component;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentUtils implements EnvironmentAware {

    private static Environment environment;

    public static String getProperties(String key) {
        if (environment == null) {
            throw new RuntimeException("Environment 对象尚未初始化");
        }
        return environment.getProperty(key);
    }

    @Override
    public void setEnvironment(Environment env) {
        environment = env;
    }

}