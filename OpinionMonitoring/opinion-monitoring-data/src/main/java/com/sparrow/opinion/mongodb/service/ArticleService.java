package com.sparrow.opinion.mongodb.service;

import com.sparrow.opinion.mongodb.entity.Article;

import java.util.List;

public interface ArticleService {

    String add(Article entity);

    Article findById(String id);

    boolean updateAnalyzedState(String id);

    boolean isExist(String urlMD5);

    /**
     * 查询总记录数
     * @return
     */
    long findCount();

    boolean delete(String id);

    List<String> findIdList(int pageNumber, int pageSize);

    boolean deleteAll();

    boolean save(Article entity);
}
