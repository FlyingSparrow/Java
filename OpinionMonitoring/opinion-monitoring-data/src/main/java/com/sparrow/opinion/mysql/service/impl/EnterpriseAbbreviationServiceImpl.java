package com.sparrow.opinion.mysql.service.impl;

import com.sparrow.opinion.constants.SysConst;
import com.sparrow.opinion.mysql.entity.EnterpriseAbbreviation;
import com.sparrow.opinion.mysql.repository.EnterpriseAbbreviationRepository;
import com.sparrow.opinion.mysql.service.EnterpriseAbbreviationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by wangjianchun on 2017/12/7.
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class EnterpriseAbbreviationServiceImpl implements EnterpriseAbbreviationService {

    @Autowired
    private EnterpriseAbbreviationRepository enterpriseAbbreviationRepository;

    @Override
    public boolean save(EnterpriseAbbreviation entity) {
        if(!isExist(entity.getEnterpriseId(), entity.getAbbreviation())){
            enterpriseAbbreviationRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public Page<EnterpriseAbbreviation> findPageList(EnterpriseAbbreviation entity) {
        Pageable pageable = new PageRequest((entity.getPageNumber()-1), entity.getPageSize(),
            new Sort(Sort.Direction.ASC, "enterpriseName"));
        ExampleMatcher matcher = ExampleMatcher.matching();
        if(StringUtils.isNotEmpty(entity.getEnterpriseName())){
            matcher = matcher.withMatcher("enterpriseName",
                    ExampleMatcher.GenericPropertyMatchers.contains());
        }else{
            entity.setEnterpriseName(null);
        }
        matcher = matcher.withMatcher("deleteFlag",
                ExampleMatcher.GenericPropertyMatchers.exact());
        Example<EnterpriseAbbreviation> example = Example.of(entity, matcher);
        return enterpriseAbbreviationRepository.findAll(example, pageable);
    }

    @Override
    public boolean isExist(String enterpriseId, String abbreviation) {
        EnterpriseAbbreviation entity = new EnterpriseAbbreviation();
        entity.setEnterpriseId(enterpriseId);
        entity.setAbbreviation(abbreviation);
        entity.setDeleteFlag(SysConst.Switch.NO.getName());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("enterpriseId", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("abbreviation", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("deleteFlag", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<EnterpriseAbbreviation> example = Example.of(entity, matcher);
        return enterpriseAbbreviationRepository.exists(example);
    }

    @Override
    public EnterpriseAbbreviation findByEnterpriseNameAndAbbreviation(String enterpriseName, String abbreviation) {
        EnterpriseAbbreviation entity = new EnterpriseAbbreviation();
        entity.setEnterpriseName(enterpriseName);
        entity.setAbbreviation(abbreviation);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("enterpriseName", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("abbreviation", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<EnterpriseAbbreviation> example = Example.of(entity, matcher);
        return enterpriseAbbreviationRepository.findOne(example);
    }

    @Override
    public List<EnterpriseAbbreviation> findAllByEnterpriseName(String enterpriseName) {
        EnterpriseAbbreviation entity = new EnterpriseAbbreviation();
        entity.setEnterpriseName(enterpriseName);
        entity.setDeleteFlag(SysConst.Switch.NO.getName());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("enterpriseName", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("deleteFlag", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<EnterpriseAbbreviation> example = Example.of(entity, matcher);
        return enterpriseAbbreviationRepository.findAll(example);
    }

    @Override
    public boolean delete(Long id) {
        EnterpriseAbbreviation entity = enterpriseAbbreviationRepository.findOne(id);
        if(entity != null){
            entity.setDeleteFlag(SysConst.Switch.YES.getName());
            enterpriseAbbreviationRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean batchDelete(Set<Long> ids) {
        for(Long id : ids){
            delete(id);
        }
        return true;
    }

    @Override
    public List<EnterpriseAbbreviation> findAll() {
        return enterpriseAbbreviationRepository.findAll();
    }

    @Override
    public EnterpriseAbbreviation findById(Long id) {
        return enterpriseAbbreviationRepository.findOne(id);
    }

    @Override
    public boolean update(EnterpriseAbbreviation entity) {
        enterpriseAbbreviationRepository.save(entity);
        return true;
    }
}
