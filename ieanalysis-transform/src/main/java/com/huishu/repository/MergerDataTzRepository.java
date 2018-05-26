package com.huishu.repository;

import com.huishu.entity.MergerDataTz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface MergerDataTzRepository extends JpaRepository<MergerDataTz, String> {

    Page<MergerDataTz> findAll(Specification<MergerDataTz> spec, Pageable pageable);
}
