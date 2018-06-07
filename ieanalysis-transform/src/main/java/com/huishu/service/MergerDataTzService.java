package com.huishu.service;

import com.huishu.entity.MergerDataTz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface MergerDataTzService {

    /**
     * 分页查询
     * @param enttiy
     * @param pageable
     * @return
     */
    List<MergerDataTz> findOneHundred(MergerDataTz enttiy, Pageable pageable);

    /**
     * 批量保存
     * @param list
     */
    void save(List<MergerDataTz> list);

    /**
     * 批量删除
     * @param list
     */
    void delete(List<MergerDataTz> list);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    Page<MergerDataTz> findByPage(MergerDataTz entity, Pageable pageable);
}
