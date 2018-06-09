package com.jdjr.opinion.mongodb.repository;

import com.jdjr.opinion.mongodb.entity.OpinionDetailsSnapshot;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: OpinionDetailsSnapshotRepository</p>
 * <p>Description: 舆情明细快照数据库接口</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
public interface OpinionDetailsSnapshotRepository extends MongoRepository<OpinionDetailsSnapshot, String> {

    OpinionDetailsSnapshot findByEnterpriseIdAndTitleArticleFingerprint(String enterpriseId,
                                                                        String titleArticleFingerprint);

    OpinionDetailsSnapshot findByEnterpriseIdAndContentArticleFingerprint(String enterpriseId,
                                                                          String contentArticleFingerprint);

    OpinionDetailsSnapshot findByEnterpriseIdAndArticleId(String enterpriseId, String articleId);

    List<OpinionDetailsSnapshot> findByArticleIdOrderByPublishDatetimeDesc(String articleId);


    long countByEnterpriseId(String enterpriseId);

    long countByEnterpriseIdAndCrawlerDateTimeBetween(String enterpriseId, Date startCrawlerDateTime,
                                                      Date endCrawlerDateTime);

    List<OpinionDetailsSnapshot> findByEnterpriseIdAndPublishDateOrderByPublishDatetime(
            String enterpriseId, String publishDate);

    List<OpinionDetailsSnapshot> findByPublishDateAndIsAnalyzedOrderByPublishDatetimeDesc(String publishDate, String isAnalyzed);

    List<OpinionDetailsSnapshot> findByIsAnalyzedOrderByPublishDatetimeDesc(String isAnalyzed);

    List<OpinionDetailsSnapshot> findByEnterpriseIdAndPublishDate(
            String enterpriseId, String publishDate);

    List<OpinionDetailsSnapshot> findByEnterpriseIdAndPublishDateIn(String enterpriseId, List<String> publishDateList);

}
