package com.huishu.service;

import com.huishu.entity.InvestmentDataSmt;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface InvestmentDataSmtService {

    List<InvestmentDataSmt> findOneHundred(InvestmentDataSmt data, Pageable pageable);

    void save(List<InvestmentDataSmt> news);

    void delete(List<InvestmentDataSmt> news);
}
