package com.huishu.service;

import com.huishu.entity.NewsLib;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface NewsLibService {

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    List<NewsLib> findOneHundred(NewsLib entity, Pageable pageable);

    /**
     * 批量保存
     *
     * @param list
     */
    void save(List<NewsLib> list);

    /**
     * 批量删除
     *
     * @param list
     */
    void delete(List<NewsLib> list);

    /**
     * 根据id进行删除
     *
     * @param id
     */
    void deleteById(String id);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    List<NewsLib> findOneHundredZonghe(NewsLib entity, Pageable pageable);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    Page<NewsLib> findZongheListByPage(NewsLib entity, Pageable pageable);

    /**
     * 批量保存
     *
     * @param list
     */
    void saveZonghe(List<NewsLib> list);

    /**
     * 批量删除
     *
     * @param list
     */
    void deleteZonghe(List<NewsLib> list);

    /**
     * 根据id进行删除
     *
     * @param id
     */
    void deleteZongheById(String id);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    List<NewsLib> findOneHundredPolicy(NewsLib entity, Pageable pageable);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    Page<NewsLib> findPolicyListByPage(NewsLib entity, Pageable pageable);

    /**
     * 批量保存
     *
     * @param list
     */
    void savePolicy(List<NewsLib> list);

    /**
     * 批量删除
     *
     * @param list
     */
    void deletePolicy(List<NewsLib> list);

    /**
     * 根据id进行删除
     *
     * @param id
     */
    void deletePolicyById(String id);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    Page<NewsLib> findByPage(NewsLib entity, Pageable pageable);
}
