package com.huishu.service;

import com.huishu.entity.KingBaseDgap;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface KingBaseDgapService {

    /**
     * 批量保存
     * @param list
     */
    void save(List<KingBaseDgap> list);

    /**
     * 批量保存数据到人大金仓数据库
     * @param list
     */
    void saveKing(List<KingBaseDgap> list);
}
