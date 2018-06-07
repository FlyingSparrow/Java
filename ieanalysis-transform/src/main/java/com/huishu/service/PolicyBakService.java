package com.huishu.service;

import com.huishu.entity.PolicyBak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface PolicyBakService {
    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    List<PolicyBak> findOneHundred(PolicyBak entity, Pageable pageable);

    /**
     * 批量保存
     *
     * @param list
     */
    void save(List<PolicyBak> list);

    /**
     * 查询已经存在的数据的记录数
     *
     * @param entity
     * @return
     */
    long findExist(PolicyBak entity);

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    Page<PolicyBak> findByPage(PolicyBak entity, Pageable pageable);
}
