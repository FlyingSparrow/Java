package com.huishu.service.impl;

import com.huishu.datasource.TargetDataSource;
import com.huishu.entity.KingBaseDgap;
import com.huishu.repository.KingBaseDgapRepository;
import com.huishu.service.KingBaseDgapService;
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
    public void save(List<KingBaseDgap> list) {
        kingBaseDgapRepository.save(list);
    }

    @Override
    @TargetDataSource(name = "king")
    public void saveKing(List<KingBaseDgap> list) {
        kingBaseDgapRepository.save(list);
    }
}
