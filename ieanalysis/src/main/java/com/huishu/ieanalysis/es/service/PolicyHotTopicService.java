package com.huishu.ieanalysis.es.service;

import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.entity.DgapData;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface PolicyHotTopicService {

    Page<DgapData> searchPolicyHotTopicInfo(ConditionDTO cond);

    List<String> searchProvince(ConditionDTO cond);
}