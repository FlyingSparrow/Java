package com.huishu.service;

import com.huishu.entity.Wechat;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface WechatService {
    /**
     * 分页查询
     *
     * @param news
     * @param pageable
     * @return
     */
    List<Wechat> findOneHundred(Wechat news, Pageable pageable);

    /**
     * 批量保存
     *
     * @param news
     */
    void save(List<Wechat> news);
}
