package com.flying.sparrow.bean;

import com.flying.sparrow.helper.ClassHelper;

import java.lang.reflect.Method;

/**
 * 封装Action信息
 * Created by wangjianchun on 2017/11/9.
 */
public class Handler {

    private Class<?> controllerClass;

    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod){
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
