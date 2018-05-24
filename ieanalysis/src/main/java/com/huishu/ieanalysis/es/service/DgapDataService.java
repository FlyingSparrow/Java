package com.huishu.ieanalysis.es.service;

import com.huishu.ieanalysis.es.entity.DgapData;
import org.elasticsearch.index.query.BoolQueryBuilder;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface DgapDataService {

    /**
     * 批量保存
     * @param list
     */
    void save(List<DgapData> list);

    /**
     * 查询
     * @param query
     * @return
     */
    Iterable<DgapData> search(BoolQueryBuilder query);
}
