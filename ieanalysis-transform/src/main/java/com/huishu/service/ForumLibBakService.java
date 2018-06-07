package com.huishu.service;

import com.huishu.entity.ForumLibBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface ForumLibBakService {

    /**
     * 分页查询
     * @param entity
     * @param pageable
     * @return
     */
    List<ForumLibBak> findOneHundred(ForumLibBak entity, Pageable pageable);

    /**
     * 批量保存
     * @param list
     */
    void save(List<ForumLibBak> list);

    /**
     * 批量删除
     * @param list
     */
    void delete(List<ForumLibBak> list);

    /**
     * 查询已经存在的数据的记录数
     * @param entity
     * @return
     */
    long findExist(ForumLibBak entity);
}
