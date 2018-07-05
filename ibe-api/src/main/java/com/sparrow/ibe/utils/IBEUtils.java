package com.sparrow.ibe.utils;

import java.util.regex.Pattern;

/**
 * IBE机票接口的工具类
 * @author wangjc
 * @date 2014-11-18
 */
public class IBEUtils {

	private static final Pattern TICKET_REGEX = Pattern.compile("([0-9]{3}-[0-9]{10}[\\s\\S^\n]{57})");

	
}