package com.jdjr.opinion.mongodb.service;

import com.jdjr.opinion.mongodb.entity.EventArticle;

import java.util.List;

public interface EventArticleService {

    /**
     * <p>Description: 添加事件文章信息</p>
     *
     * @param entity
     * @author zhangtong
     * @date 2017年6月27日
     */
    boolean add(EventArticle entity);

    /**
     * <p>Description: 根据id查询事件文章信息</p>
     *
     * @param id
     * @author zhangtong
     * @date 2017年6月27日
     */
    EventArticle findById(String id);

    /**
     * <p>Description: 根据文章id按时间排序</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月28日
     */
    List<EventArticle> findListByEntity(EventArticle entity);

    /**
     * <p>Description: 根据文章id更新文章类型</p>
     *
     * @param articleType
     * @return
     * @author zhangtong
     * @date 2017年6月29日
     */
    boolean update(String articleType, String id);

    /**
     * 根据企业id，文章id更新事件文章信息表
     *
     * @param entry
     * @return
     */
    boolean updateEventArticleByCompanyAndArticle(EventArticle entry);

    /**
     * 查询总记录数
     * @return
     */
    long findCount();

    boolean delete(String id);

    List<String> findIdList(int pageNumber, int pageSize);

    boolean deleteAll();

}
