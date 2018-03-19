package com.sparrow.opinion.es.service.impl;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.sparrow.opinion.constants.SysConst;
import com.sparrow.opinion.es.dao.CompanyDao;
import com.sparrow.opinion.es.entity.EsCompanyInvestor;
import com.sparrow.opinion.es.repository.CompanyInvestorRepository;
import com.sparrow.opinion.es.service.CompanyInvestorService;
import org.elasticsearch.index.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyInvestorServiceImpl implements CompanyInvestorService {

    private final static String TYPE = SysConst.COMPANY_INVESTOR_TYPE;
    private final static String INDEX = SysConst.COMPANY_INVESTOR_INDEX;

    @Autowired
    private CompanyInvestorRepository companyInvestorRepository;

    @Autowired
    private CompanyDao companyDao;

    @Override
    public List<EsCompanyInvestor> findByenterpriseAbbrLike(String enterpriseName) {
        // Query
        FuzzyQueryBuilder fuzzy = QueryBuilders.fuzzyQuery("name_c", enterpriseName);
        // 最大编辑距离
//        fuzzy.fuzziness(Fuzziness.ONE);
        // 公共前缀
//        fuzzy.prefixLength(0);
        WildcardQueryBuilder wildcard = QueryBuilders.wildcardQuery("name_c", "*" + enterpriseName + "*");
        TermQueryBuilder termQuery = QueryBuilders.termQuery("name_c", enterpriseName);
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name_c", enterpriseName);
        return companyDao.findCompanyInvestorByQuery(INDEX, TYPE, matchQuery);
    }

    @Override
    public List<EsCompanyInvestor> findByNameC(String name_c) {
        MatchQueryBuilder matchQuery = QueryBuilders.matchPhraseQuery("name_c", name_c);
        List<EsCompanyInvestor> list = companyDao.findCompanyInvestorByQuery(INDEX, TYPE, matchQuery);
        List<EsCompanyInvestor> result = list.stream()
                .filter(companyInvestor -> Objects.equal(companyInvestor.getName_c(), name_c))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<EsCompanyInvestor> searchCompanyInvestor(String companyName, String type) {
        List<EsCompanyInvestor> result = Lists.newArrayList();
        /**
         * 精确查询
         */
        if (SysConst.QueryType.EXACT_QUERY.getCode().equals(type)) {
            result = this.findByNameC(companyName);
        }
        /**
         * 模糊查询
         */
        if (SysConst.QueryType.FUZZYQUERY.getCode().equals(type)) {
            result = this.findByenterpriseAbbrLike(companyName);
        }

        return result;
    }

    @Override
    public List<EsCompanyInvestor> findAll() {
        Iterable<EsCompanyInvestor> iterable = companyInvestorRepository.findAll();
        if(iterable != null){
            if(iterable instanceof List){
                return (List<EsCompanyInvestor>) iterable;
            }else{
                List<EsCompanyInvestor> result = Lists.newArrayList();
                for(Iterator<EsCompanyInvestor> iterator = iterable.iterator(); iterator.hasNext();){
                    result.add(iterator.next());
                }
                return result;
            }
        }
        return null;
    }
}
