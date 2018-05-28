package com.huishu.analysis.impl;

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
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 分析新闻数据
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("newsAnalyzer")
public class NewsAnalyzer implements Analyzer {

    private static Logger logger = LoggerFactory.getLogger(NewsAnalyzer.class);

    private static List<DgapData> newsStaticList = new ArrayList<DgapData>();

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
    private Map<String, String> indexMap;

    @Override
    public String getName() {
        return SysConst.NEWS;
    }

    @Override
    public void analysis(AnalysisConfig analysisConfig, ThreadPoolExecutor executor, Map<String, String> indexMap) {
        this.indexMap = indexMap;
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

        toRecordNum();
    }

    /**
     * 根据url判断是否存在重复数据
     * @param url
     * @return
     */
    private boolean isNotExists(String url){
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
        //
        if (StringUtils.isEmpty(siteLib.getProvince())) {
            //String str = "北京,上海,重庆,天津,河北,山西,辽宁,吉林,黑龙江,江苏,浙江,安徽,福建,江西,山东,河南,湖北,湖南,广东,海南,四川,贵州,云南,陕西,甘肃,青海,台湾,广西,宁夏,西藏,新疆,内蒙古,香港,澳门";
            // String[] provinces = str.split(",");
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
        if(source != null){
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
        DgapData result = new DgapData();

        result.setPublishType(SysConst.PublishType.NEWS.getCode());
        // 分类
        result.setDataType(SysConst.DataType.POLICY.getCode());
        String singleData = newsLibBak.getFldrecddate();
        result.setTime(newsLibBak.getFldrecddate());
        // 时间
        if (toSetTime(result, singleData)) {
            return null;
        }
        // 站点
        result.setSite(newsLibBak.getWebname());
        // 省份
        if (StringUtils.isEmpty(siteLib.getProvince())
                || StringUtils.isEmpty(siteLib.getIndustry())) {
            return null;
        }
        result.setProvince(siteLib.getProvince());
        result.setArea(siteLib.getArea());
        // 行业
        result.setIndustry(siteLib.getIndustry());
        result.setSocialChannel(SysConst.SocialChannel.INTERNET_MEDIA.getCode());

        // 是否社交网站
        result.setReportType((long) SysConst.SITE_TYPE_MEDIA);
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
        if (StringUtils.isEmpty(newsLibBak.getFldcontent())
                || StringUtils.isEmpty(newsLibBak.getFldtitle())) {
            return null;
        }
        String articleCategory = searchEmotionNews(newsLibBak);
        if (SysConst.Emotion.NEUTRAL.getEmotion().equals(articleCategory)) {
            result.setEmotionMark(SysConst.Emotion.NEUTRAL.getCode());
        }
        if (SysConst.Emotion.NEGATIVE.getEmotion().equals(articleCategory)) {
            result.setEmotionMark(SysConst.Emotion.NEGATIVE.getCode());
        }
        if (SysConst.Emotion.POSITIVE.getEmotion().equals(articleCategory)) {
            result.setEmotionMark(SysConst.Emotion.POSITIVE.getCode());
        }
        searchContentNews(newsLibBak, result);
        // 标题
        if (StringUtils.isEmpty(newsLibBak.getFldtitle())) {
            return null;
        }
        result.setPolicyTitle(newsLibBak.getFldtitle());

        result.setContent(removeTag(newsLibBak.getFldcontent()));
        // url
        if (StringUtils.isEmpty(newsLibBak.getFldUrlAddr())) {
            return null;
        }
        result.setPolicyUrl(newsLibBak.getFldUrlAddr());
        // 阅读量
        result.setReadNum(Long.valueOf(newsLibBak.getFldHits()));
        // 评论量
        result.setHitNum(Long.valueOf(newsLibBak.getFldReply()));
        setDataType(result);

        return result;
    }

    private void addKingBaseData(List<KingBaseDgap> historyList, DgapData source) {
        KingBaseDgap target = new KingBaseDgap();
        BeanUtils.copyProperties(source, target);
        target.setId(UUID.randomUUID().toString());
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
     * @param historyList
     */
    private void saveToKingbase(List<KingBaseDgap> historyList) {
        kingBaseDgapService.save(historyList);
        if (analysisConfig.isKingSaveMark()) {
            kingBaseDgapService.saveKing(historyList);
        }
    }

    private synchronized void toRecordNum() {
        String filePath = System.getProperty("user.dir") + "analysis-data-temp.properties";
        FileUtils.createFileIfNotExists(new File(filePath));
        FileUtils.writeProperties(filePath, indexMap);
    }

    // 分析行业
    private String searchIndustry(String title, String content) {
        // 根据内容分析行业 如果没有则查询网站配置行业
        String tempIndustry = "";
        int max = 0;
        // 互联网
        int internetCount = 0;
        String internetKeyWords = analysisConfig.getInternetKeyWords();
        String[] internetWords = internetKeyWords.split(",");
        if (internetWords != null && internetWords.length > 0) {
            for (String word : internetWords) {
                if (StringUtils.isNotEmpty(word)) {
                    if (StringUtils.isNotEmpty(title)
                            && title.indexOf(word) >= 0) {
                        internetCount += 1;
                    }
                    if (StringUtils.isNotEmpty(content)
                            && content.indexOf(word) >= 0) {
                        internetCount += 1;
                    }
                }
            }
        }
        max = internetCount;
        tempIndustry = SysConst.INDUSTRY_TYPE_INTERNET;
        // 金融
        int financeCount = 0;
        String financeKeyWords = analysisConfig.getFinanceKeyWords();
        String[] financeWords = financeKeyWords.split(",");
        if (financeWords != null && financeWords.length > 0) {
            for (String word : financeWords) {
                if (StringUtils.isNotEmpty(word)) {
                    if (StringUtils.isNotEmpty(title)
                            && title.indexOf(word) >= 0) {
                        financeCount += 1;
                    }
                    if (StringUtils.isNotEmpty(content)
                            && content.indexOf(word) >= 0) {
                        financeCount += 1;
                    }
                }
            }
        }
        if (max < financeCount) {
            max = financeCount;
            tempIndustry = SysConst.INDUSTRY_TYPE_FINANCE;
        }
        // 教育
        int educationCount = 0;
        String educationKeyWords = analysisConfig.getEducationKeyWords();
        String[] educationWords = educationKeyWords.split(",");
        if (educationWords != null && educationWords.length > 0) {
            for (String word : educationWords) {
                if (StringUtils.isNotEmpty(word)) {
                    if (StringUtils.isNotEmpty(title)
                            && title.indexOf(word) >= 0) {
                        educationCount += 1;
                    }
                    if (StringUtils.isNotEmpty(content)
                            && content.indexOf(word) >= 0) {
                        educationCount += 1;
                    }
                }
            }
        }
        if (max < educationCount) {
            max = educationCount;
            tempIndustry = SysConst.INDUSTRY_TYPE_EDUCATION;
        }
        // 交通
        int trafficCount = 0;
        String trafficKeyWords = analysisConfig.getTrafficKeyWords();
        String[] trafficWords = trafficKeyWords.split(",");
        if (trafficWords != null && trafficWords.length > 0) {
            for (String word : trafficWords) {
                if (StringUtils.isNotEmpty(word)) {
                    if (StringUtils.isNotEmpty(title)
                            && title.indexOf(word) >= 0) {
                        trafficCount += 1;
                    }
                    if (StringUtils.isNotEmpty(content)
                            && content.indexOf(word) >= 0) {
                        trafficCount += 1;
                    }
                }
            }
        }
        if (max < trafficCount) {
            max = trafficCount;
            tempIndustry = SysConst.INDUSTRY_TYPE_TRAFFIC;
        }
        // 旅游
        int tourismCount = 0;
        String tourismKeyWords = analysisConfig.getTourismKeyWords();
        String[] tourismWords = tourismKeyWords.split(",");
        if (tourismWords != null && tourismWords.length > 0) {
            for (String word : tourismWords) {
                if (StringUtils.isNotEmpty(word)) {
                    if (StringUtils.isNotEmpty(title)
                            && title.indexOf(word) >= 0) {
                        tourismCount += 1;
                    }
                    if (StringUtils.isNotEmpty(content)
                            && content.indexOf(word) >= 0) {
                        tourismCount += 1;
                    }
                }
            }
        }
        if (max < tourismCount) {
            max = tourismCount;
            tempIndustry = SysConst.INDUSTRY_TYPE_TOURISM;
        }
        return tempIndustry;
    }

    private boolean toSetTime(DgapData data, String singleData) {
        try {
            if (StringUtils.isNotEmpty(singleData)) {
                singleData = com.huishu.utils.StringUtils.toTransformTime(singleData);
                data.setHour(0L);
                int yearindex = singleData.indexOf("-");
                int monthIndex = singleData.indexOf("-", yearindex + 1);
                int sIndex = singleData.indexOf(" ", monthIndex + 1);
                int hourIndex = singleData.indexOf(":");
                if (yearindex > 0) {
                    data.setYear(Long.valueOf(singleData
                            .substring(0, yearindex).trim()));
                    if (monthIndex > 0) {
                        data.setMonth(Long.valueOf(singleData.substring(
                                yearindex + 1, monthIndex).trim()));
                        if (sIndex > 0) {
                            data.setDay(Long.valueOf(singleData.substring(
                                    monthIndex + 1, sIndex).trim()));
                            if (hourIndex > 0) {
                                data.setHour(Long.valueOf(singleData.substring(
                                        sIndex + 1, hourIndex).trim()));
                            }
                        } else {
                            data.setDay(Long.valueOf(singleData.substring(
                                    monthIndex + 1, singleData.length()).trim()));
                        }
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            } else {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("日期转换错误：" + singleData, e);
            return false;
        }
    }

    private String searchEmotionNews(NewsLibBak single) {
//		String articleCategory = SCArticleCategory.articleCategory(
//				single.getFldtitle(), single.getFldcontent());
//		return articleCategory;
        return "";
    }

    // 分析内容类型
    private void searchContentNews(NewsLibBak single, DgapData data) {
        // 内容 分析 文章 图片 视频
        // <img
        // src="http://i.ce.cn/ce/cysc/yq/dt/201703/10/W020170310339994179672.jpg">
        if (StringUtils.isNotEmpty(single.getFldcontent())) {
            // 类型 中央 地方 法院
            if ("中华人民共和国中央人民政府".equals(single.getWebname())) {
                data.setPublishType((long) SysConst.PUBLISH_TYPE_POLICY);
                data.setHotEventMark(1l);
                String fldcontent = single.getFldcontent();
                if (StringUtils.isNotEmpty(single.getPdmc()) && ("国务院文件").equals(single.getPdmc())) {
                    data.setPublishType((long) SysConst.PUBLISH_TYPE_CENTER);
                    int start = fldcontent.indexOf("<b>发文字号：</b>");
                    int end = fldcontent.indexOf("<b>发布日期：</b>");
                    // 发文字号
                    data.setPolicyPostShopName(fldcontent.substring(start + 12,
                            end));
                    data.setPolicyReleaseMechanism("国务院文件");
                    data.setPolicyPublishAuthor("国务院");
                } else {
                    // 是否是部委发文件
                    String centerDepartment = analysisConfig.getCenterDepartment();
                    if (StringUtils.isNotEmpty(single.getPdmc()) && StringUtils.isNotEmpty(centerDepartment)) {
                        String[] split = centerDepartment.split(",");
                        for (String str : split) {
                            if (str.equals(single.getPdmc())) {
                                int first = fldcontent.indexOf("〔");
                                if (first == -1) {
                                    first = fldcontent.indexOf("[");
                                }
                                if (first >= 0) {
                                    int end = fldcontent.indexOf("号", first);
                                    int start = fldcontent.lastIndexOf(">",
                                            first);
                                    if (end >= 0) {
                                        if (end - start <= 25) {
                                            data.setPublishType((long) SysConst.PUBLISH_TYPE_CENTER);
                                            data.setPolicyPostShopName(fldcontent
                                                    .substring(start + 1,
                                                            end + 1));
                                            data.setPolicyReleaseMechanism("部委文件");
                                            data.setPolicyPublishAuthor(single
                                                    .getPdmc());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                toAnalysisPicture(single.getFldUrlAddr(), single.getFldtitle(), single.getFldcontent(), single.getPdmc(), data);
            }

            // 报道量 双创 创新 创业
            if (single.getFldcontent().indexOf("双创") >= 0
                    || single.getFldcontent().indexOf("创新") >= 0
                    || single.getFldcontent().indexOf("创业") >= 0) {
                data.setReportNum(1L);
            }

        }
    }

    private synchronized void toAnalysisPicture(String url, String title, String content, String pdmc, DgapData data) {
        // 图片
        long infoType = 1l;
        data.setPolicyInfoType(infoType);
        if (StringUtils.isNotEmpty(pdmc) && "双创动态".equals(pdmc.trim())) {
            /*(title.indexOf("双创") >= 0 || title.indexOf("创新") >= 0 || title
			.indexOf("创业") >= 0) &&*/
            if (content.indexOf("<img") >= 0) {
                String pictureSuffix = analysisConfig.getPictureSuffix();
                if (StringUtils.isNotEmpty(pictureSuffix)) {
                    String[] split = pictureSuffix.split(",");
                    for (String suffix : split) {
                        String tempSuffix = suffix.toLowerCase();
                        int end = content.indexOf(tempSuffix);
                        if (end == -1) {
                            tempSuffix = suffix.toUpperCase();
                            end = content.indexOf(tempSuffix);
                        }
                        if (end >= 0) {
                            infoType = 2l;
                            int start = content.lastIndexOf("src=", end);
                            if (start >= 0) {
                                String imageurl = content.substring(start + 5,
                                        end + suffix.length());
                                toSetImagUrl(data, infoType, imageurl, url);
                            }
                            break;
                        }
                    }
                }
            }

        }
    }

    private void toSetImagUrl(DgapData data, long infoType, String imageurl, String url) {
        if (imageurl.startsWith("http") && imageurl.length() <= 150) {
            boolean imageFlag = true;
            String invalidImageurl = analysisConfig.getInvalidImageurl();
            if (StringUtils.isNotEmpty(invalidImageurl)) {
                String[] split2 = invalidImageurl.split(",");
                for (int x = 0; x < split2.length; x++) {
                    if (split2[x].indexOf(imageurl) >= 0) {
                        imageFlag = false;
                        break;
                    }
                }
            }
            if (imageFlag) {
                data.setPolicyInfoType(infoType);
                data.setPolicyImageUrl(imageurl);
            }
        } else {
            data.setPolicyInfoType(infoType);
            data.setPolicyImageUrl(url.substring(0, url.lastIndexOf("/") + 1) + imageurl);
        }
    }

    public static String removeTag(String htmlStr) {
        if (StringUtils.isNotEmpty(htmlStr)) {
            String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // script
            String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // style
            String regEx_html = "<[^>]+>"; // HTML tag
            String regEx_space = "\\s+|\t|\r|\n";// other characters

            Pattern p_script = Pattern.compile(regEx_script,
                    Pattern.CASE_INSENSITIVE);
            Matcher m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll("");
            Pattern p_style = Pattern
                    .compile(regEx_style, Pattern.CASE_INSENSITIVE);
            Matcher m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll("");
            Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            Matcher m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll("");
            Pattern p_space = Pattern
                    .compile(regEx_space, Pattern.CASE_INSENSITIVE);
            Matcher m_space = p_space.matcher(htmlStr);
            htmlStr = m_space.replaceAll(" ");
        }
        return htmlStr;
    }

    private void setDataType(DgapData dgap) {
        // 人人网 豆瓣 爱哈友 社交类
        if (StringUtils.isNotEmpty(analysisConfig.getSocialSite())) {
            String[] split = analysisConfig.getSocialSite().split(",");
            for (String site : split) {
                if (dgap.getSite().equals(site)) {
                    dgap.setReportType(2L);
                    break;
                }
            }
        }
    }

}
