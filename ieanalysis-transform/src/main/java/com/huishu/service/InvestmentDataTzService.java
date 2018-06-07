package com.huishu.service;

import com.huishu.entity.InvestmentDataTz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface InvestmentDataTzService {

    /**
     * 分页查询
     * @param entity
     * @param pageable
     * @return
     */
    List<InvestmentDataTz> findOneHundred(InvestmentDataTz entity, Pageable pageable);

    /**
     * 批量保存
     * @param list
     */
    void save(List<InvestmentDataTz> list);

    /**
     * 批量删除
     * @param list
     */
    void delete(List<InvestmentDataTz> list);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    Page<InvestmentDataTz> findByPage(InvestmentDataTz entity, Pageable pageable);
}
