package com.huishu.constants;

/**
 * @author wangjianchun
 */
public class SysConst {

    public static final String ENCODING_UTF_8 = "UTF-8";

    /**
     * 默认的线程池数量
     */
    public static final int DEFAULT_CORE_POOL_SIZE = 10;

    public static final String RECRIUTMENT = "recriutment";
    public static final String VIDEO = "video";
    public static final String FORUM = "forum";
    public static final String NEWS = "news";
    public static final String POLICY = "policy";
    public static final String ZONGHE = "zonghe";
    public static final String WECHAT = "wechat";
    public static final String QUIT = "quit";
    public static final String MERGER = "merger";
    public static final String INVESTMENT = "investment";
    public static final String INDUSTRY = "industry";

    /**
     * 热点事件阈值
     */
    public static final int HOT_EVENT_THRESHOLD = 1000;

    /**
     * ES 数据状态枚举类
     */
    public enum ESDataStatus {

        NOT_EXISTS_IN_ES("0", "NOT_EXISTS_IN_ES", "ES中不存在"),
        EXISTS_IN_ES("1", "EXISTS_IN_ES", "ES中存在"),
        DUPLICATED("2", "DUPLICATED", "ES中存在重复的数据"),
        EXCEPTION("3", "EXCEPTION", "异常");

        private String code;
        private String status;
        private String desc;

        ESDataStatus(String code, String status, String desc) {
            this.code = code;
            this.status = status;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getStatus() {
            return status;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 情感枚举类
     */
    public enum Emotion {

        NEUTRAL(0L, "neutral", "中性"),
        NEGATIVE(1L, "negative", "负面"),
        POSITIVE(2L, "positive", "正面");

        private Long code;
        private String emotion;
        private String desc;

        Emotion(Long code, String emotion, String desc) {
            this.code = code;
            this.emotion = emotion;
            this.desc = desc;
        }

        public Long getCode() {
            return code;
        }

        public String getEmotion() {
            return emotion;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 热点事件标记枚举类
     */
    public enum HotEventMark {

        NOT_HOT_EVENT(0L, "非热点事件"),
        HOT_EVENT(1L, "热点事件");

        private Long code;
        private String desc;

        HotEventMark(Long code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Long getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 社会渠道枚举类
     */
    public enum SocialChannel {

        INTERNET_MEDIA("1", "网络媒体"),
        FORUM("2", "论坛"),
        SOCIAL("3", "社交"),
        FOREIGN_MEDIA("4", "外媒");

        private String code;
        private String name;

        SocialChannel(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 数据类型枚举类
     */
    public enum DataType {

        POLICY(1L, "POLICY", "政策"),
        RECRUITMENT(2L, "RECRUITMENT", "招聘"),
        INVESTMENT(3L, "INVESTMENT", "投资"),
        INDUSTRY(4L, "INDUSTRY", "工商");

        private Long code;
        private String type;
        private String desc;

        DataType(Long code, String type, String desc) {
            this.code = code;
            this.type = type;
            this.desc = desc;
        }

        public Long getCode() {
            return code;
        }

        public String getType() {
            return type;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 发布类型枚举类
     */
    public enum PublishType {

        CENTER(1L, "CENTER", "中央"),
        LOCAL(2L, "LOCAL", "地方"),
        LITIGATION(3L, "LITIGATION", "诉讼"),
        NEWS(4L, "POLICY", "新闻"),
        COMPREHENSIVE(5L, "COMPREHENSIVE", "综合"),
        FORUM(6L, "FORUM", "论坛"),
        RECRUITMENT(7L, "RECRUITMENT", "招聘"),
        WECHAT(8L, "WECHAT", "微信"),
        INVESTMENT(9L, "INVESTMENT", "投资"),
        INDUSTRY(10L, "INDUSTRY", "工商"),
        VIDEO(11L, "VIDEO", "视频"),
        OTHER(12L, "OTHER", "其它"),
        MERGER(13L, "MERGER", "投资并购"),
        QUIT(14L, "QUIT", "投资退出");

        private Long code;
        private String type;
        private String desc;

        PublishType(Long code, String type, String desc) {
            this.code = code;
            this.type = type;
            this.desc = desc;
        }

        public Long getCode() {
            return code;
        }

        public String getType() {
            return type;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 网站类型枚举类
     */
    public enum SiteType {

        MEDIA(1L, "媒体"),
        /**
         * 社交类网站，例如：人人网 豆瓣 爱哈友
         */
        SOCIAL(2L, "社交");

        private Long code;
        private String type;

        SiteType(Long code, String type) {
            this.code = code;
            this.type = type;
        }

        public Long getCode() {
            return code;
        }

        public String getType() {
            return type;
        }
    }

    /**
     * 行业类型枚举类
     */
    public enum IndustryType {

        INTERNET("互联网"),
        FINANCE("金融"),
        TRAFFIC("交通"),
        EDUCATION("教育"),
        TOURISM("旅游");

        private String type;

        IndustryType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

}
