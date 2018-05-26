package com.huishu.repository;

import com.huishu.entity.InvestmentDataTz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface InvestmentDataTzRepository extends JpaRepository<InvestmentDataTz, String> {

    Page<InvestmentDataTz> findAll(Specification<InvestmentDataTz> spec, Pageable pageable);
}
