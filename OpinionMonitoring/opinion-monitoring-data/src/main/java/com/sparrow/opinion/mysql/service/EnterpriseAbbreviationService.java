package com.sparrow.opinion.mysql.service;

import com.sparrow.opinion.mysql.entity.EnterpriseAbbreviation;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

/**
 * Created by wangjianchun on 2017/12/7.
 */
public interface EnterpriseAbbreviationService {

    boolean save(EnterpriseAbbreviation entity);

    boolean delete(Long id);

    boolean batchDelete(Set<Long> ids);

    Page<EnterpriseAbbreviation> findPageList(EnterpriseAbbreviation entity);

    boolean isExist(String enterpriseId, String shortName);

    EnterpriseAbbreviation findByEnterpriseNameAndAbbreviation(String enterpriseName, String abbreviation);

    List<EnterpriseAbbreviation> findAllByEnterpriseName(String enterpriseName);

    List<EnterpriseAbbreviation> findAll();

    EnterpriseAbbreviation findById(Long id);

    boolean update(EnterpriseAbbreviation entity);

}
