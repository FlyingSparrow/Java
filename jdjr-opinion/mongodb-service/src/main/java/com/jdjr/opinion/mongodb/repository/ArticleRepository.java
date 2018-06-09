package com.jdjr.opinion.mongodb.repository;

import com.jdjr.opinion.mongodb.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * <p>Title: ArticleRepository</p>
 * <p>Description: 文章信息数据库接口</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
public interface ArticleRepository extends MongoRepository<Article, String> {

    List<Article> findByUrlMD5(String urlMD5);

    Page<Article> findByIsAnalyzed(String isAnalyzed, Pageable pageable);

}
