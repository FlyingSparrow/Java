package com.sparrow.base.controller;

import com.sparrow.base.bean.AjaxResult;
import com.sparrow.constants.SysConst;
import com.sparrow.stockgarden.mysql.model.User;
import com.sparrow.utils.Des3EncryptionUtil;
import com.sparrow.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    protected User getUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Sparrow");
        return user;
//        return (User) getSession().getAttribute(SysConst.LOGIN_SESSION_KEY);
    }

    protected long getUserId() {
        Long id = 0L;
        User user = getUser();
        if (user != null) {
            id = user.getId();
        }
        return id;
    }

    protected String getUserName() {
        String userName = "StockGarden";
        User user = getUser();
        if (user != null) {
            userName = user.getUsername();
        }
        return userName;
    }

    protected String getUserIp() {
        String value = getRequest().getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(value) && !"unknown".equalsIgnoreCase(value)) {
            return value;
        } else {
            return getRequest().getRemoteAddr();
        }
    }

    protected String getPwd(String password) {
        try {
            String pwd = MD5Util.encrypt(password + SysConst.PASSWORD_KEY);
            return pwd;
        } catch (Exception e) {
            logger.error("密码加密异常：", e);
        }
        return null;
    }

    protected String cookieSign(String value) {
        try {
            value = value + SysConst.PASSWORD_KEY;
            String sign = Des3EncryptionUtil.encode(SysConst.DES3_KEY, value);
            return sign;
        } catch (Exception e) {
            logger.error("cookie签名异常：", e);
        }
        return null;
    }

}
