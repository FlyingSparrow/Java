package com.huishu.ieanalysis.mysql.service;


import com.huishu.ieanalysis.mysql.entity.KingBaseDgap;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface KingBaseDgapService {

    void saveList(List<KingBaseDgap> news);

    void save(KingBaseDgap news);
}
