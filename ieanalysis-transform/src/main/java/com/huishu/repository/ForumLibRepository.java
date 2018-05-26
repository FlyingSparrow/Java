package com.huishu.repository;

import com.huishu.entity.ForumLib;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface ForumLibRepository extends JpaRepository<ForumLib, String> {

    Page<ForumLib> findAll(Specification<ForumLib> spec, Pageable pageable);
}
