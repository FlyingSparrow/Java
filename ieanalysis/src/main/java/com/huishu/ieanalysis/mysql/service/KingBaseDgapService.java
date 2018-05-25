package com.huishu.ieanalysis.mysql.service;


import com.huishu.ieanalysis.mysql.entity.KingBaseDgap;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface KingBaseDgapService {

    /**
     * 批量保存
     * @param list
     */
    void saveList(List<KingBaseDgap> list);

    /**
     * 保存
     * @param news
     */
    void save(KingBaseDgap news);
}
