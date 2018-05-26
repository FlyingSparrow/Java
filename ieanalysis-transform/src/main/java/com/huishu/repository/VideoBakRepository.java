package com.huishu.repository;

import com.huishu.entity.VideoBak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface VideoBakRepository extends JpaRepository<VideoBak, Long> {

    Page<VideoBak> findAll(Specification<VideoBak> spec, Pageable pageable);

    long countByUrlAndFabushijian(String url, String time);
}
