package org.smart4j.framework.annotation;

import java.lang.annotation.*;

/**
 * 切面注解
 * @author wangjianchun
 * @create 2017/12/27
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * 注解
     * @return
     */
    Class<? extends Annotation> value();
}
