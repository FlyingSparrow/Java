package com.huishu.service.impl;

import com.huishu.datasource.TargetDataSource;
import com.huishu.entity.QuitDataSmt;
import com.huishu.repository.QuitDataSmtRepository;
import com.huishu.service.QuitDataSmtService;
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
public class QuitDataSmtServiceImpl implements QuitDataSmtService {

    @Autowired
    private QuitDataSmtRepository quitDataSmtRepository;

    @Override
    @TargetDataSource(name = "chuangtou")
    public List<QuitDataSmt> findOneHundred(QuitDataSmt data, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<QuitDataSmt> page = quitDataSmtRepository.findAll(new Specification<QuitDataSmt>() {
            @Override
            public Predicate toPredicate(Root<QuitDataSmt> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
            }
        }, pageable);
        if (page == null || page.getContent() == null || page.getContent().size() == 0) {
            return new ArrayList<QuitDataSmt>();
        } else {
            return page.getContent();
        }
    }

    @Override
    @TargetDataSource(name = "chuangtou")
    public void save(List<QuitDataSmt> news) {
        quitDataSmtRepository.save(news);
    }

    @Override
    @TargetDataSource(name = "chuangtou")
    public void delete(List<QuitDataSmt> news) {
        // TODO Auto-generated method stub
        quitDataSmtRepository.delete(news);
    }


}
