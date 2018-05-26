package com.huishu.service.impl;

import com.huishu.entity.PolicyBak;
import com.huishu.repository.PolicyBakRepository;
import com.huishu.service.PolicyBakService;
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
public class PolicyBakServiceImpl implements PolicyBakService {

    @Autowired
    private PolicyBakRepository policyBakRepository;

    @Override
    public List<PolicyBak> findOneHundred(PolicyBak news, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<PolicyBak> page = policyBakRepository.findAll(new Specification<PolicyBak>() {
            @Override
            public Predicate toPredicate(Root<PolicyBak> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Long> id = root.get("id");
                Path<String> biaoShi = root.get("biaoShi");
                if (news != null) {
                    List<Predicate> queryList = new ArrayList<Predicate>();
                    if (news.getId() != null) {
                        queryList.add(cb.greaterThan(id, news.getId()));
                    }
                    if (news.getBiaoShi() != null) {
                        queryList.add(cb.equal(biaoShi, news.getBiaoShi()));
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
            return new ArrayList<PolicyBak>();
        } else {
            return page.getContent();
        }
    }

    @Override
    public void save(List<PolicyBak> news) {
        policyBakRepository.save(news);
    }

    @Override
    public long findExist(PolicyBak bak) {
        return policyBakRepository.countByFldUrlAddrAndFldtitle(bak.getFldUrlAddr(), bak.getFldtitle());
    }
}
