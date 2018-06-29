package com.huishu.ieanalysis.mysql.service.impl;

import com.huishu.ieanalysis.mysql.entity.KingBaseDgap;
import com.huishu.ieanalysis.mysql.resposity.KingBaseDgapRepository;
import com.huishu.ieanalysis.mysql.service.KingBaseDgapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjianchun
 */
@Service
public class KingBaseDgapServiceImpl implements KingBaseDgapService {

    @Autowired
    private KingBaseDgapRepository kingBaseDgapRepository;

    @Override
    public synchronized void saveList(List<KingBaseDgap> list) {
        kingBaseDgapRepository.save(list);
    }

    @Override
    public synchronized void save(KingBaseDgap dgap) {
        kingBaseDgapRepository.save(dgap);
    }

    @Override
    public synchronized boolean isExists(String enterpriseName) {
        int count = kingBaseDgapRepository.countByEnterpriseName(enterpriseName);
        return (count > 1);
    }
}
