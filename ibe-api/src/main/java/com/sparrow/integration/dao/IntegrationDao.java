package com.sparrow.integration.dao;


import com.sparrow.integration.exception.IntegrationException;

import java.io.Serializable;

/**
 * @param <E>
 * @author wangjianchun
 * @date 2018-7-4
 */
public interface IntegrationDao<E extends Serializable> {

    /**
     * 执行请求，返回响应结果对象
     * @param userId
     * @param password
     * @param request
     * @return
     * @throws IntegrationException
     */
    Serializable execute(String userId, String password, E request) throws IntegrationException;

}
