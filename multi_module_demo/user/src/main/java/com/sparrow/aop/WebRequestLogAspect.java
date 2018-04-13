package com.sparrow.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author wangjianchun
 * @create 2018/4/13
 */
@Aspect
@Configuration
public class WebRequestLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebRequestLogAspect.class);

    // 定义 AOP 切点 Pointcut
    @Pointcut("execution(* com.sparrow.controller.*.*(..))")
    public void controllerLog() {
    }

    // log all of controller
    @Before("controllerLog()")
    public void before(JoinPoint joinPoint) {
        logger.info("className: {}, method: {}, params: {}",
                joinPoint.getSignature().getDeclaringType().getCanonicalName(),
                joinPoint.getSignature().getName(),
                Arrays.asList(joinPoint.getArgs()));
    }

    // result of return
    @AfterReturning(pointcut = "controllerLog()", returning = "retVal")
    public void after(JoinPoint joinPoint, Object retVal) {
        logger.info("return value: {}", retVal);
    }

}
