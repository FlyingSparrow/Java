package com.huishu.repository;

import com.huishu.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface VideoRepository extends JpaRepository<Video, String> {

    Page<Video> findAll(Specification<Video> spec, Pageable pageable);
}
