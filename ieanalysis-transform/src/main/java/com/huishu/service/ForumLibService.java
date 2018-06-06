package com.huishu.service;

import com.huishu.entity.ForumLib;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface ForumLibService {

    /**
     * 分页查询
     *
     * @param news
     * @param pageable
     * @return
     */
    List<ForumLib> findOneHundred(ForumLib news, Pageable pageable);

    /**
     * 分页查询
     *
     * @param news
     * @param pageable
     * @return
     */
    Page<ForumLib> findPageList(ForumLib news, Pageable pageable);

    /**
     * 批量保存
     *
     * @param news
     */
    void save(List<ForumLib> news);

    /**
     * 批量删除
     *
     * @param news
     */
    void delete(List<ForumLib> news);
}
