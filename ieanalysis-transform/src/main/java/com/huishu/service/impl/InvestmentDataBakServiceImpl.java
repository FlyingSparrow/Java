package com.huishu.service.impl;

import com.huishu.entity.InvestmentDataBak;
import com.huishu.repository.InvestmentDataBakRepository;
import com.huishu.service.InvestmentDataBakService;
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
public class InvestmentDataBakServiceImpl implements InvestmentDataBakService {

    @Autowired
    private InvestmentDataBakRepository investmentDataBakRepository;

    @Override
    public List<InvestmentDataBak> findOneHundred(InvestmentDataBak entity, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<InvestmentDataBak> page = investmentDataBakRepository.findAll((root, query, cb) -> {
            Path<Long> id = root.get("id");
            Path<String> biaoShi = root.get("biaoShi");
            if (entity != null) {
                List<Predicate> queryList = new ArrayList<Predicate>();
                if (entity.getId() != null) {
                    queryList.add(cb.greaterThan(id, entity.getId()));
                }
                if (entity.getBiaoShi() != null) {
                    queryList.add(cb.equal(biaoShi, entity.getBiaoShi()));
                }
                Predicate[] querys = new Predicate[queryList.size()];
                if (queryList != null && queryList.size() > 0) {
                    for (int i = 0, len = queryList.size(); i < len; i++) {
                        querys[i] = queryList.get(i);
                    }
                }
                query.where(querys).orderBy(new OrderImpl(id, true));
            }
            return null;
        }, pageable);
        if (page == null || page.getContent() == null || page.getContent().size() == 0) {
            return new ArrayList<InvestmentDataBak>();
        } else {
            return page.getContent();
        }
    }

    @Override
    public void save(List<InvestmentDataBak> list) {
        investmentDataBakRepository.save(list);
    }

    @Override
    public void delete(List<InvestmentDataBak> list) {
        investmentDataBakRepository.delete(list);
    }

    @Override
    public long findExit(InvestmentDataBak entity) {
        return investmentDataBakRepository.countByInvestorAndCompanyNameAndIndustryAndRegionAndTimeAndAmount(entity.getInvestor(), entity.getCompanyName(), entity.getIndustry(), entity.getRegion(), entity.getTime(), entity.getAmount());
    }

    @Override
    public Page<InvestmentDataBak> findByPage(InvestmentDataBak entity, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<InvestmentDataBak> page = investmentDataBakRepository.findAll((root, query, cb) -> {
            Path<Long> id = root.get("id");
            Path<String> biaoShi = root.get("biaoShi");
            if (entity != null) {
                List<Predicate> queryList = new ArrayList<Predicate>();
                if (entity.getId() != null) {
                    queryList.add(cb.greaterThan(id, entity.getId()));
                }
                if (entity.getBiaoShi() != null) {
                    queryList.add(cb.equal(biaoShi, entity.getBiaoShi()));
                }
                Predicate[] querys = new Predicate[queryList.size()];
                if (queryList != null && queryList.size() > 0) {
                    for (int i = 0, len = queryList.size(); i < len; i++) {
                        querys[i] = queryList.get(i);
                    }
                }
                query.where(querys).orderBy(new OrderImpl(id, true));
            }
            return null;
        }, pageable);

        return page;
    }
}
