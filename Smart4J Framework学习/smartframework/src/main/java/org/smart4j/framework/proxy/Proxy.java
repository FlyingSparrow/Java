package org.smart4j.framework.proxy;

/**
 * 代理接口
 * @author wangjianchun
 * @create 2017/12/27
 */
public interface Proxy {

    /**
     * 执行链式注解
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
