package com.huishu.ieanalysis.utils;

import com.alibaba.fastjson.JSONArray;

import java.util.UUID;

/**
 * <p>Title: StringUtils</p>
 * <p>Description: 字符串工具类</p>
 *
 * @author wjc
 * @date 2017年4月22日
 */
public class StringUtils {

    private StringUtils() {
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

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 根据给定正则表达式的匹配拆分此字符串
     * @param string 要拆分的额字符串
     * @param regex 正则表达式
     * @return
     */
    public static JSONArray split(String string, String regex){
        JSONArray result = new JSONArray();
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(string)){
            String[] array = string.split(regex);
            for(String item : array){
                if(org.apache.commons.lang3.StringUtils.isNotEmpty(item)){
                    result.add(item);
                }
            }
        }
        return result;
    }

}
