package com.huishu.ieanalysis.es.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.entity.DgapData;
import com.huishu.ieanalysis.es.repository.DgapDataRepository;
import com.huishu.ieanalysis.es.service.AbstractService;
import com.huishu.ieanalysis.es.service.PolicyOrientedService;
import com.huishu.ieanalysis.utils.NumberUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.aggregations.metrics.sum.SumBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjianchun
 */
@Service
public class PolicyOrientedServiceImpl extends AbstractService implements PolicyOrientedService {

    @Autowired
    private DgapDataRepository dgapDataRepository;

    @Override
    public JSONObject searchPolicyTextInfo(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "time"));
        Pageable pageable = new PageRequest(cond.getPageNumber() - 1,
                cond.getPageSize(), new Sort(orders));
        BoolQueryBuilder builders = getBuilders(cond);
        Page<DgapData> page = dgapDataRepository.search(builders, pageable);
        result.put("page", page);

        return result;
    }

    @Override
    public JSONObject searchPolicyImageInfo(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "time"));
        Pageable pageable = new PageRequest(cond.getPageNumber() - 1,
                cond.getPageSize(), new Sort(orders));
        BoolQueryBuilder builders = getBuilders(cond);
        builders.mustNot(QueryBuilders.termQuery("policyImageUrl", ""));
        Page<DgapData> page = dgapDataRepository.search(builders, pageable);
        result.put("page", page);

        return result;
    }

    @Override
    public JSONObject searchPolicyVideoInfo(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "time"));
        Pageable pageable = new PageRequest(cond.getPageNumber() - 1,
                cond.getPageSize(), new Sort(orders));
        BoolQueryBuilder builders = getBuilders(cond);
        builders.mustNot(QueryBuilders.termQuery("policyVideoUrl", ""));
        Page<DgapData> page = dgapDataRepository.search(builders, pageable);
        result.put("page", page);

        return result;
    }

    @Override
    public JSONObject searchPolicyAffectInfo(ConditionDTO cond) {
        // 工商
        cond.setDataType(SysConst.DATATYPE_INVESTMENT);
        Object[] investmentArray = searchInvestmentData(cond);

        // 招聘
        cond.setDataType(SysConst.DATATYPE_RECRUITMENT);
        Object[] recruitmentArray = searchRecruitmentData(cond);
        if (ArrayUtils.isEmpty(investmentArray) && ArrayUtils.isEmpty(recruitmentArray)) {
            return null;
        } else {
            JSONObject result = new JSONObject();

            List<Object[]> list = new ArrayList<Object[]>();
            list.add(investmentArray);
            list.add(recruitmentArray);
            List<String> nameList = new ArrayList<String>();
            nameList.add("新企业注册量");
            nameList.add("岗位数");
            result.put("value", list);
            result.put("name", nameList);

            return result;
        }
    }

    /**
     * 查询工商数据
     * @param cond
     * @return
     */
    private Object[] searchInvestmentData(ConditionDTO cond){
        int monthCount = 12;
        Object[] result = new Object[monthCount];
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(monthCount);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("month");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    result[Integer.valueOf(bucket.getKeyAsString()) - 1] = bucket.getDocCount();
                }
            }
            return null;
        });

        return result;
    }

    /**
     * 查询招聘数据
     * @param cond
     * @return
     */
    private Object[] searchRecruitmentData(ConditionDTO cond){
        Object[] result = new Object[12];

        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(12);
        SumBuilder jobsNumberAgg = AggregationBuilders.sum("jobsNumber").field("jobsNumber");
        monthAgg.subAggregation(jobsNumberAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("month");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    Sum regSum = bucket.getAggregations().get("jobsNumber");
                    double regVal = regSum == null ? 0 : regSum.getValue();
                    result[Integer.valueOf(bucket.getKeyAsString()) - 1] = NumberUtils.formatDouble(regVal / 10000);
                }
            }
            return null;
        });

        return result;
    }

    @Override
    public JSONObject searchPolicyAffectIndustryTrent(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchPolicyMediaTranspondAmount(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchPolicySocialTranspondAmount(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchPolicyUserCommentAmount(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchPolicyEmotionAnalysis(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchPolicyMediaCommentTotalRanking(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchPolicyMediaArticleProportion(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchPolicyMediaArticleTrend(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchPolicyMediaParMapAnaylysis(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchPolicyMediaParAnaylysis(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchPolicySocialParAnaylysis(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchPolicySpecialEventShaft(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchPolicyHotKeyWords(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchPolicyHotEventPlaceDistrbute(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchPolicyHotEventAmountDistrbute(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchPolicyHotKeyWordsFrequency(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }
}
