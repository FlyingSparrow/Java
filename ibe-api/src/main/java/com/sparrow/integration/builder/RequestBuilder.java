package com.sparrow.integration.builder;

import com.sparrow.integration.exception.IntegrationException;

import java.io.Serializable;

/**
 * 构造请求的接口类
 *
 * @param <E>
 * @author wangjc
 * @date 2014-7-11
 */
public interface RequestBuilder<E extends Serializable> {

    /**
     * 功能：根据请求对象生成请求的xml
     *
     * @param request
     * @return
     * @throws IntegrationException
     */
    String buildRequestXML(E request) throws IntegrationException;

}
