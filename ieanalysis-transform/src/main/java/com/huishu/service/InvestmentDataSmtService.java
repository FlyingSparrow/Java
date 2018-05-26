package com.huishu.service;

import com.huishu.entity.InvestmentDataSmt;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface InvestmentDataSmtService {

    /**
     * 分页查询
     * @param data
     * @param pageable
     * @return
     */
    List<InvestmentDataSmt> findOneHundred(InvestmentDataSmt data, Pageable pageable);

    /**
     * 批量保存
     * @param news
     */
    void save(List<InvestmentDataSmt> news);

    /**
     * 批量删除
     * @param news
     */
    void delete(List<InvestmentDataSmt> news);
}
