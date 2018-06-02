package com.huishu.analysis.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.analysis.Analyzer;
import com.huishu.analysis.vo.NewsVO;
import com.huishu.analysis.vo.ValidationVO;
import com.huishu.config.AnalysisConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.CityLib;
import com.huishu.entity.KingBaseDgap;
import com.huishu.entity.SiteLib;
import com.huishu.service.CityLibService;
import com.huishu.service.KingBaseDgapService;
import com.huishu.utils.DateUtils;
import com.huishu.utils.FileUtils;
import com.huishu.vo.DgapData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 默认的分析器，主要用于给子类提供公共方法，不进行实际的数据分析
 * @author wangjianchun
 * @create 2018/5/30
 */
@Component
public class DefaultAnalyzer implements Analyzer {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected AnalysisConfig analysisConfig;
    @Autowired
    protected CityLibService cityLibService;
    @Autowired
    private KingBaseDgapService kingBaseDgapService;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void analysis(AnalysisConfig analysisConfig, ThreadPoolExecutor executor, Map<String, String> indexMap) {
        //do nothing
    }

    /**
     * 对SiteLib对象填充地区信息
     * 分析城市
     * 根据内容分析城市 如果没有则查询网站配置城市
     *
     * @param title
     * @param content
     * @param source
     * @return
     */
    protected SiteLib fillAreaInfoForSiteLib(String title, String content, SiteLib source) {
        SiteLib siteLib = new SiteLib();
        String[] areaArray = analysisConfig.getCommonArea().split(SysConst.COMMA);
        for (String area : areaArray) {
            if (fillAreaAndProvinceInfo(title, siteLib, area)) {
                break;
            }
            if (fillAreaAndProvinceInfo(content, siteLib, area)) {
                break;
            }
        }

        if (StringUtils.isEmpty(siteLib.getProvince())) {
            List<CityLib> cityList = cityLibService.findByTypeLessThan(3);
            for (CityLib city : cityList) {
                if (StringUtils.isEmpty(city.getCity())) {
                    continue;
                }
                if (fillProvinceAndCityInfo(title, siteLib, city)) {
                    break;
                }
                if (fillProvinceAndCityInfo(content, siteLib, city)) {
                    break;
                }
            }
        }
        if (source != null) {
            // 城市
            if (StringUtils.isEmpty(siteLib.getArea())) {
                siteLib.setArea(source.getArea());
            }
            // 省份
            if (StringUtils.isEmpty(siteLib.getProvince())) {
                siteLib.setProvince(source.getProvince());
            }
            if (StringUtils.isEmpty(siteLib.getProvince())) {
                return null;
            }
            // 行业
            siteLib.setIndustry(searchIndustry(title, content));
            if (StringUtils.isEmpty(siteLib.getIndustry())) {
                siteLib.setIndustry(source.getIndustry());
            }
        }

        return siteLib;
    }

    private boolean fillAreaAndProvinceInfo(String data, SiteLib siteLib, String area) {
        if (StringUtils.isNotEmpty(data) && data.contains(area)) {
            siteLib.setArea(area);
            List<CityLib> cityList = cityLibService.findByCity(area);
            if (cityList != null && cityList.size() > 0) {
                siteLib.setProvince(cityList.get(0).getProvince());
            }
            return true;
        }
        return false;
    }

    private boolean fillProvinceAndCityInfo(String data, SiteLib siteLib, CityLib city) {
        if (StringUtils.isNotEmpty(data) && data.contains(city.getCity())) {
            siteLib.setProvince(city.getProvince());
            if (StringUtils.isEmpty(siteLib.getArea())) {
                siteLib.setArea(city.getCity());
            }
            return true;
        }
        return false;
    }

    /**
     * 分析行业
     * 根据内容分析行业 如果没有则查询网站配置行业
     *
     * @param title
     * @param content
     * @return
     */
    private String searchIndustry(String title, String content) {
        JSONObject jsonObject = new JSONObject();

        // 互联网
        int internetCount = countIndustryKeywordsOccurrenceNumber(analysisConfig.getInternetKeyWords(), title, content);
        jsonObject.put("count", internetCount);
        jsonObject.put("industry", SysConst.IndustryType.INTERNET.getType());

        // 金融
        int financeCount = countIndustryKeywordsOccurrenceNumber(analysisConfig.getFinanceKeyWords(), title, content);
        filterIndustry(SysConst.IndustryType.FINANCE.getType(), financeCount, jsonObject);

        // 教育
        int educationCount = countIndustryKeywordsOccurrenceNumber(analysisConfig.getEducationKeyWords(), title, content);
        filterIndustry(SysConst.IndustryType.EDUCATION.getType(), educationCount, jsonObject);

        // 交通
        int trafficCount = countIndustryKeywordsOccurrenceNumber(analysisConfig.getTrafficKeyWords(), title, content);
        filterIndustry(SysConst.IndustryType.TRAFFIC.getType(), trafficCount, jsonObject);

        // 旅游
        int tourismCount = countIndustryKeywordsOccurrenceNumber(analysisConfig.getTourismKeyWords(), title, content);
        filterIndustry(SysConst.IndustryType.TOURISM.getType(), tourismCount, jsonObject);

        return jsonObject.getString("industry");
    }

    /**
     * 统计行业关键词在标题和内容中出现的次数
     *
     * @param industryKeyWords 行业关键词，例如互联网行业关键词、金融行业关键词、教育行业关键词、交通行业关键词、旅游行业关键词等等
     * @param title            标题
     * @param content          内容
     * @return
     */
    private int countIndustryKeywordsOccurrenceNumber(String industryKeyWords, String title, String content) {
        int count = 0;
        JSONArray tourismKeyWordArray = com.huishu.utils.StringUtils.split(industryKeyWords, SysConst.COMMA);
        if (StringUtils.isNotEmpty(title)) {
            count += com.huishu.utils.StringUtils.countOccurrenceNumber(tourismKeyWordArray, title);
        }
        if (StringUtils.isNotEmpty(content)) {
            count += com.huishu.utils.StringUtils.countOccurrenceNumber(tourismKeyWordArray, content);
        }

        return count;
    }

    /**
     * 筛选行业
     *
     * @param industry                         行业
     * @param industryKeywordsOccurrenceNumber 行业关键词的出现次数
     * @param jsonObject                       行业关键词出现次数最高的行业的信息
     * @return 行业关键词出现次数最高的行业
     */
    protected void filterIndustry(String industry, int industryKeywordsOccurrenceNumber,
                                JSONObject jsonObject) {
        if (industryKeywordsOccurrenceNumber > jsonObject.getIntValue("count")) {
            jsonObject.put("industry", industry);
        }
    }

    protected void setReportType(DgapData dgapData) {
        JSONArray socialSiteArray = com.huishu.utils.StringUtils.split(analysisConfig.getSocialSite(), SysConst.COMMA);
        for (int i = 0, size = socialSiteArray.size(); i < size; i++) {
            if (dgapData.getSite().equals(socialSiteArray.getString(i))) {
                dgapData.setReportType(SysConst.SiteType.SOCIAL.getCode());
                break;
            }
        }
    }

    protected String searchEmotion(String title, String content) {
//		String articleCategory = SCArticleCategory.articleCategory(title, content);
//		return articleCategory;

        return "";
    }

    /**
     * 保存分析后的数据到人大金仓数据库
     *
     * @param historyList
     */
    protected void saveToKingBase(List<KingBaseDgap> historyList) {
        kingBaseDgapService.save(historyList);
        if (analysisConfig.isKingSaveMark()) {
            kingBaseDgapService.saveKing(historyList);
        }
    }

    protected void addKingBaseData(List<KingBaseDgap> historyList, DgapData source) {
        KingBaseDgap target = new KingBaseDgap();
        BeanUtils.copyProperties(source, target);
        target.setId(com.huishu.utils.StringUtils.getUUID());
        historyList.add(target);
    }

    protected void setImageUrl(DgapData dgapData, long infoType, String imageUrl, String url) {
        if (imageUrl.startsWith("http") && imageUrl.length() <= 150) {
            boolean imageFlag = true;
            String invalidImageUrl = analysisConfig.getInvalidImageurl();
            JSONArray invalidImageUrlArray = com.huishu.utils.StringUtils.split(invalidImageUrl, SysConst.COMMA);
            for (int i = 0, size = invalidImageUrlArray.size(); i < size; i++) {
                String invalidImageUrlItem = invalidImageUrlArray.getString(i);
                if (invalidImageUrlItem.indexOf(imageUrl) >= 0) {
                    imageFlag = false;
                    break;
                }
            }
            if (imageFlag) {
                dgapData.setPolicyInfoType(infoType);
                dgapData.setPolicyImageUrl(imageUrl);
            }
        } else {
            dgapData.setPolicyInfoType(infoType);
            dgapData.setPolicyImageUrl(url.substring(0, url.lastIndexOf("/") + 1) + imageUrl);
        }
    }

    protected synchronized void recordNum(Map<String, String> indexMap) {
        String filePath = System.getProperty("user.dir") + "analysis-data-temp.properties";
        FileUtils.createFileIfNotExists(new File(filePath));
        FileUtils.writeProperties(filePath, indexMap);
    }

    /**
     * 使用 fldrecddate 对象填充 dgapData 对象的时间信息，包括年、月、日、小时
     *
     * @param dgapData
     * @param time
     */
    protected void fillDateInfoOfDgapData(DgapData dgapData, String time) {
        try {
            String tempTime = com.huishu.utils.StringUtils.transformTime(time);
            dgapData.setHour(0L);
            int yearIndex = tempTime.indexOf("-");
            dgapData.setYear(Long.valueOf(tempTime.substring(0, yearIndex).trim()));
            int monthIndex = tempTime.indexOf("-", yearIndex + 1);
            dgapData.setMonth(Long.valueOf(tempTime.substring(yearIndex + 1, monthIndex).trim()));
            int sIndex = tempTime.indexOf(" ", monthIndex + 1);
            int hourIndex = tempTime.indexOf(":");
            if (sIndex > 0) {
                dgapData.setDay(Long.valueOf(tempTime.substring(monthIndex + 1, sIndex).trim()));
                if (hourIndex > 0) {
                    dgapData.setHour(Long.valueOf(tempTime.substring(sIndex + 1, hourIndex).trim()));
                }
            } else {
                dgapData.setDay(Long.valueOf(tempTime.substring(monthIndex + 1, tempTime.length()).trim()));
            }
        } catch (NumberFormatException e) {
            logger.error("日期[{}]转换错误：{}", time, e);
        }
    }

    protected void analysisPicture(String url, String content, String pdmc, DgapData data) {
        if (content.indexOf("<img") < 0) {
            return;
        }

        // 图片
        long infoType = 1L;
        data.setPolicyInfoType(infoType);
        if ("双创动态".equals(pdmc.trim())) {
            return;
        }

        JSONArray pictureSuffixArray = com.huishu.utils.StringUtils.split(analysisConfig.getPictureSuffix(), SysConst.COMMA);
        for (int i = 0, size = pictureSuffixArray.size(); i < size; i++) {
            String pictureSuffixItem = pictureSuffixArray.getString(i);
            String tempSuffix = pictureSuffixItem.toLowerCase();
            int end = content.indexOf(tempSuffix);
            if (end == -1) {
                tempSuffix = pictureSuffixItem.toUpperCase();
                end = content.indexOf(tempSuffix);
            }
            if (end >= 0) {
                infoType = 2L;
                int start = content.lastIndexOf("src=", end);
                if (start >= 0) {
                    String imageUrl = content.substring(start + 5, end + pictureSuffixItem.length());
                    setImageUrl(data, infoType, imageUrl, url);
                }
                break;
            }
        }
    }

    protected void saveToFile(List<DgapData> saveList, String type, String filePath) {
        for (DgapData item : saveList) {
            String content = JSONObject.toJSONString(item);
            StringBuilder fileName = new StringBuilder();
            fileName.append("analysis_").append(type).append("_")
                    .append(DateUtils.getDateFilePath(new Date())).append("_")
                    .append(item.getId()).append(".txt");
            File file = new File(filePath + File.separator + fileName);
            FileUtils.createFileIfNotExists(file);
            FileUtils.writeContentToFile(file.getAbsolutePath(), content);
        }
    }

    /**
     * 填充政策信息
     * 分析内容类型
     * @param content 文章内容
     * @param webName 网站名称
     * @param channelName 频道名称
     * @param url 文章url
     * @param dgapData
     */
    protected void fillPolicyInfo(String content, String webName, String channelName,
                                  String url, DgapData dgapData) {
        // 内容 分析 文章 图片 视频
        // <img
        // src="http://i.ce.cn/ce/cysc/yq/dt/201703/10/W020170310339994179672.jpg">
        if (StringUtils.isEmpty(content)) {
            return;
        }

        // 报道量 双创 创新 创业
        if (content.indexOf("双创") >= 0 || content.indexOf("创新") >= 0 || content.indexOf("创业") >= 0) {
            dgapData.setReportNum(1L);
        }

        // 类型 中央 地方 法院
        if ("中华人民共和国中央人民政府".equals(webName.trim())) {
            dgapData.setPublishType(SysConst.PublishType.LOCAL.getCode());
            dgapData.setHotEventMark(SysConst.HotEventMark.HOT_EVENT.getCode());

            analysisPicture(url, content, channelName, dgapData);

            if ("国务院文件".equals(channelName)) {
                dgapData.setPublishType(SysConst.PublishType.CENTER.getCode());

                int start = content.indexOf("<b>发文字号：</b>");
                int end = content.indexOf("<b>发布日期：</b>");
                // 发文字号
                dgapData.setPolicyPostShopName(content.substring(start + 12, end));
                dgapData.setPolicyReleaseMechanism("国务院文件");
                dgapData.setPolicyPublishAuthor("国务院");
            } else {
                // 是否是部委发文件
                String centerDepartment = analysisConfig.getCenterDepartment();
                if (StringUtils.isEmpty(channelName) || StringUtils.isEmpty(centerDepartment)) {
                    return;
                }

                JSONArray centerDepartmentArray = com.huishu.utils.StringUtils.split(centerDepartment, SysConst.COMMA);
                for (int i = 0, size = centerDepartmentArray.size(); i < size; i++) {
                    String centerDepartmentItem = centerDepartmentArray.getString(i);
                    if (!centerDepartmentItem.equals(channelName)) {
                        continue;
                    }

                    int first = content.indexOf("〔");
                    if (first == -1) {
                        first = content.indexOf("[");
                    }
                    if (first >= 0) {
                        int end = content.indexOf("号", first);
                        int start = content.lastIndexOf(">", first);
                        if (end >= 0) {
                            if (end - start <= 25) {
                                dgapData.setPublishType(SysConst.PublishType.CENTER.getCode());
                                dgapData.setPolicyPostShopName(content.substring(start + 1, end + 1));
                                dgapData.setPolicyReleaseMechanism("部委文件");
                                dgapData.setPolicyPublishAuthor(channelName);
                            }
                        }
                    }
                }
            }
        }
    }

    protected boolean isNotExists(List<DgapData> dgapDataList, String url) {
        boolean flag = true;
        for (DgapData item : dgapDataList) {
            if (StringUtils.isNotEmpty(item.getPolicyUrl()) && item.getPolicyUrl().equals(url)) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    protected boolean validate(ValidationVO validationVO){
        String publishDate = validationVO.getFldrecddate();
        if (StringUtils.isEmpty(validationVO.getProvince())
                || StringUtils.isEmpty(validationVO.getIndustry())
                || StringUtils.isEmpty(validationVO.getFldcontent())
                || StringUtils.isEmpty(validationVO.getFldtitle())
                || StringUtils.isEmpty(validationVO.getFldUrlAddr())
                || StringUtils.isEmpty(publishDate)) {
            return false;
        }

        int yearIndex = publishDate.indexOf("-");
        if (yearIndex <= 0) {
            return false;
        }
        int monthIndex = publishDate.indexOf("-", yearIndex + 1);
        if (monthIndex <= 0) {
            return false;
        }

        return true;
    }

    protected DgapData fillDgapData(NewsVO newsVO) {
        DgapData result = new DgapData();

        result.setPublishType(SysConst.PublishType.LOCAL.getCode());
        // 分类
        result.setDataType(SysConst.DataType.POLICY.getCode());
        result.setTime(newsVO.getFldrecddate());
        // 时间
        fillDateInfoOfDgapData(result, newsVO.getFldrecddate());
        // 站点
        result.setSite(newsVO.getWebname());
        // 省份
        result.setProvince(newsVO.getProvince());
        result.setArea(newsVO.getArea());
        // 行业
        result.setIndustry(newsVO.getIndustry());
        // 社会渠道(1,网络媒体,2,论坛,3,社交，4,外媒)
        result.setSocialChannel(SysConst.SocialChannel.INTERNET_MEDIA.getCode());

        // 是否社交网站
        result.setReportType(SysConst.SiteType.MEDIA.getCode());
        // 是否热点 评论 点击 转发 超过1000
        int count = Integer.parseInt(newsVO.getFldHits()) + Integer.parseInt(newsVO.getFldReply());
        if (count > SysConst.HOT_EVENT_THRESHOLD) {
            result.setHotEventMark(SysConst.HotEventMark.HOT_EVENT.getCode());
        } else {
            result.setHotEventMark(SysConst.HotEventMark.NOT_HOT_EVENT.getCode());
        }

        // 内容 分析 文章 图片 视频
        // 关注量
        result.setHitNum(Long.valueOf(newsVO.getFldHits()));

        String emotion = searchEmotion(newsVO.getFldtitle(), newsVO.getFldcontent());
        if (SysConst.Emotion.NEUTRAL.getEmotion().equals(emotion)) {
            result.setEmotionMark(SysConst.Emotion.NEUTRAL.getCode());
        }
        if (SysConst.Emotion.NEGATIVE.getEmotion().equals(emotion)) {
            result.setEmotionMark(SysConst.Emotion.NEGATIVE.getCode());
        }
        if (SysConst.Emotion.POSITIVE.getEmotion().equals(emotion)) {
            result.setEmotionMark(SysConst.Emotion.POSITIVE.getCode());
        }

        fillPolicyInfo(newsVO.getFldcontent(), newsVO.getWebname(), newsVO.getPdmc(),
                newsVO.getFldUrlAddr(), result);
        // 标题
        result.setPolicyTitle(newsVO.getFldtitle());

        result.setContent(com.huishu.utils.StringUtils.removeHtmlTag(newsVO.getFldcontent()));
        // url
        result.setPolicyUrl(newsVO.getFldUrlAddr());
        // 阅读量
        result.setReadNum(Long.valueOf(newsVO.getFldHits()));
        // 评论量
        result.setHitNum(Long.valueOf(newsVO.getFldReply()));
        setReportType(result);

        return result;
    }

}
