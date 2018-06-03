package com.huishu.ieanalysis.es.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.echarts.series.Bar;
import com.huishu.ieanalysis.echarts.series.BaseSeries;
import com.huishu.ieanalysis.echarts.series.Line;
import com.huishu.ieanalysis.echarts.vo.DataVo;
import com.huishu.ieanalysis.echarts.vo.IndicatorVo;
import com.huishu.ieanalysis.es.service.AbstractService;
import com.huishu.ieanalysis.es.service.PolicyIndexService;
import com.huishu.ieanalysis.utils.DateUtils;
import com.huishu.ieanalysis.utils.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.AvgBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.aggregations.metrics.sum.SumBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangjianchun
 */
@Service
public class PolicyIndexServiceImpl extends AbstractService implements PolicyIndexService {

    @Override
    public JSONObject searchFinancingAmountAnalysis(ConditionDTO cond) {
        return searchAmountAnalysis(cond, "financingAmount");
    }

    @Override
    public JSONObject searchMergersAmountAnalysis(ConditionDTO cond) {
        return searchAmountAnalysis(cond, "mergersAmount");
    }

    /**
     * @param cond             查询条件
     * @param aggregationField 聚合字段名称
     * @return
     */
    private JSONObject searchAmountAnalysis(ConditionDTO cond, String aggregationField) {
        Double[] amountArray = searchCapitalIndexResult(cond, aggregationField);
        if (ArrayUtils.isEmpty(amountArray)) {
            return null;
        } else {
            JSONObject result = new JSONObject();
            List<BaseSeries<Double>> series = new ArrayList<BaseSeries<Double>>();
            series.add(new Line<Double>().setData(Arrays.asList(amountArray)));
            result.put("series", series);

            return result;
        }
    }

    @Override
    public JSONObject searchQuitAmountAnalysis(ConditionDTO cond) {
        return searchAmountAnalysis(cond, "quitAmount");
    }

    private Double[] searchCapitalIndexResult(ConditionDTO cond, String aggField) {
        Double[] result = new Double[12];

        cond.setMonth(null);
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(12);
        SumBuilder financingAgg = AggregationBuilders.sum(aggField).field(aggField);
        monthAgg.subAggregation(financingAgg);
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
                    DataVo vo = new DataVo();
                    vo.setName(bucket.getKeyAsString());
                    Sum financingSum = bucket.getAggregations().get(aggField);
                    double financingVal = financingSum == null ? 0 : financingSum.getValue();
                    result[Integer.valueOf(bucket.getKeyAsString()) - 1] = NumberUtils.formatDouble((financingVal) / 100000000);
                }
            }
            return null;
        });
        return result;
    }

    @Override
    public JSONObject searchJobsNumberAnalysis(ConditionDTO cond) {
        cond.setMonth(null);
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(12);
        SumBuilder jobAgg = AggregationBuilders.sum("jobsNumber").field("jobsNumber");
        monthAgg.subAggregation(jobAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();

        logger.info("query: {}", query.toString());

        Double[] amountArray = new Double[12];
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("month");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    DataVo vo = new DataVo();
                    vo.setName(bucket.getKeyAsString());
                    Sum financingSum = bucket.getAggregations().get("jobsNumber");
                    double financingVal = financingSum == null ? 0 : financingSum.getValue();
                    amountArray[Integer.valueOf(bucket.getKeyAsString()) - 1] = financingVal;
                }
            }
            return null;
        });
        return fillEChartsData(amountArray);
    }

    @Override
    public JSONObject searchAvgRemunerationAnalysis(ConditionDTO cond) {
        cond.setMonth(null);
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(12);
        AvgBuilder jobAgg = AggregationBuilders.avg("jobsRemuneration").field("jobsRemuneration");
        monthAgg.subAggregation(jobAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();

        logger.info("query: {}", query.toString());

        Object[] amountArray = new Object[12];
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("month");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    DataVo vo = new DataVo();
                    vo.setName(bucket.getKeyAsString());
                    InternalAvg avgVal = bucket.getAggregations().get("jobsRemuneration");
                    double val = avgVal == null ? 0 : avgVal.getValue();
                    amountArray[Integer.valueOf(bucket.getKeyAsString()) - 1] = NumberUtils.formatDouble(val);
                }
            }
            return null;
        });
        return fillEChartsData(amountArray);
    }

    private JSONObject fillEChartsData(Object[] amountArray) {
        if (ArrayUtils.isEmpty(amountArray)) {
            return null;
        } else {
            JSONObject result = new JSONObject();

            List<BaseSeries<Object>> series = new ArrayList<BaseSeries<Object>>();
            series.add(new Bar<Object>().setData(Arrays.asList(amountArray)));
            result.put("series", series);

            return result;
        }
    }

    @Override
    public JSONObject searchCoverHeatAnalysis(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        List<Double> amountList = NumberUtils.generateDoubleData(4, 60);
        List<BaseSeries<Double>> series = new ArrayList<BaseSeries<Double>>();
        series.add(new Bar<Double>().setData(amountList));
        result.put("series", series);
        result.put("name", SysConst.getQuarterList());

        return result;
    }

    @Override
    public JSONObject searchCombinationTalentRatioAnalysis(ConditionDTO cond) {
        cond.setMonth(null);
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(12);
        TermsBuilder jobAgg = AggregationBuilders.terms("jobsTalentMark").field("jobsTalentMark");
        monthAgg.subAggregation(jobAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();

        logger.info("query: {}", query.toString());

        Double[] amountArray = new Double[12];
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("month");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    DataVo vo = new DataVo();
                    vo.setName(bucket.getKeyAsString());
                    Terms jobsTalentTerms = bucket.getAggregations().get("jobsTalentMark");
                    List<Terms.Bucket> jobsTalentBuckets = jobsTalentTerms.getBuckets();
                    if (jobsTalentBuckets == null || jobsTalentBuckets.size() < 1) {
                        continue;
                    }
                    Long temp = 0L;
                    for (Terms.Bucket jobsTalentBucket : jobsTalentBuckets) {
                        if (jobsTalentBucket.getKeyAsString().equals(SysConst.YES)) {
                            temp = jobsTalentBucket.getDocCount();
                        }
                    }
                    amountArray[Integer.valueOf(bucket.getKeyAsString()) - 1]
                            = NumberUtils.formatDouble(Double.valueOf(temp) * 100 / bucket.getDocCount());
                }
            }
            return null;
        });
        return fillEChartsData(amountArray);
    }

    @Override
    public JSONObject searchEnterpriseCoreCompetitivenessAnalysis(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        int monthCount = 12;
        List<Double> amountList = NumberUtils.generateDoubleData(monthCount, 600000);
        List<BaseSeries<Double>> series = new ArrayList<BaseSeries<Double>>();
        Double[] value = new Double[monthCount];
        for (int i = 0; i < monthCount; i++) {
            value[i] = Math.abs(NumberUtils.formatDouble(amountList.get(i) / 10000));
        }
        series.add(new Line<Double>().setData(Arrays.asList(value)));
        result.put("series", series);
        result.put("name", SysConst.getMonthList());

        return result;
    }

    @Override
    public JSONObject searchEnterpriseMarketForceAnalysis(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        int quarterCount = 4;
        List<Double> amountList = NumberUtils.generateDoubleData(quarterCount, 400000000);
        Double[] value = new Double[quarterCount];
        for (int i = 0; i < quarterCount; i++) {
            value[i] = Math.abs(NumberUtils.formatDouble(amountList.get(i) * 1000D / 100000000));
        }
        List<BaseSeries<Double>> series = new ArrayList<BaseSeries<Double>>();
        series.add(new Bar<Double>().setData(Arrays.asList(value)));
        result.put("series", series);
        result.put("name", SysConst.getQuarterList());

        return result;
    }

    @Override
    public JSONObject searchEnterpriseInnovationAndCreativityAnalysis(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        int quarterCount = 4;
        List<Double> amountList = NumberUtils.generateDoubleData(quarterCount, 400000000);
        Double[] value = new Double[quarterCount];
        for (int i = 0; i < quarterCount; i++) {
            value[i] = Math.abs(NumberUtils.formatDouble(amountList.get(i) * 100D / 100000000));
        }
        List<BaseSeries<Double>> series = new ArrayList<BaseSeries<Double>>();
        series.add(new Bar<Double>().setData(Arrays.asList(value)));
        result.put("series", series);
        result.put("name", SysConst.getQuarterList());

        return result;
    }

    @Override
    public JSONObject searchManagementEnvironmentAnalysis(ConditionDTO cond) {
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder publishTypeAgg = AggregationBuilders.terms("publishType").field("publishType");
        SumBuilder reportNumAgg = AggregationBuilders.sum("reportNum").field("reportNum");
        SumBuilder hitNumAgg = AggregationBuilders.sum("hitNum").field("hitNum");
        publishTypeAgg.subAggregation(reportNumAgg).subAggregation(hitNumAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(publishTypeAgg).build();

        logger.info("query: {}", query.toString());

        Long[] amountArray = {0L, 0L, 0L, 0L, 0L};
        if (cond.getYear() == DateUtils.getCurrentYear()) {
            // 知识产权保护诉讼 72587
            amountArray[2] = 72587L;
        }
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("publishType");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    if (Integer.valueOf(bucket.getKeyAsString()) == 1) {
                        amountArray[0] = bucket.getDocCount();
                    } else if (Integer.valueOf(bucket.getKeyAsString()) == 2) {
                        amountArray[1] = bucket.getDocCount();
                    } else {
                        // do nothing
                    }
                }
                Sum reportNumSum = bucket.getAggregations().get("reportNum");
                Sum hitNumSum = bucket.getAggregations().get("hitNum");
                double reportNumSumValue = reportNumSum == null ? 0 : reportNumSum.getValue();
                double hitNumSumValue = hitNumSum == null ? 0 : hitNumSum.getValue();
                amountArray[3] += Math.round(reportNumSumValue);
                amountArray[4] += Math.round(hitNumSumValue);
            }
            return null;
        });

        Long max = 0L;
        for (Long amount : amountArray) {
            if (max < amount) {
                max = amount;
            }
        }
        if (max == 0) {
            max = 100L;
        }

        List<IndicatorVo> indicatorList = new ArrayList<IndicatorVo>();
        String[] typeArray = {"中央政策", "地方政策", "知识产权保护诉讼", "报道数", "关注度"};
        for (int i = 0; i < 5; i++) {
            IndicatorVo indicatorVo = new IndicatorVo();
            indicatorVo.setText(typeArray[i]);
            indicatorVo.setMax(Double.valueOf(max + ""));
            indicatorList.add(indicatorVo);
        }

        if (ArrayUtils.isEmpty(amountArray)) {
            return null;
        } else {
            JSONObject result = new JSONObject();

            result.put("name", typeArray);
            result.put("value", amountArray);
            result.put("indicator", indicatorList);

            return result;
        }
    }

    @Override
    public JSONObject searchCentralPolicyPublishAnalysis(ConditionDTO cond) {
        int monthCount = 12;
        cond.setMonth(null);
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(monthCount);
        TermsBuilder publishTypeAgg = AggregationBuilders.terms("publishType").field("publishType");
        monthAgg.subAggregation(publishTypeAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();

        logger.info("query: {}", query.toString());

        Object[] amountArray = new Object[monthCount];
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("month");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    Terms publishTypeTerms = bucket.getAggregations().get("publishType");
                    List<Terms.Bucket> publishTypeBuckets = publishTypeTerms.getBuckets();
                    if (publishTypeBuckets != null && publishTypeBuckets.size() > 0) {
                        for (Terms.Bucket publishTypeBucket : publishTypeBuckets) {
                            if (Integer.valueOf(publishTypeBucket.getKeyAsString()) == 1) {
                                amountArray[Integer.valueOf(bucket.getKeyAsString()) - 1] = publishTypeBucket.getDocCount();
                            }
                        }
                    }
                }
            }
            return null;
        });

        return fillEChartsData(amountArray);
    }

    @Override
    public JSONObject searchLocalPolicyPublishAnalysis(ConditionDTO cond) {
        int monthCount = 12;
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(monthCount);
        TermsBuilder publishTypeAgg = AggregationBuilders.terms("publishType").field("publishType");
        monthAgg.subAggregation(publishTypeAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();

        logger.info("query: {}", query.toString());

        Object[] amountArray = new Object[monthCount];
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("month");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    Terms publishTypeTerms = bucket.getAggregations().get("publishType");
                    List<Terms.Bucket> publishTypeBuckets = publishTypeTerms.getBuckets();
                    if (publishTypeBuckets != null && publishTypeBuckets.size() > 0) {
                        for (Terms.Bucket publishTypeBucket : publishTypeBuckets) {
                            if (Integer.valueOf(publishTypeBucket.getKeyAsString()) == 2) {
                                amountArray[Integer.valueOf(bucket.getKeyAsString()) - 1] = publishTypeBucket.getDocCount();
                            }
                        }
                    }
                }
            }
            return null;
        });

        return fillEChartsData(amountArray);
    }

    @Override
    public JSONObject searchProtectionIntellectualLitigationAnalysis(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        int monthCount = 12;
        List<Integer> amountList = NumberUtils.generateIntegerData(monthCount, 10000);
        List<BaseSeries<Integer>> series = new ArrayList<BaseSeries<Integer>>();
        series.add(new Bar<Integer>().setData(amountList));
        result.put("series", series);

        return result;
    }

    @Override
    public JSONObject searchPolicyReportAndFocusAnalysis(ConditionDTO cond) {
        int monthCount = 12;
        cond.setMonth(null);
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(monthCount);
        SumBuilder reportNumAgg = AggregationBuilders.sum("reportNum").field("reportNum");
        SumBuilder hitNumAgg = AggregationBuilders.sum("hitNum").field("hitNum");
        monthAgg.subAggregation(reportNumAgg).subAggregation(hitNumAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(monthAgg).build();

        logger.info("query: {}", query.toString());

        Object[] reportArray = new Object[monthCount];
        Object[] focusArray = new Object[monthCount];
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("month");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    Sum reportNumSum = bucket.getAggregations().get("reportNum");
                    Sum hitNumSum = bucket.getAggregations().get("hitNum");
                    double reportNumSumValue = reportNumSum == null ? 0 : reportNumSum.getValue();
                    double hitNumSumValue = hitNumSum == null ? 0 : hitNumSum.getValue();
                    reportArray[Integer.valueOf(bucket.getKeyAsString()) - 1] = reportNumSumValue;
                    focusArray[Integer.valueOf(bucket.getKeyAsString()) - 1] = hitNumSumValue;
                }
            }
            return null;
        });

        if (ArrayUtils.isEmpty(reportArray) && ArrayUtils.isEmpty(focusArray)) {
            return null;
        } else {
            JSONObject result = new JSONObject();

            List<String> nameList = new ArrayList<String>();
            nameList.add("报道数");
            nameList.add("关注度");
            List<BaseSeries<Object>> series = new ArrayList<BaseSeries<Object>>();
            series.add(new Bar<Object>().setData(Arrays.asList(reportArray)).setName(nameList.get(0)));
            series.add(new Bar<Object>().setData(Arrays.asList(focusArray)).setName(nameList.get(1)));
            result.put("series", series);
            result.put("name", nameList);

            return result;
        }
    }

    @Override
    public JSONObject searchRegisterAndSurviveNumAnalysis(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        int monthCount = 12;
        List<Double> amountList = NumberUtils.generateDoubleData(monthCount, 400000);
        Double[] rateArray = new Double[monthCount];
        for (int i = 0; i < monthCount; i++) {
            if (i == 0) {
                rateArray[i] = 0D;
            } else {
                Double monthAmount = amountList.get(i);
                Double previousMonthAmount = amountList.get(i - 1);
                rateArray[i] = Math.abs(NumberUtils.formatDouble((monthAmount - previousMonthAmount)
                        * 100 / previousMonthAmount));
            }
        }
        List<BaseSeries<Double>> series = new ArrayList<BaseSeries<Double>>();
        series.add(new Line<Double>().setData(Arrays.asList(rateArray)));
        result.put("name", SysConst.getMonthList());
        result.put("series", series);

        return result;
    }

    @Override
    public JSONObject searchRegisterAndSurviveAmountAnalysis(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        int monthCount = 12;
        List<Double> amountList = NumberUtils.generateDoubleData(monthCount, 1280000000);
        Double[] value = new Double[monthCount];
        for (int i = 0; i < monthCount; i++) {
            value[i] = Math.abs(NumberUtils.formatDouble(amountList.get(i) / 100000000));
        }
        List<BaseSeries<Double>> series = new ArrayList<BaseSeries<Double>>();
        series.add(new Line<Double>().setData(Arrays.asList(value)));
        result.put("name", SysConst.getMonthList());
        result.put("series", series);

        return result;
    }

}
