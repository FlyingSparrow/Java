package com.sparrow.opinion.es.repository;

import com.sparrow.opinion.es.entity.EsCompanyInvestor;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CompanyInvestorRepository extends ElasticsearchRepository<EsCompanyInvestor, String> {

}
