package com.sparrow.opinion.mysql.repository;

import com.sparrow.opinion.mysql.entity.SimilarArticle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 * @create 2018/3/23
 */
public interface SimilarArticleRepository extends JpaRepository<SimilarArticle, Long> {
}
