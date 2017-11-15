package com.flying.sparrow.framework.proxy;

/**
 * 代理接口
 * Created by wangjianchun on 2017/11/14.
 */
public interface Proxy {

    /**
     * 执行链式代理
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;

}
