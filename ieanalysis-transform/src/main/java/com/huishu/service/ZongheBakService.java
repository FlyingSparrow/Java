package com.huishu.service;

import com.huishu.entity.ZongheBak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface ZongheBakService {
    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    List<ZongheBak> findOneHundred(ZongheBak entity, Pageable pageable);

    /**
     * 批量保存
     *
     * @param list
     */
    void save(List<ZongheBak> list);

    /**
     * 查询已经存在的数据的记录数
     *
     * @param entity
     * @return
     */
    long findExist(ZongheBak entity);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    Page<ZongheBak> findByPage(ZongheBak entity, Pageable pageable);
}
