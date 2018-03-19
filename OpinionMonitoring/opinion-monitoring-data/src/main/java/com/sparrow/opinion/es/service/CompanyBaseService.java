package com.sparrow.opinion.es.service;

import com.sparrow.opinion.es.entity.EsCompanyBase;

import java.util.List;

public interface CompanyBaseService {

    List<EsCompanyBase> findByCompanyNameLike(String companyName);

    List<EsCompanyBase> findByCompanyName(String companyName);

    List<EsCompanyBase> searchCompanyBase(String companyName, String type);

    List<EsCompanyBase> findAll();
}
