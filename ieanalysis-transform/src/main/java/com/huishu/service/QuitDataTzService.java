package com.huishu.service;

import com.huishu.entity.QuitDataTz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface QuitDataTzService {
    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    List<QuitDataTz> findOneHundred(QuitDataTz entity, Pageable pageable);

    /**
     * 批量保存
     *
     * @param list
     */
    void save(List<QuitDataTz> list);

    /**
     * 批量删除
     *
     * @param list
     */
    void delete(List<QuitDataTz> list);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    Page<QuitDataTz> findByPage(QuitDataTz entity, Pageable pageable);
}
