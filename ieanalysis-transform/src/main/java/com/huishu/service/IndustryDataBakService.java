package com.huishu.service;

import com.huishu.entity.IndustryDataBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 * @date 2018-6-6
 */
public interface IndustryDataBakService {

    /**
     * 分页查询
     *
     * @param entity
     * @param pageable
     * @return
     */
    List<IndustryDataBak> findOneHundred(IndustryDataBak entity, Pageable pageable);

    /**
     * 批量保存
     *
     * @param list
     */
    void save(List<IndustryDataBak> list);

    /**
     * 批量删除
     *
     * @param list
     */
    void delete(List<IndustryDataBak> list);

    /**
     * 是否存在
     *
     * @param entity
     * @return
     */
    boolean isExists(IndustryDataBak entity);
}
