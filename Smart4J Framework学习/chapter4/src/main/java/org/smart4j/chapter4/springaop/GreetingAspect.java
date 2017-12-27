package org.smart4j.chapter4.springaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.smart4j.chapter4.aop.Apology;
import org.smart4j.chapter4.aop.ApologyImpl;
import org.springframework.stereotype.Component;

/**
 * @author wangjianchun
 * @create 2017/12/26
 */
//@Aspect
//@Component
public class GreetingAspect {

//    @DeclareParents(value = "org.smart4j.chapter4.springaop.GreetingImpl", defaultImpl = ApologyImpl.class)
//    private Apology apology;

    /*@Around("execution(* org.smart4j.chapter4.springaop.GreetingImpl.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        before();
        Object result = pjp.proceed();
        after();
        return result;
    }*/

    /*@Around("@annotation(org.smart4j.chapter4.springaop.Tag)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        before();
        Object result = pjp.proceed();
        after();
        return result;
    }*/

    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        before();
        Object result = pjp.proceed();
        after();
        return result;
    }

    private void before(){
        System.out.println("Before");
    }

    private void after(){
        System.out.println("After");
    }
}
