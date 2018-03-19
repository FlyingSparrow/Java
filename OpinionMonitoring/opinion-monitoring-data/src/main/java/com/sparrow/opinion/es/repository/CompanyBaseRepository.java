package com.sparrow.opinion.es.repository;


import com.sparrow.opinion.es.entity.EsCompanyBase;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CompanyBaseRepository extends ElasticsearchRepository<EsCompanyBase, String> {

}
