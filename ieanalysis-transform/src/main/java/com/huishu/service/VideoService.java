package com.huishu.service;

import com.huishu.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface VideoService {
    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    List<Video> findOneHundred(Video entity, Pageable pageable);

    /**
     * 批量保存
     *
     * @param list
     */
    void save(List<Video> list);

    /**
     * 批量删除
     *
     * @param list
     */
    void delete(List<Video> list);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    Page<Video> findByPage(Video entity, Pageable pageable);
}
