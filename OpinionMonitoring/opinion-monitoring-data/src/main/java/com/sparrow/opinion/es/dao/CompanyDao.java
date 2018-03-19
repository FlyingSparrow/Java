package com.sparrow.opinion.es.dao;

import com.sparrow.opinion.es.entity.EsCompanyBase;
import com.sparrow.opinion.es.entity.EsCompanyInvestor;
import org.elasticsearch.index.query.QueryBuilder;

import java.util.List;

public interface CompanyDao {

    List<EsCompanyInvestor> findCompanyInvestorByQuery(String index, String type, QueryBuilder query);

    List<EsCompanyBase> findCompanyBaseByQuery(String index, String type, QueryBuilder query);
}
