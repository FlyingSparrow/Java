package com.huishu.ieanalysis.es.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.service.AbstractService;
import com.huishu.ieanalysis.es.service.EnergyPolicyService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.aggregations.metrics.sum.SumBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjianchun
 */
@Service
public class EnergyPolicyServiceImpl extends AbstractService implements EnergyPolicyService {

    @Override
    public JSONObject searchInvestmentAmountAndGrowthRate(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(12);
        SumBuilder financingAgg = AggregationBuilders.sum("financingAmount").field("financingAmount");
        SumBuilder mergersAgg = AggregationBuilders.sum("mergersAmount").field("mergersAmount");
        SumBuilder quitAgg = AggregationBuilders.sum("quitAmount").field("quitAmount");
        monthAgg.subAggregation(financingAgg).subAggregation(mergersAgg).subAggregation(quitAgg);

        cond.setMonth(null);

        Double[] amountArray = getAmountData(cond, monthAgg);
        if (ArrayUtils.isEmpty(amountArray)) {
            return null;
        }else{
            Double[] amountOldArray = getPreviousYearAmountData(cond, monthAgg);

            Double max = 0D;
            Double[] rateArray = new Double[12];
            Double[] resultArray = new Double[12];
            for (int i = 0; i < 12; i++) {
                Double amount = amountArray[i];
                Double previousYearAmount = amountOldArray[i];
                if (previousYearAmount != null && previousYearAmount != 0
                        && amount != null && amount != 0) {
                    rateArray[i] = com.huishu.ieanalysis.utils.StringUtils
                            .formatDouble((amount - previousYearAmount) * 100 / previousYearAmount);
                }

                if (amount != null) {
                    resultArray[i] = com.huishu.ieanalysis.utils.StringUtils.formatDouble(amount / 100000000);
                    if (max < resultArray[i]) {
                        max = resultArray[i];
                    }
                }
            }
            result.put("amount", resultArray);
            result.put("rate", rateArray);
            result.put("max", max);
        }
        return result;
    }

    private Double[] getAmountData(ConditionDTO cond, TermsBuilder monthAgg){
        Double[] result = new Double[12];

        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("month");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    Sum financingSum = bucket.getAggregations().get("financingAmount");
                    Sum mergersSum = bucket.getAggregations().get("mergersAmount");
                    double financingVal = financingSum == null ? 0 : financingSum.getValue();
                    double mergersVal = mergersSum == null ? 0 : mergersSum.getValue();
                    result[Integer.valueOf(bucket.getKeyAsString()) - 1] = financingVal + mergersVal;
                }
            }
            return null;
        });

        return result;
    }

    /**
     * 前一年
     * @param cond
     * @param monthAgg
     * @return
     */
    private Double[] getPreviousYearAmountData(ConditionDTO cond, TermsBuilder monthAgg){
        Double[] result = new Double[12];

        cond.setYear(cond.getYear() - 1);
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("month");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    Sum financingSum = bucket.getAggregations().get("financingAmount");
                    Sum mergersSum = bucket.getAggregations().get("mergersAmount");
                    double financingVal = financingSum.getValue();
                    double mergersVal = mergersSum.getValue();
                    result[Integer.valueOf(bucket.getKeyAsString()) - 1] = financingVal + mergersVal;
                }
            }
            return null;
        });
        return result;
    }

    @Override
    public JSONObject searchHotInvestmentIndustryTop(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchInvestmentIndustryDistribute(
            ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchRegistrationsContrast(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchRegistrationsRateContrast(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchNewEnterpriseRegistrationTimeDistribution(
            ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchNewEnterpriseRegistrationTimeAndSatisfactionStatistics(
            ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchNewEnterpriseRegistrationThanSamePeriod(
            ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

}
