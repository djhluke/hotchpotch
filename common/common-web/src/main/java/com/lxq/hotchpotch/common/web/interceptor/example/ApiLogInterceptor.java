package com.lxq.hotchpotch.common.web.interceptor.example;

import com.lxq.hotchpotch.common.tool.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

/**
 * 请求接口拦截器，打印接口耗时
 *
 * @author luxinqiang
 */
@Component
@Slf4j
public class ApiLogInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("ThreadLocal StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (log.isDebugEnabled()) {
            // 1、开始时间
            long beginTime = System.currentTimeMillis();
            // 线程绑定变量（该数据只有当前请求的线程可见）
            startTimeThreadLocal.set(beginTime);
            log.info("开始计时: {}  URI: {}",
                    new SimpleDateFormat("hh:mm:ss.SSS").format(beginTime),
                    request.getRequestURI());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            log.info("ViewName: " + modelAndView.getViewName());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 打印JVM信息。
        if (log.isDebugEnabled()) {
            // 得到线程绑定的局部变量（开始时间）
            long beginTime = startTimeThreadLocal.get();
            // 2、结束时间
            long endTime = System.currentTimeMillis();
            log.info("计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                    new SimpleDateFormat("hh:mm:ss.SSS").format(endTime),
                    DateUtils.formatDateTime(endTime - beginTime),
                    request.getRequestURI(),
                    Runtime.getRuntime().maxMemory() / 1024 / 1024,
                    Runtime.getRuntime().totalMemory() / 1024 / 1024,
                    Runtime.getRuntime().freeMemory() / 1024 / 1024,
                    (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        }
    }

}
