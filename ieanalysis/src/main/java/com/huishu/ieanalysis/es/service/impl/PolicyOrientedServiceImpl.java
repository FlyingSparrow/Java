package com.huishu.ieanalysis.es.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.echarts.series.Bar;
import com.huishu.ieanalysis.echarts.series.BaseSeries;
import com.huishu.ieanalysis.echarts.series.Line;
import com.huishu.ieanalysis.echarts.series.Map;
import com.huishu.ieanalysis.echarts.vo.DataLongVo;
import com.huishu.ieanalysis.es.entity.DgapData;
import com.huishu.ieanalysis.es.repository.DgapDataRepository;
import com.huishu.ieanalysis.es.service.AbstractService;
import com.huishu.ieanalysis.es.service.PolicyOrientedService;
import com.huishu.ieanalysis.utils.DateUtils;
import com.huishu.ieanalysis.utils.NumberUtils;
import com.sc.articleToKeywordCloud.ArticleConToKeywordCloud;
import com.sc.itemModel.KeywordModel;
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
import org.springframework.util.CollectionUtils;

import java.util.*;

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
        cond.setDataType(SysConst.DataType.INVESTMENT.getCode());
        Object[] investmentArray = searchInvestmentData(cond);

        // 招聘
        cond.setDataType(SysConst.DataType.RECRUITMENT.getCode());
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
     *
     * @param cond
     * @return
     */
    private Object[] searchInvestmentData(ConditionDTO cond) {
        int monthCount = 12;
        Object[] result = new Object[monthCount];
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(monthCount);
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
                    result[Integer.valueOf(bucket.getKeyAsString()) - 1] = bucket.getDocCount();
                }
            }
            return null;
        });

        return result;
    }

    /**
     * 查询招聘数据
     *
     * @param cond
     * @return
     */
    private Object[] searchRecruitmentData(ConditionDTO cond) {
        int monthCount = 12;
        Object[] result = new Object[monthCount];

        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(monthCount);
        SumBuilder jobsNumberAgg = AggregationBuilders.sum("jobsNumber").field("jobsNumber");
        monthAgg.subAggregation(jobsNumberAgg);
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
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder industryAgg = AggregationBuilders.terms("industry").field("industry");
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(industryAgg).build();

        logger.info("query: {}", query.toString());

        List<DataLongVo> list = new ArrayList<DataLongVo>();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("industry");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    DataLongVo vo = new DataLongVo();
                    vo.setName(bucket.getKeyAsString());
                    vo.setValue(bucket.getDocCount());
                    list.add(vo);
                }
            }
            return null;
        });
        list.sort(Comparator.comparing(DataLongVo::getValue).reversed());

        int len = list.size() > 5 ? 5 : list.size();
        List<DataLongVo> subList = list.subList(0, len);
        if (CollectionUtils.isEmpty(subList)) {
            return null;
        } else {
            JSONObject result = new JSONObject();

            List<String> legendList = new ArrayList<String>();
            for (DataLongVo vo : subList) {
                legendList.add(vo.getName());
            }
            result.put("piedata", subList);
            result.put("legend", legendList);

            return result;
        }
    }

    @Override
    public JSONObject searchPolicyMediaTranspondAmount(ConditionDTO cond) {
        return searchPolicyTranspondAmount(cond);
    }

    @Override
    public JSONObject searchPolicySocialTranspondAmount(ConditionDTO cond) {
        return searchPolicyTranspondAmount(cond);
    }

    private JSONObject searchPolicyTranspondAmount(ConditionDTO cond) {
        int monthCount = 12;
        Double[] valueArray = new Double[monthCount];
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        // 搜狐、网易、新浪、腾讯、凤凰
        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(monthCount);
        SumBuilder traAgg = AggregationBuilders.sum("readNum").field("readNum");
        monthAgg.subAggregation(traAgg);
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
                    Sum sum = bucket.getAggregations().get("readNum");
                    double traVal = sum == null ? 0 : sum.getValue();
                    int index = Integer.valueOf(bucket.getKeyAsString()) - 1;
                    valueArray[index] = traVal;
                }
            }
            return null;
        });

        return fillEChartsData(monthCount, valueArray);
    }

    @Override
    public JSONObject searchPolicyUserCommentAmount(ConditionDTO cond) {
        int monthCount = 12;
        Double[] valueArray = new Double[monthCount];
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        // 搜狐、网易、新浪、腾讯、凤凰
        TermsBuilder monthAgg = AggregationBuilders.terms("month").field("month").size(monthCount);
        SumBuilder traAgg = AggregationBuilders.sum("hitNum").field("hitNum");
        monthAgg.subAggregation(traAgg);
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
                    Sum sum = bucket.getAggregations().get("hitNum");
                    double traVal = sum == null ? 0 : sum.getValue();
                    int index = Integer.valueOf(bucket.getKeyAsString()) - 1;
                    valueArray[index] = traVal;
                }
            }
            return null;
        });

        return fillEChartsData(monthCount, valueArray);
    }

    private JSONObject fillEChartsData(int monthCount, Double[] valueArray) {
        if (ArrayUtils.isEmpty(valueArray)) {
            return null;
        } else {
            JSONObject result = new JSONObject();

            List<String> legendList = new ArrayList<String>();
            Double[] monthAmountArray = new Double[monthCount];
            for (int i = 0; i < monthCount; i++) {
                if (valueArray[i] != null) {
                    monthAmountArray[i] = NumberUtils.formatDouble(valueArray[i]);
                }
            }
            List<BaseSeries<Double>> series = new ArrayList<BaseSeries<Double>>();
            series.add(new Line<Double>().setData(Arrays.asList(monthAmountArray)).setName(""));
            result.put("series", series);
            result.put("legend", legendList);

            return result;
        }
    }

    @Override
    public JSONObject searchPolicyEmotionAnalysis(ConditionDTO cond) {
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder emotionMarkAgg = AggregationBuilders.terms("emotionMark").field("emotionMark").size(3);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(emotionMarkAgg).build();

        logger.info("query: {}", query.toString());

        List<DataLongVo> list = new ArrayList<DataLongVo>();
        String[] legendArray = {"负面", "中性", "正面"};
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("emotionMark");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    int index = Integer.valueOf(bucket.getKeyAsString());
                    if (index <= legendArray.length - 1) {
                        DataLongVo vo = new DataLongVo();
                        vo.setName(legendArray[index]);
                        vo.setValue(bucket.getDocCount());
                        list.add(vo);
                    }
                }
            }
            return null;
        });
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            JSONObject result = new JSONObject();
            result.put("piedata", list);
            result.put("legend", legendArray);

            return result;
        }
    }

    @Override
    public JSONObject searchPolicyMediaCommentTotalRanking(ConditionDTO cond) {
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder siteAgg = AggregationBuilders.terms("site").field("site").size(SysConst.AGG_SITE_SIZE);
        SumBuilder readNumAgg = AggregationBuilders.sum("readNum").field("readNum");
        siteAgg.subAggregation(readNumAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(siteAgg).build();

        logger.info("query: {}", query.toString());

        List<DataLongVo> list = new ArrayList<DataLongVo>();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("site");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    DataLongVo vo = new DataLongVo();
                    Sum traSum = bucket.getAggregations().get("readNum");
                    double traVal = traSum == null ? 0 : traSum.getValue();
                    vo.setName(bucket.getKeyAsString());
                    vo.setValue(Math.round(traVal));
                    list.add(vo);
                }
            }
            return null;
        });
        list.sort(Comparator.comparing(DataLongVo::getValue).reversed());

        int len = list.size() > 5 ? 5 : list.size();
        List<DataLongVo> subList = list.subList(0, len);
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
            series.add(new Bar<Object>().setData(valueList).setName(""));
            result.put("series", series);
            result.put("name", nameList);

            return result;
        }
    }

    @Override
    public JSONObject searchPolicyMediaArticleProportion(ConditionDTO cond) {
        List<String> top5MediaList = searchPolicyMediaTop5OrderByReadNumDesc(cond);
        if (top5MediaList == null) {
            return null;
        }
        cond.setSites(top5MediaList);
        List<DataLongVo> voList = searchPolicyMediaArticleProportionResult(cond);

        int len = voList.size() > 5 ? 5 : voList.size();
        List<DataLongVo> subList = voList.subList(0, len);
        if (CollectionUtils.isEmpty(subList)) {
            return null;
        } else {
            JSONObject result = new JSONObject();

            List<Object> legendList = new ArrayList<Object>();
            for (DataLongVo vo : subList) {
                legendList.add(vo.getName());
            }
            result.put("piedata", subList);
            result.put("legend", legendList);

            return result;
        }
    }

    private List<String> searchPolicyMediaTop5OrderByReadNumDesc(ConditionDTO cond) {
        List<DataLongVo> list = new ArrayList<DataLongVo>();

        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder siteAgg = AggregationBuilders.terms("site").field("site")
                .size(SysConst.AGG_SITE_SIZE);
        SumBuilder readNumAgg = AggregationBuilders.sum("readNum").field("readNum");
        siteAgg.subAggregation(readNumAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder)
                .addAggregation(siteAgg).build();

        logger.info("query: {}", query.toString());

        template.query(query, res -> {
            Terms terms = res.getAggregations().get("site");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    DataLongVo vo = new DataLongVo();
                    Sum sum = bucket.getAggregations().get("readNum");
                    double traVal = sum == null ? 0 : sum.getValue();
                    vo.setName(bucket.getKeyAsString());
                    vo.setValue((long) traVal);
                    list.add(vo);
                }
            }
            return null;
        });
        list.sort(Comparator.comparing(DataLongVo::getValue).reversed());

        int len = list.size() > 5 ? 5 : list.size();
        List<DataLongVo> subList = list.subList(0, len);
        if (CollectionUtils.isEmpty(subList)) {
            return null;
        } else {
            List<String> result = new ArrayList<String>();
            for (DataLongVo vo : subList) {
                result.add(vo.getName());
            }
            return result;
        }
    }

    private List<DataLongVo> searchPolicyMediaArticleProportionResult(ConditionDTO cond) {
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder siteAgg = AggregationBuilders.terms("site").field("site");
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(siteAgg).build();

        logger.info("query: {}", query.toString());

        return searchSiteAggregationData(query);
    }

    @Override
    public JSONObject searchPolicyMediaArticleTrend(ConditionDTO cond) {
        List<String> top5MediaList = searchPolicyMediaTop5OrderByReadNumDesc(cond);
        if (top5MediaList == null) {
            return null;
        }
        cond.setSites(top5MediaList);
        List<String> legendList = new ArrayList<String>();
        List<BaseSeries<Object>> series = searchPolicyMediaArticleTrendResult(cond, legendList);
        if (CollectionUtils.isEmpty(series)) {
            return null;
        } else {
            JSONObject result = new JSONObject();
            result.put("series", series);
            result.put("legend", legendList);
            result.put("name", DateUtils.getLegendDayList(cond.getDate()));

            return result;
        }
    }

    private List<BaseSeries<Object>> searchPolicyMediaArticleTrendResult(
            ConditionDTO cond, List<String> legendList) {
        List<BaseSeries<Object>> result = new ArrayList<BaseSeries<Object>>();

        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder siteAgg = AggregationBuilders.terms("site").field("site");
        TermsBuilder dayAgg = AggregationBuilders.terms("day").field("day");
        SumBuilder hitNumAgg = AggregationBuilders.sum("hitNum").field("hitNum");
        dayAgg.subAggregation(hitNumAgg);
        siteAgg.subAggregation(dayAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(siteAgg).build();

        logger.info("query: {}", query.toString());

        template.query(query, res -> {
            Terms terms = res.getAggregations().get("site");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            List<Integer> dayList = DateUtils.getDayList(cond.getDate());
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isEmpty(bucket.getKeyAsString())) {
                    continue;
                }
                legendList.add(bucket.getKeyAsString());

                Terms dayTerms = bucket.getAggregations().get("day");
                List<Terms.Bucket> dayBuckets = dayTerms.getBuckets();
                if (dayBuckets == null || dayBuckets.size() < 1) {
                    continue;
                }
                Object[] dayArray = new Object[10];
                for (Terms.Bucket dayBucket : dayBuckets) {
                    if (StringUtils.isNotEmpty(dayBucket.getKeyAsString())) {
                        int dayKey = Integer.valueOf(dayBucket.getKeyAsString());
                        int indexOf = dayList.indexOf(dayKey);
                        Sum sum = dayBucket.getAggregations().get("hitNum");
                        double traVal = sum == null ? 0 : sum.getValue();
                        dayArray[indexOf] = NumberUtils.formatDouble(traVal);
                    }
                }
                result.add(new Bar<Object>().setData(Arrays.asList(dayArray))
                        .setName(bucket.getKeyAsString()).setStack("网站"));
            }
            return null;
        });

        return result;
    }

    @Override
    public JSONObject searchPolicyMediaParMapAnaylysis(ConditionDTO cond) {
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder siteAgg = AggregationBuilders.terms("site").field("site").size(SysConst.AGG_SITE_SIZE);
        TermsBuilder provinceAgg = AggregationBuilders.terms("province").field("province").size(34);
        siteAgg.subAggregation(provinceAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(siteAgg).build();

        logger.info("query: {}", query.toString());

        List<String> legendList = new ArrayList<String>();
        List<Map> series = new ArrayList<Map>();
        long maxValue = template.query(query, res -> {
            long max = 0;
            Terms terms = res.getAggregations().get("site");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return max;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isEmpty(bucket.getKeyAsString())) {
                    continue;
                }
                legendList.add(bucket.getKeyAsString());

                Terms provinceTerms = bucket.getAggregations().get("province");
                List<Terms.Bucket> provinceBuckets = provinceTerms.getBuckets();
                if (provinceBuckets == null || provinceBuckets.size() < 1) {
                    continue;
                }
                String commonProvince = SysConst.COMMON_PROVINCE_STR;
                List<DataLongVo> data = new ArrayList<DataLongVo>();
                for (Terms.Bucket provinceBucket : provinceBuckets) {
                    if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                        DataLongVo vo = new DataLongVo();
                        vo.setName(provinceBucket.getKeyAsString().trim());
                        commonProvince = commonProvince.replace(vo.getName(), "");
                        vo.setValue(provinceBucket.getDocCount());
                        if (max < provinceBucket.getDocCount()) {
                            max = provinceBucket.getDocCount();
                        }
                        data.add(vo);
                    }
                }
                JSONArray provinceArray = com.huishu.ieanalysis.utils.StringUtils.split(commonProvince, ",");
                for (int i = 0, size = provinceArray.size(); i < size; i++) {
                    DataLongVo vo = new DataLongVo();
                    vo.setName(provinceArray.getString(i));
                    vo.setValue(0L);
                    data.add(vo);
                }
                series.add(new Map().setData(data).setName(bucket.getKeyAsString()));
            }
            return max;
        });
        if (CollectionUtils.isEmpty(series)) {
            return null;
        } else {
            JSONObject result = new JSONObject();
            result.put("series", series);
            result.put("legend", legendList);
            result.put("max", maxValue);

            return result;
        }
    }

    @Override
    public JSONObject searchPolicyMediaParAnaylysis(ConditionDTO cond) {
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder siteAgg = AggregationBuilders.terms("site").field("site").size(5);
        TermsBuilder emotionAgg = AggregationBuilders.terms("emotionMark").field("emotionMark");
        siteAgg.subAggregation(emotionAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(siteAgg).build();

        logger.info("query: {}", query.toString());

        List<BaseSeries<Object>> series = new ArrayList<BaseSeries<Object>>();
        List<String> nameList = new ArrayList<String>();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("site");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }

            Object[] positiveArray = new Object[buckets.size()];
            Object[] natureArray = new Object[buckets.size()];
            Object[] negativeArray = new Object[buckets.size()];

            int index = 0;
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    nameList.add(bucket.getKeyAsString());

                    Terms emotionTerms = bucket.getAggregations().get("emotionMark");
                    List<Terms.Bucket> emotionBuckets = emotionTerms.getBuckets();
                    handleEmotionBuckets(positiveArray, natureArray, negativeArray, index, emotionBuckets);
                }
                index = index + 1;
            }
            fillEChartsSeriesData(series, positiveArray, natureArray, negativeArray);
            return null;
        });
        if (CollectionUtils.isEmpty(series)) {
            return null;
        } else {
            JSONObject result = new JSONObject();

            // 0,负面;1,中性;2,正面
            Object[] legendArray = {"负面", "中性", "正面"};
            List<Object> aliasName = new ArrayList<Object>();
            if (nameList != null && nameList.size() > 0) {
                for (String name : nameList) {
                    if (name.length() > SysConst.ECHART_XNAME_LEN) {
                        aliasName.add(name.substring(0, SysConst.ECHART_XNAME_LEN) + "...");
                    } else {
                        aliasName.add(name);
                    }
                }
            }
            result.put("aliasName", aliasName);
            result.put("series", series);
            result.put("legend", legendArray);
            result.put("name", nameList);

            return result;
        }
    }

    private void fillEChartsSeriesData(List<BaseSeries<Object>> series, Object[] positiveArray,
                                       Object[] natureArray, Object[] negativeArray) {
        if (ArrayUtils.isEmpty(negativeArray) && ArrayUtils.isEmpty(natureArray)
                && ArrayUtils.isEmpty(positiveArray)) {
            // do nothing
        } else {
            for (int i = 0; i < negativeArray.length; i++) {
                if (positiveArray[i] == null) {
                    positiveArray[i] = 0d;
                }
                if (natureArray[i] == null) {
                    natureArray[i] = 0d;
                }
                if (negativeArray[i] == null) {
                    negativeArray[i] = 0d;
                }
            }
            series.add(new Bar<Object>().setData(Arrays.asList(negativeArray)).setName("负面"));
            series.add(new Bar<Object>().setData(Arrays.asList(natureArray)).setName("中性"));
            series.add(new Bar<Object>().setData(Arrays.asList(positiveArray)).setName("正面"));
        }
    }

    private void handleEmotionBuckets(Object[] positiveArray, Object[] natureArray,
                                      Object[] negativeArray, int index,
                                      List<Terms.Bucket> emotionBuckets) {
        if (emotionBuckets != null && emotionBuckets.size() > 0) {
            for (Terms.Bucket emotionBucket : emotionBuckets) {
                if (StringUtils.isNotEmpty(emotionBucket.getKeyAsString())) {
                    int emotionKey = Integer.parseInt(emotionBucket.getKeyAsString());
                    if (emotionKey == 0) {
                        negativeArray[index] = NumberUtils.formatDouble(
                                Double.valueOf(emotionBucket.getDocCount() + ""));
                    }
                    if (emotionKey == 1) {
                        natureArray[index] = NumberUtils.formatDouble(
                                Double.valueOf(emotionBucket.getDocCount() + ""));
                    }
                    if (emotionKey == 2) {
                        positiveArray[index] = NumberUtils.formatDouble(
                                Double.valueOf(emotionBucket.getDocCount() + ""));
                    }
                }
            }
        }
    }

    @Override
    public JSONObject searchPolicySocialParAnaylysis(ConditionDTO cond) {
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder publishTypeAgg = AggregationBuilders.terms("publishType").field("publishType");
        TermsBuilder emotionAgg = AggregationBuilders.terms("emotionMark").field("emotionMark");
        publishTypeAgg.subAggregation(emotionAgg);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(publishTypeAgg).build();

        logger.info("query: {}", query.toString());

        List<BaseSeries<Object>> series = new ArrayList<BaseSeries<Object>>();
        List<String> nameList = new ArrayList<String>();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("publishType");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }

            Object[] positiveArray = new Object[buckets.size()];
            Object[] natureArray = new Object[buckets.size()];
            Object[] negativeArray = new Object[buckets.size()];
            int index = 0;
            for (Terms.Bucket bucket : buckets) {
                if ("8".equals(bucket.getKeyAsString())) {
                    nameList.add("微信");
                    Terms emotionTerms = bucket.getAggregations().get("emotionMark");
                    List<Terms.Bucket> emotionBuckets = emotionTerms.getBuckets();
                    handleEmotionBuckets(positiveArray, natureArray, negativeArray, index, emotionBuckets);
                    index = index + 1;
                }
            }
            fillEChartsSeriesData(series, positiveArray, natureArray, negativeArray);
            return null;
        });
        if (CollectionUtils.isEmpty(series)) {
            return null;
        } else {
            JSONObject result = new JSONObject();
            // 0,负面;1,中性;2,正面
            Object[] legendArray = {"负面", "中性", "正面"};
            result.put("series", series);
            result.put("legend", legendArray);
            result.put("name", nameList);

            return result;
        }
    }

    @Override
    public JSONObject searchPolicySpecialEventShaft(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "time"));
        Pageable pageable = new PageRequest(cond.getPageNumber() - 1,
                cond.getPageSize(), new Sort(orders));
        BoolQueryBuilder queryBuilder = getBuilders(cond);
        queryBuilder.must(QueryBuilders.termQuery("hotEventMark", 1));

        logger.info("queryBuilder: {}", queryBuilder.toString());

        Page<DgapData> page = dgapDataRepository.search(queryBuilder, pageable);
        result.put("page", page);

        return result;
    }

    @Override
    public JSONObject searchPolicyHotKeyWords(ConditionDTO cond) {
        JSONObject result = new JSONObject();

        BoolQueryBuilder queryBuilder = getBuilders(cond);
        queryBuilder.must(QueryBuilders.termQuery("hotEventMark", 1));

        logger.info("queryBuilder: {}", queryBuilder.toString());

        Iterable<DgapData> search = dgapDataRepository.search(queryBuilder);
        Iterator<DgapData> iterator = search.iterator();
        List<String> list = new ArrayList<String>();
        if (iterator.hasNext()) {
            DgapData next = iterator.next();
            if (StringUtils.isNotEmpty(next.getContent())) {
                list.add(next.getContent());
            }
        }
        if (CollectionUtils.isEmpty(list)) {
            return result;
        }
        JSONObject keywordCloud = ArticleConToKeywordCloud.toKeywordCloud(list, 0, 100);
        if (keywordCloud == null) {
            return result;
        }
        ArrayList<KeywordModel> arrayList = (java.util.ArrayList<KeywordModel>) keywordCloud.get("sort");
        if (CollectionUtils.isEmpty(arrayList)) {
            return result;
        }
        List<DataLongVo> data = getDataLongVoList(arrayList);
        result.put("data", data);

        return result;
    }

    @Override
    public JSONObject searchPolicyHotEventPlaceDistrbute(ConditionDTO cond) {
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder provinceAgg = AggregationBuilders.terms("province").field("province").size(34);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(provinceAgg).build();

        logger.info("query: {}", query.toString());

        List<DataLongVo> data = new ArrayList<DataLongVo>();
        List<Map> series = new ArrayList<Map>();
        long maxValue = template.query(query, res -> {
            long max = 0;
            Terms terms = res.getAggregations().get("province");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return max;
            }
            String commonProvince = SysConst.COMMON_PROVINCE_STR;
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    DataLongVo vo = new DataLongVo();
                    vo.setName(bucket.getKeyAsString());
                    vo.setValue(bucket.getDocCount());
                    commonProvince = commonProvince.replace(vo.getName(), "");
                    data.add(vo);
                    if (max < bucket.getDocCount()) {
                        max = bucket.getDocCount();
                    }
                }
            }

            JSONArray provinceArray = com.huishu.ieanalysis.utils.StringUtils.split(commonProvince, ",");
            for (int i = 0, size = provinceArray.size(); i < size; i++) {
                DataLongVo vo = new DataLongVo();
                vo.setName(provinceArray.getString(i));
                vo.setValue(0L);
                data.add(vo);
            }
            series.add(new Map().setData(data).setName(""));
            return max;
        });
        if (CollectionUtils.isEmpty(series)) {
            return null;
        } else {
            JSONObject result = new JSONObject();
            result.put("series", series);
            result.put("max", maxValue);
            result.put("data", data);

            return result;
        }
    }

    @Override
    public JSONObject searchPolicyHotEventAmountDistrbute(ConditionDTO cond) {
        BoolQueryBuilder queryBuilder = getBuilders(cond);

        logger.info("queryBuilder: {}", queryBuilder.toString());

        TermsBuilder siteAgg = AggregationBuilders.terms("site").field("site").size(SysConst.AGG_SITE_SIZE);
        NativeSearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).addAggregation(siteAgg).build();

        logger.info("query: {}", query.toString());

        List<DataLongVo> data = searchSiteAggregationData(query);

        int len = data.size() > 15 ? 15 : data.size();
        List<DataLongVo> subList = data.subList(0, len);
        if (CollectionUtils.isEmpty(subList)) {
            return null;
        } else {
            JSONObject result = new JSONObject();

            List<Object> nameList = new ArrayList<Object>();
            List<Object> valueList = new ArrayList<Object>();
            for (DataLongVo vo : subList) {
                nameList.add(vo.getName());
                valueList.add(vo.getValue());
            }
            List<BaseSeries<Object>> series = new ArrayList<BaseSeries<Object>>();
            series.add(new Bar<Object>().setData(valueList).setName("网站"));
            result.put("series", series);
            result.put("name", nameList);

            return result;
        }
    }

    private List<DataLongVo> searchSiteAggregationData(NativeSearchQuery query) {
        List<DataLongVo> result = new ArrayList<DataLongVo>();

        template.query(query, res -> {
            Terms terms = res.getAggregations().get("site");
            List<Terms.Bucket> buckets = terms.getBuckets();
            if (buckets == null || buckets.size() < 1) {
                return null;
            }
            for (Terms.Bucket bucket : buckets) {
                if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                    DataLongVo vo = new DataLongVo();
                    vo.setName(bucket.getKeyAsString());
                    vo.setValue(bucket.getDocCount());
                    result.add(vo);
                }
            }
            return null;
        });
        result.sort(Comparator.comparing(DataLongVo::getValue).reversed());

        return result;
    }

    @Override
    public JSONObject searchPolicyHotKeyWordsFrequency(ConditionDTO cond) {
        BoolQueryBuilder queryBuilder = getBuilders(cond);
        queryBuilder.must(QueryBuilders.termQuery("hotEventMark", 1));

        logger.info("queryBuilder: {}", queryBuilder.toString());

        Iterable<DgapData> search = dgapDataRepository.search(queryBuilder);
        Iterator<DgapData> iterator = search.iterator();
        List<String> list = new ArrayList<String>();
        List<String> wordList = new ArrayList<String>();
        List<Long> valueList = new ArrayList<Long>();
        if (iterator.hasNext()) {
            DgapData next = iterator.next();
            if (StringUtils.isNotEmpty(next.getContent())) {
                list.add(next.getContent());
            }
        }

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        JSONObject keywordCloud = ArticleConToKeywordCloud.toKeywordCloud(list, 0, 100);
        if (keywordCloud == null) {
            return null;
        }

        ArrayList<KeywordModel> arrayList = (ArrayList<KeywordModel>) keywordCloud.get("sort");
        if (CollectionUtils.isEmpty(arrayList)) {
            return null;
        }

        List<DataLongVo> data = getDataLongVoList(arrayList);
        data.sort(Comparator.comparing(DataLongVo::getValue).reversed());

        int len = data.size() > 10 ? 10 : data.size();
        List<DataLongVo> subList = data.subList(0, len);
        for (DataLongVo key : subList) {
            if (StringUtils.isNotEmpty(key.getName())) {
                wordList.add(key.getName());
                valueList.add(key.getValue());
            }
        }

        if (CollectionUtils.isEmpty(wordList)) {
            return null;
        } else {
            JSONObject result = new JSONObject();

            List<BaseSeries<Long>> series = new ArrayList<BaseSeries<Long>>();
            series.add(new Bar<Long>().setData(valueList).setName(""));
            result.put("series", series);
            result.put("name", wordList);

            return result;
        }
    }

    private List<DataLongVo> getDataLongVoList(ArrayList<KeywordModel> arrayList) {
        List<DataLongVo> result = new ArrayList<DataLongVo>();

        for (KeywordModel key : arrayList) {
            if (StringUtils.isNotEmpty(key.getKeyword())) {
                DataLongVo vo = new DataLongVo();
                vo.setName(key.getKeyword());
                vo.setValue(Math.round(key.getCount()));
                result.add(vo);
            }
        }

        return result;
    }
}
