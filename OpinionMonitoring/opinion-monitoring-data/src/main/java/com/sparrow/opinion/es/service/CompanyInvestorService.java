package com.sparrow.opinion.es.service;


import com.sparrow.opinion.es.entity.EsCompanyInvestor;

import java.util.List;

public interface CompanyInvestorService {

    List<EsCompanyInvestor> findByenterpriseAbbrLike(String enterpriseAbbr);

    List<EsCompanyInvestor> findByNameC(String name_c);

    List<EsCompanyInvestor> searchCompanyInvestor(String companyName, String type);

    List<EsCompanyInvestor> findAll();

}
