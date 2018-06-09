package com.jdjr.opinion.mongodb.repository;

import com.jdjr.opinion.mongodb.entity.RelatedPersonOpinion;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <p>Title: RelatedPersonOpinionRepository</p>
 * <p>Description: 相关人物舆情信息数据库接口</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
public interface RelatedPersonOpinionRepository extends MongoRepository<RelatedPersonOpinion, String> {

    RelatedPersonOpinion findByRelatedPersonNameAndEnterpriseIdAndAndArticleId(
            String relatedPersonName, String enterpriseId, String articleId);

}
