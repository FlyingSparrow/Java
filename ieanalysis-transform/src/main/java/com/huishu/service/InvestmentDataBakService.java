package com.huishu.service;

import com.huishu.entity.InvestmentDataBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface InvestmentDataBakService {

    List<InvestmentDataBak> findOneHundred(InvestmentDataBak data, Pageable pageable);

    void save(List<InvestmentDataBak> news);

    void delete(List<InvestmentDataBak> news);

    long findExit(InvestmentDataBak bak);
}
