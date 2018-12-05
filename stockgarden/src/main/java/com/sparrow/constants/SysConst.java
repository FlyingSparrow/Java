package com.sparrow.constants;

/**
 * <p>Title: SysConst</p>
 * <p>Description: 系统常量类</p>
 *
 * @author wjc
 * @date 2018/12/5
 */
public class SysConst {

    public static final String ENCODING_ISO_8859_1 = "ISO-8859-1";
    public static final String ENCODING_UTF_8 = "UTF-8";

    public static String BASE_PATH;

    public static String LOGIN_SESSION_KEY = "Favorites_user";

    public static String PASSWORD_KEY = "@#$%^&*()OPG#$%^&*(HG";

    public static String DES3_KEY = "9964DYByKL967c3308imytCB";

    public static String default_logo = "img/logo.jpg";

    public static String userAgent = "Mozilla";

    public static String default_Profile = BASE_PATH + "/img/logo.jpg";

    public static String LAST_REFERER = "LAST_REFERER";

    public static int COOKIE_TIMEOUT = 30 * 24 * 60 * 60;

    /**
     * 数据库字段状态枚举类
     */
    public enum Status {

        ENABLED(1, "启用"),
        FROZEN(2, "冻结"),
        DELETED(3, "删除");

        private Integer code;
        private String description;

        Status(Integer code, String description) {
            this.code = code;
            this.description = description;
        }

        public Integer getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

}
