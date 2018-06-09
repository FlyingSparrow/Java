package com.jdjr.opinion.mongodb.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.forget.keywordCloud.KeywordCloud;
import com.forget.keywordCloud.KeywordModel;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jdjr.opinion.base.bean.PageResult;
import com.jdjr.opinion.constants.SysConst;
import com.jdjr.opinion.mongodb.dao.MongodbSearchDao;
import com.jdjr.opinion.mongodb.entity.*;
import com.jdjr.opinion.utils.DateUtils;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <p>Title: MongodbSearchDaoImpl</p>
 * <p>Description: mongodb公共查询类</p>
 *
 * @author zhangtong
 * @date 2017年6月27日
 */
@Repository
public class MongodbSearchDaoImpl implements MongodbSearchDao {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public PageResult<EventArticle> findPageEventArticleByEntity(EventArticle entity) {
        Integer pageNumber = entity.getPageNumber();
        Integer pageSize = entity.getPageSize();
        String publishDate = "publishDatetime";

        BasicDBObject gtDBObject = new BasicDBObject(publishDate, new BasicDBObject("$gte", entity.getStartPublishDate()));
        BasicDBObject ltDBObject = new BasicDBObject(publishDate, new BasicDBObject("$lte", entity.getEndPublishDate()));
        BasicDBList andList = new BasicDBList();

        if (StringUtils.isNotEmpty(entity.getEnterpriseIdList())) {
            Pattern patternEnterpriseIdList = Pattern.compile("^.*" + entity.getEnterpriseIdList() + ".*$", Pattern.CASE_INSENSITIVE);
            andList.add(new BasicDBObject("enterprise_id", patternEnterpriseIdList));
        }
        if (StringUtils.isNotEmpty(entity.getEmotion())) {
            andList.add(new BasicDBObject("emotion", entity.getEmotion()));
        }
        if (StringUtils.isNotEmpty(entity.getMediaType())) {
            andList.add(new BasicDBObject("mediaType", entity.getMediaType()));
        }
        if (StringUtils.isNotEmpty(entity.getKeywords())) {
            //模糊匹配关键字对文章标题
            Pattern patternKeywords = Pattern.compile("^.*" + entity.getKeywords() + ".*$", Pattern.CASE_INSENSITIVE);
            andList.add(new BasicDBObject("title", patternKeywords));
        }
        andList.add(ltDBObject);
        andList.add(gtDBObject);
        BasicDBObject andDBObject = new BasicDBObject("$and", andList);
        Query query = new BasicQuery(andDBObject);
        Direction sortType = "asc".equals(entity.getSortType()) ? Direction.ASC : Direction.DESC;
        if (StringUtils.isNotEmpty(entity.getSortParam())) {
            query.with(new Sort(new Order(sortType, entity.getSortParam())));
        }
        Long totalCount = mongoTemplate.count(query, EventArticle.class);
        Integer firstResult = (pageNumber - 1) * pageSize;
        query.skip(firstResult);
        query.limit(pageSize);
        List<EventArticle> list = mongoTemplate.find(query, EventArticle.class);
        Double totalPages = 0D;
        Long totalElements = 0L;
        if (totalCount != null) {
            totalPages = Double.parseDouble(totalCount.toString());
            totalElements = totalCount;
        }
        totalPages = Math.ceil(totalPages / pageSize);
        PageResult<EventArticle> page = new PageResult<>(list, totalPages.longValue(), totalElements, list.size());
        return page;
    }

    @Override
    public List<EventArticle> findListEventArticleByEntity(EventArticle entity) {
        BasicDBList andList = new BasicDBList();
        if (StringUtils.isNotEmpty(entity.getArticleId())) {
            andList.add(new BasicDBObject("articleId", entity.getArticleId()));
        }
        BasicDBObject andDBObject = new BasicDBObject("$and", andList);
        Query query = new BasicQuery(andDBObject);
        if (StringUtils.isNotEmpty(entity.getSortParam())) {
            query.with(new Sort(new Order(Direction.DESC, entity.getSortParam())));
        }
        List<EventArticle> list = mongoTemplate.find(query, EventArticle.class);
        return list;
    }


    @Override
    public List<OpinionDetailsSnapshot> findListOpinionDetailsSnapshotByEntity(OpinionDetailsSnapshot entity) {
        String publishDate = "publishDatetime";
        BasicDBObject gtDBObject = new BasicDBObject(publishDate, new BasicDBObject("$gte", entity.getStartPublishDate()));
        BasicDBObject ltDBObject = new BasicDBObject(publishDate, new BasicDBObject("$lte", entity.getEndPublishDate()));
        BasicDBList andList = new BasicDBList();
        if (null != entity.getEnterpriseId()) {
            andList.add(new BasicDBObject("enterpriseId", entity.getEnterpriseId()));
        }
        if (StringUtils.isNotEmpty(entity.getEmotion())) {
            andList.add(new BasicDBObject("emotion", entity.getEmotion()));
        }
        if (StringUtils.isNotEmpty(entity.getMediaType())) {
            andList.add(new BasicDBObject("media_type", entity.getMediaType()));
        }
        andList.add(gtDBObject);
        andList.add(ltDBObject);
        BasicDBObject andDBObject = new BasicDBObject("$and", andList);
        Query query = new BasicQuery(andDBObject);
        query.limit(entity.getLimit());
        Direction sortType = "asc".equals(entity.getSortType()) ? Direction.ASC : Direction.DESC;
        if (StringUtils.isNotEmpty(entity.getSortParam())) {
            query.with(new Sort(new Order(sortType, entity.getSortParam())));
        } else {
            query.with(new Sort(Direction.DESC, "publishDatetime"));
        }
        List<OpinionDetailsSnapshot> list = mongoTemplate.find(query, OpinionDetailsSnapshot.class);
        return list;
    }

    @Override
    public List<RelatedPersonOpinion> findListRelatedPersonOpinionByEntity(RelatedPersonOpinion entity) {
        String publishDate = "publishDatetime";

        BasicDBObject gtDBObject = new BasicDBObject(publishDate, new BasicDBObject("$gte", entity.getStartPublishDate()));
        BasicDBObject ltDBObject = new BasicDBObject(publishDate, new BasicDBObject("$lte", entity.getEndPublishDate()));
        BasicDBList andList = new BasicDBList();
        if (!entity.getRelatedPersonName().isEmpty()) {
            andList.add(new BasicDBObject("relatedPersonName", entity.getRelatedPersonName()));
        }
        andList.add(new BasicDBObject("enterpriseId", entity.getEnterpriseId()));
        andList.add(gtDBObject);
        andList.add(ltDBObject);
        BasicDBObject andDBObject = new BasicDBObject("$and", andList);
        Query query = new BasicQuery(andDBObject);
        query.limit(entity.getLimit());
        Direction sortType = "asc".equals(entity.getSortType()) ? Direction.ASC : Direction.DESC;
        if (StringUtils.isNotEmpty(entity.getSortParam())) {
            query.with(new Sort(new Order(sortType, entity.getSortParam())));
        }
        List<RelatedPersonOpinion> list = mongoTemplate.find(query, RelatedPersonOpinion.class);
        return list;
    }

    @Override
    public Long findCountRelatedPersonOpinionByEntity(RelatedPersonOpinion entity) {
        String publishDate = "publishDatetime";

        BasicDBObject gtDBObject = new BasicDBObject(publishDate, new BasicDBObject("$gte", entity.getStartPublishDate()));
        BasicDBObject ltDBObject = new BasicDBObject(publishDate, new BasicDBObject("$lte", entity.getEndPublishDate()));
        BasicDBList andList = new BasicDBList();
        if (!entity.getRelatedPersonName().isEmpty()) {
            andList.add(new BasicDBObject("relatedPersonName", entity.getRelatedPersonName()));
        }
        andList.add(new BasicDBObject("enterpriseId", entity.getEnterpriseId()));
        if (StringUtils.isNotEmpty(entity.getEmotion())) {
            andList.add(new BasicDBObject("emotion", entity.getEmotion()));
        }
        andList.add(gtDBObject);
        andList.add(ltDBObject);
        BasicDBObject andDBObject = new BasicDBObject("$and", andList);
        Query query = new BasicQuery(andDBObject);
        long total = mongoTemplate.count(query, RelatedPersonOpinion.class);
        return total;
    }

    @Override
    public List<String> finArtcileKeywords() {
        TypedAggregation<Article> agg = Aggregation.newAggregation(
                Article.class,
                Aggregation.project("keywords"),
                Aggregation.group("keywords").count().as("totalNum"),
                Aggregation.sort(Direction.DESC, "totalNum"));
        AggregationResults<BasicDBObject> result = mongoTemplate.aggregate(agg, BasicDBObject.class);
        List<BasicDBObject> resultList = result.getMappedResults();

        List<String> st = resultList.stream().map(a -> {
            return a.getString("_id");
        }).collect(Collectors.toList());
        return st;
    }


    /**
     * <p>Description: 计算昨天的压力指数和前天的压力指数的比值，
     * 计算公式：（昨天的值-前天的值）/前天的值，精确到小数点后两位</p>
     *
     * @param yesterdayPressureIndex
     * @param dayBeforeYstdPressureIndex
     * @return
     * @author Wangjianchun
     * @date 2017年6月29日
     */
    private String calculatePressureIndexRatio(double yesterdayPressureIndex,
                                               double dayBeforeYstdPressureIndex) {
        String result = "0";
        BigDecimal yesterdayValue = new BigDecimal(String.valueOf(yesterdayPressureIndex));
        BigDecimal dayBeforeYstdValue = new BigDecimal(String.valueOf(dayBeforeYstdPressureIndex));
        if (yesterdayValue.doubleValue() > 0
                && dayBeforeYstdValue.doubleValue() > 0) {
            int value = (yesterdayValue.subtract(dayBeforeYstdValue))
                    .divide(dayBeforeYstdValue, 8, BigDecimal.ROUND_HALF_UP)
                    .subtract(BigDecimal.ONE)
//                    .multiply(new BigDecimal("100"))
                    .intValue();
            result = String.valueOf(value);
        } else {
            result = "0";
        }

        return result;
    }


    @Override
    public PageResult<Article> findListByEntity(Article entity) {
        int pageSize = entity.getPageSize();
        int pageNumber = entity.getPageNumber();
        int skip = (pageNumber - 1) * pageSize;

        DBObject crawlerDateTimeDBObject = new BasicDBObject("crawlerDateTime",
                new BasicDBObject("$gt", DateUtils.parseDate("2017-01-01", DateUtils.DATE_FORMAT)));
        Query query = new BasicQuery(crawlerDateTimeDBObject);
        if (StringUtils.isNotEmpty(entity.getCrawlerDate())) {
            DBObject crawlerDateDBObject = new BasicDBObject("crawlerDate", entity.getCrawlerDate());
            query = new BasicQuery(crawlerDateDBObject);
        }

        long total = mongoTemplate.count(query, Article.class);
        query.skip(skip);
        query.limit(pageSize);
        List<Article> list = mongoTemplate.find(query, Article.class);
        long totalPages = (total + Long.valueOf(pageSize - 1)) / Long.valueOf(pageSize);
        PageResult<Article> pageResult = new PageResult<Article>(
                list, totalPages, total, list.size());

        return pageResult;
    }


    @Override
    public EventArticle findEventArticleByCompanyAndArtId(String company, String articleId) {
        Criteria criteria = Criteria.where("enterpriseIdList").is(company).and("articleId").is(articleId);
        Query query = new Query(criteria);
        List<EventArticle> list = mongoTemplate.find(query, EventArticle.class);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public EnterpriseInfo findEnterpriseInfoByEnterpriseId(String enterpriseId) {
        Criteria criteria = Criteria.where("enterpriseId").is(enterpriseId);
        Query query = new Query(criteria);
        return (EnterpriseInfo) mongoTemplate.find(query, EnterpriseInfo.class);
    }

    @Override
    public PageResult<EnterpriseInfo> findPageEnterpriseInfoList(int pageNumber, int pageSize) {

        Query query = new BasicQuery(new BasicDBList());
        long total = mongoTemplate.count(query, EnterpriseInfo.class);
        int skip = (pageNumber - 1) * pageSize;
        query.skip(skip);
        query.limit(pageSize);
        long totalPages = (total + Long.valueOf(pageSize - 1)) / Long.valueOf(pageSize);
        List<EnterpriseInfo> list = mongoTemplate.find(query, EnterpriseInfo.class);
        PageResult<EnterpriseInfo> pageResult = new PageResult<>(
                list, totalPages, total, list.size());

        return pageResult;
    }

    @Override
    public JSONObject findEventTrend(OpinionDetailsSnapshot entity) {
        JSONObject result = new JSONObject();

        EventInfo eventInfo = mongoTemplate.findById(entity.getEventId(), EventInfo.class);
        if (eventInfo != null) {
            Set<String> articleIdSet = eventInfo.getArticleIdList();
            articleIdSet.add(eventInfo.getCoreArticleId());

            Criteria criteria = Criteria.where("article_id")
                    .in(articleIdSet)
                    .and("publish_datetime")
                    .gte(entity.getStartPublishDate()).lte(entity.getEndPublishDate());
            if (StringUtils.isNotEmpty(entity.getMediaType())
                    && !"null".equals(entity.getMediaType())) {
                criteria.and("media_type").is(entity.getMediaType());
            }
            if (StringUtils.isNotEmpty(entity.getEmotion())
                    && !"null".equals(entity.getEmotion())) {
                criteria.and("emotion").is(entity.getEmotion());

            }

            Aggregation agg = Aggregation.newAggregation(
                    Aggregation.match(criteria),
                    Aggregation.group("publish_date").addToSet("article_id")
                            .as("articleIdSet"),
                    Aggregation.sort(Direction.ASC, "publish_date"));

            AggregationResults outputType = mongoTemplate.aggregate(agg,
                    "opinion_details_snapshot", BasicDBObject.class);
            JSONArray seriesData = new JSONArray();
            JSONArray xAxisData = new JSONArray();
            List<Date> list = Lists.newArrayList();
            for (Iterator it = outputType.iterator(); it.hasNext(); ) {
                BasicDBObject dbObject = (BasicDBObject) it.next();
                String tempArticleIdSet = dbObject.getString("articleIdSet");
                JSONArray array = JSON.parseArray(tempArticleIdSet);
                seriesData.add(array.size());
                xAxisData.add(dbObject.get("_id"));

                list.add(DateUtils.parseDate(dbObject.get("_id").toString(),
                        DateUtils.DATE_FORMAT));
            }
            Collections.sort(list, new Comparator<Date>() {
                @Override
                public int compare(Date o1, Date o2) {
                    return o1.compareTo(o2);
                }
            });

            JSONArray seriesData2 = new JSONArray();
            JSONArray xAxisData2 = new JSONArray();
            List<Date> dateRange = DateUtils.dateRange(entity.getStartPublishDate(), entity.getEndPublishDate());
            if (seriesData != null && seriesData.size() != 0) {
                dateRange.forEach(date -> {
                    String publishDate = DateUtils.formatDate(date);

                    boolean exits = false;
                    for (int j = 0; j < seriesData.size(); j++) {
                        if (publishDate.equals(xAxisData.getString(j))) {
                            seriesData2.add(seriesData.get(j));
                            xAxisData2.add(xAxisData.get(j));
                            exits = true;
                        }
                    }
                    if (!exits) {
                        seriesData2.add(0);
                        xAxisData2.add(publishDate);
                    }
                });
            }
            result.put("seriesData", seriesData2);
            result.put("xAxisData", xAxisData2);
        }

        return result;
    }

    @Override
    public JSONArray findEventPropagation(String eventId) {
        JSONArray result = new JSONArray();
        EventInfo eventInfo = mongoTemplate.findById(eventId, EventInfo.class);
        if (eventInfo != null) {
            String coreArticleId = eventInfo.getCoreArticleId();
            Set<String> articleIdSet = eventInfo.getArticleIdList();
            if (articleIdSet == null) {
                articleIdSet = new HashSet<>();
            }
            articleIdSet.add(coreArticleId);
            //显示字段
            ProjectionOperation projectionOperation = Aggregation.project("_id", "article_id", "publish_datetime", "media_type", "title", "media", "url");

            // 查询操作
            Criteria operator = Criteria.where("article_id").in(articleIdSet);

            // 分组操作
            GroupOperation groupOperation = Aggregation.group("media_type")
                    .last("media_type").as("minMediaType")
                    .last("publish_datetime").as("minPublishDatetime")
                    .last("title").as("minTitle")
                    .last("media").as("minMedia")
                    .last("article_id").as("minArticleId")
                    .last("_id").as("minId")
                    .last("url").as("minUrl");

            //排序操作
            SortOperation sortOperation = Aggregation.sort(Direction.DESC, "publish_datetime");

            Aggregation agg = Aggregation.newAggregation(projectionOperation, Aggregation.match(operator), sortOperation, groupOperation);
            AggregationResults<BasicDBObject> outputType = mongoTemplate.aggregate(agg,
                    "opinion_details_snapshot", BasicDBObject.class);

            for (Iterator it = outputType.iterator(); it.hasNext(); ) {
                BasicDBObject dbObject = (BasicDBObject) it.next();
                JSONObject item = new JSONObject();
                item.put("mediaType", dbObject.get("minMediaType"));
                item.put("publishDatetime", DateUtils.formatDate(dbObject.getDate("minPublishDatetime"), DateUtils.DATE_MINUTE_FORMAT));
                item.put("title", dbObject.get("minTitle"));
                item.put("media", dbObject.get("minMedia"));
                item.put("articleId", dbObject.get("minArticleId"));
                item.put("aid", dbObject.getString("minId"));
                item.put("url", dbObject.getString("minUrl"));
                result.add(item);
            }
        }

        return result;
    }

    @Override
    public JSONObject findMediaPropagation(String eventId) {
        JSONObject result = new JSONObject(true);
        EventInfo eventInfo = mongoTemplate.findById(eventId, EventInfo.class);
        if (eventInfo != null) {
            Set<String> articleIdSet = eventInfo.getArticleIdList();
            if (articleIdSet == null) {
                articleIdSet = Sets.newHashSet();
            }
            articleIdSet.add(eventInfo.getCoreArticleId());
            Aggregation agg = Aggregation.newAggregation(
                    Aggregation.project("article_id", "media", "publish_date", "publish_datetime")
                    , Aggregation.match(Criteria.where("article_id").in(articleIdSet))
                    , Aggregation.sort(Direction.DESC, "publish_datetime")
                    , Aggregation.group("publish_date", "media").count().as("mediaCount")
            );
            AggregationResults<BasicDBObject> outputType = mongoTemplate.aggregate(agg,
                    "opinion_details_snapshot", BasicDBObject.class);

            final JSONArray[] mediaJa = new JSONArray[1];
            List<String> dateList = Lists.newArrayList();
            for (Iterator it = outputType.iterator(); it.hasNext(); ) {
                BasicDBObject dbObject = (BasicDBObject) it.next();
                String publishDate = dbObject.getString("publish_date");
                dateList.add(publishDate);
            }

            List<String> datanew = DateUtils.sortListDesc(dateList);
            List<String> dateOnly = datanew.stream().distinct().collect(Collectors.toList());
            dateOnly.forEach(st -> {
                for (Iterator it = outputType.iterator(); it.hasNext(); ) {
                    BasicDBObject dbObject = (BasicDBObject) it.next();
                    String publishDate = dbObject.getString("publish_date");
                    if (st.equals(publishDate)) {
                        String media = dbObject.getString("media");
                        Integer mediaCount = dbObject.getInt("mediaCount");
                        if (result.containsKey(st)) { //是当月的
                            mediaJa[0] = result.getJSONArray(st);
                            JSONObject mediaJo = new JSONObject();
                            mediaJo.put("media", media);
                            mediaJo.put("mediaCount", mediaCount);
                            mediaJa[0].add(mediaJo);
                            result.put(st, mediaJa[0]);
                        } else { //新的一个月的
                            mediaJa[0] = new JSONArray();
                            JSONObject mediaJo = new JSONObject();
                            mediaJo.put("media", media);
                            mediaJo.put("mediaCount", mediaCount);
                            mediaJa[0].add(mediaJo);
                            result.put(st, mediaJa[0]);
                        }
                    }
                }
            });
        }

        return result;
    }

    @Override
    public JSONObject findKeywordEvolution(String eventId) {
        JSONObject result = new JSONObject(true);
        EventInfo eventInfo = mongoTemplate.findById(eventId, EventInfo.class);
        if (eventInfo != null) {
            Set<String> articleIdSet = eventInfo.getArticleIdList();
            if (articleIdSet == null) {
                articleIdSet = Sets.newHashSet();
            }
            articleIdSet.add(eventInfo.getCoreArticleId());
            Aggregation agg = Aggregation.newAggregation(
                    Aggregation.project("article_id", "keywords", "publish_date", "publish_datetime")
                    , Aggregation.match(Criteria.where("article_id").in(articleIdSet))
                    , Aggregation.sort(Direction.DESC, "publish_datetime")
                    , Aggregation.group("publish_date")
                            .addToSet("article_id").as("articleIds")
            );
            AggregationResults<BasicDBObject> outputType = mongoTemplate.aggregate(agg,
                    "opinion_details_snapshot", BasicDBObject.class);

            List<String> dateList = Lists.newArrayList();
            for (Iterator it = outputType.iterator(); it.hasNext(); ) {
                BasicDBObject dbObject = (BasicDBObject) it.next();
                String publishDate = dbObject.getString("_id");
                dateList.add(publishDate);
            }
            List<String> datanew = DateUtils.sortListDesc(dateList);
            datanew.forEach(st -> {
                for (Iterator it = outputType.iterator(); it.hasNext(); ) {
                    BasicDBObject dbObject = (BasicDBObject) it.next();
                    String publishDate = dbObject.getString("_id");
                    if (st.equals(publishDate)) {
                        String articleIds = dbObject.getString("articleIds");
                        JSONArray jsonArray = JSONArray.parseArray(articleIds);
                        List<String> articleIdList = JSON.parseArray(jsonArray.toJSONString(), String.class);

                        Criteria criteria = Criteria.where("id")
                                .in(articleIdList);
                        Query query = new Query(criteria);
                        List<Article> articleList = mongoTemplate.find(query, Article.class);
                        List<String> titleContent = articleList.stream().map(article -> {
                            StringBuilder sb = new StringBuilder(article.getTitle()).append(article.getContent());
                            return sb.toString();
                        }).collect(Collectors.toList());
                        JSONObject jsonObject = KeywordCloud.toKeywordCloudArtList(titleContent, 0, 5);
                        if (jsonObject.getJSONArray("result") != null) {
                            List<KeywordModel> keywordModels = JSON.parseArray(jsonObject.getJSONArray("result").toJSONString(), KeywordModel.class);
                            result.put(publishDate, JSON.toJSON(keywordModels));
                        }
                    }
                }
            });
        }

        return result;
    }

    @Override
    public JSONObject findViewpointAnalysis(String eventId) {
        JSONObject result = new JSONObject();
        result.put("total", 0);
        result.put("positiveCount", 0);
        result.put("negativeCount", 0);

        EventInfo eventInfo = mongoTemplate.findById(eventId, EventInfo.class);
        if (eventInfo != null) {
            Set<String> articleIdSet = eventInfo.getArticleIdList();
            if (articleIdSet == null) {
                articleIdSet = Sets.newHashSet();
            }
            articleIdSet.add(eventInfo.getCoreArticleId());

            DBObject articleIdDBObject = new BasicDBObject("articleId",
                    new BasicDBObject("$in", articleIdSet));
            DBObject fieldsDBObject = new BasicDBObject("articleId", true);
            fieldsDBObject.put("emotion", true);
            Query query = new BasicQuery(articleIdDBObject, fieldsDBObject);
            List<OpinionDetailsSnapshot> list = mongoTemplate.find(query, OpinionDetailsSnapshot.class);
            if (list != null) {
                result.put("total", list.size());
                int positiveCount = 0;
                int negativeCount = 0;
                for (OpinionDetailsSnapshot item : list) {
                    if (SysConst.EmotionType.POSITIVE.getCode().equals(item.getEmotion())) {
                        positiveCount++;
                    }
                    if (SysConst.EmotionType.NEGATIVE.getCode().equals(item.getEmotion())) {
                        negativeCount++;
                    }
                }
                result.put("positiveCount", positiveCount);
                result.put("negativeCount", negativeCount);
            }
        }

        return result;
    }

    @Override
    public List<Article> findArticleListByIds(Set<String> ids) {
        DBObject queryDBObject = new BasicDBObject("id", new BasicDBObject("$in", ids));
        DBObject fieldsDBObject = new BasicDBObject("title", true);
        fieldsDBObject.put("content", true);
        Query query = new BasicQuery(queryDBObject, fieldsDBObject);
        return mongoTemplate.find(query, Article.class);
    }

    @Override
    public JSONObject findNewsList(String eventId) {
        JSONObject result = new JSONObject();
        result.put("positiveData", "");
        result.put("negativeData", "");
        result.put("publishDateData", "");

        EventInfo eventInfo = mongoTemplate.findById(eventId, EventInfo.class);
        if (eventInfo != null) {
            Set<String> articleIdSet = eventInfo.getArticleIdList();
            if (articleIdSet == null) {
                articleIdSet = Sets.newHashSet();
            }
            articleIdSet.add(eventInfo.getCoreArticleId());

            DBObject match = new BasicDBObject("articleId", new BasicDBObject("$in", articleIdSet));

            Query queryMax = new BasicQuery(match);
            queryMax.with(new Sort(new Order(Direction.DESC, "publishDate")));
            List<OpinionDetailsSnapshot> maxODSList = mongoTemplate.find(queryMax, OpinionDetailsSnapshot.class);
            List<String> dateRange = maxODSList.stream()
                    .filter(ods -> Objects.equal(ods.getEmotion(), SysConst.EmotionType.NEGATIVE.getCode())
                            || Objects.equal(ods.getEmotion(), SysConst.EmotionType.POSITIVE.getCode()))
                    .map(OpinionDetailsSnapshot::getPublishDate).distinct().collect(Collectors.toList());

            JSONArray positiveData = new JSONArray();
            JSONArray negativeData = new JSONArray();
            JSONArray publishDateData = new JSONArray();

            for (String publishDate : dateRange) {
                publishDateData.add(publishDate);
                Criteria operator = Criteria.where("article_id").in(articleIdSet);
                operator.and("emotion").ne(SysConst.EmotionType.NEUTRAL.getCode());
                operator.and("publish_date").is(publishDate);
                Aggregation agg = Aggregation.newAggregation(
                        Aggregation.project("_id", "article_id", "publish_date", "hot", "emotion", "publish_datetime", "title", "url")
                        , Aggregation.match(operator)
                        , Aggregation.sort(Direction.DESC, "hot")
                        , Aggregation.group("emotion")
                                .first("emotion").as("emotion")
                                .first("title").as("title")
                                .first("article_id").as("articleId")
                                .first("_id").as("idods")
                                .first("url").as("url")
                );
                AggregationResults<BasicDBObject> outputType = mongoTemplate.aggregate(agg, "opinion_details_snapshot", BasicDBObject.class);
                JSONObject jsonObjectEmpty = new JSONObject();
                jsonObjectEmpty.put("articleId", "-1");
                jsonObjectEmpty.put("title", "当前时间段暂无相关新闻");
                if (outputType.iterator().hasNext()) {
                    boolean insertPositive = false;
                    boolean insertnegative = false;
                    for (Iterator it = outputType.iterator(); it.hasNext(); ) {
                        DBObject dbObject = (DBObject) it.next();
                        String id = dbObject.get("idods").toString();
                        String emotion = dbObject.get("emotion").toString();
                        String articleId = dbObject.get("articleId").toString();
                        String title = dbObject.get("title").toString();
                        String url = dbObject.get("url").toString();
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("articleId", articleId);
                        jsonObject.put("id", id);
                        jsonObject.put("title", title);
                        jsonObject.put("url", url);
                        if (SysConst.EmotionType.POSITIVE.getCode().equals(emotion)) {
                            insertPositive = true;
                            positiveData.add(jsonObject);
                        } else if (SysConst.EmotionType.NEGATIVE.getCode().equals(emotion)) {
                            insertnegative = true;
                            negativeData.add(jsonObject);
                        }
                    }
                    if (!insertPositive) {
                        positiveData.add(jsonObjectEmpty);
                    }
                    if (!insertnegative) {
                        negativeData.add(jsonObjectEmpty);
                    }
                } else {
                    positiveData.add(jsonObjectEmpty);
                    negativeData.add(jsonObjectEmpty);
                }
            }
            result.put("positiveData", positiveData);
            result.put("negativeData", negativeData);
            result.put("publishDateData", publishDateData);
        }

        return result;
    }

    @Override
    public PageResult<OpinionDetailsSnapshot> findPageOpinionDetailsSnapshotByEntity(OpinionDetailsSnapshot entity, Set<String> articleIdList) {
        Integer pageNumber = entity.getPageNumber();
        Integer pageSize = entity.getPageSize();

        String publishDatetime = "publishDatetime";
        BasicDBObject gtDBObject = new BasicDBObject(publishDatetime, new BasicDBObject("$gte", entity.getStartPublishDate()));
        BasicDBObject ltDBObject = new BasicDBObject(publishDatetime, new BasicDBObject("$lte", entity.getEndPublishDate()));
        BasicDBList andList = new BasicDBList();


        andList.add(new BasicDBObject("enterpriseId", entity.getEnterpriseId()));
        andList.add(new BasicDBObject("articleId", new BasicDBObject("$in", articleIdList)));
        if (StringUtils.isNotEmpty(entity.getEmotion())) {
            andList.add(new BasicDBObject("emotion", entity.getEmotion()));
        }
        if (StringUtils.isNotEmpty(entity.getMediaType())) {
            andList.add(new BasicDBObject("mediaType", entity.getMediaType()));
        }
        andList.add(ltDBObject);
        andList.add(gtDBObject);
        BasicDBObject andDBObject = new BasicDBObject("$and", andList);
        Query query = new BasicQuery(andDBObject);
        Direction sortType = "asc".equals(entity.getSortType()) ? Direction.ASC : Direction.DESC;
        if (StringUtils.isNotEmpty(entity.getSortParam())) {
            query.with(new Sort(new Order(sortType, entity.getSortParam())));
        }
        Long totalCount = mongoTemplate.count(query, OpinionDetailsSnapshot.class);
        Integer firstResult = (pageNumber - 1) * pageSize;
        query.skip(firstResult);
        query.limit(pageSize);
        List<OpinionDetailsSnapshot> list = mongoTemplate.find(query, OpinionDetailsSnapshot.class);
//        logger.info("生成query语句，参数：{}，响应结果：{}", query.toString(), list.size());
        Double totalPages = 0D;
        Long totalElements = 0L;
        if (totalCount != null) {
            totalPages = Double.parseDouble(totalCount.toString());
            totalElements = totalCount;
        }
        totalPages = Math.ceil(totalPages / pageSize);
        PageResult<OpinionDetailsSnapshot> page = new PageResult<>(list, totalPages.longValue(), totalElements, list.size());
        return page;
    }

    @Override
    public PageResult<OpinionDetailsSnapshot> findPageOpinionDetailsSnapshotByEntity(OpinionDetailsSnapshot entity) {
        Integer pageNumber = entity.getPageNumber();
        Integer pageSize = entity.getPageSize();

        BasicDBList andList = new BasicDBList();
        andList.add(new BasicDBObject("enterpriseId", entity.getEnterpriseId()));
        if (StringUtils.isNotEmpty(entity.getCrawlerDate())) {
            andList.add(new BasicDBObject("crawlerDate", entity.getCrawlerDate()));
        }
        if (StringUtils.isNotEmpty(entity.getEmotion())) {
            andList.add(new BasicDBObject("emotion", entity.getEmotion()));
        }
        if (StringUtils.isNotEmpty(entity.getMediaType())) {
            andList.add(new BasicDBObject("mediaType", entity.getMediaType()));
        }
        if (StringUtils.isNotEmpty(entity.getKeywords())) {
            //模糊匹配关键字对文章标题
            Pattern patternKeywords = Pattern.compile("^.*" + entity.getKeywords() + ".*$", Pattern.CASE_INSENSITIVE);
            andList.add(new BasicDBObject("title", patternKeywords));
        }
        DBObject publishDatetimeDBObject = new BasicDBObject("publishDatetime", new BasicDBObject("$exists", "true"));
        if (entity.getStartPublishDate() != null && entity.getEndPublishDate() != null) {
            String publishDatetime = "publishDatetime";
            BasicDBObject gtDBObject = new BasicDBObject(publishDatetime, new BasicDBObject("$gte", entity.getStartPublishDate()));
            BasicDBObject ltDBObject = new BasicDBObject(publishDatetime, new BasicDBObject("$lte", entity.getEndPublishDate()));
            andList.add(ltDBObject);
            andList.add(gtDBObject);
        }

        andList.add(publishDatetimeDBObject);
        BasicDBObject andDBObject = new BasicDBObject("$and", andList);
        BasicQuery query = new BasicQuery(andDBObject);
        Direction sortType = "asc".equals(entity.getSortType()) ? Direction.ASC : Direction.DESC;
        if (StringUtils.isNotEmpty(entity.getSortParam())) {
            query.with(new Sort(new Order(sortType, entity.getSortParam())));
        }
        Long totalCount = mongoTemplate.count(query, OpinionDetailsSnapshot.class);
        Integer firstResult = (pageNumber - 1) * pageSize;
        query.skip(firstResult);
        query.limit(pageSize);
        List<OpinionDetailsSnapshot> list = mongoTemplate.find(query, OpinionDetailsSnapshot.class);
        list.stream().forEach(item -> {
            Query countQuery = new BasicQuery(new BasicDBObject("articleId", item.getArticleId()));
            long similarArticleCount = mongoTemplate.count(countQuery, SimilarArticle.class);
            item.setHot(item.getHot() * Integer.parseInt(similarArticleCount + ""));
        });
//        logger.info("生成query语句，参数：{}，响应结果：{}", query.toString(), list.size());
        Double totalPages = 0D;
        Long totalElements = 0L;
        if (totalCount != null) {
            totalPages = Double.parseDouble(totalCount.toString());
            totalElements = totalCount;
        }
        totalPages = Math.ceil(totalPages / pageSize);
        PageResult<OpinionDetailsSnapshot> page = new PageResult<>(list, totalPages.longValue(), totalElements, list.size());
        return page;
    }

    @Override
    public Long findOpinionDetailsSnapshotCountByEntity(OpinionDetailsSnapshot entity) {
        String publishDatetime = "publishDatetime";
        BasicDBObject gtDBObject = new BasicDBObject(publishDatetime, new BasicDBObject("$gte", entity.getStartPublishDate()));
        BasicDBObject ltDBObject = new BasicDBObject(publishDatetime, new BasicDBObject("$lte", entity.getEndPublishDate()));
        BasicDBList andList = new BasicDBList();
        if (StringUtils.isNotEmpty(entity.getEmotion())) {
            andList.add(new BasicDBObject("emotion", entity.getEmotion()));
        }
        if (StringUtils.isNotEmpty(entity.getArticleType())) {
            andList.add(new BasicDBObject("articleType", entity.getArticleType()));
        }
        andList.add(ltDBObject);
        andList.add(gtDBObject);
        BasicDBObject andDBObject = new BasicDBObject("$and", andList);
        Query query = new BasicQuery(andDBObject);
        return mongoTemplate.count(query, OpinionDetailsSnapshot.class);
    }

    @Override
    public JSONArray findListByEntity(OpinionDetailsSnapshot entity) {
        JSONArray result = new JSONArray();

        long startTime = System.currentTimeMillis();
        String enterpriseId = entity.getEnterpriseId();
        BasicDBList eventAndList = new BasicDBList();
        String publishDatetime = "publishDatetime";
        BasicDBObject gtDBObject = new BasicDBObject(publishDatetime,
                new BasicDBObject("$gte", entity.getStartPublishDate()));
        BasicDBObject ltDBObject = new BasicDBObject(publishDatetime,
                new BasicDBObject("$lte", entity.getEndPublishDate()));
        eventAndList.add(ltDBObject);
        eventAndList.add(gtDBObject);
        eventAndList.add(new BasicDBObject("enterpriseId", enterpriseId));
        eventAndList.add(new BasicDBObject("is_replace", null));
        if (!"全部".equals(entity.getArticleType())) {
            eventAndList.add(new BasicDBObject("eventType", entity.getArticleType()));
        }
        BasicDBObject andDBObject = new BasicDBObject("$and", eventAndList);
        Query query = new BasicQuery(andDBObject);
        query.with(new Sort(Direction.DESC, "publishDatetime"));
        List<EventInfo> list = mongoTemplate.find(query, EventInfo.class);

        logger.info("查询企业id[{}] 的事件列表耗时：{} 秒", enterpriseId,
                ((System.currentTimeMillis() - startTime)/1000));

        long startTime2 = System.currentTimeMillis();
        if (list != null && list.size() > 0) {
            logger.info("企业id[{}] 共 {} 个事件", enterpriseId, list.size());

            long eventStartTime = System.currentTimeMillis();
            for(EventInfo item : list){
                JSONObject eventSummary = getEventSummary(item, entity);
                if(eventSummary != null){
                    result.add(eventSummary);
                }
            }
            logger.info("查询事件摘要信息耗时：{} 秒", ((System.currentTimeMillis() - eventStartTime)/1000));
        }
        logger.info("处理企业id[{}] 的事件列表的最早发布时间耗时：{} 秒", enterpriseId,
                ((System.currentTimeMillis() - startTime2)/1000));

        long startTime3 = System.currentTimeMillis();
        //按照最早发布时间倒序排序事件列表
        result.sort((itemA, itemB)->{
            JSONObject itemAA = (JSONObject) itemA;
            JSONObject itemBB = (JSONObject) itemB;

            Long publishDateA = itemAA.getLong("earliestPublishDateLongValue");
            Long publishDateB = itemBB.getLong("earliestPublishDateLongValue");

            return publishDateB.compareTo(publishDateA);
        });
        logger.info("处理企业id[{}] 的事件列表按照最早发布时间排序耗时：{} 秒", enterpriseId,
                ((System.currentTimeMillis() - startTime3)/1000));

        return result;
    }

    private JSONObject getEventSummary(EventInfo item, OpinionDetailsSnapshot entity){
        JSONObject jsonObject = null;
        Set<String> articleIdList = item.getArticleIdList();
        if (articleIdList == null) {
            articleIdList = Sets.newHashSet();
        }
        String enterpriseId = entity.getEnterpriseId();
        String coreArticleId = item.getCoreArticleId();
        BasicDBList andListEmotion = new BasicDBList();
        BasicDBList andList = new BasicDBList();
        andListEmotion.add(new BasicDBObject("articleId", coreArticleId));
        andListEmotion.add(new BasicDBObject("enterpriseId", enterpriseId));
        if (StringUtils.isNotEmpty(entity.getEmotion())) {
            DBObject emotionDBObject = new BasicDBObject("emotion", entity.getEmotion());
            andListEmotion.add(emotionDBObject);
            andList.add(emotionDBObject);
        }
        DBObject odsAndEmotionDBObject = new BasicDBObject("$and", andListEmotion);
        List<OpinionDetailsSnapshot> emotionOds = mongoTemplate.find(new BasicQuery(odsAndEmotionDBObject), OpinionDetailsSnapshot.class);  //查询该事件的情感核心文章如果存在则显示事件
        if (emotionOds != null && emotionOds.size() > 0) {
            DBObject articleIdDBObject = new BasicDBObject("$in", articleIdList);

            andList.add(new BasicDBObject("articleId", articleIdDBObject));
            DBObject gteDBObject = new BasicDBObject("publishDatetime",
                    new BasicDBObject("$gte", entity.getStartPublishDate()));
            DBObject lteDBObject = new BasicDBObject("publishDatetime",
                    new BasicDBObject("$lte", entity.getEndPublishDate()));
            DBObject enterpriseIdDBObject = new BasicDBObject("enterpriseId", enterpriseId);
            andList.add(gteDBObject);
            andList.add(lteDBObject);
            andList.add(enterpriseIdDBObject);
            DBObject odsAndDBObject = new BasicDBObject("$and", andList);
            Query odsQuery = new BasicQuery(odsAndDBObject);
            odsQuery.with(new Sort(Direction.DESC, "publishDatetime"));
            List<OpinionDetailsSnapshot> odsList = mongoTemplate.find(odsQuery, OpinionDetailsSnapshot.class);

            if (odsList != null && odsList.size() > 0) {
                //确保只显示最早报道时间
                BasicDBList beforBasic = new BasicDBList();
                beforBasic.add(new BasicDBObject("publishDatetime",
                        new BasicDBObject("$lt", entity.getStartPublishDate())));
                beforBasic.add(new BasicDBObject("articleId", articleIdDBObject));
                beforBasic.add(enterpriseIdDBObject);
                DBObject beforOdsDBObject = new BasicDBObject("$and", beforBasic);
                Query beforOdsQuery = new BasicQuery(beforOdsDBObject);
                List<OpinionDetailsSnapshot> beforeOdsList = mongoTemplate.find(beforOdsQuery, OpinionDetailsSnapshot.class);
                if (beforeOdsList.isEmpty()) {
                    String publishDate = DateUtils.formatDate(odsList.get(0).getPublishDatetime(), DateUtils.DATE_MINUTE_FORMAT);
                    jsonObject = new JSONObject();
                    jsonObject.put("eventId", item.getId());
                    jsonObject.put("eventName", item.getEventName());
                    jsonObject.put("earliestPublishDate", publishDate);
                    jsonObject.put("earliestPublishDateLongValue", Long.valueOf(publishDate
                            .replace("-", "").replace(" ", "")
                            .replace(":", "")));
                    jsonObject.put("opinionCount", articleIdList.size());
                }
            }
        }
        return jsonObject;
    }

    @Override
    public List<OpinionDetailsSnapshot> findListByEnterpriseIdListLimit(List<String> enterpriseIdList, Integer limit) {
        Criteria criteria = Criteria.where("enterpriseId").in(enterpriseIdList);
        Query query = new Query(criteria);
        query.limit(limit);
        query.with(new Sort(new Order(Direction.DESC, "publishDatetime")));
        List<OpinionDetailsSnapshot> list = mongoTemplate.find(query, OpinionDetailsSnapshot.class);
        return list;
    }

    @Override
    public JSONArray findByEnterpriseId(String enterpriseId, Date startTime, Date endTime) {
        //显示字段
        ProjectionOperation projectionOperation = Aggregation.project("_id", "article_type", "article_id", "publish_date", "enterprise_id", "publish_datetime");

        // 查询操作
        Criteria operator = Criteria.where("enterprise_id").is(enterpriseId)
                .and("publish_datetime")
                .gte(startTime).lte(endTime);

        // 分组操作
        GroupOperation groupOperation = Aggregation.group("article_type", "publish_date")
                .first("publish_datetime").as("minPublishDatetime")
                .first("article_id").as("minArticleId")
                .first("article_type").as("minArticleType")
                .first("_id").as("minId");

        //排序操作
        SortOperation sortOperation = Aggregation.sort(Direction.DESC, "publish_datetime");
        Aggregation agg = Aggregation.newAggregation(projectionOperation, Aggregation.match(operator), sortOperation, groupOperation);
        AggregationResults<BasicDBObject> outputType = mongoTemplate.aggregate(agg,
                "opinion_details_snapshot", BasicDBObject.class);
        JSONArray result = new JSONArray();
        for (Iterator it = outputType.iterator(); it.hasNext(); ) {
            BasicDBObject dbObject = (BasicDBObject) it.next();
            JSONObject item = new JSONObject();
            item.put("publishDatetime", DateUtils.formatDate(dbObject.getDate("minPublishDatetime"), DateUtils.DATE_FORMAT));
            item.put("articleId", dbObject.get("minArticleId"));
            item.put("aid", dbObject.getString("minId"));
            item.put("articleType", dbObject.getString("minArticleType"));
            result.add(item);
        }
        return result;
    }

    @Override
    public List<OpinionRisk> findListOpinionRiskByEntity(OpinionRisk entity) {
        String createdDate = "publishDatetime";
        Date startPublishDate = entity.getStartPublishDate();
        Date endPublishDate = entity.getEndPublishDate();
        BasicDBList andList = new BasicDBList();
        if (entity.getContain()) {
            if (startPublishDate != null) {
                BasicDBObject gtDBObject = new BasicDBObject(createdDate, new BasicDBObject("$gte", startPublishDate));
                andList.add(gtDBObject);
            }
            if (endPublishDate != null) {
                BasicDBObject ltDBObject = new BasicDBObject(createdDate, new BasicDBObject("$lte", endPublishDate));
                andList.add(ltDBObject);
            }
        } else {
            if (startPublishDate != null) {
                BasicDBObject gtDBObject = new BasicDBObject(createdDate, new BasicDBObject("$gt", startPublishDate));
                andList.add(gtDBObject);
            }
            if (endPublishDate != null) {
                BasicDBObject ltDBObject = new BasicDBObject(createdDate, new BasicDBObject("$lt", endPublishDate));
                andList.add(ltDBObject);
            }
        }

        andList.add(new BasicDBObject("enterpriseId", entity.getEnterpriseId()));
        BasicDBObject andDBObject = new BasicDBObject("$and", andList);
        Query query = new BasicQuery(andDBObject);
        if (entity.getLimit() != null) {
            query.limit(entity.getLimit());
        }
        query.with(new Sort(new Order(Direction.DESC, "publishDatetime")));
        List<OpinionRisk> list = mongoTemplate.find(query, OpinionRisk.class);
        return list;
    }

    @Override
    public List<OpinionRisk> findListOpinionRiskByInPublishDate(String enterpriseId, List<String> publishDate) {
        Criteria criteria = Criteria.where("publishDate").in(publishDate).and("enterpriseId").is(enterpriseId);
        Query query = new Query(criteria);
        List<OpinionRisk> list = mongoTemplate.find(query, OpinionRisk.class);
        return list;
    }

    @Override
    public Map<String, String> findOpinionRiskGroupEnterprise(String publishDate) {
        //显示字段
        ProjectionOperation projectionOperation = Aggregation.project("enterprise_id", "enterprise_name");
        Aggregation agg;
        if (StringUtils.isNotEmpty(publishDate)) {
            agg = Aggregation.newAggregation(projectionOperation,
                    Aggregation.match(Criteria.where("publish_date").is(publishDate)));
        } else {
            // 分组操作
            GroupOperation groupOperation = Aggregation.group("enterprise_id")
                    .last("enterprise_name").as("enterprise_name")
                    .last("enterprise_id").as("enterprise_id");
            agg = Aggregation.newAggregation(projectionOperation, groupOperation);
        }
        AggregationResults<BasicDBObject> outputType = mongoTemplate.aggregate(agg,
                "opinion_risk", BasicDBObject.class);
        Map<String, String> result = Maps.newHashMap();
        for (Iterator it = outputType.iterator(); it.hasNext(); ) {
            BasicDBObject dbObject = (BasicDBObject) it.next();
            result.put(dbObject.getString("enterprise_id"), dbObject.getString("enterprise_name"));
        }
        return result;
    }

    @Override
    public List<OpinionRisk> findOpinionRiskByEnterpriseIdList(List<String> enterpriseIds, List<String> publishDate) {
        Criteria criteria = Criteria.where("enterpriseId").in(enterpriseIds).and("publishDate").in(publishDate);
        Query query = new Query(criteria);
        query.with(new Sort(new Order(Direction.DESC, "publishDatetime")));
        List<OpinionRisk> list = mongoTemplate.find(query, OpinionRisk.class);
        return list;
    }

    @Override
    public List<EventInfo> findListEventInfoByCoreArticleId(String coreArticleId, String enterpriseId) {
        Criteria criteria = Criteria.where("coreArticleId").is(coreArticleId)
                .and("enterpriseId").is(enterpriseId);
        Query query = new Query(criteria);
        List<EventInfo> list = mongoTemplate.find(query, EventInfo.class);
        return list;
    }

    @Override
    public List<OpinionRisk> findListOpinionRiskByInPublishDate(List<String> enterpriseId, List<String> publishDate) {
        Criteria criteria = Criteria.where("publishDate").in(publishDate).and("enterpriseId").in(enterpriseId);
        Query query = new Query(criteria);
        List<OpinionRisk> list = mongoTemplate.find(query, OpinionRisk.class);
        return list;
    }

    @Override
    public List<OpinionRisk> findOpinionRiskListByEntity(String yesterday, String today, List<String> enterpriseIds) {
        DBObject articleIdDBObject = new BasicDBObject("enterpriseId",
                new BasicDBObject("$in", enterpriseIds));
        BasicDBList orList = new BasicDBList();
        orList.add(new BasicDBObject("publishDate", today));
        orList.add(new BasicDBObject("publishDate", yesterday));
        DBObject orDBObject = new BasicDBObject("$or", orList);
        BasicDBList andList = new BasicDBList();
        andList.add(articleIdDBObject);
        andList.add(orDBObject);
        DBObject andDBObject = new BasicDBObject("$and", andList);
        Query query = new BasicQuery(andDBObject);
        return mongoTemplate.find(query, OpinionRisk.class);
    }

    @Override
    public Set<String> findDistinctEnterpriseIdSetByPublishDate(String publishDate) {
        Criteria criteria = Criteria.where("publishDate").is(publishDate);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group("enterpriseId").addToSet("enterpriseId")
                        .as("enterpriseIdSet").count().as("value"));
        AggregationResults aggregationResults = mongoTemplate.aggregate(aggregation,
                OpinionDetailsSnapshot.class, BasicDBObject.class);
        Set<String> enterpriseIdSet = Sets.newHashSet();
        for (Iterator iterator = aggregationResults.iterator(); iterator.hasNext(); ) {
            BasicDBObject dbObject = (BasicDBObject) iterator.next();
            enterpriseIdSet.add(dbObject.getString("_id"));
        }
        return enterpriseIdSet;
    }

    @Override
    public Set<String> findDistinctRelatedEnterpriseIdSet() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("relatedEnterpriseId").addToSet("relatedEnterpriseId")
                        .as("enterpriseIdSet").count().as("value"));
        AggregationResults aggregationResults = mongoTemplate.aggregate(aggregation,
                RelatedEnterprise.class, BasicDBObject.class);
        Set<String> enterpriseIdSet = Sets.newHashSet();
        for (Iterator iterator = aggregationResults.iterator(); iterator.hasNext(); ) {
            BasicDBObject dbObject = (BasicDBObject) iterator.next();
            String enterpriseId = dbObject.getString("_id");
            enterpriseIdSet.add(enterpriseId);
        }
        return enterpriseIdSet;
    }

    @Override
    public Set<String> findDistinctEnterpriseIdSet() {
        String[] fields = {"id"};
        String reference = "id";
        String alias = "enterpriseIdSet";
        return findDistinctFieldSet(reference, alias, EnterpriseInfo.class, fields);
    }

    @Override
    public Set<String> findDistinctEnterpriseNameSet() {
        String[] fields = {"enterpriseName"};
        String reference = "enterpriseName";
        String alias = "enterpriseNameSet";
        return findDistinctFieldSet(reference, alias, EnterpriseInfo.class, fields);
    }

    private Set<String> findDistinctFieldSet(String reference, String alias,
                                             Class<?> inputType,
                                             String... groupFields){
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group(groupFields).addToSet(reference)
                        .as(alias).count().as("value"));
        AggregationResults aggregationResults = mongoTemplate.aggregate(aggregation,
                inputType, BasicDBObject.class);
        Set<String> enterpriseNameSet = Sets.newHashSet();
        for (Iterator iterator = aggregationResults.iterator(); iterator.hasNext(); ) {
            BasicDBObject dbObject = (BasicDBObject) iterator.next();
            String enterpriseName = dbObject.getString("_id");
            enterpriseNameSet.add(enterpriseName);
        }
        return enterpriseNameSet;
    }

    @Override
    public JSONArray findOpinionDetailsSnapshotInfoList(String isWarning, Integer limit) {
        JSONArray result = new JSONArray();
        DBObject isWarningDBObject = new BasicDBObject("isWarning", isWarning);
        DBObject fieldObject = new BasicDBObject();
        fieldObject.put("id", true);
        fieldObject.put("enterpriseId", true);
        fieldObject.put("enterpriseName", true);
        fieldObject.put("articleId", true);
        Query query = new BasicQuery(isWarningDBObject, fieldObject);
        query.limit(limit);
        List<OpinionDetailsSnapshot> list = mongoTemplate.find(query, OpinionDetailsSnapshot.class);
        for(OpinionDetailsSnapshot item : list){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("odsId", item.getId());
            jsonObject.put("enterpriseId", item.getEnterpriseId());
            jsonObject.put("enterpriseName", item.getEnterpriseName());
            jsonObject.put("article", mongoTemplate.findById(item.getArticleId(), Article.class));
            result.add(jsonObject);
        }
        return result;
    }
    
    @Override
    public List<OpinionRisk> findOpinionRiskListByIsWarning(String isWarning, Integer limit) {
    	DBObject isWarningDBObject = new BasicDBObject("isWarning", "0");
    	Query query = new BasicQuery(isWarningDBObject);
    	query.limit(limit);
    	return mongoTemplate.find(query, OpinionRisk.class);
    }

    @Override
    public List<OpinionRisk> findOpinionRiskListByEntityOfLast7Days(String today, List<String> enterpriseIds) {
        DBObject articleIdDBObject = new BasicDBObject("enterpriseId",
                new BasicDBObject("$in", enterpriseIds));
        Date todayDate = DateUtils.parseDate(today, DateUtils.DATE_FORMAT);
        List<String> dateList = DateUtils.getDateList(todayDate, 7);
        DBObject publishDateDBObject = new BasicDBObject("publishDate",
                new BasicDBObject("$in", dateList));
        BasicDBList andList = new BasicDBList();
        andList.add(articleIdDBObject);
        andList.add(publishDateDBObject);
        DBObject andDBObject = new BasicDBObject("$and", andList);
        Query query = new BasicQuery(andDBObject);
        return mongoTemplate.find(query, OpinionRisk.class);
    }

    @Override
    public List<OpinionDetailsSnapshot> findListByEnterpriseIdListAndPublishDateList(JSONArray enterpriseIdList, List<String> publishDateList) {
        DBObject enterpriseIdDBObject = new BasicDBObject("enterpriseId",
                new BasicDBObject("$in", enterpriseIdList));
        DBObject publishDateDBObject = new BasicDBObject("publishDate",
                new BasicDBObject("$in", publishDateList));
        BasicDBList andList = new BasicDBList();
        andList.add(enterpriseIdDBObject);
        andList.add(publishDateDBObject);
        DBObject andDBObject = new BasicDBObject("$and", andList);
        Query query = new BasicQuery(andDBObject);
        return mongoTemplate.find(query, OpinionDetailsSnapshot.class);
    }
}
