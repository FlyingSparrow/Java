package com.huishu.service;

import com.huishu.entity.Wechat;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface WechatService {

    List<Wechat> findOneHundred(Wechat news, Pageable pageable);

    void save(List<Wechat> news);
}
