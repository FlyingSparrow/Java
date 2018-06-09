package com.jdjr.opinion.mongodb.service;

import com.alibaba.fastjson.JSONArray;
import com.jdjr.opinion.mongodb.entity.OpinionDetailsSnapshot;
import com.jdjr.opinion.mongodb.entity.RelatedEnterprise;
import com.jdjr.opinion.mongodb.entity.RelatedEnterpriseMsg;

import java.util.List;

public interface RelatedEnterpriseService {

    /**
     * <p>Description: 相关企业信息添加方法</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月29日
     */
    boolean save(RelatedEnterprise entity);

    boolean isExist(String enterpriseId, String relatedEnterpriseId);

    boolean isExistByEnterpriseIdAndEnterpriseName(String enterpriseId, String enterpriseName);


    /**
     * <p>Description: 相关企业信息添加方法</p>
     *
     * @param list
     * @return
     * @author zhangtong
     * @date 2017年6月29日
     */
    boolean batchAdd(List<RelatedEnterprise> list);

    /**
     *
     * <p>Description: 根据企业id查看相关企业信息</p>
     * @param enterpriseId
     * @return
     * @author zhangtong
     * @date 2017年6月29日
     */
    List<RelatedEnterprise> findByEnterpriseId(String enterpriseId);

    /**
     * <p>Description: 查询相关企业的舆情信息</p>
     *
     * @param entity
     * @return
     * @author Wangjianchun
     * @date 2017年7月7日
     */
    List<RelatedEnterpriseMsg> findOpinionInfoOfRelatedEnterprise(OpinionDetailsSnapshot entity);

    List<RelatedEnterprise> findByEnterpriseName(String enterpriseName);
}
