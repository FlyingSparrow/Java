package com.sparrow.opinion.mongodb.service;


import com.sparrow.opinion.mongodb.entity.Article;

import java.util.List;

public interface ArticleService {

    boolean save(Article entity);

    Article findById(String id);

    List<Article> findListByUrlMD5(String urlMD5);

    /**
     * 获取文章总数
     * @return
     */
    long findArticleCount();

    boolean delete(String id);

    List<Article> findIdsByPublishDate(String publishDate);
    
    long findArticleCountByCrawlerDate(String crawlerDate);

    String add(Article entity);
}
