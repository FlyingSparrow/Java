package com.huishu.repository;

import com.huishu.entity.ForumLibBak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface ForumLibBakRepository extends JpaRepository<ForumLibBak, Long> {

    Page<ForumLibBak> findAll(Specification<ForumLibBak> spec, Pageable pageable);

    long countByFldUrlAddrAndFldtitle(String url, String title);
}
