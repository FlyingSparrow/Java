package org.smart4j.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author wangjianchun
 * @create 2018/1/5
 */
public final class StringUtil {

    public static String decode(String str){
        String result = "";
        try {
            result = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(System.err);
        }

        return result;
    }
}
