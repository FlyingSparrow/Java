package com.huishu.service;

import com.huishu.entity.KingBaseDgap;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface KingBaseDgapService {

    /**
     * 批量保存
     * @param news
     */
    void save(List<KingBaseDgap> news);

    /**
     * 批量保存数据到人大金仓数据库
     * @param news
     */
    void saveKing(List<KingBaseDgap> news);
}
