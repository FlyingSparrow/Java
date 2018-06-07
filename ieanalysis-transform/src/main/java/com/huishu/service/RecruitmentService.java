package com.huishu.service;

import com.huishu.entity.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface RecruitmentService {
    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    List<Recruitment> findOneHundred(Recruitment entity, Pageable pageable);

    /**
     * 批量保存
     *
     * @param list
     */
    void save(List<Recruitment> list);

    /**
     * 批量删除
     *
     * @param list
     */
    void delete(List<Recruitment> list);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    Page<Recruitment> findByPage(Recruitment entity, Pageable pageable);
}
