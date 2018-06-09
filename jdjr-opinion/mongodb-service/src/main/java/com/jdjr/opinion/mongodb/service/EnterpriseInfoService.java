package com.jdjr.opinion.mongodb.service;


import com.jdjr.opinion.mongodb.entity.EnterpriseInfo;

import java.util.List;

public interface EnterpriseInfoService {

    boolean batchAdd(List<EnterpriseInfo> list);

    boolean save(EnterpriseInfo entity);

    EnterpriseInfo saveResult(EnterpriseInfo entity);

    boolean isExist(String enterpriseName);

    List<String> findEnterpriseIdList();
    
    List<String> findEnterpriseNameList();

    List<EnterpriseInfo> findEnterpriseInfoList(String enterpriseName);

    List<EnterpriseInfo> findListByIds(List<String> ids);

    EnterpriseInfo findById(String id);

    EnterpriseInfo findByEnterpriseName(String enterpriseName);
}
