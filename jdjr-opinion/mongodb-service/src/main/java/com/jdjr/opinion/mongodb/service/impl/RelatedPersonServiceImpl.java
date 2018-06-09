package com.jdjr.opinion.mongodb.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jdjr.opinion.constants.SysConst;
import com.jdjr.opinion.mongodb.entity.RelatedPerson;
import com.jdjr.opinion.mongodb.entity.RelatedPersonMsg;
import com.jdjr.opinion.mongodb.entity.RelatedPersonOpinion;
import com.jdjr.opinion.mongodb.repository.RelatedPersonRepository;
import com.jdjr.opinion.mongodb.service.RelatedPersonOpinionService;
import com.jdjr.opinion.mongodb.service.RelatedPersonService;
import com.jdjr.opinion.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title: RelatedPersonRepository</p>
 * <p>Description: 相关人物信息接口实现类</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
@Service
public class RelatedPersonServiceImpl implements RelatedPersonService {
    @Autowired
    private RelatedPersonRepository relatedPersonRepository;

    @Autowired
    private RelatedPersonOpinionService relatedPersonOpinionService;

    @Override
    public boolean save(RelatedPerson entity) {
        boolean isExist = this.isExist(entity.getEnterpriseId(), entity.getName());
        if (!isExist) {
            relatedPersonRepository.save(entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isExist(String enterpriseId, String relatedPersonName) {
        RelatedPerson relatedPerson = this.findByEnterpriseIdAndName(enterpriseId, relatedPersonName);
        return relatedPerson != null;
    }

    @Override
    public boolean batchAdd(List<RelatedPerson> list) {
        for(RelatedPerson item : list){
            if(!isExist(item.getEnterpriseId(), item.getName())){
                relatedPersonRepository.save(item);
            }
        }
        return true;
    }

    @Override
    public RelatedPerson findByEnterpriseIdAndName(String enterpriseId, String name) {
        return relatedPersonRepository.findRelatedPeopleByEnterpriseIdAndName(enterpriseId, name);
    }

    @Override
    public List<RelatedPerson> findListByEnterpriseId(String enterpriseId) {
        return relatedPersonRepository.findListByEnterpriseId(enterpriseId);
    }

    @Override
    public List<RelatedPersonMsg> findPersonListByEntity(RelatedPersonOpinion entity) {
        String enterpriseId = entity.getEnterpriseId();
        List<RelatedPerson> relatedPersonList = this.findListByEnterpriseId(enterpriseId);
        List<RelatedPersonMsg> result = Lists.newArrayList();

        Long totalRPOCount = relatedPersonList.stream().filter(relatedPerson -> !relatedPerson.getName().equals("未知")).map(relatedPerson -> {
            String relatedPersonName = relatedPerson.getName();
            entity.setRelatedPersonName(relatedPersonName);
            entity.setEmotion(null);
            Long allRPOCount = relatedPersonOpinionService.findCountByEntity(entity);
            return allRPOCount;
        }).collect(Collectors.summingLong(sa -> sa));

        relatedPersonList.stream().filter(relatedPerson -> !relatedPerson.getName().equals("未知")).forEach(relatedPerson -> {
            String relatedPersonName = relatedPerson.getName();
            entity.setRelatedPersonName(relatedPersonName);
            entity.setEmotion(null);
            Long allRPOCount = relatedPersonOpinionService.findCountByEntity(entity);

            entity.setEmotion(SysConst.EmotionType.NEGATIVE.getCode());
            Long negativeRPOCount = relatedPersonOpinionService.findCountByEntity(entity);
            String negativePressureIndex = "";
            negativePressureIndex = NumberUtils.divideMultiply(negativeRPOCount, allRPOCount, 2).toString();

            String pessureProportion = NumberUtils.divide(allRPOCount, totalRPOCount, 2).toString();

            result.add(new RelatedPersonMsg(relatedPersonName,negativePressureIndex,pessureProportion));
        });
        return result;
    }
}
