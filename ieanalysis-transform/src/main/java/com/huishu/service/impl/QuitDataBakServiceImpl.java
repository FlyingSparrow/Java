package com.huishu.service.impl;

import com.huishu.datasource.TargetDataSource;
import com.huishu.entity.QuitDataBak;
import com.huishu.repository.QuitDataBakRepository;
import com.huishu.service.QuitDataBakService;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjianchun
 */
@Service
public class QuitDataBakServiceImpl implements QuitDataBakService {

    @Autowired
    private QuitDataBakRepository quitDataBakRepository;

    @Override
    public List<QuitDataBak> findOneHundred(QuitDataBak data, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<QuitDataBak> page = quitDataBakRepository.findAll(new Specification<QuitDataBak>() {
            @Override
            public Predicate toPredicate(Root<QuitDataBak> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Long> id = root.get("id");
                Path<String> biaoShi = root.get("biaoShi");
                if (data != null) {
                    List<Predicate> queryList = new ArrayList<Predicate>();
                    if (data.getId() != null) {
                        queryList.add(cb.greaterThan(id, data.getId()));
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
                    query.where(querys).orderBy(new OrderImpl(id, true));
                }
                return null;
            }
        }, pageable);
        if (page == null || page.getContent() == null || page.getContent().size() == 0) {
            return new ArrayList<QuitDataBak>();
        } else {
            return page.getContent();
        }
    }

    @Override
    public void save(List<QuitDataBak> news) {
        quitDataBakRepository.save(news);
    }

    @Override
    @TargetDataSource(name = "chuangtou")
    public void delete(List<QuitDataBak> news) {
        quitDataBakRepository.delete(news);
    }

    @Override
    public long findExit(QuitDataBak bak) {
        return quitDataBakRepository.countByInvestorAndCompanyNameAndIndustryAndTime(bak.getInvestor(), bak.getCompanyName(), bak.getIndustry(), bak.getTime());
    }


}
