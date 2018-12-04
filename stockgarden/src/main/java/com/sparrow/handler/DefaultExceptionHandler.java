package com.sparrow.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: DefaultExceptionHandler</p>
 * <p>Description: 统一的异常处理器</p>
 *
 * @author wjc
 * @date 2017年6月20日
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);
    private static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(Exception e, HttpServletRequest request) throws Exception {
        logger.info("请求地址：" + request.getRequestURL());
        logger.error("异常信息：", e);

        ModelAndView mav = new ModelAndView();
        mav.setViewName(DEFAULT_ERROR_VIEW);

        return mav;
    }
}