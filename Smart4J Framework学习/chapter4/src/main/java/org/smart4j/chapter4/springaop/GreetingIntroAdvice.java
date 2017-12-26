package org.smart4j.chapter4.springaop;

import org.aopalliance.intercept.MethodInvocation;
import org.smart4j.chapter4.aop.Apology;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * @author wangjianchun
 * @create 2017/12/26
 */
@Component
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology {

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        return super.invoke(mi);
    }

    @Override
    public void saySorry(String name) {
        System.out.println("Sorry! "+name);
    }
}
