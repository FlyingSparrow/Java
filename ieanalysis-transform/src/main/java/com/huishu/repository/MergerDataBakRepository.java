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
    /**
     * 分页查询
     *
     * @param spec
     * @param pageable
     * @return
     */
    Page<MergerDataBak> findAll(Specification<MergerDataBak> spec, Pageable pageable);

    /**
     * 查询已经存在的数据的记录数
     * @param acquirer
     * @param beMergered
     * @param industry
     * @param endTime
     * @param mergerAmount
     * @return
     */
    long countByAcquirerAndBeMergeredAndIndustryAndEndTimeAndMergerAmount(
            String acquirer, String beMergered, String industry,
            String endTime, String mergerAmount);
}
