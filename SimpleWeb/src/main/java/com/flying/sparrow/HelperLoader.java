package com.flying.sparrow;

import com.flying.sparrow.helper.*;
import com.flying.sparrow.util.ClassUtil;

/**
 * 加载相应的Helper类
 * Created by wangjianchun on 2017/11/9.
 */
public final class HelperLoader {

    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for(Class<?> cls: classList){
            ClassUtil.loadClass(cls.getName());
        }
    }

}
