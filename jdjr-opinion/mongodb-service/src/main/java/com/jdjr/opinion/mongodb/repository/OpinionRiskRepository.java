package com.jdjr.opinion.mongodb.repository;

import com.jdjr.opinion.mongodb.entity.OpinionRisk;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OpinionRiskRepository extends MongoRepository<OpinionRisk, String> {

    OpinionRisk findByEnterpriseIdAndPublishDate(String enterpriseId, String publishDate);

    List<OpinionRisk> findByEnterpriseIdAndPublishDateIn(String enterpriseId, List<String> publishDateList);

    List<OpinionRisk> findByEnterpriseIdInAndPublishDateIn(List<String> enterpriseIdList, List<String> publishDateList);
}
