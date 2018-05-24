package com.huishu.ieanalysis.es.service.impl;

import com.huishu.ieanalysis.es.entity.DgapData;
import com.huishu.ieanalysis.es.repository.DgapDataRepository;
import com.huishu.ieanalysis.es.service.DgapDataService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjianchun
 */
@Service
public class DgapDataServiceImpl implements DgapDataService {

    @Autowired
    private DgapDataRepository dgapDataRepository;

    @Override
    public void save(List<DgapData> list) {
        dgapDataRepository.save(list);
    }

    @Override
    public Iterable<DgapData> search(BoolQueryBuilder query) {
        return dgapDataRepository.search(query);
    }

}
