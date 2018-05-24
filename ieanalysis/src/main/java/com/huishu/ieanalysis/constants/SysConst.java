package com.huishu.ieanalysis.constants;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Title: SysConst</p>
 * <p>Description: 系统常量类</p>
 *
 * @author wjc
 * @date 2016年12月26日
 */
public class SysConst {

    /**
     * 默认每页记录数
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    public static final String ENCODING_UTF_8 = "UTF-8";
    //dataType
    /**
     * 政策
     */
    public static final int DATATYPE_POLICY = 1;
    /**
     * 招聘
     */
    public static final int DATATYPE_RECRUITMENT = 2;
    /**
     * 投资
     */
    public static final int DATATYPE_INVESTMENT = 3;
    /**
     * 工商
     */
    public static final int DATATYPE_INDUSTRY = 4;

    public static final String ES_INDEX = "dgap";
    public static final String ES_TYPE = "info";

    public static final String ES_CLOUD_INDEX = "dgap_word_cloud";
    public static final String ES_CLOUD_TYPE = "info";

    public static final String YES = "1";
    public static final String NO = "0";
    /**
     * 中央
     */
    public static final String PUBLISHTYPE_CENTER = "1";
    /**
     * 地方
     */
    public static final String PUBLISHTYPE_LOCAL = "2";
    /**
     * 知识产权保护诉讼
     */
    public static final String PUBLISHTYPE_LITIGATION = "3";

    public static final int PAGE_SIZE = 15;

    public static final int PAGE_NUMBER = 1;

    public static final int AGG_SITE_SIZE = 1000;
    public static final int AGG_JOBS_SIZE = 1000;

    public static final String[] X_NAME_MONTH = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
    public static final String[] X_NAME_QUARTER = {"2016年第一季度", "2016年第二季度", "2016年第三季度", "2016年第四季度"};

    public static final String[] SOCIAL_CHANNEL = {"媒体", "论坛", "社交", "其它"};

    private static final Set<String> COMMON_PROVINCE_SET = new HashSet<>(49);
    public static final String COMMON_PROCINCE_STR = "北京,上海,重庆,天津,河北,山西,辽宁,吉林,黑龙江,江苏,浙江,安徽,福建,江西,山东,河南,湖北,湖南,广东,海南,四川,贵州,云南,陕西,甘肃,青海,台湾,广西,宁夏,西藏,新疆,内蒙古,香港,澳门,南海诸岛";
    public static final int ECHART_XNAME_LEN = 4;

    static {
        String[] provinceArray = {"北京", "上海", "重庆", "天津", "河北", "山西", "辽宁", "吉林", "黑龙江", "江苏", "浙江", "安徽", "福建", "江西", "山东", "河南", "湖北", "湖南", "广东", "海南", "四川", "贵州", "云南", "陕西", "甘肃", "青海", "台湾", "广西", "宁夏", "西藏", "新疆", "内蒙古", "香港", "澳门"};
        for(String item : provinceArray){
            COMMON_PROVINCE_SET.add(item);
        }
    }

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

    public static Set<String> getProvinceSet(){
        return Collections.unmodifiableSet(COMMON_PROVINCE_SET);
    }

}
