package com.sparrow.utils;

import com.sparrow.ibe.constants.IBEConstants;
import com.sparrow.ibe.enums.IBEError;

import java.util.UUID;

/**
 * 字符串工具类
 *
 * @author wangjianchun
 * @date 2018-7-4
 */
public class StringUtils {

    private StringUtils() {
    }

    public static boolean isEmpty(String arg) {
        return org.apache.commons.lang3.StringUtils.isEmpty(arg);
    }

    public static boolean isNotEmpty(String arg) {
        return !isEmpty(arg);
    }

    public static String defaultValueIfNull(String arg) {
        return (arg == null ? "" : arg);
    }

    public static String defaultValueIfNull(String arg, String defaultValue) {
        return (arg == null || "".equals(arg.trim()) ? defaultValue : arg);
    }

    /**
     * 根据机票接口的错误代码获取中文格式的错误信息
     *
     * @param code
     * @return
     */
    public static String ibeMessage(String code) {
        IBEError errorEnum = IBEConstants.IBE_ERROR_MAP.get(code);
        if (errorEnum != null) {
            return errorEnum.getCnMessage();
        } else {
            return "no chinese error message for the error code: " + code;
        }
    }

    /**
     * 返回32位长的随机字符串
     *
     * @return
     * @author wangjc
     * @date 2014-11-19
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 返回指定长度的随机字符串
     *
     * @param length 字符串长度
     * @return
     * @author wangjc
     * @date 2014-11-19
     */
    public static String randomUUID(int length) {
        String result = "";
        if(length <= 0){
            return result;
        }

        result = randomUUID();
        if(length > result.length()){
            return result;
        }
        return result.substring(0, length);
    }

    /**
     * 判断当前系统是否是windows系统
     *
     * @return
     */
    public static boolean isWindows() {
        boolean flag = false;
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            flag = true;
        }
        return flag;
    }

}