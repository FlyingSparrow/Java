package com.huishu.service;

import com.huishu.entity.SiteLib;

/**
 * @author wangjianchun
 */
public interface SiteLibService {

    /**
     * 根据名称进行查询
     * @param name
     * @return
     */
    SiteLib findByName(String name);
}
