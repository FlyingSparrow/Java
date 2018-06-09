package com.jdjr.opinion.mongodb.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jdjr.opinion.base.bean.PageResult;
import com.jdjr.opinion.mongodb.entity.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MongodbSearchDao {

    /**
     * <p>Description: 根据(时间范围，情感，媒体，关键词。排序方式)查询事件文章信息</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月27日
     */
    PageResult<EventArticle> findPageEventArticleByEntity(EventArticle entity);

    /*
     *
     */
    EventArticle findEventArticleByCompanyAndArtId(String company, String articleId);

    /**
     * <p>Description:  根据(文章id以及排序规则查询)事件文章信息</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月28日
     */
    List<EventArticle> findListEventArticleByEntity(EventArticle entity);

    /**
     * <p>Description: 根据(根据企业id，时间范围，情感类型，媒体查询，根据发布时间 热度排序)查询舆情明细快照</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月28日
     */
    List<OpinionDetailsSnapshot> findListOpinionDetailsSnapshotByEntity(OpinionDetailsSnapshot entity);

    /**
     * <p>Description: 根据(相关人物id，时间区间)查询相关人物舆情信息</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月29日
     */
    List<RelatedPersonOpinion> findListRelatedPersonOpinionByEntity(RelatedPersonOpinion entity);

    /**
     * <p>Description: 根据(相关人物id，时间区间)查询相关人物舆情信息</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月29日
     */
    Long findCountRelatedPersonOpinionByEntity(RelatedPersonOpinion entity);

    /**
     * <p>Description: 只查询唯一的keyword值</p>
     *
     * @return
     * @author zhangtong
     * @date 2017年6月27日
     */
    List<String> finArtcileKeywords();

    /**
     * <p>Description: 对文章进行分页查询</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年7月4日
     */
    PageResult<Article> findListByEntity(Article entity);

    /**
     * 根据企业ID查询企业相关信息
     *
     * @param enterpriseId
     * @return
     */
    EnterpriseInfo findEnterpriseInfoByEnterpriseId(String enterpriseId);

    /**
     * 分页查询企业相关信息
     *
     * @return
     */
    PageResult<EnterpriseInfo> findPageEnterpriseInfoList(int pageNumber, int pageSize);

    /**
     * <p>Description: 查询事件趋势信息</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年7月14日
     */
    JSONObject findEventTrend(OpinionDetailsSnapshot entity);

    /**
     * <p>Description: 查询首发媒体溯源</p>
     *
     * @param eventId
     * @return
     * @author Wangjianchun
     * @date 2017年7月17日
     */
    JSONArray findEventPropagation(String eventId);

    /**
     * <p>Description: 查询媒体传播分析</p>
     *
     * @param eventId
     * @return
     * @author Wangjianchun
     * @date 2017年7月18日
     */
    JSONObject findMediaPropagation(String eventId);

    /**
     * <p>Description: 查询关键词演化分析</p>
     *
     * @param eventId
     * @return
     * @author Wangjianchun
     * @date 2017年7月18日
     */
    JSONObject findKeywordEvolution(String eventId);

    /**
     * <p>Description: 查询观点分析</p>
     *
     * @param eventId
     * @return
     * @author Wangjianchun
     * @date 2017年7月18日
     */
    JSONObject findViewpointAnalysis(String eventId);

    /**
     * <p>Description: 根据文章id查询文章列表</p>
     *
     * @param ids
     * @return
     * @author Wangjianchun
     * @date 2017年7月18日
     */

    List<Article> findArticleListByIds(Set<String> ids);

    /**
     * <p>Description: 根据事件id查询事件的新闻列表</p>
     *
     * @param eventId
     * @return
     * @author Wangjianchun
     * @date 2017年7月18日
     */
    JSONObject findNewsList(String eventId);

    /**
     * <p>Description: 根据(根据企业id，时间范围，情感类型，媒体查询，根据发布时间 热度排序)查询舆情明细快照</p>
     *
     * @param entity
     * @param articleIdList
     * @return
     * @author zhangtong
     * @date 2017年6月28日
     */
    PageResult<OpinionDetailsSnapshot> findPageOpinionDetailsSnapshotByEntity(OpinionDetailsSnapshot entity, Set<String> articleIdList);

    /**
     * <p>Description: 根据(根据企业id，时间范围，情感类型，媒体查询，根据发布时间 热度排序)查询舆情明细快照</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月28日
     */
    PageResult<OpinionDetailsSnapshot> findPageOpinionDetailsSnapshotByEntity(OpinionDetailsSnapshot entity);

    /**
     * <p>Description:  根据（公司id，时间范围，情感，文章类型,查询多少条记录）</p>
     *
     * @param entity
     * @return
     * @author zy
     * @date 2017年8月18日
     */
    Long findOpinionDetailsSnapshotCountByEntity(OpinionDetailsSnapshot entity);

    /**
     * 根据事件分类查询事件信息列表
     *
     * @param entity 事件参数
     * @return 事件信息列表
     * @date 2017年7月31日
     * @author Wangjianchun
     */
    JSONArray findListByEntity(OpinionDetailsSnapshot entity);

    /**
     * 根据
     *
     * @param enterpriseIdList
     * @param limit
     * @return
     */
    List<OpinionDetailsSnapshot> findListByEnterpriseIdListLimit(List<String> enterpriseIdList, Integer limit);

    JSONArray findByEnterpriseId(String enterpriseId, Date startTime, Date endTime);

    /**
     * <p>Description: 根据公司id和发布时间范围查询舆情风险</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月26日
     */
    List<OpinionRisk> findListOpinionRiskByEntity(OpinionRisk entity);

    /**
     * <p>Description: 根据公司id和发布时间范围查询舆情风险</p>
     *
     * @param enterpriseId
     * @param publishDate
     * @return
     * @author zhangtong
     * @date 2017年6月26日
     */
    List<OpinionRisk> findListOpinionRiskByInPublishDate(String enterpriseId, List<String> publishDate);

    Map<String, String> findOpinionRiskGroupEnterprise(String publishDate);

    List<OpinionRisk> findOpinionRiskByEnterpriseIdList(List<String> enterpriseIds, List<String> publishDate);

    List<EventInfo> findListEventInfoByCoreArticleId(String coreArticleId, String enterpriseId);

    /**
     * <p>Description: 根据公司id和发布时间范围查询舆情风险</p>
     *
     * @param enterpriseId
     * @param publishDate
     * @return
     * @author zhangtong
     * @date 2017年6月26日
     */
    List<OpinionRisk> findListOpinionRiskByInPublishDate(List<String> enterpriseId, List<String> publishDate);

    List<OpinionRisk> findOpinionRiskListByEntity(String yesterday, String today, List<String> enterpriseIds);

    /**
     * 查询最近7天的舆情风险信息
     * @param today
     * @param enterpriseIds
     * @return
     */
    List<OpinionRisk> findOpinionRiskListByEntityOfLast7Days(String today, List<String> enterpriseIds);

    Set<String> findDistinctEnterpriseIdSetByPublishDate(String crawlerDate);

    Set<String> findDistinctRelatedEnterpriseIdSet();

    Set<String> findDistinctEnterpriseIdSet();

    Set<String> findDistinctEnterpriseNameSet();

    JSONArray findOpinionDetailsSnapshotInfoList(String isWarning, Integer limit);

	List<OpinionRisk> findOpinionRiskListByIsWarning(String isWarning, Integer limit);

    List<OpinionDetailsSnapshot> findListByEnterpriseIdListAndPublishDateList(JSONArray enterpriseIdList, List<String> publishDateList);

}
