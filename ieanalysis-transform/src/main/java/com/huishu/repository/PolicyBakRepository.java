package com.huishu.repository;

import com.huishu.entity.PolicyBak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface PolicyBakRepository extends JpaRepository<PolicyBak, Long> {

    Page<PolicyBak> findAll(Specification<PolicyBak> spec, Pageable pageable);

    long countByFldUrlAddrAndFldtitle(String url, String title);
}
