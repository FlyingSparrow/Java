package com.huishu.service;

import com.huishu.entity.InvestmentDataTz;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface InvestmentDataTzService {

    /**
     * 分页查询
     * @param data
     * @param pageable
     * @return
     */
    List<InvestmentDataTz> findOneHundred(InvestmentDataTz data, Pageable pageable);

    /**
     * 批量保存
     * @param news
     */
    void save(List<InvestmentDataTz> news);

    /**
     * 批量删除
     * @param news
     */
    void delete(List<InvestmentDataTz> news);
}
