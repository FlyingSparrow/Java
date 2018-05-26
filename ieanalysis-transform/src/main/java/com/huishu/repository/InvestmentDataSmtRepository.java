package com.huishu.repository;

import com.huishu.entity.InvestmentDataSmt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface InvestmentDataSmtRepository extends JpaRepository<InvestmentDataSmt, String> {

    Page<InvestmentDataSmt> findAll(Specification<InvestmentDataSmt> spec, Pageable pageable);
}
