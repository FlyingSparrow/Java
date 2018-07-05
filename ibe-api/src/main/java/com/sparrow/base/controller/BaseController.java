package com.sparrow.base.controller;

import com.sparrow.base.bean.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: BaseController</p>
 * <p>Description: 提供公共接口方法的基础控制器类</p>
 *
 * @author wjc
 * @date 2017年1月5日
 */
public abstract class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected AjaxResult success(Object data) {
        return AjaxResult.success(data);
    }

    protected AjaxResult failure(String message) {
        return AjaxResult.failure(message);
    }

    protected AjaxResult failure(int status, String message) {
        return AjaxResult.failure(status, message);
    }

}
