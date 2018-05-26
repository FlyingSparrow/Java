package com.huishu.repository;

import com.huishu.entity.MergerDataBak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface MergerDataBakRepository extends JpaRepository<MergerDataBak, Long> {

    Page<MergerDataBak> findAll(Specification<MergerDataBak> spec, Pageable pageable);

    long countByAcquirerAndBeMergeredAndIndustryAndEndTimeAndMergerAmount(
            String acquirer, String beMergered, String industry,
            String endTime, String mergerAmount);
}
