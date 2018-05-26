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
    /**
     * 分页查询
     *
     * @param spec
     * @param pageable
     * @return
     */
    Page<QuitDataBak> findAll(Specification<QuitDataBak> spec, Pageable pageable);

    /**
     * 查询已经存在的数据的记录数
     * @param investor
     * @param companyName
     * @param industry
     * @param time
     * @return
     */
    long countByInvestorAndCompanyNameAndIndustryAndTime(
            String investor, String companyName, String industry, String time);
}
