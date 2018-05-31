package com.huishu.analysis.impl;

import com.huishu.config.AnalysisConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.KingBaseDgap;
import com.huishu.entity.NewsLibBak;
import com.huishu.entity.SiteLib;
import com.huishu.service.NewsLibBakService;
import com.huishu.service.SiteLibService;
import com.huishu.vo.DgapData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
public class NewsAnalyzer extends DefaultAnalyzer {

    private static final List<DgapData> newsStaticList = new ArrayList<DgapData>();

    @Autowired
    private NewsLibBakService newsLibService;
    @Autowired
    private SiteLibService siteLibService;

    @Override
    public String getName() {
        return SysConst.NEWS;
    }

    @Override
    public void analysis(AnalysisConfig analysisConfig, ThreadPoolExecutor executor, Map<String, String> indexMap) {
        newsStaticList.clear();
        if (analysisConfig.isNewsMark()) {
            for (int i = 0; i < analysisConfig.getNewsThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{}新闻数据分析开始", currentThread.getName(), currentThread.getId());
                    try {
                        analysisData(analysisConfig, indexMap, pageNumber);
                    } catch (Exception e) {
                        logger.error("新闻数据分析异常", e);
                    }
                    logger.info("{}:{}新闻数据分析结束", currentThread.getName(), currentThread.getId());
                });
            }
        }
    }

    /**
     * 分析数据
     * @param analysisConfig
     * @param indexMap
     * @param pageNumber
     */
    private void analysisData(AnalysisConfig analysisConfig, Map<String, String> indexMap, int pageNumber) {
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
        for (NewsLibBak item : newsList) {
            if (isNotExists(item.getFldUrlAddr())) {
                // 分析
                SiteLib site = siteLibService.findByName(item.getWebname());
                SiteLib newSite = fillAreaInfoForSiteLib(item.getFldtitle(), item.getFldcontent(), site);
                if (newSite != null) {
                    DgapData dgapData = fillDgapData(item, newSite);
                    if (dgapData != null) {
                        item.setBiaoShi(SysConst.ESDataStatus.EXISTS_IN_ES.getCode());
                        addKingBaseData(historyList, dgapData);
                        dgapData.setId(String.valueOf(item.getId()));
                        saveList.add(dgapData);
                        newsStaticList.add(dgapData);
                    }
                }
                if (SysConst.ESDataStatus.NOT_EXISTS_IN_ES.getCode().equals(item.getBiaoShi())) {
                    item.setBiaoShi(SysConst.ESDataStatus.EXCEPTION.getCode());
                }
                readList.add(item);
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
        for (DgapData item : newsStaticList) {
            if (StringUtils.isNotEmpty(item.getPolicyUrl())
                    && item.getPolicyUrl().equals(url)) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    private DgapData fillDgapData(NewsLibBak newsLibBak, SiteLib siteLib) {
        if (!validate(newsLibBak, siteLib)) {
            return null;
        }

        DgapData result = new DgapData();
        // 时间
        fillDateInfoOfDgapData(result, newsLibBak.getFldrecddate());

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
        if (count > SysConst.HOT_EVENT_THRESHOLD) {
            result.setHotEventMark(SysConst.HotEventMark.HOT_EVENT.getCode());
        } else {
            result.setHotEventMark(SysConst.HotEventMark.NOT_HOT_EVENT.getCode());
        }
        // 内容 分析 文章 图片 视频
        // 关注量
        result.setHitNum(Long.valueOf(newsLibBak.getFldHits()));

        String emotion = searchEmotion(newsLibBak.getFldtitle(), newsLibBak.getFldcontent());
        if (SysConst.Emotion.NEUTRAL.getEmotion().equals(emotion)) {
            result.setEmotionMark(SysConst.Emotion.NEUTRAL.getCode());
        }
        if (SysConst.Emotion.NEGATIVE.getEmotion().equals(emotion)) {
            result.setEmotionMark(SysConst.Emotion.NEGATIVE.getCode());
        }
        if (SysConst.Emotion.POSITIVE.getEmotion().equals(emotion)) {
            result.setEmotionMark(SysConst.Emotion.POSITIVE.getCode());
        }
        fillPolicyInfo(newsLibBak.getFldcontent(), newsLibBak.getWebname(), newsLibBak.getPdmc(),
                newsLibBak.getFldUrlAddr(), result);
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

    private boolean validate(NewsLibBak newsLibBak, SiteLib siteLib){
        if (StringUtils.isEmpty(siteLib.getProvince())
                || StringUtils.isEmpty(siteLib.getIndustry())) {
            return false;
        }

        String fldrecddate = newsLibBak.getFldrecddate();
        if (StringUtils.isEmpty(newsLibBak.getFldcontent())
                || StringUtils.isEmpty(newsLibBak.getFldtitle())
                || StringUtils.isEmpty(newsLibBak.getFldUrlAddr())
                || StringUtils.isEmpty(fldrecddate)) {
            return false;
        }

        int yearIndex = fldrecddate.indexOf("-");
        if (yearIndex <= 0) {
            return false;
        }
        int monthIndex = fldrecddate.indexOf("-", yearIndex + 1);
        if (monthIndex <= 0) {
            return false;
        }

        return true;
    }

}