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
     * @param entity
     * @param pageable
     * @return
     */
    List<ForumLib> findOneHundred(ForumLib entity, Pageable pageable);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    Page<ForumLib> findByPage(ForumLib entity, Pageable pageable);

    /**
     * 批量保存
     *
     * @param list
     */
    void save(List<ForumLib> list);

    /**
     * 批量删除
     *
     * @param list
     */
    void delete(List<ForumLib> list);
}
