package com.huishu.service;

import com.huishu.entity.Recruitment;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface RecruitmentService {
    /**
     * 分页查询
     *
     * @param news
     * @param pageable
     * @return
     */
    List<Recruitment> findOneHundred(Recruitment news, Pageable pageable);

    /**
     * 批量保存
     *
     * @param news
     */
    void save(List<Recruitment> news);

    /**
     * 批量删除
     *
     * @param news
     */
    void delete(List<Recruitment> news);
}
