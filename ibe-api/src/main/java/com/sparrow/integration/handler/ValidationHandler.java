package com.sparrow.integration.handler;

import java.io.Serializable;

/**
 * 验证处理器接口，用于对接口的请求对象进行验证
 *
 * @author wangjc
 */
public interface ValidationHandler<E extends Serializable> {

    /**
     * 功能：验证请求
     *
     * @param userId
     * @param password
     * @param request
     * @return
     */
    boolean validate(String userId, String password, E request);

    /**
     * 功能：设置验证结果
     *
     * @param validationResult
     * @author wangjc
     * @date 2014-08-11
     */
    void setValidationResult(Serializable validationResult);

    /**
     * 功能：获取验证结果
     * @return
     */
    Serializable getValidationResult();

}
