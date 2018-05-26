package com.huishu.service;

import com.huishu.entity.RecruitmentBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface RecruitmentBakService {
    /**
     * 分页查询
     *
     * @param news
     * @param pageable
     * @return
     */
    List<RecruitmentBak> findOneHundred(RecruitmentBak news, Pageable pageable);

    /**
     * 批量保存
     *
     * @param news
     */
    void save(List<RecruitmentBak> news);

    /**
     * 批量删除
     *
     * @param news
     */
    void delete(List<RecruitmentBak> news);

    /**
     * 查询已经存在的数据的记录数
     *
     * @param bak
     * @return
     */
    long findExist(RecruitmentBak bak);
}
