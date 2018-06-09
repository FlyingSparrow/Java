package com.jdjr.opinion.mongodb.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jdjr.opinion.mongodb.entity.EventInfo;
import com.jdjr.opinion.mongodb.entity.OpinionDetailsSnapshot;

import java.util.List;

public interface EventService {

    /**
     * <p>Description: 事件信息添加方法</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月29日
     */
    boolean add(EventInfo entity);

    /**
     * <p>Description: 事件信息添加方法</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月29日
     */
    boolean update(EventInfo entity);

    /**
     * 根据文章更新事件信息库
     *
     * @param articleId
     * @param article
     * @param enterpriseId
     * @param enterpriseName
     * @param publishDate
     * @return
     */
    boolean updateEventByArticle(String articleId, String article, String enterpriseId, String enterpriseName, String publishDate);

    /**
     * <p>Description: 根据事件id查询事件信息</p>
     *
     * @return
     * @author Wangjianchun
     * @date 2017年7月14日
     */
    EventInfo findById(String id);

    /**
     * 根据事件分类查询事件信息列表
     *
     * @param entity 事件分类
     * @return 事件信息列表
     * @author Wangjianchun
     * @date 2017年7月31日
     */
    JSONArray findListByEntity(OpinionDetailsSnapshot entity);

    /**
     * 新增事件
     */
    EventInfo save(EventInfo entity);

    /**
     * 根据核心文章id企业id查询
     *
     * @param coreArticleId
     * @return
     */
    List<EventInfo> findListByCoreArticleIdAndEnterpriseId(String coreArticleId, String enterpriseId);

    /**
     * <p>Description: 查询事件摘要信息</p>
     *
     * @return
     * @author Wangjianchun
     * @date 2017年7月14日
     */
    JSONObject findEventAbstract(String eventId);

    String getEventInfoRootId(String eventId);

    /**
     * 查询总记录数
     * @return
     */
    long findCount();

    boolean delete(String id);

    List<String> findIdList(int pageNumber, int pageSize);

    boolean deleteAll();

}
