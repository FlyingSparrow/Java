package com.huishu.service;

import com.huishu.entity.QuitDataBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface QuitDataBakService {
    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    List<QuitDataBak> findOneHundred(QuitDataBak entity, Pageable pageable);

    /**
     * 批量保存
     *
     * @param list
     */
    void save(List<QuitDataBak> list);

    /**
     * 批量删除
     *
     * @param list
     */
    void delete(List<QuitDataBak> list);

    /**
     * 查询已经存在的数据的记录数
     *
     * @param entity
     * @return
     */
    long findExit(QuitDataBak entity);
}
