package com.sparrow.utils;

import com.sparrow.constants.SysConst;

import java.security.MessageDigest;

/**
 * <p>Title: MD5Util</p>
 * <p>Description: </p>
 *
 * @author wjc
 * @date 2018/12/5
 */
public class Md5Util {

	public static String encrypt(String dataStr) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(dataStr.getBytes(SysConst.ENCODING_UTF_8));
			byte s[] = m.digest();
			String result = "";
			for (int i = 0; i < s.length; i++) {
				result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00)
						.substring(6);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

}
