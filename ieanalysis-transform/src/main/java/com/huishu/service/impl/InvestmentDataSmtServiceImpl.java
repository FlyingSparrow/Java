package com.huishu.service.impl;

import com.huishu.datasource.TargetDataSource;
import com.huishu.entity.InvestmentDataSmt;
import com.huishu.repository.InvestmentDataSmtRepository;
import com.huishu.service.InvestmentDataSmtService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjianchun
 */
@Service
public class InvestmentDataSmtServiceImpl implements InvestmentDataSmtService {

    @Autowired
    private InvestmentDataSmtRepository investmentDataSmtRepository;

    @Override
    @TargetDataSource(name = "chuangtou")
    public List<InvestmentDataSmt> findOneHundred(InvestmentDataSmt data, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<InvestmentDataSmt> page = investmentDataSmtRepository.findAll((root, query, cb) -> {
            Path<String> fldRecdId = root.get("fldRecdId");
            Path<String> biaoShi = root.get("biaoShi");
            if (data != null) {
                List<Predicate> queryList = new ArrayList<Predicate>();
                if (StringUtils.isNotEmpty(data.getFldRecdId())) {
                    queryList.add(cb.greaterThan(fldRecdId, data.getFldRecdId()));
                }
                if (data.getBiaoShi() != null) {
                    queryList.add(cb.equal(biaoShi, data.getBiaoShi()));
                }
                Predicate[] querys = new Predicate[queryList.size()];
                if (queryList != null && queryList.size() > 0) {
                    for (int i = 0, len = queryList.size(); i < len; i++) {
                        querys[i] = queryList.get(i);
                    }
                }
                query.where(querys).orderBy(new OrderImpl(fldRecdId, true));
            }
            return null;
        }, pageable);
        if (page == null || page.getContent() == null || page.getContent().size() == 0) {
            return new ArrayList<InvestmentDataSmt>();
        } else {
            return page.getContent();
        }
    }

    @Override
    @TargetDataSource(name = "chuangtou")
    public void save(List<InvestmentDataSmt> news) {
        investmentDataSmtRepository.save(news);
    }

    @Override
    @TargetDataSource(name = "chuangtou")
    public void delete(List<InvestmentDataSmt> news) {
        investmentDataSmtRepository.delete(news);
    }


}
