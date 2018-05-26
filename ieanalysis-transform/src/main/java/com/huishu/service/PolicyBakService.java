package com.huishu.service;

import com.huishu.entity.PolicyBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface PolicyBakService {

    List<PolicyBak> findOneHundred(PolicyBak news, Pageable pageable);

    void save(List<PolicyBak> news);

    long findExist(PolicyBak bak);
}
