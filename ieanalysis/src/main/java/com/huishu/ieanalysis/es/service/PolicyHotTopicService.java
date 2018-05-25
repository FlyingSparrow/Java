package com.huishu.ieanalysis.es.service;

import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.entity.DgapData;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface PolicyHotTopicService {

    /**
     * 政策热点主题信息
     * @param cond
     * @return
     */
    Page<DgapData> searchPolicyHotTopicInfo(ConditionDTO cond);

    /**
     * 查询省信息
     * @param cond
     * @return
     */
    List<String> searchProvince(ConditionDTO cond);
}