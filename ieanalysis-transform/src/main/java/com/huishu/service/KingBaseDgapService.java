package com.huishu.service;

import com.huishu.entity.KingBaseDgap;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface KingBaseDgapService {

    void save(List<KingBaseDgap> news);

    void saveKing(List<KingBaseDgap> news);
}
