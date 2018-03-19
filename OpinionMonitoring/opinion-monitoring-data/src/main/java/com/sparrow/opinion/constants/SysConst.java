package com.sparrow.opinion.constants;

/**
 * <p>Title: SysConst</p>
 * <p>Description: 系统常量类</p>
 *
 * @author wjc
 * @date 2016年12月26日
 */
public class SysConst {
    /**
     * 默认批处理大小
     */
    public static final int DEFAULT_BATCH_SIZE = 200;

    /**
     * 默认清理业务数据的记录数限制，如果业务表中的数据超过该限制，那么则应该采用分页的方式进行处理
     */
    public static final int DEFAULT_CLEAR_DATA_LIMIT = 3000000;

    public static final String ENCODING_ISO8859_1 = "ISO8859-1";
    public static final String ENCODING_UTF_8 = "UTF-8";

    /**
     * 系统的默认用户账号
     */
    public static final String DEFAULT_USER_ACCOUNT = "admin";

    /**
     * 默认的京东金融JSF邮件接口的组织名称
     */
    public static final String DEFAULT_JSF_MAIL_ORGANIZATION_NAME = "京东金融";
    /**
     * 默认的京东金融JSF邮件接口的token
     */
    public static final String DEFAULT_JSF_MAIL_TOKEN = "1234";

    /**
     * 缓存name值
     */
    public static final String USERBASE_CACHE_NAME = "userAccountCacheName";
    public static final String MEDIA_CACHE_NAME = "mediaCacheName";
    public static final String KEYWORD_WARNING_CACHE_NAME = "KeywordWarningCacheName";
    public static final String THRESHOLD_WARNING_CACHE_NAME = "ThresholdWarningCacheName";
    public static final String WARNING_RECEIVER_CACHE_NAME = "WarningReceiverCacheName";

    /**
     * es的索引
     */
    public static final String COMPANY_INVESTOR_INDEX = "dmt_risk_ic_company_investor_i_d";
    public static final String COMPANY_INVESTOR_TYPE = "dmt_risk_ic_company_investor_i_d";

    public static final String COMPANY_BASE_INDEX = "dmt_risk_ic_company_base_i_d";
    public static final String COMPANY_BASE_TYPE = "dmt_risk_ic_company_base_i_d";

    /**
     * 供企业舆情压力接口使用的 R2M key，存储的是通过验证的文章的id
     *
     * 2018-1-10 wangjianchun
     * 说明：由于mongodb 数据库的 article 表是根据 url的md5值判断是否重复的，
     * 在从JMQ 接收爬虫推送的采集数据（也就是文章），经过校验以后，保存到 article 表时
     * 会根据url的md5值判断是否存在相应的记录，如果存在，不做处理；如果不存在，添加。
     * 随着 article 表中的数据不断增长，导致判断记录是否存在消耗的时间也越来越长，因此考虑将
     * R2M 存放的文章id改为存放文章的 url的md5值，同时允许 article 表中存在相同的记录
     */
    public static final String R2M_EOPS_ARTICLE_ID_KEY = "eops_jd_com_articleId_new";

    /**
     * 供企业舆情压力系统使用的 R2M key，存储的是舆情风险表 opinion_risk 的id
     */
    public static final String R2M_EOP_OPINION_RISK_ID_KEY = "eop_jd_com_opinionRiskId_new";

    /**
     * 供企业舆情压力系统使用的 R2M key，存储的是分析后的文章的id
     */
    public static final String R2M_EOP_ARTICLE_ID_KEY = "eop_jd_com_articleId_new";

    /**
     * 供企业舆情压力系统使用的 R2M key，存储的是分析后的舆情明细快照表 opinion_details_snapshot 的id
     */
    public static final String R2M_EOP_OPINION_DETAILS_SNAPSHOT_ID_KEY = "eop_jd_com_opinionDetailsSnapshot_new";

    /**
     * <p>Title: EmotionType</p>
     * <p>Description: 情感类型枚举类</p>
     *
     * @author Wangjianchun
     * @date 2017年7月5日
     */
    public enum EmotionType {

        GENERAL("general", "综合"),
        POSITIVE("positive", "正面"),
        NEUTRAL("neutral", "中性"),
        NEGATIVE("negative", "负面");

        private String code;
        private String name;

        EmotionType(String code, String name) {
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
     * <p>Title: MediaType</p>
     * <p>Description: 媒体类型枚举类</p>
     *
     * @author Wangjianchun
     * @date 2017年7月5日
     */
    public enum MediaType {

        NEWS("新闻"),
        WECHAT("微信"),
        WEIBO("微博"),
        BLOG("博客"),
        FORUM("论坛");

        private String name;

        MediaType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    /**
     * <p>Title: PressureType</p>
     * <p>Description: 舆情压力类型枚举类</p>
     *
     * @author Wangjianchun
     * @date 2017年7月7日
     */
    public enum PressureType {

        OVERALL("整体压力"),
        POSITIVE("正面压力"),
        NEGATIVE("负面压力");

        private String name;

        PressureType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }
    public enum JudgeType {
        MACHINE("机器研判"),
        PEOPLE("人工研判");

        private String name;

        JudgeType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
    /**
     * <p>Title: EmotionType</p>
     * <p>Description: 情感类型枚举类</p>
     *
     * @author Wangjianchun
     * @date 2017年7月5日
     */
    public enum InvestorType {

        NATURALPERSON(Short.valueOf("1"), "自然人"),
        NOTNATURALPERSON(Short.valueOf("2"), "非自然人");

        private Short code;
        private String name;

        InvestorType(Short code, String name) {
            this.code = code;
            this.name = name;
        }

        public Short getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

    }

    /**
     * <p>Title: EmotionType</p>
     * <p>Description: 情感类型枚举类</p>
     *
     * @author Wangjianchun
     * @date 2017年7月5日
     */
    public enum OperationType2 {

        ADD("ADD", "新增"),
        EDIT("EDIT", "修改"),
        DEL("DEL", "删除");

        private String code;
        private String name;

        OperationType2(String code, String name) {
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

    public enum QueryType {

        FUZZYQUERY("fuzzy", "模糊查询"),
        EXACT_QUERY("exact", "精确查询");

        private String code;
        private String name;

        QueryType(String code, String name) {
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
     * <p>Title: Switch</p>
     * <p>Description: 开关枚举类</p>
     *
     * @author Wangjianchun
     * @date 2017年8月16日
     */
    public enum Switch {

        YES("是"),
        NO("否");

        private String name;

        Switch(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }
}
