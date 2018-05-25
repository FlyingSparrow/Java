package com.huishu.ieanalysis.es.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.echarts.series.Bar;
import com.huishu.ieanalysis.echarts.series.BaseSeries;
import com.huishu.ieanalysis.echarts.series.Map;
import com.huishu.ieanalysis.echarts.vo.DataLongVo;
import com.huishu.ieanalysis.echarts.vo.DataVo;
import com.huishu.ieanalysis.es.service.AbstractService;
import com.huishu.ieanalysis.es.service.PolicyHotService;
import com.huishu.ieanalysis.utils.NumberUtils;
import org.apache.commons.lang.StringUtils;
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
import java.util.Comparator;
import java.util.List;

/**
 * @author wangjianchun
 */
@Service
public class PolicyHotServiceImpl extends AbstractService implements PolicyHotService {

    @Override
    public JSONObject searchPolicyHotAreaFocusMapAnalysis(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder provinceAgg = AggregationBuilders.terms("province").field("province").size(34);
        SumBuilder focusAgg = AggregationBuilders.sum("hitNum").field("hitNum");
        provinceAgg.subAggregation(focusAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(provinceAgg).build();

        List<Map> series = new ArrayList<>();
        List<DataVo> data = new ArrayList<DataVo>();
        long maxValue = template.query(query, res -> {
            String commonProvince = SysConst.COMMON_PROCINCE_STR;
            Long max = 0L;
            Terms terms = res.getAggregations().get("province");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return max;
            }
            for (Terms.Bucket bucket : buckets) {
                Sum sum = bucket.getAggregations().get("hitNum");
                double val = sum == null ? 0 : sum.getValue();
                DataVo vo = new DataVo();
                vo.setName(bucket.getKeyAsString().trim());
                long round = Math.round(val);
                vo.setValue(val);
                commonProvince = commonProvince.replace(vo.getName(), "");
                data.add(vo);
                if (max < round) {
                    max = round;
                }
            }
            JSONArray provinceArray = com.huishu.ieanalysis.utils.StringUtils.split(commonProvince, ",");
            for (int i = 0, size = provinceArray.size(); i < size; i++) {
                DataVo vo = new DataVo();
                vo.setName(provinceArray.getString(i));
                vo.setValue(0D);
                data.add(vo);
            }

            series.add(new Map().setData(data).setName("热力图"));

            return max;
        });
        if (CollectionUtils.isEmpty(series)) {
            return null;
        } else {
            result.put("series", series);
            result.put("max", maxValue);
            result.put("data", data);
        }

        return result;
    }

    @Override
    public JSONObject searchPolicyHotAreaFocusRankAnalysis(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder provinceAgg = AggregationBuilders.terms("province").field("province").size(34);
        SumBuilder focusAgg = AggregationBuilders.sum("hitNum").field("hitNum");
        provinceAgg.subAggregation(focusAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(provinceAgg).build();

        List<DataLongVo> data = new ArrayList<DataLongVo>();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("province");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            fillEChartsDataWithHitNum(data, buckets);
            return null;
        });
        data.sort(Comparator.comparing(DataLongVo::getValue).reversed());


        int len = data.size() > 10 ? 10 : data.size();
        List<DataLongVo> subList = data.subList(0, len);
        if (CollectionUtils.isEmpty(subList)) {
            return null;
        } else {
            List<Object> nameList = new ArrayList<Object>();
            List<Object> valueList = new ArrayList<Object>();
            for (DataLongVo vo : subList) {
                nameList.add(vo.getName());
                valueList.add(NumberUtils.formatDouble(Double.valueOf(vo.getValue() + "")));
            }
            List<BaseSeries<Object>> series = new ArrayList<BaseSeries<Object>>();
            series.add(new Bar<Object>().setData(valueList).setName("地区"));
            result.put("series", series);
            result.put("name", nameList);
        }

        return result;
    }

    @Override
    public JSONObject searchPolicyHotAreaFocusSocialSubjectAnalysis(ConditionDTO cond) {
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder jobAgg = AggregationBuilders.terms("industry").field("industry");
        SumBuilder focusAgg = AggregationBuilders.sum("hitNum").field("hitNum");
        jobAgg.subAggregation(focusAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(jobAgg).build();

        List<DataLongVo> data = new ArrayList<DataLongVo>();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("industry");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            fillEChartsDataWithHitNum(data, buckets);
            return null;
        });
        data.sort(Comparator.comparing(DataLongVo::getValue).reversed());

        int len = data.size() > 10 ? 10 : data.size();
        List<DataLongVo> subList = data.subList(0, len);

        return transformData(subList);
    }

    private void fillEChartsDataWithHitNum(List<DataLongVo> data, List<Terms.Bucket> buckets) {
        for (Terms.Bucket bucket : buckets) {
            if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                Sum sum = bucket.getAggregations().get("hitNum");
                double val = sum == null ? 0 : sum.getValue();
                DataLongVo vo = new DataLongVo();
                vo.setName(bucket.getKeyAsString());
                vo.setValue(Math.round(val));
                data.add(vo);
            }
        }
    }

    @Override
    public JSONObject searchPolicyHotChannelSiteAnalysis(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder siteAgg = AggregationBuilders.terms("site").field("site");
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(siteAgg).build();
        List<DataLongVo> data = new ArrayList<DataLongVo>();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("site");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            fillEChartsData(data, buckets);
            return null;
        });
        data.sort(Comparator.comparing(DataLongVo::getValue).reversed());

        int len = data.size() > 10 ? 10 : data.size();
        List<DataLongVo> subList = data.subList(0, len);
        if (CollectionUtils.isEmpty(subList)) {
            return null;
        } else {
            List<Object> nameList = new ArrayList<Object>();
            for (DataLongVo vo : subList) {
                nameList.add(vo.getName());
            }
            result.put("piedata", subList);
            result.put("legend", nameList);
        }

        return result;
    }

    @Override
    public JSONObject searchPolicyHotChannelSocialSubjectAnalysis(ConditionDTO cond) {
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder jobAgg = AggregationBuilders.terms("industry").field("industry");
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(jobAgg).build();
        List<DataLongVo> data = new ArrayList<DataLongVo>();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("industry");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            fillEChartsData(data, buckets);
            return null;
        });
        data.sort(Comparator.comparing(DataLongVo::getValue).reversed());

        int len = data.size() > 5 ? 5 : data.size();
        List<DataLongVo> subList = data.subList(0, len);

        return transformData(subList);
    }

    private void fillEChartsData(List<DataLongVo> data, List<Terms.Bucket> buckets) {
        for (Terms.Bucket bucket : buckets) {
            if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                DataLongVo vo = new DataLongVo();
                vo.setName(bucket.getKeyAsString());
                vo.setValue(bucket.getDocCount());
                data.add(vo);
            }
        }
    }

    private JSONObject transformData(List<DataLongVo> subList) {
        if (CollectionUtils.isEmpty(subList)) {
            return null;
        } else {
            JSONObject result = new JSONObject();

            List<Object> nameList = new ArrayList<Object>();
            List<Object> valueList = new ArrayList<Object>();
            for (DataLongVo vo : subList) {
                nameList.add(vo.getName());
                valueList.add(NumberUtils.formatDouble(Double.valueOf(vo.getValue() + "")));
            }
            List<BaseSeries<Object>> series = new ArrayList<BaseSeries<Object>>();
            series.add(new Bar<Object>().setData(valueList).setName("行业"));
            result.put("series", series);
            result.put("name", nameList);

            return result;
        }
    }

    @Override
    public JSONObject searchPolicyHotMediaChannelEmotionAnalysis(ConditionDTO cond) {
        return searchEmotionAnalysis(cond);
    }

    private JSONObject searchEmotionAnalysis(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder emotionAgg = AggregationBuilders.terms("emotionMark").field("emotionMark");
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(emotionAgg).build();

        List<DataLongVo> data = new ArrayList<DataLongVo>();
        // 0,负面;1,中性;2,正面
        String[] legend = {"负面", "中性", "正面"};
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("emotionMark");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    DataLongVo vo = new DataLongVo();
                    int mark = Integer.valueOf(bucket.getKeyAsString());
                    vo.setName(legend[mark]);
                    vo.setValue(bucket.getDocCount());
                    data.add(vo);
                }
            }
            return null;
        });
        if (CollectionUtils.isEmpty(data)) {
            return null;
        } else {
            result.put("piedata", data);
            result.put("legend", legend);
        }

        return result;
    }

    @Override
    public JSONObject searchPolicyHotMediaChannelRankAnalysis(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder siteAgg = AggregationBuilders.terms("site").field("site").size(SysConst.AGG_SITE_SIZE);
        SumBuilder traAgg = AggregationBuilders.sum("readNum").field("readNum");
        siteAgg.subAggregation(traAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(siteAgg).build();

        List<DataLongVo> data = new ArrayList<DataLongVo>();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("site");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            fillEChartsDataWithReadNum(data, buckets);
            return null;
        });
        data.sort(Comparator.comparing(DataLongVo::getValue).reversed());

        int len = data.size() > 10 ? 10 : data.size();
        List<DataLongVo> subList = data.subList(0, len);
        if (CollectionUtils.isEmpty(subList)) {
            return null;
        } else {
            List<Object> nameList = new ArrayList<Object>();
            List<Object> valueList = new ArrayList<Object>();
            List<Object> aliasName = new ArrayList<Object>();
            for (DataLongVo vo : subList) {
                if (vo.getName().length() > SysConst.ECHART_XNAME_LEN) {
                    aliasName.add(vo.getName().substring(0, SysConst.ECHART_XNAME_LEN) + "...");
                } else {
                    aliasName.add(vo.getName());
                }
                nameList.add(vo.getName());
                valueList.add(NumberUtils.formatDouble(Double.valueOf(vo.getValue())));
            }
            List<BaseSeries<Object>> series = new ArrayList<BaseSeries<Object>>();
            series.add(new Bar<Object>().setData(valueList).setName("媒体参与"));
            result.put("series", series);
            result.put("name", nameList);
            result.put("aliasName", aliasName);
        }

        return result;
    }

    @Override
    public JSONObject searchPolicyHotMediaChannelDistributeAnalysis(ConditionDTO cond) {
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder emotionAgg = AggregationBuilders.terms("emotionMark").field("emotionMark");
        TermsBuilder industryAgg = AggregationBuilders.terms("site").field("site");
        industryAgg.subAggregation(emotionAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(industryAgg).build();

        List<String> nameList = new ArrayList<String>();
        List<Long> positiveList = new ArrayList<Long>();
        List<Long> neutralList = new ArrayList<Long>();
        List<Long> negativeList = new ArrayList<Long>();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("site");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            fillEmotionData(nameList, positiveList, neutralList, negativeList, buckets);
            return null;
        });

        return fillEChartsDataForPolicyHotMediaChannelDistributeAnalysis(
                nameList, positiveList, neutralList, negativeList);
    }

    /**
     * 政策热点---媒体参与度分析--各大媒体渠道倾向性分布统，填充ECharts图表需要的数据
     *
     * @param nameList
     * @return
     */
    private JSONObject fillEChartsDataForPolicyHotMediaChannelDistributeAnalysis(
            List<String> nameList, List<Long> positiveList, List<Long> neutralList,
            List<Long> negativeList) {
        if (CollectionUtils.isEmpty(nameList)) {
            return null;
        } else {
            JSONObject result = new JSONObject();
            fillEmotionDataForECharts(nameList, positiveList, neutralList, negativeList, result);

            List<String> aliasName = new ArrayList<String>();
            for (int i = 0; i < nameList.size(); i++) {
                if (nameList.get(i).length() > SysConst.ECHART_XNAME_LEN) {
                    aliasName.add(nameList.get(i).substring(0, SysConst.ECHART_XNAME_LEN) + "...");
                } else {
                    aliasName.add(nameList.get(i));
                }
            }
            result.put("name", nameList);
            result.put("aliasName", aliasName);

            return result;
        }
    }

    private void fillEmotionData(List<String> nameList, List<Long> positiveList,
                                 List<Long> neutralList, List<Long> negativeList,
                                 List<Terms.Bucket> buckets) {
        int index = 0;
        for (Terms.Bucket bucket : buckets) {
            if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                nameList.add(bucket.getKeyAsString());
                Terms emotionTerms = bucket.getAggregations().get("emotionMark");
                List<Terms.Bucket> emotionBuckets = emotionTerms.getBuckets();

                positiveList.add(null);
                neutralList.add(null);
                negativeList.add(null);
                if (emotionBuckets != null && emotionBuckets.size() > 0) {
                    for (Terms.Bucket emotionBucket : emotionBuckets) {
                        if (StringUtils.isNotEmpty(emotionBucket.getKeyAsString())) {
                            if ("0".equals(emotionBucket.getKeyAsString())) {
                                negativeList.set(index, emotionBucket.getDocCount());
                            } else if ("1".equals(emotionBucket.getKeyAsString())) {
                                neutralList.set(index, emotionBucket.getDocCount());
                            } else if ("2".equals(emotionBucket.getKeyAsString())) {
                                positiveList.set(index, emotionBucket.getDocCount());
                            }
                        }
                    }
                }
                index++;
            }
        }
    }

    @Override
    public JSONObject searchPolicyHotSocialEmotionAnalysis(ConditionDTO cond) {
        return searchEmotionAnalysis(cond);
    }

    @Override
    public JSONObject searchPolicyHotSocialChannelAnalysis(ConditionDTO cond) {
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder siteAgg = AggregationBuilders.terms("socialChannel").field("socialChannel");
        SumBuilder traAgg = AggregationBuilders.sum("readNum").field("readNum");
        siteAgg.subAggregation(traAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(siteAgg).build();

        List<DataLongVo> data = new ArrayList<DataLongVo>();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("socialChannel");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    Sum sum = bucket.getAggregations().get("readNum");
                    double val = sum == null ? 0 : sum.getValue();
                    DataLongVo vo = new DataLongVo();
                    vo.setName(SysConst.SOCIAL_CHANNEL[Integer.valueOf(bucket.getKeyAsString()) - 1]);
                    vo.setValue(Math.round(val));
                    data.add(vo);
                }
            }
            return null;
        });
        data.sort(Comparator.comparing(DataLongVo::getValue).reversed());

        int len = data.size() > 10 ? 10 : data.size();
        List<DataLongVo> subList = data.subList(0, len);
        if (CollectionUtils.isEmpty(subList)) {
            return null;
        } else {
            JSONObject result = new JSONObject();

            List<Object> nameList = new ArrayList<Object>();
            List<Object> valueList = new ArrayList<Object>();
            for (DataLongVo vo : subList) {
                if (StringUtils.isNotEmpty(vo.getName().trim())) {
                    nameList.add(vo.getName());
                    valueList.add(NumberUtils.formatDouble(Double.valueOf(vo.getValue())));
                }
            }
            List<BaseSeries<Object>> series = new ArrayList<BaseSeries<Object>>();
            series.add(new Bar<Object>().setData(valueList).setName("社会参与"));
            result.put("series", series);
            result.put("name", nameList);

            return result;
        }
    }

    @Override
    public JSONObject searchPolicyHotSocialChannelDistributeAnalysis(ConditionDTO cond) {
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder emotionAgg = AggregationBuilders.terms("emotionMark").field("emotionMark");
        TermsBuilder industryAgg = AggregationBuilders.terms("socialChannel").field("socialChannel");
        industryAgg.subAggregation(emotionAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(industryAgg).build();

        List<String> nameList = new ArrayList<String>();
        List<Long> positiveList = new ArrayList<Long>();
        List<Long> neutralList = new ArrayList<Long>();
        List<Long> negativeList = new ArrayList<Long>();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("socialChannel");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            fillEmotionData(nameList, positiveList, neutralList, negativeList, buckets);
            return null;
        });

        if (CollectionUtils.isEmpty(nameList)) {
            return null;
        } else {
            JSONObject result = new JSONObject();
            fillEmotionDataForECharts(nameList, positiveList, neutralList, negativeList, result);

            List<String> namesList = new ArrayList<String>();
            for (String name : nameList) {
                namesList.add(SysConst.SOCIAL_CHANNEL[Integer.valueOf(name) - 1]);
            }
            result.put("name", namesList);

            return result;
        }
    }

    private void fillEmotionDataForECharts(List<String> nameList, List<Long> positiveList,
                                           List<Long> neutralList, List<Long> negativeList,
                                           JSONObject result) {
        // 0,负面;1,中性;2,正面
        String[] legend = {"负面", "中性", "正面"};

        List<Double> positiveRateList = new ArrayList<Double>();
        List<Double> neutralRateList = new ArrayList<Double>();
        List<Double> negativeRateList = new ArrayList<Double>();
        for (int i = 0; i < nameList.size(); i++) {
            Long positiveValue = positiveList.get(i);
            Long neutralValue = neutralList.get(i);

            long count = 0;
            if (positiveValue != null) {
                count += positiveValue;
            }
            if (neutralValue != null) {
                count += neutralValue;
            }
            if (negativeList.get(i) != null) {
                count += negativeList.get(i);
            }
            if (count == 0) {
                positiveRateList.add(0d);
                neutralRateList.add(0d);
                negativeRateList.add(0d);
            } else {
                double rateCount = 0d;
                if (positiveValue != null) {
                    Double formattedPositiveValue = NumberUtils.formatDouble((double) (positiveValue * 10000 / count) / 100);
                    rateCount += formattedPositiveValue;
                    positiveRateList.add(formattedPositiveValue);
                } else {
                    positiveRateList.add(0d);
                }
                if (neutralValue != null) {
                    Double formattedNeutralValue = NumberUtils.formatDouble((double) (neutralValue * 10000 / count) / 100);
                    rateCount += formattedNeutralValue;
                    neutralRateList.add(formattedNeutralValue);
                } else {
                    neutralRateList.add(0d);
                }
                negativeRateList.add(NumberUtils.formatDouble(100.00 - rateCount));
            }
        }
        List<BaseSeries<Double>> series = new ArrayList<BaseSeries<Double>>();
        series.add(new Bar<Double>().setData(positiveRateList).setName("正面").setStack("网站"));
        series.add(new Bar<Double>().setData(neutralRateList).setName("中性").setStack("网站"));
        series.add(new Bar<Double>().setData(negativeRateList).setName("负面").setStack("网站"));
        result.put("series", series);
        result.put("legend", legend);
    }

    @Override
    public JSONObject searchPolicyHotIndustryEmotionAnalysis(ConditionDTO cond) {
        return searchEmotionAnalysis(cond);
    }

    @Override
    public JSONObject searchPolicyHotIndustryRankAnalysis(ConditionDTO cond) {
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder siteAgg = AggregationBuilders.terms("industry").field("industry");
        SumBuilder traAgg = AggregationBuilders.sum("readNum").field("readNum");
        siteAgg.subAggregation(traAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(siteAgg).build();

        List<DataLongVo> data = new ArrayList<DataLongVo>();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("industry");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            fillEChartsDataWithReadNum(data, buckets);
            return null;
        });
        data.sort(Comparator.comparing(DataLongVo::getValue).reversed());

        int len = data.size() > 5 ? 5 : data.size();
        List<DataLongVo> subList = data.subList(0, len);
        if (CollectionUtils.isEmpty(subList)) {
            return null;
        } else {
            JSONObject result = new JSONObject();

            List<Object> nameList = new ArrayList<Object>();
            List<Object> valueList = new ArrayList<Object>();
            for (DataLongVo vo : subList) {
                nameList.add(vo.getName());
                valueList.add(NumberUtils.formatDouble(Double.valueOf(vo.getValue())));
            }
            List<BaseSeries<Object>> series = new ArrayList<BaseSeries<Object>>();
            series.add(new Bar<Object>().setData(valueList).setName("行业参与"));
            result.put("series", series);
            result.put("name", nameList);

            return result;
        }
    }

    private void fillEChartsDataWithReadNum(List<DataLongVo> data, List<Terms.Bucket> buckets) {
        for (Terms.Bucket bucket : buckets) {
            if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                Sum sum = bucket.getAggregations().get("readNum");
                double val = sum == null ? 0 : sum.getValue();
                DataLongVo vo = new DataLongVo();
                vo.setName(bucket.getKeyAsString());
                vo.setValue(Math.round(val));
                data.add(vo);
            }
        }
    }

    @Override
    public JSONObject searchPolicyHotIndustryDistributeAnalysis(ConditionDTO cond) {
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info(queryBuilder.toString());

        TermsBuilder emotionAgg = AggregationBuilders.terms("emotionMark").field("emotionMark");
        TermsBuilder industryAgg = AggregationBuilders.terms("industry").field("industry");
        industryAgg.subAggregation(emotionAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(industryAgg).build();

        List<String> nameList = new ArrayList<String>();
        List<Long> positiveList = new ArrayList<Long>();
        List<Long> neutralList = new ArrayList<Long>();
        List<Long> negativeList = new ArrayList<Long>();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("industry");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            fillEmotionData(nameList, positiveList, neutralList, negativeList, buckets);
            return null;
        });

        if (CollectionUtils.isEmpty(nameList)) {
            return null;
        } else {
            JSONObject result = new JSONObject();
            fillEmotionDataForECharts(nameList, positiveList, neutralList, negativeList, result);

            result.put("name", nameList);

            return result;
        }
    }
}
