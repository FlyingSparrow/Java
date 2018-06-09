package com.jdjr.opinion.mongodb.service.impl;


import com.jdjr.opinion.mongodb.entity.EnterpriseInfo;
import com.jdjr.opinion.mongodb.repository.EnterpriseInfoRepository;
import com.jdjr.opinion.mongodb.service.EnterpriseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnterpriseInfoServiceImpl implements EnterpriseInfoService {

    @Autowired
    private EnterpriseInfoRepository enterpriseInfoRepository;

    @Override
    public boolean batchAdd(List<EnterpriseInfo> list) {
        enterpriseInfoRepository.save(list);
        return true;
    }

    @Override
    public boolean save(EnterpriseInfo entity) {
        boolean isExist = this.isExist(entity.getEnterpriseName());
        if (!isExist) {
            enterpriseInfoRepository.save(entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public EnterpriseInfo saveResult(EnterpriseInfo entity) {
        EnterpriseInfo isExist = this.findByEnterpriseName(entity.getEnterpriseName());
        if (isExist == null) {
            return enterpriseInfoRepository.save(entity);
        } else {
            return isExist;
        }
    }

    @Override
    public boolean isExist(String enterpriseName) {
        EnterpriseInfo enterpriseInfo = this.findByEnterpriseName(enterpriseName);
        return enterpriseInfo != null;
    }

    @Override
    public List<String> findEnterpriseIdList() {
        return enterpriseInfoRepository.findAll().stream()
                .map(EnterpriseInfo::getId)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<String> findEnterpriseNameList() {
        return enterpriseInfoRepository.findAll().stream()
                .map(EnterpriseInfo::getEnterpriseName)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnterpriseInfo> findEnterpriseInfoList(String enterpriseName) {
        return enterpriseInfoRepository.findByEnterpriseNameContaining(enterpriseName);
    }

    @Override
    public List<EnterpriseInfo> findListByIds(List<String> ids) {
        return enterpriseInfoRepository.findByIdIn(ids);
    }

    @Override
    public EnterpriseInfo findById(String id) {
        return enterpriseInfoRepository.findOne(id);
    }

    @Override
    public EnterpriseInfo findByEnterpriseName(String enterpriseName) {
        return enterpriseInfoRepository.findByEnterpriseName(enterpriseName);
    }
}
