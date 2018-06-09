package com.jdjr.opinion.mongodb.service;

import com.jdjr.opinion.mongodb.entity.RelatedPersonOpinion;

import java.util.List;

public interface RelatedPersonOpinionService {

    /**
     * <p>Description: 添加相关人物舆情信息</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月29日
     */
    boolean save(RelatedPersonOpinion entity);

    boolean isExists(String relatedPersonName, String enterpriseId, String articleId);

    /**
     * <p>Description: 根据(时间范围，)相关人物舆情信息</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月29日
     */
    List<RelatedPersonOpinion> findListByEntity(RelatedPersonOpinion entity);

    /**
     * <p>Description: 根据(时间范围，公司id，情感)相关人物舆情信息数量</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月29日
     */
    Long findCountByEntity(RelatedPersonOpinion entity);

    RelatedPersonOpinion findById(String id);

    /**
     * 查询总记录数
     * @return
     */
    long findCount();

    boolean delete(String id);

    List<String> findIdList(int pageNumber, int pageSize);

    boolean deleteAll();
}
