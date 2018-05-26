package com.huishu.repository;

import com.huishu.entity.ZongheBak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface ZongheBakRepository extends JpaRepository<ZongheBak, Long> {

    Page<ZongheBak> findAll(Specification<ZongheBak> spec, Pageable pageable);

    long countByFldUrlAddrAndFldtitle(String url, String title);
}
