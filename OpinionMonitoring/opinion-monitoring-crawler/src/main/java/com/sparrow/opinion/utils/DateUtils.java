package com.sparrow.opinion.utils;

import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title: DateUtils</p>
 * <p>Description: 日期工具类</p>
 *
 * @author wjc
 * @date 2017年4月6日
 */
public class DateUtils {

    public static final String DATE_FORMAT_SIMPLE = "MM-dd HH:mm";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_2 = "yyyyMMdd";
    public static final String DATE_SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_SECOND_FORMAT_2 = "yyyyMMddHHmmss";
    public static final String DATE_SECOND_FORMAT_3 = "yyyy/MM/dd  HH:mm:ss";
    public static final String DATE_SECOND_FORMAT_4 = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String CN_DATE_FORMAT = "yyyy年MM月dd日";

    private DateUtils() {
    }

    /**
     * <p>Description: 获取指定日期的前一天的日期</p>
     *
     * @param date
     * @return
     * @author wjc
     * @date 2017年4月6日
     */
    public static Date getYesterdayDate(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.minusDays(1).toDate();
    }

    /**
     * <p>Description: 获取指定日期的前一天的日期</p>
     *
     * @param dateStr
     * @return
     * @author wjc
     * @date 2017年4月6日
     */
    public static String getYesterdayDateStr(String dateStr) {
        Date date = parseDate(dateStr, DATE_FORMAT);
        DateTime dateTime = new DateTime(date);
        String result = formatDate(dateTime.minusDays(1).toDate(), DATE_FORMAT);
        return result;
    }

    /**
     * <p>Description: 获取指定日期的前6天的日期</p>
     *
     * @param date
     * @return
     * @author wjc
     * @date 2017年4月6日
     */
    public static Date getWeekAgo(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.minusDays(6).toDate();
    }

    /**
     * <p>Description: 获取指定日期的前9天的日期</p>
     *
     * @param date
     * @return
     * @author wjc
     * @date 2017年4月6日
     */
    public static Date getTenDaysAgo(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.minusDays(9).toDate();
    }

    public static List<String> getHourList(Date date) {
        List<String> result = new ArrayList<String>();
        if (date == null) {
            return result;
        }
        DateTime dateTime = new DateTime(date);
        int hour = dateTime.getHourOfDay() + 1;
        for (int i = 1; i <= 24; i++) {
            int temp = hour + i;
            if (temp > 24) {
                temp = temp - 24;
            }
            result.add(temp + "H");
        }

        return result;
    }

    public static List<Integer> getDayList(Date date) {
        List<Integer> result = new ArrayList<Integer>();
        if (date == null) {
            return result;
        }
        DateTime dateTime = new DateTime(date);
        for (int i = 9; i >= 0; i--) {
            result.add(dateTime.minusDays(i).getDayOfMonth());
        }

        return result;
    }

    public static List<String> getLegendDayList2(Date date) {
        List<String> result = new ArrayList<String>();
        if (date == null) {
            return result;
        }
        DateTime dateTime = new DateTime(date);
        for (int i = 9; i >= 0; i--) {
            result.add(dateTime.minusDays(i).toString("M月d日"));
        }

        return result;
    }

    /**
     * <p>
     * Description: 解析yyyy-MM-dd HH:mm:ss格式的日期字符串，返回日期对象
     * </p>
     *
     * @param date
     * @return
     * @author wjc
     * @date 2016年12月30日
     */
    public static Date parseDate(String date) {
        return parseDate(date, DATE_SECOND_FORMAT);
    }

    /**
     * <p>
     * Description: 使用指定的日期格式解析日期字符串，返回日期对象
     * </p>
     *
     * @param date
     * @param dateFormat
     * @return
     * @author wjc
     * @date 2016年12月30日
     */
    public static Date parseDate(String date, String dateFormat) {
        Date result = null;
        if (StringUtils.isNotEmpty(date) && StringUtils.isNotEmpty(dateFormat)) {
            DateTimeFormatter format = DateTimeFormat.forPattern(dateFormat);
            DateTime dateTime = DateTime.parse(date, format);
            result = dateTime.toDate();
        }

        return result;
    }

    /**
     * <p>
     * Description: 使用指定的格式格式化日期对象，返回格式化后的日期字符串
     * </p>
     *
     * @param date
     * @param dateFormat
     * @return
     * @author wjc
     * @date 2016年12月30日
     */
    public static String formatDate(Date date, String dateFormat) {
        String result = null;
        if (date != null && StringUtils.isNotEmpty(dateFormat)) {
            DateTimeFormatter format = DateTimeFormat.forPattern(dateFormat);
            DateTime dateTime = new DateTime(date.getTime());
            result = dateTime.toString(format);
        }
        return result;
    }

    /**
     * <p>Description: 将日期对象格式化为yyyy-MM-dd格式的字符串</p>
     *
     * @param date
     * @return
     * @author wjc
     * @date 2017年4月6日
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        return formatDate(date, DATE_FORMAT);
    }

    /**
     * <p>Description: 将日期对象格式化为yyyy-MM-dd HH:mm:ss格式的字符串</p>
     *
     * @param date
     * @return
     * @author wjc
     * @date 2017年4月6日
     */
    public static String formatDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return formatDate(date, DATE_SECOND_FORMAT);
    }

    /**
     * <p>
     * Description: 将yyyy-MM-dd格式的日期字符串转换为这一天的开始时间的日期对象
     * 例如：将2017-01-01的日期字符串转换为表示2017-01-01 00:00:00的日期对象
     * </p>
     *
     * @param date yyyy-MM-dd的日期格式的字符串
     * @return
     * @author wjc
     * @date 2017年1月5日
     */
    public static Date getStartDateTimeOfDay(String date) {
        Date result = null;
        if (StringUtils.isNotEmpty(date)) {
            DateTimeFormatter format = DateTimeFormat.forPattern(DATE_FORMAT);
            DateTime dateTime = format.parseDateTime(date);
            result = dateTime.withTimeAtStartOfDay().toDate();
        }

        return result;
    }

    /**
     * <p>
     * Description: 将yyyy-MM-dd格式的日期字符串转换为这一天的结束时间的日期对象
     * 例如：将2017-01-01的日期字符串转换为表示2017-01-01 23:59:59的日期对象
     * </p>
     *
     * @param date yyyy-MM-dd的日期格式的字符串
     * @return
     * @author wjc
     * @date 2017年1月5日
     */
    public static Date getEndDateTimeOfDay(String date) {
        Date result = null;
        if (StringUtils.isNotEmpty(date)) {
            DateTimeFormatter format = DateTimeFormat.forPattern(DATE_FORMAT);
            DateTime dateTime = format.parseDateTime(date);
            result = dateTime.millisOfDay().withMaximumValue().toDate();
        }

        return result;
    }

    /**
     * <p>
     * Description: 将不包含时间信息的日期对象转换为这一天的开始时间的日期对象
     * 例如：将2017-01-01的日期对象转换为表示2017-01-01 00:00:00的日期对象
     * </p>
     *
     * @param date
     * @return
     * @author wjc
     * @date 2017年1月5日
     */
    public static Date getStartDateTimeOfDay(Date date) {
        Date result = null;
        if (date != null) {
            DateTime dateTime = new DateTime(date);
            result = dateTime.withTimeAtStartOfDay().toDate();
        }

        return result;
    }

    /**
     * <p>
     * Description: 将不包含时间信息的日期对象转换为这一天的结束时间的日期对象
     * 例如：将2017-01-01的日期对象转换为表示2017-01-01 23:59:59的日期对象
     * </p>
     *
     * @param date
     * @return
     * @author wjc
     * @date 2017年1月5日
     */
    public static Date getEndDateTimeOfDay(Date date) {
        Date result = null;
        if (date != null) {
            DateTime dateTime = new DateTime(date);
            String dateStr = dateTime.millisOfDay().withMaximumValue().toString(DATE_SECOND_FORMAT);
            result = parseDate(dateStr);
        }

        return result;
    }

    /**
     * <p>
     * Description: 获取当前时间的日期对象
     * </p>
     *
     * @return
     * @author wjc
     * @date 2017年1月10日
     */
    public static Date currentDate() {
        DateTime now = new DateTime();
        return now.toDate();
    }

    public static int getYearOfDate(Date date) {
        if (date == null) {
            return -1;
        }
        return new DateTime(date).getYear();
    }

    public static List<Integer> getHourList() {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < 24; i++) {
            result.add(i);
        }
        return result;
    }

    /**
     * <p>Description: 获取当前时期前后分钟的日期</p>
     *
     * @param minute
     * @param date
     * @return
     * @author zhangtong
     * @date 2017年6月21日
     */
    public static Date currentDateAddMinute(Integer minute, Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minute).toDate();
    }

    /**
     * <p>Description: 获取当前时期前后分钟的日期</p>
     *
     * @param minute
     * @return
     * @author zhangtong
     * @date 2017年6月21日
     */
    public static Date currentDateAddMinute(Integer minute) {
        DateTime dateTime = new DateTime();
        return dateTime.plusMillis(minute).toDate();
    }

    /**
     * <p>Description: 获取指定时期前后某个天的日期</p>
     *
     * @param day
     * @param date
     * @return
     * @author zhangtong
     * @date 2017年6月21日
     */
    public static Date paramesDateAddDay(Integer day, String date) {
        DateTime dateTime = DateTime.parse(date);
        return dateTime.plusDays(day).toDate();
    }

    /**
     * <p>Description: 获取当前时期前后某个天的日期</p>
     *
     * @param day
     * @return
     * @author zhangtong
     * @date 2017年6月21日
     */
    public static Date currentDateAddDay(Integer day) {
        DateTime dateTime = new DateTime();
        return dateTime.plusDays(day).toDate();
    }

    /**
     * <p>Description: 获取当前时期前后某个天的日期</p>
     *
     * @param date 日期对象
     * @param day  增加的天数，可以为负数
     * @return
     * @author Wangjianchun
     * @date 2017年8月18日
     */
    public static Date dateAddDay(Date date, Integer day) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(day).toDate();
    }

    /**
     * <p>Description: 获取指定时期前后某个月的日期</p>
     *
     * @param month
     * @param date
     * @return
     * @author zhangtong
     * @date 2017年6月21日
     */
    public static Date paramesDateAddMonth(Integer month, String date) {
        DateTime dateTime = DateTime.parse(date);
        return dateTime.plusMonths(month).toDate();
    }

    /**
     * <p>Description: 获取当前时期前后某个月的日期</p>
     *
     * @param month
     * @return
     * @author zhangtong
     * @date 2017年6月21日
     */
    public static Date currentDateAddMonth(Integer month) {
        DateTime dateTime = new DateTime();
        return dateTime.plusMonths(month).toDate();
    }

    /**
     * <p>Description: 获取当前时期前后某个月的日期为23.59.59</p>
     *
     * @param month
     * @return
     * @author zhangtong
     * @date 2017年6月21日
     */
    public static Date currentDateAddMonthEndDateTimeOfDay(Integer month) {
        DateTime dateTime = new DateTime();
        return dateTime.plusMonths(month).millisOfDay().withMaximumValue().toDate();
    }

    /**
     * <p>Description: 在日期对象的指定日期字段（年、月、日、周、时、分、秒）
     * 上加上指定的数值后，返回该日期对象的副本，如果需要减少相应的数值，传入负数即可</p>
     *
     * @param date       日期对象
     * @param dateFields 日期字段（DateFields枚举类的值）
     * @param value      需要增加的数值
     * @return 新的日期对象的副本
     * @author Wangjianchun
     * @date 2017年7月27日
     */
    public static Date plus(Date date, DateFields dateFields, int value) {
        if (date == null) {
            return null;
        }
        if (dateFields == null) {
            return date;
        }
        Date result;
        DateTime dateTime = new DateTime(date);
        switch (dateFields) {
            case YEAR:
                result = dateTime.plusYears(value).toDate();
                break;
            case MONTH:
                result = dateTime.plusMonths(value).toDate();
                break;
            case DAY:
                result = dateTime.plusDays(value).toDate();
                break;
            case WEEK:
                result = dateTime.plusWeeks(value).toDate();
                break;
            case HOUR:
                result = dateTime.plusHours(value).toDate();
                break;
            case MINUTE:
                result = dateTime.plusMinutes(value).toDate();
                break;
            case SECOND:
                result = dateTime.plusSeconds(value).toDate();
                break;
            default:
                result = date;
        }
        return result;
    }

    /**
     * <p>Title: DateFields</p>
     * <p>Description: 日期字段枚举类，包含常用的日期字段：年、月、日、周、时、分、秒</p>
     *
     * @author Wangjianchun
     * @date 2017年7月27日
     */
    public static enum DateFields {

        YEAR, MONTH, DAY, WEEK, HOUR, MINUTE, SECOND;

    }

    /**
     * <p>Title: DateFields</p>
     * <p>Description: 输入开始时间结束时间查找时间范围</p>
     *
     * @param startDate
     * @param endDate
     * @author Wangjianchun
     * @date 2017年7月27日
     */
    public static List<Date> dateRange(Date startDate, Date endDate) {
        Date tempEndDate = getStartDateTimeOfDay(endDate);
        List<Date> result = Lists.newArrayList();
        while (tempEndDate.after(startDate)) {
            result.add(startDate);
            DateTime dateTime = new DateTime(startDate);
            startDate = dateTime.plusDays(1).toDate();
        }
        result.add(tempEndDate);
        return result;
    }

    /**
     * <p>Title: DateFields</p>
     * <p>Description: 输入开始时间结束时间查找时间范围</p>
     *
     * @param startDate
     * @param endDate
     * @author Wangjianchun
     * @date 2017年7月27日
     */
    public static List<String> dateList(Date startDate, Date endDate) {
        List<String> result = Lists.newArrayList();
        while (endDate.after(startDate)) {
            result.add(formatDate(startDate));
            DateTime dateTime = new DateTime(startDate);
            startDate = dateTime.plusDays(1).toDate();
        }
        result.add(formatDate(endDate));
        return result;
    }

    /**
     * 对string类型时间排序
     *
     * @param list
     * @return
     * @throws ParseException
     */
    public static List<String> sortListDesc(List<String> list) {
        List<String> result = Lists.newArrayList();
        if (list != null) {
            result = list.stream().sorted().collect(Collectors.toList());
        }
        return result;
    }

    public static boolean checkDateFormat(String date, String dateFormat) {
        DateTimeFormatter format = DateTimeFormat.forPattern(dateFormat);
        if (StringUtils.isEmpty(date)) {
            return false;
        }
        try {
            DateTime dateTime = DateTime.parse(date, format);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

}
