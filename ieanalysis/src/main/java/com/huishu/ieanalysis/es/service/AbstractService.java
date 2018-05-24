package com.huishu.ieanalysis.es.service;

import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

/**
 * 抽象服务类
 * 提供公共公共方法
 *
 * @author yuwei
 */
public abstract class AbstractService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    protected ElasticsearchTemplate template;

    @SuppressWarnings("deprecation")
    protected BoolQueryBuilder getBuilders(ConditionDTO cond) {
        BoolQueryBuilder bool = new BoolQueryBuilder();
        if (StringUtils.isNotEmpty(cond.getProvince())) {
            bool.must(QueryBuilders.termQuery("province", cond.getProvince()));
        }
        if (StringUtils.isNotEmpty(cond.getArea())) {
            bool.must(QueryBuilders.termQuery("area", cond.getArea()));
        }
        if (cond.getYear() != null) {
            bool.must(QueryBuilders.termQuery("year", cond.getYear()));
        }
        if (cond.getMonth() != null) {
            bool.must(QueryBuilders.termQuery("month", cond.getMonth()));
        }
        if (cond.getDataType() != null) {
            bool.must(QueryBuilders.termQuery("dataType", cond.getDataType()));
        }
        if (cond.getPublishType() != null && cond.getPublishType().size() > 0) {
            BoolQueryBuilder or = new BoolQueryBuilder();
            for (String str : cond.getPublishType()) {
                or.should(QueryBuilders.termQuery("publishType", str));
            }
            bool.must(or);
        }
        if (cond.getSites() != null && cond.getSites().size() > 0) {
            BoolQueryBuilder or = new BoolQueryBuilder();
            for (String str : cond.getSites()) {
                or.should(QueryBuilders.termQuery("site", str));
            }
            bool.must(or);
        }
        if (cond.getSocialChannels() != null && cond.getSocialChannels().size() > 0) {
            BoolQueryBuilder or = new BoolQueryBuilder();
            for (String str : cond.getSocialChannels()) {
                or.should(QueryBuilders.termQuery("socialChannel", str));
            }
            bool.must(or);
        }
        if (cond.getJobsName() != null && cond.getJobsName().size() > 0) {
            BoolQueryBuilder or = new BoolQueryBuilder();
            for (String str : cond.getJobsName()) {
                or.should(QueryBuilders.termQuery("jobsName", str));
            }
            bool.must(or);
        }
        if (StringUtils.isNotEmpty(cond.getDepartment())) {
            bool.must(QueryBuilders.termQuery("publishDepartment", cond.getDepartment()));
        }
        if (StringUtils.isNotEmpty(cond.getVectorType())) {
            if (SysConst.VectorType.SITE.getCode().equals(cond.getVectorType())) {
                bool.mustNot(QueryBuilders.termQuery("site", ""));
            }
            if (SysConst.VectorType.SOCIAL_CHANNEL.getCode().equals(cond.getVectorType())) {
                bool.mustNot(QueryBuilders.termQuery("socialChannel", ""));
            }
            if (SysConst.VectorType.INDUSTRY.getCode().equals(cond.getVectorType())) {
                bool.mustNot(QueryBuilders.termQuery("industry", ""));
            }
        }
        if (StringUtils.isNotEmpty(cond.getPolicyInfoType())) {
            bool.must(QueryBuilders.termQuery("policyInfoType", cond.getPolicyInfoType()));
        }
        if (cond.getHotEventMark() != null) {
            bool.must(QueryBuilders.termQuery("hotEventMark", cond.getHotEventMark()));
        }
        if (cond.getReportType() != null) {
            bool.must(QueryBuilders.termQuery("reportType", cond.getReportType()));
        }
        RangeQueryBuilder rangBuilders = getRangBuilders(cond);
        if (rangBuilders != null) {
            bool.must(rangBuilders);
        }
        return bool;
    }

    protected RangeQueryBuilder getRangBuilders(ConditionDTO cond) {
        if (StringUtils.isNotEmpty(cond.getStartTime()) && StringUtils.isNotEmpty(cond.getEndTime())) {
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("time");
            if (StringUtils.isNotEmpty(cond.getStartTime())) {
                rangeQuery.gte(cond.getStartTime());
            }
            if (StringUtils.isNotEmpty(cond.getEndTime())) {
                rangeQuery.lte(cond.getEndTime());
            }
            return rangeQuery;
        } else {
            return null;
        }
    }

    protected NativeSearchQueryBuilder getSearchQueryBuilder() {
        return new NativeSearchQueryBuilder()
                .withIndices(SysConst.ES_INDEX)
                .withTypes(SysConst.ES_TYPE);
    }

    protected NativeSearchQueryBuilder getSearchWordQueryBuilder() {
        return new NativeSearchQueryBuilder()
                .withIndices(SysConst.ES_CLOUD_INDEX)
                .withTypes(SysConst.ES_CLOUD_TYPE);
    }
}
