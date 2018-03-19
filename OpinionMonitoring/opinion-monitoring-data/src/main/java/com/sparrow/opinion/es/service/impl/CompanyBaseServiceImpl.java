package com.sparrow.opinion.es.service.impl;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.sparrow.opinion.constants.SysConst;
import com.sparrow.opinion.es.dao.CompanyDao;
import com.sparrow.opinion.es.entity.EsCompanyBase;
import com.sparrow.opinion.es.repository.CompanyBaseRepository;
import com.sparrow.opinion.es.service.CompanyBaseService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyBaseServiceImpl implements CompanyBaseService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyBaseServiceImpl.class);

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private CompanyBaseRepository companyBaseRepository;

    /**
     * 监控企业ES查询需要排除的关键词
     */
    @Value("${custom.es.search.enterprise.exclude.keywords}")
    private String enterpriseExcludeKeywords;

    /**
     * 监控企业ES查询需要包含的关键词
     */
    @Value("${custom.es.search.enterprise.include.keywords}")
    private String enterpriseIncludeKeywords;

    private final static String TYPE = SysConst.COMPANY_BASE_TYPE;
    private final static String INDEX = SysConst.COMPANY_BASE_INDEX;

    @Override
    public List<EsCompanyBase> findByCompanyNameLike(String companyName) {
        // Query
        FuzzyQueryBuilder fuzzyQuery = QueryBuilders.fuzzyQuery("company_name", companyName);
        // 最大编辑距离
        fuzzyQuery.fuzziness(Fuzziness.AUTO);
        // 公共前缀
        fuzzyQuery.prefixLength(0);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        WildcardQueryBuilder wildcardQuery = QueryBuilders.wildcardQuery("company_name", "*" + companyName + "*");

        TermQueryBuilder termQuery = QueryBuilders.termQuery("company_name", companyName);
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("company_name", companyName);

        boolQuery.must(wildcardQuery);
        if(StringUtils.isNotEmpty(enterpriseExcludeKeywords)){
            String[] array = enterpriseExcludeKeywords.split(",");
            if(array != null){
                for(String keyword : array){
                    if(StringUtils.isNotEmpty(keyword)){
                        boolQuery.mustNot(QueryBuilders.wildcardQuery("company_name", "*"+keyword));
                    }
                }
            }
        }
        if(StringUtils.isNotEmpty(enterpriseIncludeKeywords)){
            String[] array = enterpriseIncludeKeywords.split(",");
            if(array != null){
                BoolQueryBuilder orQuery = QueryBuilders.boolQuery();
                for(String keyword : array){
                    if(StringUtils.isNotEmpty(keyword)){
                        orQuery.should(QueryBuilders.wildcardQuery("company_name", "*"+keyword));
                    }
                }
                boolQuery.must(orQuery);
            }
        }
        return companyDao.findCompanyBaseByQuery(INDEX, TYPE, boolQuery);
    }

    @Override
    public List<EsCompanyBase> findByCompanyName(String companyName) {
        MatchQueryBuilder matchQuery = QueryBuilders.matchPhraseQuery("company_name", companyName);
        List<EsCompanyBase> list = companyDao.findCompanyBaseByQuery(INDEX, TYPE, matchQuery);
        List<EsCompanyBase> result = list.stream()
                .filter(companyBase -> Objects.equal(companyBase.getCompany_name(), companyName))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<EsCompanyBase> searchCompanyBase(String companyName, String type) {
        List<EsCompanyBase> result = Lists.newArrayList();
        /**
         * 精确查询
         */
        if (SysConst.QueryType.EXACT_QUERY.getCode().equals(type)) {
            result = this.findByCompanyName(companyName);
        }
        /**
         * 模糊查询
         */
        if (SysConst.QueryType.FUZZYQUERY.getCode().equals(type)) {
            result = this.findByCompanyNameLike(companyName);
        }
        return result;
    }

    @Override
    public List<EsCompanyBase> findAll() {
        Iterable<EsCompanyBase> iterable = companyBaseRepository.findAll();
        if(iterable != null){
            if(iterable instanceof List){
                return (List<EsCompanyBase>) iterable;
            }else{
                List<EsCompanyBase> result = Lists.newArrayList();
                for(Iterator<EsCompanyBase> iterator = iterable.iterator(); iterator.hasNext();){
                    result.add(iterator.next());
                }
                return result;
            }
        }
        return null;
    }
}
