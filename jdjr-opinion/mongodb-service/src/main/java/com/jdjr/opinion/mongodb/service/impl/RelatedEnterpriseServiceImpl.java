package com.jdjr.opinion.mongodb.service.impl;

import com.google.common.collect.Lists;
import com.jdjr.opinion.mongodb.entity.OpinionDetailsSnapshot;
import com.jdjr.opinion.mongodb.entity.OpinionRisk;
import com.jdjr.opinion.mongodb.entity.RelatedEnterprise;
import com.jdjr.opinion.mongodb.entity.RelatedEnterpriseMsg;
import com.jdjr.opinion.mongodb.repository.RelatedEnterpriseRepository;
import com.jdjr.opinion.mongodb.service.OpinionRiskService;
import com.jdjr.opinion.mongodb.service.RelatedEnterpriseService;
import com.jdjr.opinion.utils.DateUtils;
import com.jdjr.opinion.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: RelatedEnterpriseRepository</p>
 * <p>Description: 相关企业信息接口实现类</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
@Service
public class RelatedEnterpriseServiceImpl implements RelatedEnterpriseService {
    @Autowired
    private RelatedEnterpriseRepository relatedEnterpriseRepository;

    @Autowired
    private OpinionRiskService opinionRiskService;

    @Override
    public boolean save(RelatedEnterprise entity) {
        boolean isExist = this.isExist(entity.getEnterpriseId(), entity.getRelatedEnterpriseId());
        if (!isExist) {
            relatedEnterpriseRepository.save(entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isExist(String enterpriseId, String relatedEnterpriseId) {
        RelatedEnterprise relatedEnterprise = relatedEnterpriseRepository.findByEnterpriseIdAndRelatedEnterpriseId(enterpriseId, relatedEnterpriseId);
        return (relatedEnterprise != null);
    }

    @Override
    public boolean isExistByEnterpriseIdAndEnterpriseName(String enterpriseId, String enterpriseName) {
        List<RelatedEnterprise> list = relatedEnterpriseRepository.findListByEnterpriseIdAndEnterpriseName(
                enterpriseId, enterpriseName);
        return (list != null && list.size() > 0);
    }

    @Override
    public boolean batchAdd(List<RelatedEnterprise> list) {
        for(RelatedEnterprise item : list){
            if(!isExistByEnterpriseIdAndEnterpriseName(item.getEnterpriseId(), item.getEnterpriseName())){
                relatedEnterpriseRepository.save(item);
            }
        }
        return true;
    }

    @Override
    public List<RelatedEnterprise> findByEnterpriseId(String enterpriseId) {
        return relatedEnterpriseRepository.findListByEnterpriseId(enterpriseId);
    }

    @Override
    public List<RelatedEnterpriseMsg> findOpinionInfoOfRelatedEnterprise(OpinionDetailsSnapshot entity) {
        List<RelatedEnterpriseMsg> result = Lists.newArrayList();
        Date yesterday = DateUtils.getYesterdayDate(DateUtils.currentDate());
        String yesterdayStr = DateUtils.formatDate(yesterday);
        List<RelatedEnterprise> list = relatedEnterpriseRepository.findListByEnterpriseId(entity.getEnterpriseId());
        if (list != null) {
            list.forEach(item -> {
                OpinionRisk risk = opinionRiskService.findByEntity(item.getRelatedEnterpriseId(), yesterdayStr);
                double overallPressure = 0D;
                if (risk != null) {
                    overallPressure = NumberUtils.formatDoubleNewScale(risk.getTransformedNegativePressureIndex(), 2);
                }
                result.add(new RelatedEnterpriseMsg(item.getEnterpriseName(),item.getRelatedEnterpriseId(),overallPressure));
            });
        }

        return result;
    }

    @Override
    public List<RelatedEnterprise> findByEnterpriseName(String enterpriseName) {
        return relatedEnterpriseRepository.findListByEnterpriseName(enterpriseName);
    }
}
