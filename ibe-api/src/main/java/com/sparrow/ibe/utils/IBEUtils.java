package com.sparrow.ibe.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IBE机票接口的工具类
 * @author wangjc
 * @date 2014-11-18
 */
public class IBEUtils {

	/**
	 * Office号格式的正则表达式
	 */
	private static final Pattern OFFICE_CODE_REGEX = Pattern.compile("^([A-Z0-9])+$");
	private static final Pattern TICKET_REGEX = Pattern.compile("([0-9]{3}-[0-9]{10}[\\s\\S^\n]{57})");

	/**
	 * 验证Office号格式是否有效：office代码，大写，例如：BJS723
	 *
	 * @param officeCode
	 * @return
	 */
	public static boolean isValidOfficeCode(String officeCode){
		Matcher matcher = OFFICE_CODE_REGEX.matcher(officeCode);
		return matcher.matches();
	}

}
