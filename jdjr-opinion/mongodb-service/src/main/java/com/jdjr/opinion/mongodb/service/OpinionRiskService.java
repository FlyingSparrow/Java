package com.jdjr.opinion.mongodb.service;

import com.alibaba.fastjson.JSONObject;
import com.jdjr.opinion.mongodb.entity.OpinionRisk;

import java.util.List;
import java.util.Map;

public interface OpinionRiskService {

    /**
     * <p>Description: 添加</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月26日
     */
    boolean save(OpinionRisk entity);

    OpinionRisk saveResult(OpinionRisk entity);

    /**
     * <p>Description: 批量添加</p>
     *
     * @param list
     * @return
     * @author zhangtong
     * @date 2017年6月26日
     */
    boolean batchAdd(List<OpinionRisk> list);
    /**
     * <p>Description: 根据id</p>
     *
     * @param id
     * @return
     * @author zhangtong
     * @date 2017年6月26日
     */
    OpinionRisk findById(String id);

    /**
     * <p>Description: 根据公司id和发布时间查询</p>
     *
     * @param enterpriseId
     * @param publishDate
     * @return
     * @author zhangtong
     * @date 2017年6月26日
     */
    OpinionRisk findByEntity(String enterpriseId, String publishDate);

    /**
     * <p>Description: 根据公司id和发布时间范围查询舆情风险</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月26日
     */
    List<OpinionRisk> findListByEntity(OpinionRisk entity);

    /**
     * <p>Description: 根据公司id和发布时间in查询舆情风险</p>
     *
     * @param enterpriseId
     * @param publishDate
     * @return
     * @author zhangtong
     * @date 2017年6月26日
     */
    List<OpinionRisk> findListByInPublishDate(String enterpriseId, List<String> publishDate);

    /**
     * <p>Description: 根据公司id和发布时间查询是否存在</p>
     *
     * @param enterpriseId
     * @param publishDate
     * @return
     * @author zhangtong
     * @date 2017年6月26日
     */
    boolean findExistByEntity(String enterpriseId, String publishDate);

    boolean update(OpinionRisk entity);

    Map<String, String> findEnterpriseInfoMap(String publishDate);

    List<OpinionRisk> findByEnterpriseIdList(List<String> enterpriseIds, List<String> publishDate);


    /**
     * 查询舆情量化指标信息
     *
     * @param entity
     * @return
     */
    JSONObject findOpinionQuantitativeIndicator(OpinionRisk entity);

    /**
     * 查询最近7天的舆情量化指标信息
     *
     * @param entity
     * @return
     */
    JSONObject findOpinionQuantitativeIndicatorOfLast7Days(OpinionRisk entity);

    /**
     * 查询舆情压力趋势
     *
     * @param entity
     * @return
     */
    JSONObject findOpinionPressureTrend(OpinionRisk entity);

    /**
     * <p>Description: 根据公司id和发布时间范围查询舆情风险</p>
     *
     * @param enterpriseIdList
     * @param publishDateList
     * @return
     * @author zhangtong
     * @date 2017年6月26日
     */
    List<OpinionRisk> findListOpinionRiskByInPublishDate(List<String> enterpriseIdList, List<String> publishDateList);

    List<OpinionRisk> findOpinionRiskListByEntity(String yesterday, String today, List<String> enterpriseIds);

    /**
     * 查询最近7天的舆情风险信息
     * @param today
     * @param enterpriseIds
     * @return
     */
    List<OpinionRisk> findOpinionRiskListByEntityOfLast7Days(String today, List<String> enterpriseIds);

    /**
     * 查询总记录数
     * @return
     */
    long findCount();

    boolean delete(String id);

    List<String> findIdList(int pageNumber, int pageSize);

    boolean deleteAll();

}
