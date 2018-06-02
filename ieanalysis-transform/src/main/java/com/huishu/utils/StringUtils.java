package com.huishu.utils;

import com.alibaba.fastjson.JSONArray;
import com.huishu.config.UnitsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Properties;
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

    private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

    /**
     * script 标签的正则表达式
     */
    private static final Pattern SCRIPT_REGEX = Pattern.compile("<script[^>]*?>[\\s\\S]*?<\\/script>",
            Pattern.CASE_INSENSITIVE);
    /**
     * style 标签的正则表达式
     */
    private static final Pattern STYLE_REGEX = Pattern.compile("<style[^>]*?>[\\s\\S]*?<\\/style>",
            Pattern.CASE_INSENSITIVE);
    /**
     * HTML 标签的正则表达式
     */
    private static final Pattern HTML_REGEX = Pattern.compile("<[^>]+>", Pattern.CASE_INSENSITIVE);

    /**
     * 制表符、换行符等其它字符的正则表达式
     */
    private static final Pattern SPACE_REGEX = Pattern.compile("\\s+|\t|\r|\n", Pattern.CASE_INSENSITIVE);

    /**
     * 判断日期格式的正则表达式
     */
    private static final Pattern DATE_FORMAT_REGEX = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}");

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
        if (isNotEmpty(string)) {
            String[] array = string.split(regex);
            for (String item : array) {
                if (isNotEmpty(item)) {
                    result.add(item);
                }
            }
        }
        return result;
    }

    /**
     * 统计wordArray中每个词在data中出现的次数，每个词在data中出现的话，出现次数加1
     *
     * @param wordArray
     * @param data
     * @return 返回出现次数合计
     */
    public static int countOccurrenceNumber(JSONArray wordArray, String data) {
        int result = 0;
        if (wordArray == null || isEmpty(data)) {
            return result;
        }
        for (int i = 0, size = wordArray.size(); i < size; i++) {
            String word = wordArray.getString(i);
            if (data.contains(word)) {
                result += 1;
            }
        }

        return result;
    }

    public static boolean isEmpty(String string) {
        return org.apache.commons.lang3.StringUtils.isEmpty(string);
    }

    public static boolean isNotEmpty(String string) {
        return org.apache.commons.lang3.StringUtils.isNotEmpty(string);
    }

    /**
     * 转换时间
     * @param time
     * @return
     */
    public static String transformTime(String time) {
        String result = time;

        Date currentDate = DateUtils.currentDate();
        if (isNotEmpty(result)) {
            if (isToday(result)) {
                result = DateUtils.getFormatDate(currentDate);
            } else if (result.contains("昨天")) {
                result = DateUtils.getFormatDate(DateUtils.getYesterdayNow(currentDate));
            } else if (result.contains("前天")) {
                result = DateUtils.getFormatDate(DateUtils.getBeforeYesterdayNow(currentDate));
            } else if (result.length() <= 4 && result.indexOf("天前") >= 0) {
                int days = Integer.parseInt(result.replaceAll("天前", ""));
                result = DateUtils.getFormatDate(DateUtils.getBeforeYesterdayNow(currentDate, days));
            } else if (result.length() <= 5 && result.indexOf("小时前") >= 0) {
                int hours = Integer.parseInt(result.replaceAll("小时前", ""));
                int hour = DateUtils.getHour(currentDate);
                if (hours > hour) {
                    result = DateUtils.getFormatDate(DateUtils.getYesterdayNow(currentDate));
                } else {
                    result = DateUtils.getFormatDate(currentDate);
                }
            } else if (result.length() <= 8 && result.indexOf(":") >= 0) {
                result = DateUtils.getFormatDate(currentDate);
            } else if (result.indexOf("/") >= 0) {
                result = result.replaceAll("/", "-");
            } else if (result.length() >= 24 && result.indexOf("-") >= 0) {
                String headerTime = extractDate(result.substring(0, 20));
                String endTime = extractDate(result.substring(result.length() - 20, result.length()));
                if (isDate(headerTime)) {
                    result = headerTime;
                } else if (isDate(endTime)) {
                    result = endTime;
                }
            } else if (result.indexOf("-") >= 0 && result.length() >= 20) {
                result = result.substring(0, 19);
            } else if (result.indexOf("-") == -1) {
                result = "";
            }
        }

        return result;
    }

    private static String extractDate(String date){
        String result = "";

        String hyphen = "-";
        if (date.indexOf(hyphen) >= 0) {
            int first = date.indexOf(hyphen);
            int second = date.lastIndexOf(hyphen);
            if (second > first) {
                int starIndex = first - 4;
                int endIndex = second + 3;
                if (starIndex < 0) {
                    starIndex = 0;
                }
                if (endIndex > date.length()) {
                    endIndex = date.length();
                }
                result = date.substring(starIndex, endIndex);
            }
        }

        return result;
    }

    private static boolean isToday(String result) {
        return (result.contains("刚刚") || result.contains("今天"));
    }

    /**
     * 判断 date 是否是yyyy-MM-dd格式的日期字符串
     * @param date
     * @return
     */
    public static boolean isDate(String date) {
        Matcher matcher = DATE_FORMAT_REGEX.matcher(date);
        return matcher.matches();
    }

    /**
     * 移除html标记
     * @param htmlStr
     * @return
     */
    public static String removeHtmlTag(String htmlStr) {
        String result = htmlStr;
        if (isNotEmpty(result)) {
            Matcher scriptMatcher = SCRIPT_REGEX.matcher(result);
            result = scriptMatcher.replaceAll("");
            Matcher styleMatcher = STYLE_REGEX.matcher(result);
            result = styleMatcher.replaceAll("");
            Matcher htmlMatcher = HTML_REGEX.matcher(result);
            result = htmlMatcher.replaceAll("");
            Matcher spaceMatcher = SPACE_REGEX.matcher(result);
            result = spaceMatcher.replaceAll(" ");
        }
        return result;
    }

    public static String getCity(String str) {
        if (isEmpty(str)) {
            return null;
        }

        String result = str;
        String replacement = "";
        result = result.replaceAll("特别行政区", replacement)
                .replaceAll("回族", replacement)
                .replaceAll("维吾尔", replacement)
                .replaceAll("壮族", replacement)
                .replaceAll("自治区", replacement)
                .replaceAll("省", replacement)
                .replaceAll("市", replacement);

        return result;
    }

    public static Double transformAmount(UnitsConfig unitsConfig, String amount) {
        String types = unitsConfig.getTypes();
        if (isEmpty(types)) {
            return null;
        }

        String unitReplace = amount.toUpperCase();
        Integer index = null;
        String replacement = "";
        String[] units = types.split(",");
        for (int i = 0; i < units.length; i++) {
            if (unitReplace.contains(units[i])) {
                index = i;
                break;
            }
        }

        Double ratio = 1D;
        Properties properties = FileUtils.getProperties("units.properties");
        if (index != null) {
            unitReplace = unitReplace.replaceAll(units[index].toUpperCase(), replacement);
            ratio = Double.valueOf(properties.getProperty(units[index].toUpperCase() + unitsConfig.getSuffix()));
        }
        // 标准单位转换
        if (unitReplace.contains("M")) {
            unitReplace = unitReplace.replaceAll("M", replacement).replaceAll(" ", replacement);
            ratio = ratio * 1000000;
        }
        // 中文单位转换 #美元USD
        String chinaUnits = unitsConfig.getChinaUnits();
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(chinaUnits)) {
            String[] chinaSplit = chinaUnits.split(",");
            Integer chinaIndex = null;
            for (int i = 0; i < chinaSplit.length; i++) {
                if (unitReplace.contains(chinaSplit[i])) {
                    chinaIndex = i;
                    break;
                }
            }
            unitReplace = unitReplace.replace("(估)", replacement);
            if (chinaIndex != null) {
                unitReplace = unitReplace.replace(chinaSplit[chinaIndex], replacement);
                ratio = Double.valueOf(properties.getProperty(units[chinaIndex].toUpperCase() + unitsConfig.getSuffix()));
            }
            if (unitReplace.contains("万")) {
                unitReplace = unitReplace.replace("万", replacement);
                ratio = ratio * 10000;
            }
            if (unitReplace.contains("亿")) {
                unitReplace = unitReplace.replace("亿", replacement);
                ratio = ratio * 100000000;
            }
            unitReplace = unitReplace.replace("元", replacement);
        }
        unitReplace = unitReplace.replace("N/A", replacement);

        try {
            return Double.valueOf(unitReplace.trim()) * ratio;
        } catch (NumberFormatException e) {
            logger.error("金额转换异常: {}", amount, e);
        }

        return null;
    }

}
