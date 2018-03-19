package com.sparrow.opinion.base.controller;

import com.sparrow.opinion.base.bean.AjaxPageListResult;
import com.sparrow.opinion.base.bean.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: BaseController</p>
 * <p>Description: 提供公共接口方法的基础控制器类</p>
 *
 * @author wjc
 * @date 2017年6月19日
 */
public abstract class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected AjaxResult success(Object data) {
        return AjaxResult.success(data);
    }

    protected AjaxPageListResult success(int pageNumber, int pageSize, int total, Object rows) {
        return AjaxPageListResult.success(pageNumber, pageSize, total, rows);
    }

    protected AjaxResult fail(String message) {
        return AjaxResult.fail(message);
    }

    protected AjaxResult fail(int status, String message) {
        return AjaxResult.fail(status, message);
    }

}
