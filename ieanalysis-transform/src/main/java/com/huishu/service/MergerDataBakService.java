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
     * @param data
     * @param pageable
     * @return
     */
    List<MergerDataBak> findOneHundred(MergerDataBak data, Pageable pageable);

    /**
     * 批量保存
     * @param news
     */
    void save(List<MergerDataBak> news);

    /**
     * 批量删除
     * @param news
     */
    void delete(List<MergerDataBak> news);

    /**
     * 查询已经存在的数据的记录数
     * @param bak
     * @return
     */
    long findExit(MergerDataBak bak);
}
