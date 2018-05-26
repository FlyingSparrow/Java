package com.huishu.service;

import com.huishu.entity.VideoBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface VideoBakService {
    /**
     * 分页查询
     *
     * @param news
     * @param pageable
     * @return
     */
    List<VideoBak> findOneHundred(VideoBak news, Pageable pageable);

    /**
     * 批量保存
     *
     * @param news
     */
    void save(List<VideoBak> news);

    /**
     * 批量删除
     *
     * @param news
     */
    void delete(List<VideoBak> news);

    /**
     * 查询已经存在的数据的记录数
     *
     * @param bak
     * @return
     */
    long findExist(VideoBak bak);
}
