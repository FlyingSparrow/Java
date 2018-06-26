package com.huishu.service.impl;

import com.huishu.datasource.TargetDataSource;
import com.huishu.entity.IndustryDataTyc;
import com.huishu.repository.IndustryDataTycRepository;
import com.huishu.service.IndustryDataTycService;
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
public class IndustryDataTycServiceImpl implements IndustryDataTycService {

    @Autowired
    private IndustryDataTycRepository industryDataTycRepository;

    @Override
    @TargetDataSource(name="news")
    public List<IndustryDataTyc> findOneHundred(IndustryDataTyc entity, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<IndustryDataTyc> page = industryDataTycRepository.findAll((root, query, cb) -> {
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
            return new ArrayList<IndustryDataTyc>();
        } else {
            return page.getContent();
        }
    }

    @Override
    @TargetDataSource(name="news")
    public Page<IndustryDataTyc> findByPage(IndustryDataTyc entity, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }

        Page<IndustryDataTyc> page = industryDataTycRepository.findAll((root, query, cb) ->  {
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

        return page;
    }

    @Override
    @TargetDataSource(name="news")
    public void save(List<IndustryDataTyc> list) {
        industryDataTycRepository.save(list);
    }

    @Override
    @TargetDataSource(name="news")
    public void delete(List<IndustryDataTyc> list) {
        industryDataTycRepository.delete(list);
    }

}
