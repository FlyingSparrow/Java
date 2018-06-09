package com.jdjr.opinion.mongodb.repository;

import com.jdjr.opinion.mongodb.entity.EnterpriseInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * <p>Title: EnterpriseInfoRepository</p>
 * <p>Description: 企业信息的数据库接口</p>
 *
 * @author wjc
 * @date 2017年6月19日
 */
public interface EnterpriseInfoRepository extends MongoRepository<EnterpriseInfo, String> {

    EnterpriseInfo findByEnterpriseName(String enterpriseName);

    List<EnterpriseInfo> findByEnterpriseNameContaining(String enterpriseName);

    List<EnterpriseInfo> findByIdIn(List<String> ids);

}
