package com.jdjr.opinion.mongodb.repository;

        import com.jdjr.opinion.mongodb.entity.RelatedEnterprise;
        import org.springframework.data.mongodb.repository.MongoRepository;

        import java.util.List;

/**
 * <p>Title: RelatedEnterpriseRepository</p>
 * <p>Description: 相关企业信息数据库接口</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
public interface RelatedEnterpriseRepository extends MongoRepository<RelatedEnterprise, String> {

    /**
     * <p>Description: 根据企业id查看相关企业信息</p>
     *
     * @param enterpriseId
     * @return
     * @author zhangtong
     * @date 2017年6月29日
     */
    List<RelatedEnterprise> findListByEnterpriseId(String enterpriseId);

    RelatedEnterprise findByEnterpriseIdAndRelatedEnterpriseId(String enterpriseId, String relatedEnterpriseId);

    List<RelatedEnterprise> findListByEnterpriseIdAndEnterpriseName(String enterpriseId, String enterpriseName);

    List<RelatedEnterprise> findListByEnterpriseName(String enterpriseName);
}
