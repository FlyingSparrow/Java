package com.sparrow.integration.dao;


import com.sparrow.integration.exception.IntegrationException;

import java.io.Serializable;

/**
 * @param <E>
 * @author wangjianchun
 * @date 2018-7-4
 */
public interface IntegrationDao<E extends Serializable> {

    Serializable execute(String userId, String password, E request) throws IntegrationException;

}
