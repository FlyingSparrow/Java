package com.huishu.service;

import com.huishu.entity.InvestmentDataSmt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface InvestmentDataSmtService {

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    List<InvestmentDataSmt> findOneHundred(InvestmentDataSmt entity, Pageable pageable);

    /**
     * 批量保存
     *
     * @param list
     */
    void save(List<InvestmentDataSmt> list);

    /**
     * 批量删除
     *
     * @param list
     */
    void delete(List<InvestmentDataSmt> list);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    Page<InvestmentDataSmt> findByPage(InvestmentDataSmt entity, Pageable pageable);
}
