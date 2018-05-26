package com.huishu.repository;

import com.huishu.entity.NewsLibBak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface NewsLibBakRepository extends JpaRepository<NewsLibBak, Long> {

    Page<NewsLibBak> findAll(Specification<NewsLibBak> spec, Pageable pageable);

    long countByFldUrlAddrAndFldtitle(String url, String title);
}
