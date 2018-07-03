package com.huishu.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangjianchun
 */
public class DateUtils {

	public static final String FORMAT_DATE = "yyyy-MM-dd";

	public static final String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";

	public static final String FORMAT_FILE_PATH = "yyyyMMdd";

	public static String getDateFilePath(Date date) {
		return formatDate(date, FORMAT_FILE_PATH);
	}

	/**
	 * 获取格式化后的日期
	 * @param date
	 * @return yyyy-MM-dd 格式的日期字符串
	 */
	public static String getFormatDate(Date date) {
		return formatDate(date, FORMAT_DATE);
	}

	/**
	 * 获取格式化后的日期
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss 格式的日期字符串
	 */
	public static String getFormatTime(Date date) {
		return formatDate(date, FORMAT_TIME);
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
	public enum DateFields {

		YEAR, MONTH, DAY, WEEK, HOUR, MINUTE, SECOND;

	}

	public static Date getYesterdayNow(Date date) {
		return plus(date, DateFields.DAY, -1);
	}

	public static Date getWeekAgoNow(Date date) {
		return plus(date, DateFields.DAY, -6);
	}

	public static Date getTenDaysAgoNow(Date date) {
		return plus(date, DateFields.DAY, -9);
	}

	public static List<String> getHourList(Date date) {
		List<String> list = new ArrayList<String>();
		DateTime dateTime = new DateTime(date);
		int hour = dateTime.getHourOfDay() + 1;
		int maxHour = 24;
		for (int i = 1; i <= maxHour; i++) {
			int temp = hour + i;
			if (temp > maxHour) {
				temp = temp - maxHour;
			}
			list.add(temp + "H");
		}
		return list;
	}

	public static List<Integer> getDayList(Date date) {
		List<Integer> list = new ArrayList<Integer>();
		DateTime dateTime;
		int days = 9;
		for (int i = days; i >= 0; i--) {
			dateTime = new DateTime(date);
			list.add(dateTime.minusDays(i).getDayOfMonth());
		}
		return list;
	}

	public static List<String> getLegendDayList(Date date) {
		List<String> list = new ArrayList<String>();
		DateTime dateTime;
		int days = 6;
		for (int i = days; i >= 0; i--) {
			dateTime = new DateTime(date);
			dateTime = dateTime.minusDays(i);
			list.add(dateTime.getMonthOfYear() + "月" + dateTime.getDayOfMonth() + "日");
		}
		return list;
	}

	public static List<String> getWeekDayList(Date date) {
		List<String> list = new ArrayList<String>();
		DateTime dateTime;
		int days = 7;
		for (int i = days; i >= 1; i--) {
			dateTime = new DateTime(date);
			dateTime = dateTime.minusDays(i);
			list.add(getFormatDate(dateTime.toDate()));
		}
		return list;
	}

	/**
	 * 获取昨天的开始时间，例如：2018-05-24 00:00:00
	 * @return
	 */
	public static Date getYesterdayTimesMorning() {
		DateTime dateTime = new DateTime();
		return dateTime.minusDays(1).withTimeAtStartOfDay().toDate();
	}

	/**
	 * 获取昨天的结束时间，例如：2018-05-24 23:59:59
	 * @return
	 */
	public static Date getYesterdayTimesNight() {
		DateTime dateTime = new DateTime();
		return dateTime.minusDays(1).millisOfDay().withMaximumValue().toDate();
	}

	public static int getCurrentYear(){
		return new DateTime().getYear();
	}

	public static int getCurrentMonth(){
		return new DateTime().getMonthOfYear();
	}

	public static Date getBeforeYesterdayNow(Date date) {
		return plus(date, DateFields.DAY, -2);
	}

	public static Date getBeforeYesterdayNow(Date date,int day) {
		return plus(date, DateFields.DAY, -day);
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

	/**
	 * 返回 date 对象表示的小时，如果date为null，返回0
	 * @param date
	 * @return
	 */
	public static int getHour(Date date){
		if(date == null){
			return 0;
		}
		return new DateTime(date).getHourOfDay();
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
		return parseDate(date, FORMAT_TIME);
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
		if (StringUtils.isNotEmpty(date) && StringUtils.isNotEmpty(dateFormat)
				&& (date.length() == dateFormat.length())) {
			DateTimeFormatter format = DateTimeFormat.forPattern(dateFormat);
			DateTime dateTime = DateTime.parse(date, format);
			result = dateTime.toDate();
		}

		return result;
	}

	/**
	 * 获取指定日期对象所属的年份
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		if (date == null) {
			return -1;
		}
		return new DateTime(date).getYear();
	}

	/**
	 * 获取指定日期对象所属的月份
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		if (date == null) {
			return -1;
		}
		return new DateTime(date).getMonthOfYear();
	}

}
