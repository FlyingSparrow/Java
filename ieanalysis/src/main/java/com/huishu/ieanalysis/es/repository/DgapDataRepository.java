package com.huishu.ieanalysis.es.repository;

import com.huishu.ieanalysis.es.entity.DgapData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * <p>Title: DgapDataRepository</p>
 * <p>Description: </p>
 *
 * @author xiaobo
 * @date 2017年3月21日
 */
public interface DgapDataRepository extends ElasticsearchRepository<DgapData, String> {

}