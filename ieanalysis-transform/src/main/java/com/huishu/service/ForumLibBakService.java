package com.huishu.service;

import com.huishu.entity.ForumLibBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface ForumLibBakService {

    /**
     * 分页查询
     * @param news
     * @param pageable
     * @return
     */
    List<ForumLibBak> findOneHundred(ForumLibBak news, Pageable pageable);

    /**
     * 批量保存
     * @param news
     */
    void save(List<ForumLibBak> news);

    /**
     * 批量删除
     * @param news
     */
    void delete(List<ForumLibBak> news);

    /**
     * 查询已经存在的数据的记录数
     * @param bak
     * @return
     */
    long findExist(ForumLibBak bak);
}
