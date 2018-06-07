package com.huishu.service;

import com.huishu.entity.VideoBak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface VideoBakService {
    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    List<VideoBak> findOneHundred(VideoBak entity, Pageable pageable);

    /**
     * 批量保存
     *
     * @param list
     */
    void save(List<VideoBak> list);

    /**
     * 批量删除
     *
     * @param list
     */
    void delete(List<VideoBak> list);

    /**
     * 查询已经存在的数据的记录数
     *
     * @param entity
     * @return
     */
    long findExist(VideoBak entity);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    Page<VideoBak> findByPage(VideoBak entity, Pageable pageable);
}
