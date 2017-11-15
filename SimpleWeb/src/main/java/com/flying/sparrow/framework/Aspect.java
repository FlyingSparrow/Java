package com.flying.sparrow.framework;

import java.lang.annotation.*;

/**
 * 切面注解
 * Created by wangjianchun on 2017/11/14.
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
