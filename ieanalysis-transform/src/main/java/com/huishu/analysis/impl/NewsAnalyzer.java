package com.huishu.analysis.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.analysis.Analyzer;
import com.huishu.config.AnalysisConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.CityLib;
import com.huishu.entity.KingBaseDgap;
import com.huishu.entity.NewsLibBak;
import com.huishu.entity.SiteLib;
import com.huishu.service.CityLibService;
import com.huishu.service.KingBaseDgapService;
import com.huishu.service.NewsLibBakService;
import com.huishu.service.SiteLibService;
import com.huishu.utils.DateUtils;
import com.huishu.utils.FileUtils;
import com.huishu.vo.DgapData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 分析新闻数据
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("newsAnalyzer")
public class NewsAnalyzer implements Analyzer {

    private static Logger logger = LoggerFactory.getLogger(NewsAnalyzer.class);

    private static final List<DgapData> newsStaticList = new ArrayList<DgapData>();

    @Autowired
    private NewsLibBakService newsLibService;
    @Autowired
    private SiteLibService siteLibService;
    @Autowired
    private CityLibService cityLibService;
    @Autowired
    private KingBaseDgapService kingBaseDgapService;
    @Autowired
    private AnalysisConfig analysisConfig;

    @Override
    public String getName() {
        return SysConst.NEWS;
    }

    @Override
    public void analysis(AnalysisConfig analysisConfig, ThreadPoolExecutor executor, Map<String, String> indexMap) {
        if (analysisConfig.isNewsMark()) {
            for (int i = 0; i < analysisConfig.getNewsThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{}新闻数据分析开始", currentThread.getName(), currentThread.getId());
                    try {
                        analysisNews(analysisConfig, indexMap, pageNumber);
                    } catch (Exception e) {
                        logger.error("新闻数据分析异常", e);
                    }
                    logger.info("{}:{}新闻数据分析结束", currentThread.getName(), currentThread.getId());
                });
            }
        }
    }

    private void analysisNews(AnalysisConfig analysisConfig, Map<String, String> indexMap, int pageNumber) {
        // 1、获取数据 是否重复数据
        NewsLibBak news = new NewsLibBak();
        news.setId(Long.valueOf(indexMap.get(SysConst.NEWS)));
        Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
        List<NewsLibBak> newsList = newsLibService.findOneHundred(news, pageable);

        logger.info("新闻分析,读取 {} 条", newsList.size());

        if (newsList.size() <= 0) {
            return;
        }
        String newId = newsList.get(newsList.size() - 1).getId() + "";
        String oldId = indexMap.get(SysConst.NEWS);
        if (Long.valueOf(newId) > Long.valueOf(oldId)) {
            indexMap.put(SysConst.NEWS, newId);
        }
        List<DgapData> saveList = new ArrayList<DgapData>();
        List<KingBaseDgap> historyList = new ArrayList<KingBaseDgap>();
        List<NewsLibBak> readList = new ArrayList<NewsLibBak>();
        for (NewsLibBak newsLibBak : newsList) {
            if (isNotExists(newsLibBak.getFldUrlAddr())) {
                // 分析
                SiteLib site = siteLibService.findByName(newsLibBak.getWebname());
                SiteLib newSite = fillAreaInfoForSiteLib(newsLibBak.getFldtitle(), newsLibBak.getFldcontent(), site);
                if (newSite != null) {
                    DgapData dgapData = fillDgapData(newsLibBak, newSite);
                    if (dgapData != null) {
                        newsLibBak.setBiaoShi(SysConst.ESDataStatus.EXISTS_IN_ES.getCode());
                        addKingBaseData(historyList, dgapData);
                        dgapData.setId(String.valueOf(newsLibBak.getId()));
                        saveList.add(dgapData);
                        newsStaticList.add(dgapData);
                    }
                }
                if (SysConst.ESDataStatus.NOT_EXISTS_IN_ES.getCode().equals(newsLibBak.getBiaoShi())) {
                    newsLibBak.setBiaoShi(SysConst.ESDataStatus.EXCEPTION.getCode());
                }
                readList.add(newsLibBak);
            }
        }
        if (saveList.size() > 0) {
            saveToFile(saveList, SysConst.NEWS, analysisConfig.getSourceMorePath());
            saveToKingbase(historyList);
        }
        if (readList.size() > 0) {
            newsLibService.save(readList);
        }
        logger.info("新闻分析,入库 {} 条", saveList.size());
        logger.info("新闻分析,分析 {} 条", readList.size());

        recordNum(indexMap);
    }

    /**
     * 根据url判断是否存在重复数据
     *
     * @param url
     * @return
     */
    private boolean isNotExists(String url) {
        boolean flag = true;
        for (DgapData dgapData : newsStaticList) {
            if (StringUtils.isNotEmpty(dgapData.getPolicyUrl())
                    && dgapData.getPolicyUrl().equals(url)) {
                flag = false;
                break;
            }
        }

        return flag;
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
    private SiteLib fillAreaInfoForSiteLib(String title, String content, SiteLib source) {
        SiteLib siteLib = new SiteLib();
        String[] areaArray = analysisConfig.getCommonArea().split(",");
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

    private boolean fillProvinceAndCityInfo(String data, SiteLib siteLib, CityLib city) {
        if (StringUtils.isNotEmpty(data) && data.indexOf(city.getCity()) >= 0) {
            siteLib.setProvince(city.getProvince());
            if (StringUtils.isEmpty(siteLib.getArea())) {
                siteLib.setArea(city.getCity());
            }
            return true;
        }
        return false;
    }

    private boolean fillAreaAndProvinceInfo(String data, SiteLib siteLib, String area) {
        if (StringUtils.isNotEmpty(data) && data.indexOf(area) >= 0) {
            siteLib.setArea(area);
            List<CityLib> cityList = cityLibService.findByCity(area);
            if (cityList != null && cityList.size() > 0) {
                siteLib.setProvince(cityList.get(0).getProvince());
            }
            return true;
        }
        return false;
    }

    private DgapData fillDgapData(NewsLibBak newsLibBak, SiteLib siteLib) {
        if (StringUtils.isEmpty(siteLib.getProvince())
                || StringUtils.isEmpty(siteLib.getIndustry())) {
            return null;
        }
        if (StringUtils.isEmpty(newsLibBak.getFldcontent())
                || StringUtils.isEmpty(newsLibBak.getFldtitle())) {
            return null;
        }
        if (StringUtils.isEmpty(newsLibBak.getFldtitle())) {
            return null;
        }
        if (StringUtils.isEmpty(newsLibBak.getFldUrlAddr())) {
            return null;
        }

        DgapData result = new DgapData();
        // 时间
        if (fillDateInfoOfDgapData(result, newsLibBak.getFldrecddate())) {
            return null;
        }

        result.setPublishType(SysConst.PublishType.NEWS.getCode());
        // 分类
        result.setDataType(SysConst.DataType.POLICY.getCode());
        result.setTime(newsLibBak.getFldrecddate());

        // 站点
        result.setSite(newsLibBak.getWebname());
        // 省份
        result.setProvince(siteLib.getProvince());
        result.setArea(siteLib.getArea());
        // 行业
        result.setIndustry(siteLib.getIndustry());
        result.setSocialChannel(SysConst.SocialChannel.INTERNET_MEDIA.getCode());

        // 是否社交网站
        result.setReportType(SysConst.SiteType.MEDIA.getCode());
        // 是否热点 评论 点击 转发 超过1000
        int count = Integer.valueOf(newsLibBak.getFldHits())
                + Integer.valueOf(newsLibBak.getFldReply());
        //热点事件阈值
        int hotEventThreshold = 1000;
        if (count > hotEventThreshold) {
            result.setHotEventMark(SysConst.HotEventMark.HOT_EVENT.getCode());
        } else {
            result.setHotEventMark(SysConst.HotEventMark.NOT_HOT_EVENT.getCode());
        }
        // 内容 分析 文章 图片 视频
        // 关注量
        result.setHitNum(Long.valueOf(newsLibBak.getFldHits()));

        String emotion = searchEmotion(newsLibBak);
        if (SysConst.Emotion.NEUTRAL.getEmotion().equals(emotion)) {
            result.setEmotionMark(SysConst.Emotion.NEUTRAL.getCode());
        }
        if (SysConst.Emotion.NEGATIVE.getEmotion().equals(emotion)) {
            result.setEmotionMark(SysConst.Emotion.NEGATIVE.getCode());
        }
        if (SysConst.Emotion.POSITIVE.getEmotion().equals(emotion)) {
            result.setEmotionMark(SysConst.Emotion.POSITIVE.getCode());
        }
        searchContentNews(newsLibBak, result);
        // 标题
        result.setPolicyTitle(newsLibBak.getFldtitle());

        result.setContent(com.huishu.utils.StringUtils.removeHtmlTag(newsLibBak.getFldcontent()));
        // url
        result.setPolicyUrl(newsLibBak.getFldUrlAddr());
        // 阅读量
        result.setReadNum(Long.valueOf(newsLibBak.getFldHits()));
        // 评论量
        result.setHitNum(Long.valueOf(newsLibBak.getFldReply()));
        setReportType(result);

        return result;
    }

    private void addKingBaseData(List<KingBaseDgap> historyList, DgapData source) {
        KingBaseDgap target = new KingBaseDgap();
        BeanUtils.copyProperties(source, target);
        target.setId(com.huishu.utils.StringUtils.getUUID());
        historyList.add(target);
    }

    private void saveToFile(List<DgapData> saveList, String type, String filePath) {
        for (DgapData dgapData : saveList) {
            String content = JSONObject.toJSONString(dgapData);
            StringBuilder fileName = new StringBuilder();
            fileName.append("analysis_").append(type).append("_")
                    .append(DateUtils.getDateFilePath(new Date())).append("_")
                    .append(dgapData.getId()).append(".txt");
            File file = new File(filePath + File.separator + fileName);
            FileUtils.createFileIfNotExists(file);
            FileUtils.writeContentToFile(file.getAbsolutePath(), content);
        }
    }

    /**
     * 保存分析后的数据到人大金仓数据库
     *
     * @param historyList
     */
    private void saveToKingbase(List<KingBaseDgap> historyList) {
        kingBaseDgapService.save(historyList);
        if (analysisConfig.isKingSaveMark()) {
            kingBaseDgapService.saveKing(historyList);
        }
    }

    private synchronized void recordNum(Map<String, String> indexMap) {
        String filePath = System.getProperty("user.dir") + "analysis-data-temp.properties";
        FileUtils.createFileIfNotExists(new File(filePath));
        FileUtils.writeProperties(filePath, indexMap);
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
     * 筛选行业
     *
     * @param industry                         行业
     * @param industryKeywordsOccurrenceNumber 行业关键词的出现次数
     * @param jsonObject                       行业关键词出现次数最高的行业的信息
     * @return 行业关键词出现次数最高的行业
     */
    private void filterIndustry(String industry, int industryKeywordsOccurrenceNumber,
                                JSONObject jsonObject) {
        if (industryKeywordsOccurrenceNumber > jsonObject.getIntValue("count")) {
            jsonObject.put("industry", industry);
        }
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
        JSONArray tourismKeyWordArray = com.huishu.utils.StringUtils.split(industryKeyWords, ",");
        if (StringUtils.isNotEmpty(title)) {
            count += com.huishu.utils.StringUtils.countOccurrenceNumber(tourismKeyWordArray, title);
        }
        if (StringUtils.isNotEmpty(content)) {
            count += com.huishu.utils.StringUtils.countOccurrenceNumber(tourismKeyWordArray, content);
        }

        return count;
    }

    /**
     * 使用 fldrecddate 对象填充 dgapData 对象的时间信息，包括年、月、日、小时
     *
     * @param dgapData
     * @param fldrecddate
     * @return
     */
    private boolean fillDateInfoOfDgapData(DgapData dgapData, String fldrecddate) {
        try {
            if (StringUtils.isEmpty(fldrecddate)) {
                return true;
            }
            fldrecddate = com.huishu.utils.StringUtils.transformTime(fldrecddate);
            dgapData.setHour(0L);
            int yearIndex = fldrecddate.indexOf("-");
            int monthIndex = fldrecddate.indexOf("-", yearIndex + 1);
            int sIndex = fldrecddate.indexOf(" ", monthIndex + 1);
            int hourIndex = fldrecddate.indexOf(":");
            if (yearIndex <= 0) {
                return true;
            }
            dgapData.setYear(Long.valueOf(fldrecddate.substring(0, yearIndex).trim()));
            if (monthIndex <= 0) {
                return true;
            }
            dgapData.setMonth(Long.valueOf(fldrecddate.substring(yearIndex + 1, monthIndex).trim()));
            if (sIndex > 0) {
                dgapData.setDay(Long.valueOf(fldrecddate.substring(monthIndex + 1, sIndex).trim()));
                if (hourIndex > 0) {
                    dgapData.setHour(Long.valueOf(fldrecddate.substring(sIndex + 1, hourIndex).trim()));
                }
            } else {
                dgapData.setDay(Long.valueOf(fldrecddate.substring(monthIndex + 1, fldrecddate.length()).trim()));
            }
        } catch (NumberFormatException e) {
            logger.error("日期[{}]转换错误：{}", fldrecddate, e);
        }
        return false;
    }

    private String searchEmotion(NewsLibBak single) {
//		String articleCategory = SCArticleCategory.articleCategory(
//				single.getFldtitle(), single.getFldcontent());
//		return articleCategory;
        return "";
    }

    // 分析内容类型
    private void searchContentNews(NewsLibBak newsLibBak, DgapData dgapData) {
        // 内容 分析 文章 图片 视频
        // <img
        // src="http://i.ce.cn/ce/cysc/yq/dt/201703/10/W020170310339994179672.jpg">
        String content = newsLibBak.getFldcontent();
        if (StringUtils.isNotEmpty(content)) {
            return;
        }

        // 报道量 双创 创新 创业
        if (content.indexOf("双创") >= 0 || content.indexOf("创新") >= 0 || content.indexOf("创业") >= 0) {
            dgapData.setReportNum(1L);
        }

        String channelName = newsLibBak.getPdmc();
        // 类型 中央 地方 法院
        if ("中华人民共和国中央人民政府".equals(newsLibBak.getWebname())) {
            dgapData.setPublishType(SysConst.PublishType.LOCAL.getCode());
            dgapData.setHotEventMark(SysConst.HotEventMark.HOT_EVENT.getCode());
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
                if (StringUtils.isNotEmpty(channelName) && StringUtils.isNotEmpty(centerDepartment)) {
                    JSONArray centerDepartmentArray = com.huishu.utils.StringUtils.split(centerDepartment, ",");
                    for (int i = 0, size = centerDepartmentArray.size(); i < size; i++) {
                        String centerDepartmentItem = centerDepartmentArray.getString(i);
                        if (centerDepartmentItem.equals(channelName)) {
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

            analysisPicture(newsLibBak.getFldUrlAddr(), content, channelName, dgapData);
        }
    }

    private void analysisPicture(String url, String content, String pdmc, DgapData data) {
        if (content.indexOf("<img") < 0) {
            return;
        }

        // 图片
        long infoType = 1L;
        data.setPolicyInfoType(infoType);
        if ("双创动态".equals(pdmc.trim())) {
            JSONArray pictureSuffixArray = com.huishu.utils.StringUtils.split(analysisConfig.getPictureSuffix(), ",");
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
    }

    private void setImageUrl(DgapData dgapData, long infoType, String imageUrl, String url) {
        if (imageUrl.startsWith("http") && imageUrl.length() <= 150) {
            boolean imageFlag = true;
            String invalidImageUrl = analysisConfig.getInvalidImageurl();
            JSONArray invalidImageUrlArray = com.huishu.utils.StringUtils.split(invalidImageUrl, ",");
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

    private void setReportType(DgapData dgapData) {
        JSONArray socialSiteArray = com.huishu.utils.StringUtils.split(analysisConfig.getSocialSite(), ",");
        for (int i = 0, size = socialSiteArray.size(); i < size; i++) {
            if (dgapData.getSite().equals(socialSiteArray.getString(i))) {
                dgapData.setReportType(SysConst.SiteType.SOCIAL.getCode());
                break;
            }
        }
    }

}
