package com.jdjr.opinion.mongodb.service;

import com.alibaba.fastjson.JSONArray;
import com.jdjr.opinion.mongodb.entity.RelatedPerson;
import com.jdjr.opinion.mongodb.entity.RelatedPersonMsg;
import com.jdjr.opinion.mongodb.entity.RelatedPersonOpinion;

import java.util.List;

public interface RelatedPersonService {

    /**
     * <p>Description:  添加相关人物信息</p>
     *
     * @return
     * @author zhangtong
     * @date 2017年6月29日
     */
    boolean save(RelatedPerson entity);

    boolean isExist(String enterpriseId, String relatedPersonName);

    /**
     * <p>Description:  批量添加相关人物信息</p>
     *
     * @param list
     * @return
     * @author zhangtong
     * @date 2017年6月29日
     */
    boolean batchAdd(List<RelatedPerson> list);

    /**
     *
     * <p>Description: 根据(企业id,相关人物名称)查询相关人物信息</p>
     * @param enterpriseId
     * @param name
     * @return
     * @author zhangtong
     * @date 2017年6月29日
     */
    RelatedPerson findByEnterpriseIdAndName(String enterpriseId, String name);

    /**
     * <p>Description: 根据(企业id)查询相关人物信息</p>
     *
     * @param enterpriseId
     * @return
     * @author zhangtong
     * @date 2017年6月29日
     */
    List<RelatedPerson> findListByEnterpriseId(String enterpriseId);

    /**
     * 查询企业的相关人物信息
     *
     * @param entity
     * @return
     */
    List<RelatedPersonMsg> findPersonListByEntity(RelatedPersonOpinion entity);
}
