package com.huishu.service;

import com.huishu.entity.PolicyBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface PolicyBakService {
    /**
     * 分页查询
     *
     * @param news
     * @param pageable
     * @return
     */
    List<PolicyBak> findOneHundred(PolicyBak news, Pageable pageable);

    /**
     * 批量保存
     *
     * @param news
     */
    void save(List<PolicyBak> news);

    /**
     * 查询已经存在的数据的记录数
     *
     * @param bak
     * @return
     */
    long findExist(PolicyBak bak);
}
