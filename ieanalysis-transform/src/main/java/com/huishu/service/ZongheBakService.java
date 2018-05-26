package com.huishu.service;

import com.huishu.entity.ZongheBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface ZongheBakService {
    /**
     * 分页查询
     *
     * @param news
     * @param pageable
     * @return
     */
    List<ZongheBak> findOneHundred(ZongheBak news, Pageable pageable);

    /**
     * 批量保存
     *
     * @param news
     */
    void save(List<ZongheBak> news);

    /**
     * 查询已经存在的数据的记录数
     *
     * @param bak
     * @return
     */
    long findExist(ZongheBak bak);
}
