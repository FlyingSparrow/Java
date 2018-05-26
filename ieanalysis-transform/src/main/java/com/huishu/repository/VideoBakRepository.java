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
    /**
     * 分页查询
     *
     * @param spec
     * @param pageable
     * @return
     */
    Page<VideoBak> findAll(Specification<VideoBak> spec, Pageable pageable);

    /**
     * 根据url和time查询已经存在的数据的记录数
     * @param url
     * @param time
     * @return
     */
    long countByUrlAndFabushijian(String url, String time);
}
