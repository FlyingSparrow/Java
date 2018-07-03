package com.huishu.ieanalysis.es.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.echarts.series.Bar;
import com.huishu.ieanalysis.echarts.series.BaseSeries;
import com.huishu.ieanalysis.echarts.series.Line;
import com.huishu.ieanalysis.echarts.vo.DataLongVo;
import com.huishu.ieanalysis.echarts.vo.DataVo;
import com.huishu.ieanalysis.echarts.vo.IndicatorVo;
import com.huishu.ieanalysis.es.service.AbstractService;
import com.huishu.ieanalysis.es.service.EnergyPolicyService;
import com.huishu.ieanalysis.utils.NumberUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.aggregations.metrics.sum.SumBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCount;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
        } else {
            Double[] amountOldArray = getPreviousYearAmountData(cond, monthAgg);

            int monthCount = 12;
            Double max = 0D;
            Double[] rateArray = new Double[monthCount];
            Double[] resultArray = new Double[monthCount];
            for (int i = 0; i < monthCount; i++) {
                Double amount = amountArray[i];
                Double previousYearAmount = amountOldArray[i];
                if (previousYearAmount != null && previousYearAmount != 0
                        && amount != null && amount != 0) {
                    rateArray[i] = NumberUtils.formatDouble((amount - previousYearAmount) * 100 / previousYearAmount);
                }

                if (amount != null) {
                    resultArray[i] = NumberUtils.formatDouble(amount / 100000000);
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

    private Double[] getAmountData(ConditionDTO cond, TermsBuilder monthAgg) {
        Double[] result = new Double[12];

        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

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
     *
     * @param cond
     * @param monthAgg
     * @return
     */
    private Double[] getPreviousYearAmountData(ConditionDTO cond, TermsBuilder monthAgg) {
        Double[] result = new Double[12];

        cond.setYear(cond.getYear() - 1);
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();

        logger.info("query: {}", query.toString());

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

        cond.setMonth(null);
        List<DataVo> list = searchInvestmentIndustry(cond);

        int len = list.size() > 5 ? 5 : list.size();
        List<String> nameList = new ArrayList<String>();
        Object[] valueArray = new Object[len];
        for (int i = 0; i < len; i++) {
            DataVo dataVo = list.get(i);
            nameList.add(dataVo.getName());
            valueArray[i] = dataVo.getValue();
        }
        if (ArrayUtils.isEmpty(valueArray)) {
            return null;
        } else {
            List<BaseSeries<Object>> series = new ArrayList<BaseSeries<Object>>();
            series.add(new Bar<Object>().setData(Arrays.asList(valueArray)));
            result.put("name", nameList);
            result.put("series", series);
        }

        return result;
    }

    @Override
    public JSONObject searchInvestmentIndustryDistribute(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        List<DataVo> list = searchInvestmentIndustry(cond);

        int len = list.size() > 5 ? 5 : list.size();
        String[] nameArray = new String[len];
        Double[] valueArray = new Double[len];
        List<IndicatorVo> indicatorList = new ArrayList<IndicatorVo>();
        for (int i = 0; i < len; i++) {
            DataVo dataVo = list.get(i);
            IndicatorVo indicatorVo = new IndicatorVo();
            indicatorVo.setText(dataVo.getName());
            indicatorVo.setMax(list.get(0).getValue());
            nameArray[i] = dataVo.getName();
            valueArray[i] = dataVo.getValue();
            indicatorList.add(indicatorVo);
        }

        if (ArrayUtils.isEmpty(valueArray)) {
            return null;
        } else {
            result.put("name", nameArray);
            result.put("value", valueArray);
            result.put("indicator", indicatorList);
        }

        return result;
    }

    private List<DataVo> searchInvestmentIndustry(ConditionDTO cond) {
        List<DataVo> result = new ArrayList<DataVo>();

        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("industry").field("industry").size(100);
        SumBuilder financingAgg = AggregationBuilders.sum("financingAmount").field("financingAmount");
        SumBuilder mergersAgg = AggregationBuilders.sum("mergersAmount").field("mergersAmount");
        SumBuilder quitAgg = AggregationBuilders.sum("quitAmount").field("quitAmount");
        monthAgg.subAggregation(financingAgg).subAggregation(mergersAgg).subAggregation(quitAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();

        logger.info("query: {}", query.toString());

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
                    Sum mergersSum = bucket.getAggregations().get("mergersAmount");
                    double financingVal = financingSum == null ? 0 : financingSum.getValue();
                    double mergersVal = mergersSum == null ? 0 : mergersSum.getValue();
                    vo.setValue(NumberUtils.formatDouble((financingVal + mergersVal) / 100000000));
                    result.add(vo);
                }
            }
            return null;
        });
        result.sort(Comparator.comparing(DataVo::getValue).reversed());

        return result;
    }

    @Override
    public JSONObject searchRegistrationsContrast(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        List<BaseSeries<Integer>> series = new ArrayList<BaseSeries<Integer>>();

        int monthCount = 12;
        Integer[] value = new Integer[monthCount];
        for (int i = 0; i < monthCount; i++) {
            value[i] = 0;
        }

        cond.setProvince(null);
        cond.setDataType(SysConst.DataType.INDUSTRY.getCode());
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(monthCount);
        ValueCountBuilder enterpriseNameNumAgg = AggregationBuilders.count("enterpriseName").field("enterpriseName");
        monthAgg.subAggregation(enterpriseNameNumAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();

        logger.info("query: {}", query.toString());

        template.query(query, res -> {
            Terms terms = res.getAggregations().get("month");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (org.apache.commons.lang.StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    int month = bucket.getKeyAsNumber().intValue();
                    ValueCount enterpriseCount = bucket.getAggregations().get("enterpriseName");
                    long enterpriseCountValue = enterpriseCount == null ? 0 : enterpriseCount.getValue();
                    value[month-1] = Integer.valueOf(enterpriseCountValue+"");
                }
            }
            return null;
        });

        series.add(new Bar<Integer>().setData(Arrays.asList(value)));
        result.put("series", series);
        result.put("name", SysConst.getMonthList());

        return result;
    }

    @Override
    public JSONObject searchRegistrationsRateContrast(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        int monthCount = 12;
        Integer[] value = new Integer[monthCount];
        for (int i = 0; i < monthCount; i++) {
            value[i] = 0;
        }

        cond.setProvince(null);
        cond.setDataType(SysConst.DataType.INDUSTRY.getCode());
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(monthCount);
        ValueCountBuilder enterpriseNameNumAgg = AggregationBuilders.count("enterpriseName").field("enterpriseName");
        monthAgg.subAggregation(enterpriseNameNumAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();

        logger.info("query: {}", query.toString());

        template.query(query, res -> {
            Terms terms = res.getAggregations().get("month");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (org.apache.commons.lang.StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    int month = bucket.getKeyAsNumber().intValue();
                    ValueCount enterpriseCount = bucket.getAggregations().get("enterpriseName");
                    long enterpriseCountValue = enterpriseCount == null ? 0 : enterpriseCount.getValue();
                    value[month-1] = Integer.valueOf(enterpriseCountValue+"");
                }
            }
            return null;
        });

        Double[] rateArray = new Double[monthCount];
        for (int i = 0; i < monthCount; i++) {
            if (i == 0) {
                rateArray[i] = 0D;
            } else {
                Integer monthAmount = value[i];
                Integer previousMonthAmount = value[i - 1];
                if(previousMonthAmount > 0){
                    rateArray[i] = Math.abs(NumberUtils.formatDouble(
                            Double.valueOf(monthAmount - previousMonthAmount) * 100 / previousMonthAmount));
                }else{
                    rateArray[i] = Math.abs(NumberUtils.formatDouble(
                            Double.valueOf(monthAmount - previousMonthAmount) * 100));
                }
            }
        }

        List<BaseSeries<Double>> series = new ArrayList<BaseSeries<Double>>();
        series.add(new Line<Double>().setData(Arrays.asList(rateArray)));
        result.put("name", SysConst.getMonthList());
        result.put("series", series);

        return result;
    }

    @Override
    public JSONObject searchNewEnterpriseRegistrationTimeDistribution(
            ConditionDTO cond) {
        JSONObject result = new JSONObject();

        int monthCount = 12;
        Long[] value = new Long[monthCount];
        for (int i = 0; i < monthCount; i++) {
            value[i] = 0L;
        }

        int year = cond.getYear();
        cond.setProvince(null);
        cond.setDataType(SysConst.DataType.INDUSTRY.getCode());
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(monthCount);
        ValueCountBuilder enterpriseNameNumAgg = AggregationBuilders.count("enterpriseName").field("enterpriseName");
        monthAgg.subAggregation(enterpriseNameNumAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();

        logger.info("query: {}", query.toString());

        template.query(query, res -> {
            Terms terms = res.getAggregations().get("month");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (org.apache.commons.lang.StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    int month = bucket.getKeyAsNumber().intValue();
                    ValueCount enterpriseCount = bucket.getAggregations().get("enterpriseName");
                    long enterpriseCountValue = enterpriseCount == null ? 0 : enterpriseCount.getValue();
                    value[month-1] = enterpriseCountValue;
                }
            }
            return null;
        });

        List<Long> amountList = Lists.newArrayList();
        long firstQuarterAmount = value[0]+value[1]+value[2];
        long secondQuarterAmount = value[3]+value[4]+value[5];
        long thirdQuarterAmount = value[6]+value[7]+value[8];
        long fourthQuarterAmount = value[9]+value[10]+value[11];
        amountList.add(firstQuarterAmount);
        amountList.add(secondQuarterAmount);
        amountList.add(thirdQuarterAmount);
        amountList.add(fourthQuarterAmount);

        List<String> quarterList = Arrays.asList(
                new String[]{year+"年第一季度", year+"年第二季度", year+"年第三季度", year+"年第四季度"});
        List<DataLongVo> dataList = new ArrayList<DataLongVo>();
        for (int i = 0; i < quarterList.size(); i++) {
            DataLongVo vo = new DataLongVo();
            vo.setName(quarterList.get(i));
            vo.setValue(amountList.get(i));
            dataList.add(vo);
        }

        result.put("legend", quarterList);
        result.put("piedata", dataList);

        return result;
    }

    @Override
    public JSONObject searchNewEnterpriseRegistrationTimeAndSatisfactionStatistics(
            ConditionDTO cond) {
        JSONObject result = new JSONObject();

        int monthCount = 12;
        Long[] value = new Long[monthCount];
        for (int i = 0; i < monthCount; i++) {
            value[i] = 0L;
        }

        int year = cond.getYear();
        cond.setProvince(null);
        cond.setDataType(SysConst.DataType.INDUSTRY.getCode());
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(monthCount);
        ValueCountBuilder enterpriseNameNumAgg = AggregationBuilders.count("enterpriseName").field("enterpriseName");
        monthAgg.subAggregation(enterpriseNameNumAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();

        logger.info("query: {}", query.toString());

        template.query(query, res -> {
            Terms terms = res.getAggregations().get("month");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (org.apache.commons.lang.StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    int month = bucket.getKeyAsNumber().intValue();
                    ValueCount enterpriseCount = bucket.getAggregations().get("enterpriseName");
                    long enterpriseCountValue = enterpriseCount == null ? 0 : enterpriseCount.getValue();
                    value[month-1] = enterpriseCountValue;
                }
            }
            return null;
        });

        List<Long> amountList = Lists.newArrayList();
        long firstQuarterAmount = value[0]+value[1]+value[2];
        long secondQuarterAmount = value[3]+value[4]+value[5];
        long thirdQuarterAmount = value[6]+value[7]+value[8];
        long fourthQuarterAmount = value[9]+value[10]+value[11];
        amountList.add(firstQuarterAmount);
        amountList.add(secondQuarterAmount);
        amountList.add(thirdQuarterAmount);
        amountList.add(fourthQuarterAmount);

        List<String> quarterList = Arrays.asList(
                new String[]{year+"年第一季度", year+"年第二季度", year+"年第三季度", year+"年第四季度"});
        List<DataLongVo> dataList = new ArrayList<DataLongVo>();
        for (int i = 0; i < quarterList.size(); i++) {
            DataLongVo vo = new DataLongVo();
            vo.setName(quarterList.get(i));
            vo.setValue(amountList.get(i));
            dataList.add(vo);
        }


        List<BaseSeries<Long>> series = new ArrayList<BaseSeries<Long>>();
        series.add(new Bar<Long>().setData(amountList));
        result.put("series", series);
        result.put("name", quarterList);

        return result;
    }

    @Override
    public JSONObject searchNewEnterpriseRegistrationThanSamePeriod(
            ConditionDTO cond) {
        JSONObject result = new JSONObject();

        int monthCount = 12;
        List<Integer> spaceList = new ArrayList<>(monthCount);
        Integer[] value = new Integer[monthCount];
        for (int i = 0; i < monthCount; i++) {
            value[i] = 0;
            spaceList.add(0);
        }

        cond.setProvince(null);
        cond.setDataType(SysConst.DataType.INDUSTRY.getCode());
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(monthCount);
        ValueCountBuilder enterpriseNameNumAgg = AggregationBuilders.count("enterpriseName").field("enterpriseName");
        monthAgg.subAggregation(enterpriseNameNumAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();

        logger.info("query: {}", query.toString());

        template.query(query, res -> {
            Terms terms = res.getAggregations().get("month");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (org.apache.commons.lang.StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    int month = bucket.getKeyAsNumber().intValue();
                    ValueCount enterpriseCount = bucket.getAggregations().get("enterpriseName");
                    long enterpriseCountValue = enterpriseCount == null ? 0 : enterpriseCount.getValue();
                    value[month-1] = Integer.valueOf(enterpriseCountValue+"");
                }
            }
            return null;
        });


        List<Integer> monthAmountList = Arrays.asList(value);

        List<List<Integer>> list = new ArrayList<List<Integer>>();
        list.add(monthAmountList);
        list.add(spaceList);
        List<String> nameList = new ArrayList<String>();
        nameList.add("新企业注册量");
        nameList.add("众创空间量");
        result.put("value", list);
        result.put("name", nameList);

        return result;
    }

}
