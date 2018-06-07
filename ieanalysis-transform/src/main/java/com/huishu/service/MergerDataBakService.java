package com.huishu.service;

import com.huishu.entity.MergerDataBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface MergerDataBakService {

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    List<MergerDataBak> findOneHundred(MergerDataBak entity, Pageable pageable);

    /**
     * 批量保存
     *
     * @param list
     */
    void save(List<MergerDataBak> list);

    /**
     * 批量删除
     *
     * @param list
     */
    void delete(List<MergerDataBak> list);

    /**
     * 查询已经存在的数据的记录数
     *
     * @param entity
     * @return
     */
    long findExit(MergerDataBak entity);
}
