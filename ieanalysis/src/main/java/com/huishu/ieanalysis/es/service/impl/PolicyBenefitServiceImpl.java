package com.huishu.ieanalysis.es.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.echarts.series.Bar;
import com.huishu.ieanalysis.echarts.series.BaseSeries;
import com.huishu.ieanalysis.echarts.vo.DataVo;
import com.huishu.ieanalysis.es.service.AbstractService;
import com.huishu.ieanalysis.es.service.PolicyBenefitService;
import com.huishu.ieanalysis.utils.NumberUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.aggregations.metrics.sum.SumBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author wangjianchun
 */
@Service
public class PolicyBenefitServiceImpl extends AbstractService implements PolicyBenefitService {

    @Override
    public JSONObject searchPolicyBenefitEveryMonth(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        Double[] valueArray = new Double[12];
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(12);
        SumBuilder financingAgg = AggregationBuilders.sum("financingAmount").field("financingAmount");
        monthAgg.subAggregation(financingAgg);
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
                    double financingVal = financingSum == null ? 0 : financingSum.getValue();
                    valueArray[Integer.valueOf(bucket.getKeyAsString()) - 1] = NumberUtils.formatDouble(financingVal / 100000000);
                }
            }
            return null;
        });
        if (ArrayUtils.isEmpty(valueArray)) {
            return null;
        } else {
            List<BaseSeries<Double>> series = new ArrayList<BaseSeries<Double>>();
            series.add(new Bar<Double>().setData(Arrays.asList(valueArray)));
            List<String> nameList = new ArrayList<String>();
            for (int i = 1; i <= 12; i++) {
                nameList.add(i + "月");
            }
            result.put("name", nameList);
            result.put("series", series);
        }
        return result;
    }

    @Override
    public JSONObject searchPolicyBenefitEnterpriseInvestmentTopFive(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        List<DataVo> list = new ArrayList<DataVo>();
        cond.setMonth(null);
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("companyName").field("companyName").size(100);
        SumBuilder financingAgg = AggregationBuilders.sum("financingAmount").field("financingAmount");
        monthAgg.subAggregation(financingAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("companyName");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())
                        && !bucket.getKeyAsString().equals("N/A")) {
                    DataVo vo = new DataVo();
                    vo.setName(bucket.getKeyAsString());
                    Sum financingSum = bucket.getAggregations().get("financingAmount");
                    double financingVal = financingSum == null ? 0 : financingSum.getValue();
                    vo.setValue(financingVal);
                    list.add(vo);
                }
            }
            return null;
        });
        list.sort(Comparator.comparing(DataVo::getValue).reversed());

        int len = list.size() > 5 ? 5 : list.size();
        List<String> nameList = new ArrayList<String>();
        List<String> aliasName = new ArrayList<String>();
        Object[] valueArray = new Object[len];
        for (int i = 0; i < len; i++) {
            DataVo dataVo = list.get(i);
            nameList.add(dataVo.getName());
            valueArray[i] = NumberUtils.formatDouble(dataVo.getValue() / 100000000);
            if (dataVo.getName().length() > SysConst.ECHART_XNAME_LEN) {
                aliasName.add(dataVo.getName().substring(0, SysConst.ECHART_XNAME_LEN) + "...");
            } else {
                aliasName.add(dataVo.getName());
            }
        }
        if (ArrayUtils.isEmpty(valueArray)) {
            return null;
        } else {
            List<BaseSeries<Object>> series = new ArrayList<BaseSeries<Object>>();
            series.add(new Bar<Object>().setData(Arrays.asList(valueArray)));
            result.put("name", nameList);
            result.put("series", series);
            result.put("aliasName", aliasName);
        }
        return result;
    }

    @Override
    public JSONObject searchPolicyBenefIndustryArea(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder proAgg = AggregationBuilders.terms("province").field("province").size(34);
        SumBuilder financingAgg = AggregationBuilders.sum("financingAmount").field("financingAmount");
        proAgg.subAggregation(financingAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(proAgg).build();
        List<DataVo> list = new ArrayList<DataVo>();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("province");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    Sum financingSum = bucket.getAggregations().get("financingAmount");
                    double financingVal = financingSum == null ? 0 : financingSum.getValue();
                    DataVo vo = new DataVo();
                    vo.setName(bucket.getKeyAsString());
                    vo.setValue(financingVal);
                    list.add(vo);
                }
            }
            return null;
        });
        list.sort(Comparator.comparing(DataVo::getValue).reversed());

        int len = list.size() > 5 ? 5 : list.size();
        List<DataVo> subList = list.subList(0, len);
        if (CollectionUtils.isEmpty(subList)) {
            return null;
        } else {
            transformData(result, subList);
        }

        return result;
    }

    @Override
    public JSONObject searchPolicyBenefItenterpriseIndustryTopFive(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder indAgg = AggregationBuilders.terms("industry").field("industry");
        SumBuilder financingAgg = AggregationBuilders.sum("financingAmount").field("financingAmount");
        indAgg.subAggregation(financingAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(indAgg).build();
        List<DataVo> list = new ArrayList<DataVo>();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("industry");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    DataVo vo = new DataVo();
                    vo.setName(bucket.getKeyAsString());
                    Sum financingSum = bucket.getAggregations().get("financingAmount");
                    double financingVal = financingSum == null ? 0 : financingSum.getValue();
                    vo.setValue(financingVal);
                    list.add(vo);
                }
            }
            return null;
        });
        list.sort(Comparator.comparing(DataVo::getValue).reversed());
        int len = list.size() > 5 ? 5 : list.size();
        List<DataVo> subList = list.subList(0, len);
        if (subList == null || subList.size() == 0) {
            return null;
        } else {
            transformData(result, subList);
        }

        return result;
    }

    private void transformData(JSONObject result, List<DataVo> subList) {
        List<Double> value = new ArrayList<Double>();
        List<String> name = new ArrayList<String>();
        for (DataVo vo : subList) {
            value.add(NumberUtils.formatDouble(vo.getValue() / 100000000));
            name.add(vo.getName());
        }
        List<BaseSeries<Double>> series = new ArrayList<BaseSeries<Double>>();
        series.add(new Bar<Double>().setData(value));
        result.put("name", name);
        result.put("series", series);
    }

    @Override
    public JSONObject searchPolicyBenefPublicServiceEfficiencyAnalysis(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        cond.setDataType(SysConst.DATATYPE_POLICY);
        cond.setDataType(1);
        List<String> publishType = new ArrayList<String>();
        publishType.add("1");
        publishType.add("2");
        cond.setPublishType(publishType);
        cond.setPolicyInfoType("1");

        Object[] policyArray = searchPublishPolicy(cond);
        Object[] focusArray = searchFocus(cond);
        if (ArrayUtils.isEmpty(policyArray) && ArrayUtils.isEmpty(focusArray)) {
            return null;
        } else {
            List<Object[]> list = new ArrayList<Object[]>();
            list.add(policyArray);
            list.add(focusArray);
            List<String> nameList = new ArrayList<String>();
            nameList.add("政策发布量");
            nameList.add("政策关注度");
            result.put("value", list);
            result.put("name", nameList);
        }

        return result;
    }

    /**
     * 发布政策数量
     *
     * @param cond
     * @return
     */
    private Object[] searchPublishPolicy(ConditionDTO cond) {
        Object[] result = new Object[12];

        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(12);
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
     * 关注度
     *
     * @param cond
     * @return
     */
    private Object[] searchFocus(ConditionDTO cond) {
        Object[] result = new Object[12];

        ConditionDTO tempCond = new ConditionDTO();
        tempCond.setProvince(cond.getProvince());
        tempCond.setYear(cond.getYear());
        tempCond.setDataType(SysConst.DATATYPE_POLICY);
        BoolQueryBuilder queryBuilder = getBuilders(tempCond);

        logger.info(queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(12);
        SumBuilder hitNumAgg = AggregationBuilders.sum("hitNum").field("hitNum");
        monthAgg.subAggregation(hitNumAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("month");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    Sum regSum = bucket.getAggregations().get("hitNum");
                    double regVal = regSum == null ? 0 : regSum.getValue();
                    result[Integer.valueOf(bucket.getKeyAsString()) - 1] = NumberUtils.formatDouble(regVal / 10000);
                }
            }
            return null;
        });

        return result;
    }

}
