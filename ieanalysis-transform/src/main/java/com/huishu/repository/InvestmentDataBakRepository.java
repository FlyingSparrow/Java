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
    /**
     * 分页查询
     *
     * @param spec
     * @param pageable
     * @return
     */
    Page<InvestmentDataBak> findAll(Specification<InvestmentDataBak> spec, Pageable pageable);

    /**
     * 查询已经存在的数据的记录数
     * @param investor
     * @param companyName
     * @param industry
     * @param region
     * @param time
     * @param amount
     * @return
     */
    long countByInvestorAndCompanyNameAndIndustryAndRegionAndTimeAndAmount(
            String investor, String companyName, String industry,
            String region, String time, String amount);
}

