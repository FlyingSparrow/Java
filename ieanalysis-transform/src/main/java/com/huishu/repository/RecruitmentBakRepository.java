package com.huishu.repository;

import com.huishu.entity.RecruitmentBak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface RecruitmentBakRepository extends JpaRepository<RecruitmentBak, Long> {

    Page<RecruitmentBak> findAll(Specification<RecruitmentBak> spec, Pageable pageable);

    long countByFldUrlAddrAndFldtitle(String url, String title);
}
