package com.huishu.service;

import com.huishu.entity.QuitDataSmt;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface QuitDataSmtService {
    /**
     * 分页查询
     *
     * @param data
     * @param pageable
     * @return
     */
    List<QuitDataSmt> findOneHundred(QuitDataSmt data, Pageable pageable);

    /**
     * 批量保存
     *
     * @param news
     */
    void save(List<QuitDataSmt> news);

    /**
     * 批量删除
     *
     * @param news
     */
    void delete(List<QuitDataSmt> news);
}
