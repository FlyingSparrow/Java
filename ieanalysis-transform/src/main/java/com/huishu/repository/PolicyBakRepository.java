package com.huishu.repository;

import com.huishu.entity.PolicyBak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 */
public interface PolicyBakRepository extends JpaRepository<PolicyBak, Long> {
    /**
     * 分页查询
     *
     * @param spec
     * @param pageable
     * @return
     */
    Page<PolicyBak> findAll(Specification<PolicyBak> spec, Pageable pageable);
    /**
     * 根据url和title查询已经存在的数据的记录数
     *
     * @param url
     * @param title
     * @return
     */
    long countByFldUrlAddrAndFldtitle(String url, String title);
}
