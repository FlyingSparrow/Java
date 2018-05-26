package com.huishu.service;

import com.huishu.entity.MergerDataTz;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface MergerDataTzService {

    /**
     * 分页查询
     * @param data
     * @param pageable
     * @return
     */
    List<MergerDataTz> findOneHundred(MergerDataTz data, Pageable pageable);

    /**
     * 批量保存
     * @param news
     */
    void save(List<MergerDataTz> news);

    /**
     * 批量删除
     * @param news
     */
    void delete(List<MergerDataTz> news);
}
