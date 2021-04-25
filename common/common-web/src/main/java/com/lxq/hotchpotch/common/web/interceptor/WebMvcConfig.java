package com.lxq.hotchpotch.common.web.interceptor;

import com.lxq.hotchpotch.common.web.interceptor.example.ApiLogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 *
 * @author luxinqiang
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        registry.addInterceptor(new ApiLogInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }

}
