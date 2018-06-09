package com.jdjr.opinion.mongodb.repository;

import com.jdjr.opinion.mongodb.entity.SimilarArticle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * <p>Title: SimilarArticleRepository</p>
 * <p>Description: 相似文章信息数据库接口<</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
public interface SimilarArticleRepository extends MongoRepository<SimilarArticle, String> {

    List<SimilarArticle> findTop10ByArticleIdOrderByPublishDateTimeDesc(String articleId);

    SimilarArticle findBySimilarArticleId(String similarArticleId);
}
