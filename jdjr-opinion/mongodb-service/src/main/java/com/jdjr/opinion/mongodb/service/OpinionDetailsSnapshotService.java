package com.jdjr.opinion.mongodb.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jdjr.opinion.base.bean.PageResult;
import com.jdjr.opinion.mongodb.entity.OpinionDetailsSnapshot;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OpinionDetailsSnapshotService {

    /**
     * <p>Description:  添加舆情明细快照</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月28日
     */
    String save(OpinionDetailsSnapshot entity);

    /**
     * <p>Description:  根据文章指纹判断是否存在相应的记录</p>
     *
     * @param enterpriseId 企业id
     * @return
     * @author zhangtong
     * @date 2017年7月19日
     */
    boolean isExistByEnterpriseId(String enterpriseId);

    /**
     * 根据公司id 文章id 查询舆情明细快照信息
     *
     * @param enterpriseId
     * @param articleId
     * @return
     */
    OpinionDetailsSnapshot findByEnterpriseIdAndArticleId(String enterpriseId, String articleId);
    /**
     * 根据公司id 文章标题的文章指纹查询舆情明细快照信息
     *
     * @param enterpriseId
     * @param titleArticleFingerprint
     * @return
     */
    OpinionDetailsSnapshot findByEnterpriseIdAndTitleArticleFingerprint(String enterpriseId, String titleArticleFingerprint);

    /**
     * 根据公司id 文章内容的文章指纹查询舆情明细快照信息
     *
     * @param enterpriseId
     * @param contentArticleFingerprint
     * @return
     */
    OpinionDetailsSnapshot findByEnterpriseIdAndContentArticleFingerprint(String enterpriseId, String contentArticleFingerprint);

    /**
     * <p>Description:  根据文章指纹判断是否存在相应的记录</p>
     *
     * @param enterpriseId            企业id
     * @param titleArticleFingerprint 文章标题的文章指纹
     * @return
     * @author Wangjianchun
     * @date 2017年7月19日
     */
    boolean isExistByEnterpriseIdAndTitleArticleFingerprint(String enterpriseId, String titleArticleFingerprint);

    /**
     * <p>Description:  根据文章指纹判断是否存在相应的记录</p>
     *
     * @param enterpriseId              企业id
     * @param contentArticleFingerprint 文章内容的文章指纹
     * @return
     * @author Wangjianchun
     * @date 2017年7月19日
     */
    boolean isExistByEnterpriseIdAndContentArticleFingerprint(String enterpriseId, String contentArticleFingerprint);


    /**
     * <p>Description:  根据id查询舆情明细快照</p>
     *
     * @param id
     * @return
     * @author zhangtong
     * @date 2017年6月28日
     */
    OpinionDetailsSnapshot findById(String id);

    /**
     * <p>Description:  根据（公司id，时间范围，情感，查询多少条记录）</p>
     *
     * @param entity
     * @return
     * @author zy
     * @date 2017年8月18日
     */
    Long findCountByEntity(OpinionDetailsSnapshot entity);


    /**
     * <p>Description:  更新文章类型</p>
     *
     * @param articleType
     * @param id
     * @return
     * @author zhangtong
     * @date 2017年6月28日
     */
    boolean updateArticleType(String articleType, String id);
    /**
     * <p>Description: 根据(根据企业id，时间范围，情感类型，媒体查询，根据发布时间 热度排序)查询舆情明细快照</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月28日
     */
    List<OpinionDetailsSnapshot> findListByEntity(OpinionDetailsSnapshot entity);


    List<OpinionDetailsSnapshot> findListByArticleId(String articleId);

    /**
     * <p>Description: 根据(根据企业id，时间范围，情感类型，媒体查询，根据发布时间 热度排序)查询舆情明细快照</p>
     *
     * @param entity
     * @param articleIdList
     * @return
     * @author zhangtong
     * @date 2017年6月28日
     */
    PageResult<OpinionDetailsSnapshot> findPageByEntity(OpinionDetailsSnapshot entity, Set<String> articleIdList);

    /**
     * <p>Description: 根据(根据企业id，时间范围，情感类型，媒体查询，根据发布时间 热度排序)查询舆情明细快照</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月28日
     */
    PageResult<OpinionDetailsSnapshot> findPageByEntity(OpinionDetailsSnapshot entity);

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
     * @author zhangtong
     * @date 2017年6月28日
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
     * <p>Description: 查询观点分析词云</p>
     *
     * @param eventId
     * @return
     * @author Wangjianchun
     * @date 2017年7月18日
     */
    JSONObject findViewpointAnalysisWordCloud(String eventId) throws Exception;

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
     * <p>Description:  批量添加舆情明细快照</p>
     *
     * @param list
     * @return
     * @author Wangjianchun
     * @date 2017年7月19日
     */
    boolean batchAdd(List<OpinionDetailsSnapshot> list);

    List<OpinionDetailsSnapshot> findListByEnterpriseIdListLimit(List<String> enterpriseIdList, Integer limit);

    /**
     * 查询总记录数
     * @return
     */
    long findCount();

    boolean delete(String id);

    List<String> findIdList(int pageNumber, int pageSize);

    boolean deleteAll();

    List<OpinionDetailsSnapshot> findByEnterpriseIdAndPublishDate(String enterpriseId, String publishDate);

    JSONObject findOpinionCountInfo(String enterpriseId, String publishDate);

    /**
     * 查询企业一段时间的舆情数信息
     * @param enterpriseId
     * @param publishDateList
     * @return
     */
    JSONObject findOpinionCountInfo(JSONArray enterpriseId, List<String> publishDateList);

    /**
     * 根据企业id查询该企业最近30天的舆情数
     * @param enterpriseId
     * @return
     */
    JSONObject findOpinionCountByEnterpriseIdListAndLast30Days(JSONArray enterpriseId);

}
