package com.huishu.utils;

import com.alibaba.fastjson.JSONArray;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     *
     * @param string 要拆分的额字符串
     * @param regex  正则表达式
     * @return
     */
    public static JSONArray split(String string, String regex) {
        JSONArray result = new JSONArray();
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(string)) {
            String[] array = string.split(regex);
            for (String item : array) {
                if (org.apache.commons.lang3.StringUtils.isNotEmpty(item)) {
                    result.add(item);
                }
            }
        }
        return result;
    }

    public static synchronized String toTransformTime(String time) {
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(time)) {
            if (time.indexOf("刚刚") >= 0 || time.indexOf("今天") >= 0) {
                //23:45:09
                time = DateUtils.getFormatDate(new Date());
            } else if (time.indexOf("昨天") >= 0) {
                time = DateUtils.getFormatDate(DateUtils.getYesterdayNow(new Date()));
            } else if (time.indexOf("前天") >= 0) {
                time = DateUtils.getFormatDate(DateUtils.getBeforeYesterdayNow(new Date()));
            } else if (time.length() <= 4 && time.indexOf("天前") >= 0) {
                String day = time.replaceAll("天前", "");
                Integer valueOf = Integer.valueOf(day);
                time = DateUtils.getFormatDate(DateUtils.getBeforeYesterdayNow(new Date(), valueOf));
            } else if (time.length() <= 5 && time.indexOf("小时前") >= 0) {
                String day = time.replaceAll("小时前", "");
                Integer valueOf = Integer.valueOf(day);
                int hours = new Date().getHours();
                if (valueOf > hours) {
                    time = DateUtils.getFormatDate(DateUtils.getYesterdayNow(new Date()));
                } else {
                    time = DateUtils.getFormatDate(new Date());
                }
            } else if (time.length() <= 8 && time.indexOf(":") >= 0) {
                time = DateUtils.getFormatDate(new Date());
            } else if (time.indexOf("/") >= 0) {
                time = time.replaceAll("/", "-");
            } else if (time.length() >= 24 && time.indexOf("-") >= 0) {
                String header = time.substring(0, 20);
                String headertime = "";
                if (header.indexOf("-") >= 0) {
                    int first = header.indexOf("-");
                    int second = header.lastIndexOf("-");
                    if (second > first) {
                        int start = first - 4;
                        int end = second + 3;
                        if (start < 0) {
                            start = 0;
                        }
                        if (end > header.length()) {
                            end = header.length();
                        }
                        headertime = header.substring(start, end);
                    }
                }
                String end = time.substring(time.length() - 20, time.length());
                String endTime = "";
                if (end.indexOf("-") >= 0) {
                    int first = end.indexOf("-");
                    int second = end.lastIndexOf("-");
                    if (second > first) {
                        int startindex = first - 4;
                        int endindex = second + 3;
                        if (startindex < 0) {
                            startindex = 0;
                        }
                        if (endindex > end.length()) {
                            endindex = end.length();
                        }
                        endTime = end.substring(startindex, endindex);
                    }
                }
                if (isDate(headertime)) {
                    time = headertime;
                } else if (isDate(endTime)) {
                    time = endTime;
                }

            } else if (time.indexOf("-") >= 0 && time.length() >= 20) {
                time = time.substring(0, 19);
            } else if (time.indexOf("-") == -1) {
                time = "";
            }
        }
        return time;
    }

    public static boolean isDate(String date) {
        /**
         * 判断日期格式和范围
         */
        String rexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}";
        Pattern pat = Pattern.compile(rexp);

        Matcher mat = pat.matcher(date);

        boolean dateType = mat.matches();

        return dateType;
    }

}
