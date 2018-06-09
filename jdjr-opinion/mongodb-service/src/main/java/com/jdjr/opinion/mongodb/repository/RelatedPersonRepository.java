package com.jdjr.opinion.mongodb.repository;

import com.jdjr.opinion.mongodb.entity.RelatedPerson;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * <p>Title: RelatedPersonRepository</p>
 * <p>Description: 相关人物信息数据库接口<</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
public interface RelatedPersonRepository extends MongoRepository<RelatedPerson, String> {

    /**
     * <p>Description: 根据(企业id)查询相关人物信息</p>
     *
     * @param enterpriseId
     * @return
     * @author zhangtong
     * @date 2017年6月29日
     */
    List<RelatedPerson> findListByEnterpriseId(String enterpriseId);

    RelatedPerson findRelatedPeopleByEnterpriseIdAndName(String enterpriseId, String name);

}
