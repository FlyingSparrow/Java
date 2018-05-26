package com.huishu.repository;

import com.huishu.entity.Wechat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface WechatRepository extends JpaRepository<Wechat, Long> {

    Page<Wechat> findAll(Specification<Wechat> spec, Pageable pageable);
}
