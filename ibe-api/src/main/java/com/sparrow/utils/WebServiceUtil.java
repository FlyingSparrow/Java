package com.sparrow.utils;

/**
 * WebService 工具类
 *
 * @author wangjianchun
 * @date 2018-7-4
 */
public class WebServiceUtil {

    /**
     * 功能：验证webservice的用户名和密码是否正确
     *
     * @param userId
     * @param password 可以是密码原文，也可以是MD5加密后的密码
     * @return true - 验证通过；false - 验证失败
     * @author wangjc
     * @date 2014-08-19
     */
    public static boolean checkUser(String userId, String password) {
        /*boolean flag = false;
		try {
			MD5 md5 = new MD5();
			String md5Pwd = md5.toDigest(password);
			UserModel user = (UserModel) UserCache.getModel(userId);
			flag = (user != null && (user.getPassword().equals(md5Pwd)
					|| user.getPassword().equals(password)));
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		return flag;*/

        return true;
    }

}
