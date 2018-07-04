package com.sparrow.utils;

/**
 * 字符串工具类
 *
 * @author wangjianchun
 * @date 2018-7-4
 */
public class StringUtil {

    private StringUtil() {
    }

    public static boolean isEmpty(String arg) {
        return org.apache.commons.lang3.StringUtils.isEmpty(arg);
    }

    public static boolean isNotEmpty(String arg) {
        return !isEmpty(arg);
    }

}
