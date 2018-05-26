package com.huishu.service;

import com.huishu.entity.RecruitmentBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface RecruitmentBakService {

    List<RecruitmentBak> findOneHundred(RecruitmentBak news, Pageable pageable);

    void save(List<RecruitmentBak> news);

    void delete(List<RecruitmentBak> news);

    long findExist(RecruitmentBak bak);
}
