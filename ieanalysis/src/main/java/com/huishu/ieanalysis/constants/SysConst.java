package com.huishu.ieanalysis.constants;

import java.util.*;

/**
 * <p>Title: SysConst</p>
 * <p>Description: 系统常量类</p>
 *
 * @author wjc
 * @date 2016年12月26日
 */
public class SysConst {

    public static final String ENCODING_UTF_8 = "UTF-8";

    public static final String ES_INDEX = "dgap";
    public static final String ES_TYPE = "info";

    public static final String ES_CLOUD_INDEX = "dgap_word_cloud";
    public static final String ES_CLOUD_TYPE = "info";

    public static final String YES = "1";
    public static final String NO = "0";

    public static final int PAGE_SIZE = 15;

    public static final int AGG_SITE_SIZE = 1000;
    public static final int AGG_JOBS_SIZE = 1000;

    private static final List<String> X_NAME_MONTH = Arrays.asList(
            new String[]{"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"});

    private static final List<String> X_NAME_QUARTER = Arrays.asList(
            new String[]{"2016年第一季度", "2016年第二季度", "2016年第三季度", "2016年第四季度"});

    private static final List<String> SOCIAL_CHANNEL_LIST = Arrays.asList(new String[]{"媒体", "论坛", "社交", "其它"});

    private static final Set<String> COMMON_PROVINCE_SET = new HashSet<>(Arrays.asList(
            new String[]{"北京", "上海", "重庆", "天津", "河北", "山西", "辽宁", "吉林", "黑龙江",
                    "江苏", "浙江", "安徽", "福建", "江西", "山东", "河南", "湖北", "湖南", "广东",
                    "海南", "四川", "贵州", "云南", "陕西", "甘肃", "青海", "台湾", "广西", "宁夏",
                    "西藏", "新疆", "内蒙古", "香港", "澳门"}));

    public static final String COMMON_PROVINCE_STR = "北京,上海,重庆,天津,河北,山西,辽宁,吉林,黑龙江,江苏,浙江,安徽,福建,江西,山东,河南,湖北,湖南,广东,海南,四川,贵州,云南,陕西,甘肃,青海,台湾,广西,宁夏,西藏,新疆,内蒙古,香港,澳门,南海诸岛";
    public static final int ECHART_XNAME_LEN = 4;

    public enum VectorType{

        SITE("1"),
        SOCIAL_CHANNEL("2"),
        INDUSTRY("3");

        private String code;

        VectorType(String code){
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    /**
     * 发布类型枚举类
     */
    public enum PublishType{

        CENTER("1", "中央"),
        LOCAL("2", "地方"),
        LITIGATION("3", "知识产权保护诉讼");

        private String code;
        private String type;

        PublishType(String code, String type){
            this.code = code;
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public String getType() {
            return type;
        }
    }

    /**
     * 数据类型枚举类
     */
    public enum DataType{

        POLICY(1, "政策"),
        RECRUITMENT(2, "招聘"),
        INVESTMENT(3, "投资"),
        INDUSTRY(4, "工商");

        private int code;
        private String type;

        DataType(int code, String type){
            this.code = code;
            this.type = type;
        }

        public int getCode() {
            return code;
        }

        public String getType() {
            return type;
        }
    }

    public static Set<String> getProvinceSet(){
        return Collections.unmodifiableSet(COMMON_PROVINCE_SET);
    }

    public static String getSocialChannel(int index){
        return SOCIAL_CHANNEL_LIST.get(index);
    }

    public static List<String> getMonthList(){
        return Collections.unmodifiableList(X_NAME_MONTH);
    }

    public static List<String> getQuarterList(){
        return Collections.unmodifiableList(X_NAME_QUARTER);
    }

    public static String getQuarter(int index){
        return X_NAME_QUARTER.get(index);
    }

}
