package com.sparrow.opinion.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sparrow.opinion.constants.SysConst;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title: StringUtils</p>
 * <p>Description: 字符串工具类</p>
 *
 * @author wjc
 * @date 2017年4月22日
 */
public final class StringUtils {

    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    private static final Pattern dateTimePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");
    private static final Pattern dateTimePattern2 = Pattern.compile("^(\\d{1,2}-\\d{1,2} \\d{2}:\\d{2})$");
    private static final Pattern dateTimePattern3 = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} \\d{2}:\\d{2}");
    private static final Pattern dateTimePattern4 = Pattern.compile("(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})");
    private static final Pattern dateTimePattern5 = Pattern.compile("^(\\d{4}-\\d{1,2}-\\d{1,2} \\d{2}:\\d{2})$");
    private static final Pattern dateTimePattern6 = Pattern.compile("^(\\d{4}-\\d{1,2}-\\d{1,2}\\d{2}:\\d{2}:\\d{2} \\d{2}:\\d{2}:\\d{2})$");

    private static final Pattern WEBSITE_PATTERN = Pattern.compile("(http(s?)://|)((\\w)+\\.)+\\w+");

    private StringUtils() {
    }

    /**
     * <p>Description: 根据数量和边界值生成随机的数据，用于测试或者演示</p>
     *
     * @param quantity
     * @param bound
     * @return
     * @author wjc
     * @date 2017年4月22日
     */
    public static List<Integer> generateData(int quantity, int bound) {
        List<Integer> result = new ArrayList<Integer>();
        Random random = new Random();
        for (int i = 0; i < quantity; i++) {
            result.add(random.nextInt(bound));
        }

        return result;
    }

    /**
     * <p>Description: 根据数量和边界值生成随机的数据，生成的数据按照正序排序，用于测试或者演示</p>
     *
     * @param quantity
     * @param bound
     * @return
     * @author wjc
     * @date 2017年4月22日
     */
    public static List<Integer> generateDataOrderByAsc(int quantity, int bound) {
        List<Integer> result = generateData(quantity, bound);
        result.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        return result;
    }

    /**
     * <p>Description: 根据数量和边界值生成随机的数据，生成的数据按照降序排序，用于测试或者演示</p>
     *
     * @param quantity
     * @param bound
     * @return
     * @author wjc
     * @date 2017年4月22日
     */
    public static List<Integer> generateDataOrderByDesc(int quantity, int bound) {
        List<Integer> result = generateData(quantity, bound);
        result.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        return result;
    }

    /**
     * <p>Description: 根据指定的边界值生成一个随机数值</p>
     *
     * @param bound
     * @return
     * @author wjc
     * @date 2017年4月23日
     */
    public static Integer randomInteger(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }

    /**
     * <p>Description: 根据指定的边界值生成一个随机数值</p>
     *
     * @return 0-1之间的double值
     * @author wjc
     * @date 2017年7月24日
     */
    public static Double randomDouble() {
        Random random = new Random();
        BigDecimal bd = new BigDecimal(random.nextDouble());
        return bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * <p>Description: 获取34个省份列表</p>
     *
     * @return
     * @author wjc
     * @date 2017年5月4日
     */
    public static List<String> getProvinceList() {
        String[] provincesText = {"上海", "河北", "山西", "内蒙古", "辽宁",
                "吉林", "黑龙江", "江苏", "浙江", "安徽", "福建", "江西",
                "山东", "河南", "湖北", "湖南", "广东", "广西", "海南", "四川",
                "贵州", "云南", "西藏", "陕西", "甘肃", "青海", "宁夏", "新疆",
                "北京", "天津", "重庆", "香港", "澳门", "台湾"};
        return Arrays.asList(provincesText);
    }

    /**
     * <p>Description: 对于参数使用UTF-8进行解码</p>
     *
     * @param params
     * @return
     * @author wjc
     * @date 2017年2月22日
     */
    public static String decode(String params) {
        String result = null;
        try {
            result = URLDecoder.decode(params, SysConst.ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照',
        //'分割"***.***.***.***".length() = 15
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 根据用户请求判断用户使用的是否是IE浏览器
     *
     * @param request
     * @return
     */
    public static boolean isIE(HttpServletRequest request) {
        String agent = request.getHeader("User-Agent").toUpperCase();
        boolean isIE = ((agent.indexOf("MSIE") != -1)
                || (-1 != agent.indexOf("LIKE GECKO"))); // 判断版本,后边是判断IE11的

        return isIE;
    }

    /**
     * 将参数转化为JSONObject对象 参数格式:username=a111&age=12
     *
     * @param param
     * @return JSONObject
     */
    public static JSONObject paramToJson(String param) {
        if (param == null || "".equals(param.trim())) {
            return null;
        }
        JSONObject jsonObj = new JSONObject();
        try {
            String decodedParam = URLDecoder.decode(param, SysConst.ENCODING_UTF_8);
            String[] params = decodedParam.split("&");
            for (String str : params) {
                try {
                    String[] strs = str.split("=");
                    if (strs.length == 2) {
                        if (strs[1].startsWith("[")) {
                            JSONArray jsons = JSONArray.parseArray(strs[1]);
                            jsonObj.put(strs[0], jsons);
                        } else if (strs[1].startsWith("{")) {
                            JSONObject jsons = JSONObject.parseObject(strs[1]);
                            jsonObj.put(strs[0], jsons);
                        } else {
                            jsonObj.put(strs[0], strs[1].replace("\"", ""));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }

    public static boolean isEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.isNotEmpty(str);
    }

    /**
     * <p>Description: 统计关键词在内容中出现的次数</p>
     *
     * @param content
     * @param keyword
     * @return
     * @author wjc
     * @date 2017年5月22日
     */
    public static int countOccurrenceNumber(String content, String keyword) {
        if (isEmpty(content) || isEmpty(keyword)) {
            return 0;
        }
        int result = 0;
        Pattern pattern = Pattern.compile("(" + keyword + ")");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            result++;
        }
        return result;
    }

    /**
     * <p>Description: 生成指定长度的随机字符串</p>
     *
     * @param length
     * @return
     * @author wjc
     * @date 2017年2月7日
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int baseLength = base.length();
        Random random = new Random();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(baseLength);
            result.append(base.charAt(index));
        }
        return result.toString();
    }

    /**
     * <p>Description: 判断字符串中是否包含乱码，该方法的返回结果仅供参考</p>
     *
     * @param str
     * @return
     * @author Wangjianchun
     * @date 2017年6月29日
     */
    public static boolean isContainMessyCode(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return (str.indexOf("��") != -1);
    }

    /**
     * <p>Description: 判断str表示的是否一个url</p>
     *
     * @param str
     * @return
     * @author Wangjianchun
     * @date 2017年6月29日
     */
    public static boolean isUrl(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        boolean result = (str.endsWith(".com") || str.endsWith(".cn")
                || str.endsWith(".org") || str.endsWith(".net"));
        return result;
    }

    /**
     * <p>Description: 根据文章的url获取文章所在网站的url</p>
     *
     * @param url
     * @return
     * @author Wangjianchun
     * @date 2017年7月3日
     */
    public static String getWebsiteUrl(String url) {
        String result = "";
        if (isEmpty(url)) {
            return result;
        }
        int index = url.indexOf("://");
        if (index != -1) {
            int startIndex = url.indexOf("/", index + 3);
            if (startIndex != -1) {
                result = url.substring(0, startIndex);
            }
        } else {
            result = url.substring(0, url.indexOf("/"));
        }
        return result;
    }

    /**
     * <p>Description: 截取字符串</p>
     *
     * @param content
     * @param splitLen
     * @return
     * @author Wangjianchun
     * @date 2017年7月3日
     */
    public static String substr(String content, Integer splitLen, String replaceStr) {
        return content.length() > splitLen ? content.substring(0, splitLen) + replaceStr : content;
    }

    /**
     * <p>Description: 调整不规范的日期格式为yyyy-MM-dd HH:mm:ss的日期字符串</p>
     *
     * @param date
     * @return
     * @author wjc
     * @date 2017年3月1日
     */
    public static String adjustDateTime(String date) {
        if (date == null) {
            return null;
        }

        String result = null;

        String dateStr = date.trim();
        Matcher matcher = dateTimePattern.matcher(dateStr);
        Matcher matcher2 = dateTimePattern2.matcher(dateStr);
        Matcher matcher3 = dateTimePattern3.matcher(dateStr);
        Matcher matcher4 = dateTimePattern4.matcher(dateStr);
        Matcher matcher5 = dateTimePattern5.matcher(dateStr);
        Matcher matcher6 = dateTimePattern6.matcher(dateStr);

        if (matcher.find()) {
            result = matcher.group();
        } else if (matcher2.find()) {
            String group = matcher2.group();
            int index = group.indexOf("-");
            String month = group.substring(0, index);
            String day = group.substring(index, group.indexOf(" "));
            String time = group.substring(group.indexOf(" "));
            if (month.length() < 2) {
                month = "0" + month;
            }
            if (day.length() < 2) {
                day = "0" + day;
            }
            StringBuilder newDate = new StringBuilder();
            DateTime dateTime = new DateTime();
            dateTime.getYear();
            newDate.append(dateTime.getYear()).append("-").append(month)
                    .append(day).append(time).append(":00");
            result = newDate.toString();
        } else if (matcher3.find()) {
            String group = matcher3.group();
            String[] array = group.split(" ");
            StringBuilder newDate = new StringBuilder();
            newDate.append(array[0]).append(" ")
                    .append(array[2]).append(":00");
            result = newDate.toString();
        } else if (matcher4.find()) {
            String group = matcher4.group();
            result = group + ":00";
        } else if (matcher5.find()) {
            String group = matcher5.group();
            DateTime dt = DateTimeFormat.forPattern("yyyy-M-d HH:mm").parseDateTime(group);
            result = dt.toString(DateUtils.DATE_SECOND_FORMAT);
        }
        if (matcher6.find()) {
            String group = matcher6.group();
            String datePart = group.substring(0, 10);
            String timePart = group.substring(group.indexOf(" ") + 1);
            result = datePart + " " + timePart;
        }

        return result;
    }

    public static String getAbbreviation(String enterpriseName) {
        String result = null;
        if (enterpriseName.endsWith("有限责任公司")) {
            result = enterpriseName.substring(0, enterpriseName.lastIndexOf("有限责任公司"));
        } else if (enterpriseName.endsWith("股份有限公司")) {
            result = enterpriseName.substring(0, enterpriseName.lastIndexOf("股份有限公司"));
        } else if (enterpriseName.endsWith("有限公司")) {
            result = enterpriseName.substring(0, enterpriseName.lastIndexOf("有限公司"));
        } else {
            result = enterpriseName;
        }
        return result;
    }

    /**
     * 判断当前系统是否是windows系统
     *
     * @return
     */
    public static boolean isWindows() {
        boolean flag = false;
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            flag = true;
        }
        return flag;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getMediaHost(String url) {
        if (url == null) {
            return null;
        }
        String result;
        int startIndex = url.indexOf("://") + 3;
        int virguleIndex = url.indexOf("/", startIndex);
        int questionIndex = url.indexOf("?", startIndex);
        if (virguleIndex != -1 && questionIndex != -1) {
            if (virguleIndex < questionIndex) {
                result = url.substring(0, virguleIndex);
            } else {
                result = url.substring(0, questionIndex);
            }
        } else if (virguleIndex == -1 && questionIndex != -1) {
            result = url.substring(0, questionIndex);
        } else if (virguleIndex != -1 && questionIndex == -1) {
            result = url.substring(0, virguleIndex);
        } else {
            result = url;
        }

        return result;
    }

    /**
     * 正则提取url中的网站地址
     *
     * @param url
     * @return
     */
    public static String getWebSiteUrl(String url) {
        Matcher m = WEBSITE_PATTERN.matcher(url);
        String result;
        if (m.find()) {
            result = m.group();
        } else {
            result = url;
        }
        return result;
    }

}
