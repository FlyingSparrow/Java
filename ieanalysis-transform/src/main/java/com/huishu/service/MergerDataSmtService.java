package com.huishu.service;

import com.huishu.entity.MergerDataSmt;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface MergerDataSmtService {

    /**
     * 分页查询
     * @param data
     * @param pageable
     * @return
     */
    List<MergerDataSmt> findOneHundred(MergerDataSmt data, Pageable pageable);

    /**
     * 批量保存
     * @param news
     */
    void save(List<MergerDataSmt> news);

    /**
     * 批量删除
     * @param news
     */
    void delete(List<MergerDataSmt> news);
}
