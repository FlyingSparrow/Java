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
     * @param data
     * @param pageable
     * @return
     */
    List<QuitDataBak> findOneHundred(QuitDataBak data, Pageable pageable);

    /**
     * 批量保存
     *
     * @param news
     */
    void save(List<QuitDataBak> news);

    /**
     * 批量删除
     *
     * @param news
     */
    void delete(List<QuitDataBak> news);

    /**
     * 查询已经存在的数据的记录数
     *
     * @param bak
     * @return
     */
    long findExit(QuitDataBak bak);
}
