package com.huishu.service;

import com.huishu.entity.InvestmentDataBak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface InvestmentDataBakService {

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    List<InvestmentDataBak> findOneHundred(InvestmentDataBak entity, Pageable pageable);

    /**
     * 批量保存
     *
     * @param list
     */
    void save(List<InvestmentDataBak> list);

    /**
     * 批量删除
     *
     * @param list
     */
    void delete(List<InvestmentDataBak> list);

    /**
     * 查询已经存在的数据的记录数
     *
     * @param entity
     * @return
     */
    long findExit(InvestmentDataBak entity);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    Page<InvestmentDataBak> findByPage(InvestmentDataBak entity, Pageable pageable);
}
