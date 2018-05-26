package com.huishu.service;

import com.huishu.entity.Video;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface VideoService {
    /**
     * 分页查询
     *
     * @param news
     * @param pageable
     * @return
     */
    List<Video> findOneHundred(Video news, Pageable pageable);

    /**
     * 批量保存
     *
     * @param news
     */
    void save(List<Video> news);

    /**
     * 批量删除
     *
     * @param news
     */
    void delete(List<Video> news);
}
