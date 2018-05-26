package com.huishu.service;

import com.huishu.entity.InvestmentDataTz;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface InvestmentDataTzService {

    List<InvestmentDataTz> findOneHundred(InvestmentDataTz data, Pageable pageable);

    void save(List<InvestmentDataTz> news);

    void delete(List<InvestmentDataTz> news);
}
