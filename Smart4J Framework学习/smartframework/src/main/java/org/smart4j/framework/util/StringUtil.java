package org.smart4j.framework.util;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author wangjianchun
 * @create 2018/1/5
 */
public final class StringUtil {

    /**
     * 字符串分隔符
     */
    public static final String SEPARATOR = String.valueOf((char)29);

    public static String decode(String str){
        String result = "";
        try {
            result = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(System.err);
        }

        return result;
    }

    public static boolean isNotEmpty(String str){
        return StringUtils.isNotEmpty(str);
    }

    public static String[] splitString(String str, String separator){
        return StringUtils.split(str, separator);
    }
}
