package com.sparrow.opinion.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

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
public final class StringUtils {

    private static final Pattern dateTimePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");
    private static final Pattern dateTimePattern2 = Pattern.compile("^(\\d{1,2}-\\d{1,2} \\d{2}:\\d{2})$");
    private static final Pattern dateTimePattern3 = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} \\d{2}:\\d{2}");
    private static final Pattern dateTimePattern4 = Pattern.compile("(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})");
    private static final Pattern dateTimePattern5 = Pattern.compile("^(\\d{4}-\\d{1,2}-\\d{1,2} \\d{2}:\\d{2})$");
    private static final Pattern dateTimePattern6 = Pattern.compile("^(\\d{4}-\\d{1,2}-\\d{1,2}\\d{2}:\\d{2}:\\d{2} \\d{2}:\\d{2}:\\d{2})$");

    private StringUtils() {
    }

    public static boolean isEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.isNotEmpty(str);
    }

    /**
     * <p>Description: 统计关键词在内容中出现的次数</p>
     *
     * @param content
     * @param keyword
     * @return
     * @author wjc
     * @date 2017年5月22日
     */
    public static int countOccurrenceNumber(String content, String keyword) {
        if (isEmpty(content) || isEmpty(keyword)) {
            return 0;
        }
        int result = 0;
        Pattern pattern = Pattern.compile("(" + keyword + ")");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            result++;
        }
        return result;
    }

    /**
     * <p>Description: 截取字符串</p>
     *
     * @param content
     * @param splitLen
     * @return
     * @author Wangjianchun
     * @date 2017年7月3日
     */
    public static String substr(String content, Integer splitLen, String replaceStr) {
        return content.length() > splitLen ? content.substring(0, splitLen) + replaceStr : content;
    }

    public static String getAbbreviation(String enterpriseName) {
        String result = null;
        if (enterpriseName.endsWith("有限责任公司")) {
            result = enterpriseName.substring(0, enterpriseName.lastIndexOf("有限责任公司"));
        } else if (enterpriseName.endsWith("股份有限公司")) {
            result = enterpriseName.substring(0, enterpriseName.lastIndexOf("股份有限公司"));
        } else if (enterpriseName.endsWith("有限公司")) {
            result = enterpriseName.substring(0, enterpriseName.lastIndexOf("有限公司"));
        } else {
            result = enterpriseName;
        }
        return result;
    }

    /**
     * 判断当前系统是否是windows系统
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
     * <p>Description: 根据文章的url获取文章所在网站的url</p>
     *
     * @param url
     * @return
     * @author Wangjianchun
     * @date 2017年7月3日
     */
    public static String getWebsiteUrl(String url) {
        String result = "";
        if (isEmpty(url)) {
            return result;
        }
        int index = url.indexOf("://");
        if (index != -1) {
            result = url.substring(0, url.indexOf("/", index + 3));
        } else {
            result = url.substring(0, url.indexOf("/"));
        }
        return result;
    }

    /**
     * <p>Description: 调整不规范的日期格式为yyyy-MM-dd HH:mm:ss的日期字符串</p>
     *
     * @param date
     * @return
     * @author wjc
     * @date 2017年3月1日
     */
    public static String adjustDateTime(String date) {
        if (date == null) {
            return null;
        }

        String result = null;

        String dateStr = date.trim();
        Matcher matcher = dateTimePattern.matcher(dateStr);
        Matcher matcher2 = dateTimePattern2.matcher(dateStr);
        Matcher matcher3 = dateTimePattern3.matcher(dateStr);
        Matcher matcher4 = dateTimePattern4.matcher(dateStr);
        Matcher matcher5 = dateTimePattern5.matcher(dateStr);
        Matcher matcher6 = dateTimePattern6.matcher(dateStr);

        if (matcher.find()) {
            result = matcher.group();
        } else if (matcher2.find()) {
            String group = matcher2.group();
            int index = group.indexOf("-");
            String month = group.substring(0, index);
            String day = group.substring(index, group.indexOf(" "));
            String time = group.substring(group.indexOf(" "));
            if (month.length() < 2) {
                month = "0" + month;
            }
            if (day.length() < 2) {
                day = "0" + day;
            }
            StringBuilder newDate = new StringBuilder();
            DateTime dateTime = new DateTime();
            dateTime.getYear();
            newDate.append(dateTime.getYear()).append("-").append(month)
                    .append(day).append(time).append(":00");
            result = newDate.toString();
        } else if (matcher3.find()) {
            String group = matcher3.group();
            String[] array = group.split(" ");
            StringBuilder newDate = new StringBuilder();
            newDate.append(array[0]).append(" ")
                    .append(array[2]).append(":00");
            result = newDate.toString();
        } else if (matcher4.find()) {
            String group = matcher4.group();
            result = group + ":00";
        } else if (matcher5.find()) {
            String group = matcher5.group();
            DateTime dt = DateTimeFormat.forPattern("yyyy-M-d HH:mm").parseDateTime(group);
            result = dt.toString(DateUtils.DATE_SECOND_FORMAT);
        }
        if (matcher6.find()) {
            String group = matcher6.group();
            String datePart = group.substring(0, 10);
            String timePart = group.substring(group.indexOf(" ") + 1);
            result = datePart + " " + timePart;
        }

        return result;
    }

    public static String md5(String data) {
        return DigestUtils.md5Hex(data);
    }

}
