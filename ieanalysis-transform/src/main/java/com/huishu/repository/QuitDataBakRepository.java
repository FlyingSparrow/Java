package com.huishu.repository;

import com.huishu.entity.QuitDataBak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface QuitDataBakRepository extends JpaRepository<QuitDataBak, Long> {

    Page<QuitDataBak> findAll(Specification<QuitDataBak> spec, Pageable pageable);

    long countByInvestorAndCompanyNameAndIndustryAndTime(
            String investor, String companyName, String industry, String time);
}
