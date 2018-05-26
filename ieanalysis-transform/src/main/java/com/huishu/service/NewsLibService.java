package com.huishu.service;

import com.huishu.entity.NewsLib;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface NewsLibService {

    /**
     * 分页查询
     *
     * @param news
     * @param pageable
     * @return
     */
    List<NewsLib> findOneHundred(NewsLib news, Pageable pageable);

    /**
     * 批量保存
     *
     * @param news
     */
    void save(List<NewsLib> news);

    /**
     * 批量删除
     *
     * @param news
     */
    void delete(List<NewsLib> news);

    /**
     * 根据id进行删除
     *
     * @param id
     */
    void deleteById(String id);

    /**
     * 分页查询
     *
     * @param news
     * @param pageable
     * @return
     */
    List<NewsLib> findOneHundredZonghe(NewsLib news, Pageable pageable);

    /**
     * 批量保存
     *
     * @param news
     */
    void saveZonghe(List<NewsLib> news);

    /**
     * 批量删除
     *
     * @param news
     */
    void deleteZonghe(List<NewsLib> news);

    /**
     * 根据id进行删除
     *
     * @param id
     */
    void deleteZongheById(String id);

    /**
     * 分页查询
     *
     * @param news
     * @param pageable
     * @return
     */
    List<NewsLib> findOneHundredPolicy(NewsLib news, Pageable pageable);

    /**
     * 批量保存
     *
     * @param news
     */
    void savePolicy(List<NewsLib> news);

    /**
     * 批量删除
     *
     * @param news
     */
    void deletePolicy(List<NewsLib> news);

    /**
     * 根据id进行删除
     *
     * @param id
     */
    void deletePolicyById(String id);
}
