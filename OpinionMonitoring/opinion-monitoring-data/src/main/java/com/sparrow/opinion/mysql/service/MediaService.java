package com.sparrow.opinion.mysql.service;

import com.sparrow.opinion.mysql.entity.Media;

import java.util.List;
import java.util.Map;

public interface MediaService {

    /**
     * <p>Description: 媒体信息添加</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年7月4日
     */
    boolean add(Media entity);

    /**
     * <p>Description: 批量添加媒体信息</p>
     *
     * @param list
     * @return
     * @author zhangtong
     * @date 2017年7月4日
     */
    boolean batchAdd(List<Media> list);

    /**
     * <p>Description: 删除媒体信息（根据id删除）</p>
     *
     * @param id
     * @return
     * @author zhangtong
     * @date 2017年7月4日
     */
    boolean del(Long id);

    /**
     * <p>Description: 更新媒体信息</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年7月4日
     */
    boolean update(Media entity);

    /**
     * <p>Description: 查询媒体信息</p>
     *
     * @return
     * @author zhangtong
     * @date 2017年7月4日
     */
    List<Media> findList();

    /**
     * <p>Description: 根据媒体地址查询是否存在</p>
     *
     * @param urlMD5
     * @return
     * @author zhangtong
     * @date 2017年7月4日
     */
    boolean isExistByUrlMD5(String urlMD5);

    /**
     * <p>Description: 根据媒体url的md5值查询媒体信息</p>
     *
     * @param urlMD5
     * @return
     * @author Wangjianchun
     * @date 2017年7月5日
     */
    List<Media> findByUrlMD5(String urlMD5);

    Map<String, Integer> findMediaWeightCache(String url);

    Map<String, Integer> updateMediaWeightCache(String url);
}
