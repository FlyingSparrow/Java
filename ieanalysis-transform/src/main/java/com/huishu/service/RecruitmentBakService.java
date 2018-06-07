package com.huishu.service;

import com.huishu.entity.RecruitmentBak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface RecruitmentBakService {
    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    List<RecruitmentBak> findOneHundred(RecruitmentBak entity, Pageable pageable);

    /**
     * 批量保存
     *
     * @param list
     */
    void save(List<RecruitmentBak> list);

    /**
     * 批量删除
     *
     * @param list
     */
    void delete(List<RecruitmentBak> list);

    /**
     * 查询已经存在的数据的记录数
     *
     * @param entity
     * @return
     */
    long findExist(RecruitmentBak entity);

    Page<RecruitmentBak> findByPage(RecruitmentBak entity, Pageable pageable);
}
