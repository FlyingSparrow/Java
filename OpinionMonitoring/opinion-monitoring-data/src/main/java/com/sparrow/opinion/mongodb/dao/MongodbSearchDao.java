package com.sparrow.opinion.mongodb.dao;

import com.sparrow.opinion.base.bean.PageResult;
import com.sparrow.opinion.mongodb.entity.Article;

import java.util.List;
import java.util.Set;

public interface MongodbSearchDao {
    
    /**
     * <p>Description: 只查询唯一的keyword值</p>
     *
     * @return
     * @author zhangtong
     * @date 2017年6月27日
     */
    List<String> finArtcileKeywords();

    /**
     * <p>Description: 对文章进行分页查询</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年7月4日
     */
    PageResult<Article> findListByEntity(Article entity);

    /**
     * <p>Description: 根据文章id查询文章列表</p>
     *
     * @param ids
     * @return
     * @author Wangjianchun
     * @date 2017年7月18日
     */

    List<Article> findArticleListByIds(Set<String> ids);

}
