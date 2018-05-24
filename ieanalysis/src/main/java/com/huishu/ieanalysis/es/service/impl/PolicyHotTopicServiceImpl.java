package com.huishu.ieanalysis.es.service.impl;

import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.entity.DgapData;
import com.huishu.ieanalysis.es.repository.DgapDataRepository;
import com.huishu.ieanalysis.es.service.AbstractService;
import com.huishu.ieanalysis.es.service.PolicyHotTopicService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjianchun
 */
@Service
public class PolicyHotTopicServiceImpl extends AbstractService implements
        PolicyHotTopicService {

    @Autowired
    private DgapDataRepository dgapDataRepository;

    @Override
    public Page<DgapData> searchPolicyHotTopicInfo(ConditionDTO cond) {
        // vectorType;//1,媒体;2, 社会 ;3,行业;4, 政策;5, 其它
        cond.setPageNumber(1);
        cond.setPageSize(20);
        cond.setHotEventMark(1L);
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Direction.DESC, "time"));
        orders.add(new Order(Direction.DESC, "hitNum"));
        Pageable page = new PageRequest(cond.getPageNumber() - 1,
                cond.getPageSize(), new Sort(orders));
        BoolQueryBuilder builders = getBuilders(cond);
        Page<DgapData> search = dgapDataRepository.search(builders, page);
        return search;
    }

    @Override
    public List<String> searchProvince(ConditionDTO cond) {
        BoolQueryBuilder builders = getBuilders(cond);
        logger.info(builders.toString());
        TermsBuilder agg = AggregationBuilders.terms("province")
                .field("province").size(34);

        NativeSearchQuery query = getSearchQueryBuilder().withQuery(builders)
                .addAggregation(agg).build();
        List<String> list = new ArrayList<String>();
        template.query(query, res -> {
            Terms terms = res.getAggregations().get("province");
            List<Bucket> buckets = terms.getBuckets();
            if (buckets != null && buckets.size() > 0) {
                for (Bucket bucket : buckets) {
                    if (StringUtils.isNotEmpty(bucket.getKeyAsString())) {
                        if (!list.contains(bucket.getKeyAsString().trim())) {
                            list.add(bucket.getKeyAsString().trim());
                        }
                    }
                }
            }
            return null;
        });
        return list;
    }
}
