package com.huishu.service;

import com.huishu.entity.QuitDataTz;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface QuitDataTzService {
    /**
     * 分页查询
     *
     * @param data
     * @param pageable
     * @return
     */
    List<QuitDataTz> findOneHundred(QuitDataTz data, Pageable pageable);

    /**
     * 批量保存
     *
     * @param news
     */
    void save(List<QuitDataTz> news);

    /**
     * 批量删除
     *
     * @param news
     */
    void delete(List<QuitDataTz> news);
}
