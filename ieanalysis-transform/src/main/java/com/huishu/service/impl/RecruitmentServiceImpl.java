package com.huishu.service.impl;

import com.huishu.datasource.TargetDataSource;
import com.huishu.entity.Recruitment;
import com.huishu.repository.RecruitmentRepository;
import com.huishu.service.RecruitmentService;
import org.apache.commons.lang3.StringUtils;
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
public class RecruitmentServiceImpl implements RecruitmentService {

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Override
    @TargetDataSource(name = "news")
    public List<Recruitment> findOneHundred(Recruitment recruitment, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<Recruitment> page = recruitmentRepository.findAll(new Specification<Recruitment>() {
            @Override
            public Predicate toPredicate(Root<Recruitment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> fldRecdId = root.get("fldRecdId");
                Path<String> biaoShi = root.get("biaoShi");
                if (recruitment != null) {
                    List<Predicate> queryList = new ArrayList<Predicate>();
                    if (StringUtils.isNotEmpty(recruitment.getFldRecdId())) {
                        queryList.add(cb.greaterThan(fldRecdId, recruitment.getFldRecdId()));
                    }
                    if (recruitment.getBiaoShi() != null) {
                        queryList.add(cb.equal(biaoShi, recruitment.getBiaoShi()));
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
            }
        }, pageable);
        if (page == null || page.getContent() == null || page.getContent().size() == 0) {
            return new ArrayList<Recruitment>();
        } else {
            return page.getContent();
        }
    }

    @Override
    @TargetDataSource(name = "news")
    public void save(List<Recruitment> news) {
        recruitmentRepository.save(news);
    }

    @Override
    @TargetDataSource(name = "news")
    public void delete(List<Recruitment> news) {
        recruitmentRepository.delete(news);
    }


}
