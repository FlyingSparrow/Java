package com.jdjr.opinion.mongodb.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jdjr.opinion.base.bean.PageResult;
import com.jdjr.opinion.constants.SysConst;
import com.jdjr.opinion.mongodb.dao.MongodbSearchDao;
import com.jdjr.opinion.mongodb.entity.*;
import com.jdjr.opinion.mongodb.repository.EventRepository;
import com.jdjr.opinion.mongodb.repository.OpinionDetailsSnapshotRepository;
import com.jdjr.opinion.mongodb.service.OpinionDetailsSnapshotService;
import com.jdjr.opinion.utils.DateUtils;
import com.jdjr.opinion.utils.NumberUtils;
import com.jdjr.opinion.utils.OpinionAnalysisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * <p>Title: OpinionDetailsSnapshotRepository</p>
 * <p>Description: 舆情明细快照接口实现类</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
@Service
public class OpinionDetailsSnapshotServiceImpl implements OpinionDetailsSnapshotService {

    @Autowired
    private OpinionDetailsSnapshotRepository opinionDetailsSnapshotRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MongodbSearchDao mongodbSearchDao;

    @Override
    public String save(OpinionDetailsSnapshot entity) {
        OpinionDetailsSnapshot opinionDetailsSnapshot = opinionDetailsSnapshotRepository.save(entity);
        return opinionDetailsSnapshot.getId();
    }

    @Override
    public boolean isExistByEnterpriseId(String enterpriseId) {
        long count = opinionDetailsSnapshotRepository.countByEnterpriseId(enterpriseId);
        return count > 0;
    }

    @Override
    public OpinionDetailsSnapshot findByEnterpriseIdAndArticleId(String enterpriseId, String articleId) {
        return opinionDetailsSnapshotRepository.findByEnterpriseIdAndArticleId(enterpriseId, articleId);
    }

    @Override
    public OpinionDetailsSnapshot findByEnterpriseIdAndTitleArticleFingerprint(String enterpriseId, String titleArticleFingerprint) {
        return opinionDetailsSnapshotRepository.findByEnterpriseIdAndTitleArticleFingerprint(
                enterpriseId, titleArticleFingerprint);
    }

    @Override
    public OpinionDetailsSnapshot findByEnterpriseIdAndContentArticleFingerprint(String enterpriseId, String contentArticleFingerprint) {
        return opinionDetailsSnapshotRepository.findByEnterpriseIdAndContentArticleFingerprint(enterpriseId, contentArticleFingerprint);
    }

    @Override
    public boolean isExistByEnterpriseIdAndTitleArticleFingerprint(String enterpriseId, String titleArticleFingerprint) {
        OpinionDetailsSnapshot entity = this.findByEnterpriseIdAndTitleArticleFingerprint(enterpriseId, titleArticleFingerprint);
        return (entity != null);
    }

    @Override
    public boolean isExistByEnterpriseIdAndContentArticleFingerprint(String enterpriseId, String contentArticleFingerprint) {
        OpinionDetailsSnapshot entity = this.findByEnterpriseIdAndContentArticleFingerprint(enterpriseId, contentArticleFingerprint);
        return (entity != null);
    }

    @Override
    public OpinionDetailsSnapshot findById(String id) {
        return opinionDetailsSnapshotRepository.findOne(id);
    }

    @Override
    public Long findCountByEntity(OpinionDetailsSnapshot entity) {
        return mongodbSearchDao.findOpinionDetailsSnapshotCountByEntity(entity);
    }

    @Override
    public boolean updateArticleType(String articleType, String id) {
        OpinionDetailsSnapshot ods = opinionDetailsSnapshotRepository.findOne(id);
        ods.setArticleType(articleType);
        Integer judgeNumber = NumberUtils.defaultInt(ods.getJudgeNumber());
        ods.setJudgeNumber(judgeNumber + 1);
        ods.setJudgeType(SysConst.JudgeType.PEOPLE.getName());
        ods.setJudger(SysConst.DEFAULT_USER_ACCOUNT);
        opinionDetailsSnapshotRepository.save(ods);
        return true;
    }

    @Override
    public List<OpinionDetailsSnapshot> findListByEntity(OpinionDetailsSnapshot entity) {
        return mongodbSearchDao.findListOpinionDetailsSnapshotByEntity(entity);
    }

    @Override
    public List<OpinionDetailsSnapshot> findListByArticleId(String articleId) {
        return opinionDetailsSnapshotRepository.findByArticleIdOrderByPublishDatetimeDesc(articleId);
    }

    @Override
    public PageResult<OpinionDetailsSnapshot> findPageByEntity(OpinionDetailsSnapshot entity, Set<String> articleIdList) {
        return mongodbSearchDao.findPageOpinionDetailsSnapshotByEntity(entity, articleIdList);
    }

    @Override
    public PageResult<OpinionDetailsSnapshot> findPageByEntity(OpinionDetailsSnapshot entity) {
        return mongodbSearchDao.findPageOpinionDetailsSnapshotByEntity(entity);
    }

    @Override
    public JSONObject findEventTrend(OpinionDetailsSnapshot entity) {
        return mongodbSearchDao.findEventTrend(entity);
    }

    @Override
    public JSONArray findEventPropagation(String eventId) {
        return mongodbSearchDao.findEventPropagation(eventId);
    }

    @Override
    public JSONObject findMediaPropagation(String eventId) {
        return mongodbSearchDao.findMediaPropagation(eventId);
    }

    @Override
    public JSONObject findKeywordEvolution(String eventId) {
        return mongodbSearchDao.findKeywordEvolution(eventId);
    }

    @Override
    public JSONObject findViewpointAnalysis(String eventId) {
        return mongodbSearchDao.findViewpointAnalysis(eventId);
    }

    @Override
    public JSONObject findViewpointAnalysisWordCloud(String eventId) throws Exception {
        JSONObject result = new JSONObject();
        result.put("negativeKeywords", "");
        result.put("positiveKeywords", "");
        EventInfo eventInfo = eventRepository.findOne(eventId);
        if (eventInfo != null) {
            Set<String> articleIdSet = eventInfo.getArticleIdList();
            if (articleIdSet == null) {
                articleIdSet = Sets.newHashSet();
            }
            articleIdSet.add(eventInfo.getCoreArticleId());

            List<Article> articleList = mongodbSearchDao.findArticleListByIds(articleIdSet);
            if (articleList != null) {
                List<String> list = Lists.newArrayList();
                for (Article item : articleList) {
                    list.add(item.getTitle());
                    list.add(item.getContent());
                }
                String positive = SysConst.EmotionType.POSITIVE.getCode();
                String negative = SysConst.EmotionType.NEGATIVE.getCode();
                int startIndex = 0;
                int keywordCount = 20;

                JSONObject positiveKeywords = OpinionAnalysisUtils.getKeywordCloud(list, positive,
                        startIndex, keywordCount);
                JSONObject negativeKeywords = OpinionAnalysisUtils.getKeywordCloud(list, negative,
                        startIndex, keywordCount);

                result.put("positiveKeywords", positiveKeywords);
                result.put("negativeKeywords", negativeKeywords);
            }
        }

        return result;
    }

    @Override
    public JSONObject findNewsList(String eventId) {
        return mongodbSearchDao.findNewsList(eventId);
    }

    @Override
    public boolean batchAdd(List<OpinionDetailsSnapshot> list) {
        opinionDetailsSnapshotRepository.save(list);
        return true;
    }

    @Override
    public List<OpinionDetailsSnapshot> findListByEnterpriseIdListLimit(List<String> enterpriseIdList, Integer limit) {
        return mongodbSearchDao.findListByEnterpriseIdListLimit(enterpriseIdList, limit);
    }

    @Override
    public long findCount() {
        return opinionDetailsSnapshotRepository.count();
    }

    @Override
    public boolean delete(String id) {
        opinionDetailsSnapshotRepository.delete(id);
        return true;
    }

    @Override
    public List<String> findIdList(int pageNumber, int pageSize) {
        Pageable pageable = new PageRequest(pageNumber - 1, pageSize);
        Page<OpinionDetailsSnapshot> page = opinionDetailsSnapshotRepository.findAll(pageable);
        return page.getContent().parallelStream().map(OpinionDetailsSnapshot::getId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteAll() {
        opinionDetailsSnapshotRepository.deleteAll();
        return true;
    }

    @Override
    public List<OpinionDetailsSnapshot> findByEnterpriseIdAndPublishDate(String enterpriseId, String publishDate) {
        return opinionDetailsSnapshotRepository.findByEnterpriseIdAndPublishDate(
                enterpriseId, publishDate);
    }

    @Override
    public JSONObject findOpinionCountInfo(String enterpriseId, String publishDate) {
        List<OpinionDetailsSnapshot> odsList = opinionDetailsSnapshotRepository.findByEnterpriseIdAndPublishDate(
                enterpriseId, publishDate);
        return getOpinionCountInfo(odsList);
    }

    @Override
    public JSONObject findOpinionCountInfo(JSONArray enterpriseIdList, List<String> publishDateList) {
        JSONObject result = new JSONObject();
        if(enterpriseIdList != null){
            List<OpinionDetailsSnapshot> opinionDetailsSnapshotList = mongodbSearchDao.findListByEnterpriseIdListAndPublishDateList(
                            enterpriseIdList, publishDateList);
            Map<String, List<OpinionDetailsSnapshot>> map = opinionDetailsSnapshotList.parallelStream().collect(
                    groupingBy(OpinionDetailsSnapshot::getEnterpriseId));
            for(Map.Entry<String, List<OpinionDetailsSnapshot>> entry : map.entrySet()){
                String enterpriseId = entry.getKey();
                List<OpinionDetailsSnapshot> odsList = entry.getValue();
                OpinionCountInfo countInfo = getOpinionCountInfo2(odsList);
                countInfo.setEnterpriseId(enterpriseId);
                result.put(enterpriseId, countInfo);
            }
        }
        return result;
    }

    private JSONObject getOpinionCountInfo(List<OpinionDetailsSnapshot> odsList) {
        JSONObject result = new JSONObject();
        //今日舆情总数
        int totalOpinionCount = 0;
        //今日负面舆情数
        int negativeOpinionCount = 0;
        //今日正面舆情数
        int positiveOpinionCount = 0;
        for (OpinionDetailsSnapshot item : odsList) {
            totalOpinionCount++;
            if (SysConst.EmotionType.POSITIVE.getCode().equals(item.getEmotion())) {
                positiveOpinionCount++;
            } else if (SysConst.EmotionType.NEGATIVE.getCode().equals(item.getEmotion())) {
                negativeOpinionCount++;
            }
        }

        result.put("totalOpinionCount", totalOpinionCount);
        result.put("negativeOpinionCount", negativeOpinionCount);
        result.put("positiveOpinionCount", positiveOpinionCount);

        return result;
    }

    private OpinionCountInfo getOpinionCountInfo2(List<OpinionDetailsSnapshot> odsList) {
        OpinionCountInfo result = new OpinionCountInfo();
        //今日舆情总数
        int totalOpinionCount = 0;
        //今日负面舆情数
        int negativeOpinionCount = 0;
        //今日正面舆情数
        int positiveOpinionCount = 0;
        for (OpinionDetailsSnapshot item : odsList) {
            totalOpinionCount++;
            if (SysConst.EmotionType.POSITIVE.getCode().equals(item.getEmotion())) {
                positiveOpinionCount++;
            } else if (SysConst.EmotionType.NEGATIVE.getCode().equals(item.getEmotion())) {
                negativeOpinionCount++;
            }
        }

        result.setTotalOpinionCount(totalOpinionCount);
        result.setNegativeOpinionCount(negativeOpinionCount);
        result.setPositiveOpinionCount(positiveOpinionCount);

        return result;
    }

    @Override
    public JSONObject findOpinionCountByEnterpriseIdListAndLast30Days(JSONArray enterpriseIdList) {
        JSONObject result = new JSONObject();
        Date tempDate = DateUtils.getStartDateTimeOfDay(DateUtils.currentDate());
        Date startCrawlerDateTime = DateUtils.plus(tempDate, DateUtils.DateFields.DAY, -31);
        Date endCrawlerDateTime = DateUtils.currentDate();
        if(enterpriseIdList != null){
            for(int i=0; i<enterpriseIdList.size(); i++){
                String enterpriseId = enterpriseIdList.getString(i);
                long count = opinionDetailsSnapshotRepository.countByEnterpriseIdAndCrawlerDateTimeBetween(
                        enterpriseId, startCrawlerDateTime, endCrawlerDateTime);
                result.put(enterpriseId, count);
            }
        }

        return result;
    }
}
