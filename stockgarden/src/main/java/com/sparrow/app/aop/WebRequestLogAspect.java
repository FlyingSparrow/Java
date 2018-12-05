package com.sparrow.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * <p>Title: WebRequestLogAspect</p>
 * <p>Description: Web请求日志AOP拦截器类</p>
 * 说明：记录web请求的类名、方法名、请求参数，以及web请求的返回值
 *
 * @author wjc
 * @date 2018/12/5
 */
@Aspect
@Configuration
public class WebRequestLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebRequestLogAspect.class);

    /**
     * 定义 AOP 切点 Pointcut
     * 两个..代表所有子目录，最后括号里的两个..代表所有参数
     */
    @Pointcut("execution(* com.sparrow.stockgarden.web..*.*(..))")
    public void controllerLog() {
    }

    /**
     * log all of controller
     *
     * @param joinPoint
     */
    @Before("controllerLog()")
    public void before(JoinPoint joinPoint) {
        logger.info("className: {}, method: {}, params: {}",
                joinPoint.getSignature().getDeclaringType().getCanonicalName(),
                joinPoint.getSignature().getName(),
                Arrays.asList(joinPoint.getArgs()));
    }

    /**
     * result of return
     *
     * @param joinPoint
     * @param retVal
     */
    @AfterReturning(pointcut = "controllerLog()", returning = "retVal")
    public void after(JoinPoint joinPoint, Object retVal) {
        logger.info("return value: {}", retVal);
    }

    @Around("controllerLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        // ob 为方法的返回值
        Object returnValue = pjp.proceed();
        String className = pjp.getSignature().getDeclaringType().getCanonicalName();
        String method = pjp.getSignature().getName();
        logger.info("className: {}, method: {}, 耗时: {} 毫秒", className, method,
                (System.currentTimeMillis() - startTime));

        return returnValue;
    }

}
