package com.huishu.repository;

import com.huishu.entity.InvestmentDataBak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface InvestmentDataBakRepository extends JpaRepository<InvestmentDataBak, Long> {

    Page<InvestmentDataBak> findAll(Specification<InvestmentDataBak> spec, Pageable pageable);

    long countByInvestorAndCompanyNameAndIndustryAndRegionAndTimeAndAmount(
            String investor, String companyName, String industry,
            String region, String time, String amount);
}

