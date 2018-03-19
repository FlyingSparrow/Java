package com.sparrow.opinion.es.dao.impl;

import com.sparrow.opinion.es.dao.CompanyDao;
import com.sparrow.opinion.es.entity.EsCompanyBase;
import com.sparrow.opinion.es.entity.EsCompanyInvestor;
import org.elasticsearch.index.query.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDaoImpl implements CompanyDao {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    private static final Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);

    @Override
    public List<EsCompanyInvestor> findCompanyInvestorByQuery(String index, String type, QueryBuilder query) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(query)
                .withIndices(index)
                .withTypes(type)
                .withPageable(new PageRequest(0, 1000))
                .build();
        logger.info("findCompanyInvestorByQuery:{}",searchQuery.getQuery().toString());
        List<EsCompanyInvestor> result = elasticsearchTemplate.queryForList(searchQuery, EsCompanyInvestor.class);
        return result;
    }

    @Override
    public List<EsCompanyBase> findCompanyBaseByQuery(String index, String type, QueryBuilder query) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(query)
                .withIndices(index)
                .withTypes(type)
                .withPageable(new PageRequest(0, 1000))
                .build();
        logger.info("findCompanyBaseByQuery:{}",searchQuery.getQuery().toString());
        List<EsCompanyBase> result = elasticsearchTemplate.queryForList(searchQuery, EsCompanyBase.class);
        return result;
    }
}
