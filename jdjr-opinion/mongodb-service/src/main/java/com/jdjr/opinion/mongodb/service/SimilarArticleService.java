package com.jdjr.opinion.mongodb.service;

import com.jdjr.opinion.mongodb.entity.SimilarArticle;

import java.util.List;

public interface SimilarArticleService {

    /**
     * <p>Description: 相似文章信息添加</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月28日
     */
    boolean save(SimilarArticle entity);

    /**
     * 根据相似文章id判断该篇文章是否存在
     *
     * @param similarArticleId 相似文章id
     * @return
     */
    boolean isExist(String similarArticleId);

    /**
     * <p>Description: 相似文章查询（根据文章id查询）</p>
     *
     * @return
     * @author zhangtong
     * @date 2017年6月28日
     */
    List<SimilarArticle> findListByArticleId(String articleId);

    /**
     * 查询总记录数
     * @return
     */
    long findCount();

    boolean delete(String id);

    List<String> findIdList(int pageNumber, int pageSize);

    boolean deleteAll();

}
