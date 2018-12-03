package com.sparrow.utils;

import com.alibaba.fastjson.JSONArray;
import com.sparrow.ibe.constants.IBEConstants;
import com.sparrow.ibe.enums.IBEError;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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

    /**
     * 根据机票接口的错误代码获取中文格式的错误信息
     *
     * @param code
     * @return
     */
    public static String ibeMessage(String code) {
        IBEError error = IBEConstants.IBE_ERROR_MAP.get(code);
        if (error != null) {
            return error.getCnMessage();
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

    /**
     * 计算年龄
     * @param birthDate
     * @return
     */
    public static int calculateAge(Date birthDate){
        if(birthDate == null){
            return 0;
        }
        int currentYear = DateUtils.getYearOfDate(DateUtils.currentDate());
        int yearOfBirthDate = DateUtils.getYearOfDate(birthDate);

        return (currentYear - yearOfBirthDate);
    }

    /**
     * 功能：转换转义字符
     *
     * @author wangjc
     * @date 2014-07-16
     */
    public static String decode(String content) {
        if (content == null) {
            return "";
        } else {
            String result = content.replace("&lt;", "<")
                    .replace("&gt;", ">").replace("&amp;", "&")
                    .replace("&apos;", "'").replace("&quot;", "\"\"");

            return result;
        }
    }

    /**
     * 判断是否是火狐浏览器
     *
     * @param request
     * @return
     */
    public static boolean isFirefox(HttpServletRequest request){
        return (request.getHeader("User-Agent").toUpperCase().indexOf("FIREFOX") != -1);
    }

    /**
     * 判断是否是谷歌chrome浏览器
     *
     * @param request
     * @return
     */
    public static boolean isChrome(HttpServletRequest request){
        return (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") != -1);
    }

    /**
     * 判断是否是IE浏览器
     *
     * @param request
     * @return
     */
    public static boolean isIE(HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent").toUpperCase();
        if(userAgent.indexOf("MSIE") != -1){
            return true;
        }else if(userAgent.indexOf("GECKO") != -1 && userAgent.indexOf("RV:11") != -1){
            //IE11 浏览器
            return true;
        }else if(userAgent.indexOf("EDGE") != -1){
            //微软的EDGE浏览器
            return true;
        }else{
            return false;
        }
    }

    /**
     * 根据给定正则表达式的匹配拆分此字符串
     * @param string 要拆分的额字符串
     * @param regex 正则表达式
     * @return
     */
    public static JSONArray split(String string, String regex){
        JSONArray result = new JSONArray();
        if(org.apache.commons.lang3.StringUtils.isEmpty(string)){
            return result;
        }
        String[] array = string.split(regex);
        for(String item : array){
            if(org.apache.commons.lang3.StringUtils.isNotEmpty(item)){
                result.add(item);
            }
        }
        return result;
    }

    public static String replace(String str, String replacement, String... targetList){
        if(org.apache.commons.lang3.StringUtils.isEmpty(str)){
            return str;
        }
        String result = str;
        for(String target : targetList){
            result = result.replace(target, replacement);
        }

        return result;
    }

}
