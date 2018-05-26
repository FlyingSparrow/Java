package com.huishu.service;

import com.huishu.entity.Recruitment;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface RecruitmentService {

    List<Recruitment> findOneHundred(Recruitment news, Pageable pageable);

    void save(List<Recruitment> news);

    void delete(List<Recruitment> news);
}
