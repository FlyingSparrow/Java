package com.huishu.service.impl;

import com.huishu.entity.IndustryDataBak;
import com.huishu.repository.IndustryDataBakRepository;
import com.huishu.service.IndustryDataBakService;
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

@Service
public class IndustryDataBakServiceImpl implements IndustryDataBakService {

    @Autowired
    private IndustryDataBakRepository industryDataBakRepository;

    @Override
    public List<IndustryDataBak> findOneHundred(IndustryDataBak entity, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<IndustryDataBak> page = industryDataBakRepository.findAll((root, query, cb) -> {
            Path<Integer> id = root.get("id");
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
            return new ArrayList<IndustryDataBak>();
        } else {
            return page.getContent();
        }
    }

    @Override
    public void save(List<IndustryDataBak> list) {
        industryDataBakRepository.save(list);
    }

    @Override
    public void delete(List<IndustryDataBak> list) {
        industryDataBakRepository.delete(list);
    }

    @Override
    public boolean isExists(IndustryDataBak entity) {
        List<IndustryDataBak> list = industryDataBakRepository.findByEnterpriseName(entity.getEnterpriseName());
        return (list != null && list.size() > 0);
    }
}
