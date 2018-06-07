package com.huishu.service;

import com.huishu.entity.NewsLibBak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface NewsLibBakService {

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    List<NewsLibBak> findOneHundred(NewsLibBak entity, Pageable pageable);

    /**
     * 批量保存
     *
     * @param list
     */
    void save(List<NewsLibBak> list);

    /**
     * 查询已经存在的数据的记录数
     *
     * @param entity
     * @return
     */
    long findExist(NewsLibBak entity);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    Page<NewsLibBak> findByPage(NewsLibBak entity, Pageable pageable);
}
