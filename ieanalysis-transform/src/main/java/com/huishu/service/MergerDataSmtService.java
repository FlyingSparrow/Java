package com.huishu.service;

import com.huishu.entity.MergerDataSmt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface MergerDataSmtService {

    /**
     * 分页查询
     * @param entity
     * @param pageable
     * @return
     */
    List<MergerDataSmt> findOneHundred(MergerDataSmt entity, Pageable pageable);

    /**
     * 批量保存
     * @param list
     */
    void save(List<MergerDataSmt> list);

    /**
     * 批量删除
     * @param list
     */
    void delete(List<MergerDataSmt> list);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    Page<MergerDataSmt> findByPage(MergerDataSmt entity, Pageable pageable);
}
