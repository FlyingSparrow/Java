package com.huishu.service;

import com.huishu.entity.InvestmentDataBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface InvestmentDataBakService {

    /**
     * 分页查询
     *
     * @param data
     * @param pageable
     * @return
     */
    List<InvestmentDataBak> findOneHundred(InvestmentDataBak data, Pageable pageable);

    /**
     * 批量保存
     *
     * @param news
     */
    void save(List<InvestmentDataBak> news);

    /**
     * 批量删除
     *
     * @param news
     */
    void delete(List<InvestmentDataBak> news);

    /**
     * 查询已经存在的数据的记录数
     *
     * @param bak
     * @return
     */
    long findExit(InvestmentDataBak bak);
}
