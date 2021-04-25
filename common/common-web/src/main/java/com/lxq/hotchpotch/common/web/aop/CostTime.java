package com.lxq.hotchpotch.common.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.annotation.Configuration;

/**
 * 切面计算方法耗时
 *
 * @author luxinqiang
 */
@Aspect
@Configuration
public class CostTime {

    protected static Logger logger = LoggerFactory.getLogger(CostTime.class);

    @Pointcut("execution(* com.yz.cloud.microserviceapi..*.*(..))")
    public void costTimeCut() {
    }

    @Before("costTimeCut()")
    public void before() {
    }

    @Around("costTimeCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        String name = AopUtils.getTargetClass(pjp.getTarget()).getName() + "." + pjp.getSignature().getName();
        long before = System.currentTimeMillis();
        Object result = pjp.proceed();
        long after = System.currentTimeMillis();
        long distance = after - before;
        logger.info(distance + "ms---" + name);
        if (distance > 1000) {
            logger.info("cost more than 1000ms");
        }
        if (distance > 10000) {
            logger.info("cost more than 10000ms");
        }
        return result;
    }

    @After("costTimeCut()")
    public void after() {
    }

}
