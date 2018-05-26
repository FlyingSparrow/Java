package com.huishu.repository;

import com.huishu.entity.NewsLib;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface NewsLibRepository extends JpaRepository<NewsLib, String> {

    Page<NewsLib> findAll(Specification<NewsLib> spec, Pageable pageable);
}
