package com.huishu.repository;

import com.huishu.entity.KingBaseDgap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface KingBaseDgapRepository extends JpaRepository<KingBaseDgap, String> {
    /**
     * 分页查询
     *
     * @param spec
     * @param pageable
     * @return
     */
    Page<KingBaseDgap> findAll(Specification<KingBaseDgap> spec, Pageable pageable);
}
