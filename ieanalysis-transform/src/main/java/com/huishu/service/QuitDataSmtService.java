package com.huishu.service;

import com.huishu.entity.QuitDataSmt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface QuitDataSmtService {
    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    List<QuitDataSmt> findOneHundred(QuitDataSmt entity, Pageable pageable);

    /**
     * 批量保存
     *
     * @param list
     */
    void save(List<QuitDataSmt> list);

    /**
     * 批量删除
     *
     * @param list
     */
    void delete(List<QuitDataSmt> list);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    Page<QuitDataSmt> findByPage(QuitDataSmt entity, Pageable pageable);
}
