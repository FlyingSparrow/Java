package com.huishu.service.impl;

import com.huishu.datasource.TargetDataSource;
import com.huishu.entity.ForumLib;
import com.huishu.repository.ForumLibRepository;
import com.huishu.service.ForumLibService;
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
public class ForumLibServiceImpl implements ForumLibService {

    @Autowired
    private ForumLibRepository forumLibRepository;

    @Override
    @TargetDataSource(name = "news")
    public List<ForumLib> findOneHundred(ForumLib entity, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }

        Page<ForumLib> page = forumLibRepository.findAll((root, query, cb) ->  {
            Path<String> fldRecdId = root.get("fldRecdId");
            Path<Integer> fldFlowFlag = root.get("fldFlowFlag");
            if (entity != null) {
                List<Predicate> queryList = new ArrayList<Predicate>();
                if (StringUtils.isNotEmpty(entity.getFldRecdId())) {
                    queryList.add(cb.greaterThan(fldRecdId, entity.getFldRecdId()));
                }
                if (entity.getFldReadFlag() != null) {
                    queryList.add(cb.equal(fldFlowFlag, entity.getFldReadFlag()));
                }
                Predicate[] querys = new Predicate[queryList.size()];
                if (queryList != null && queryList.size() > 0) {
                    for (int i = 0, len = queryList.size(); i < len; i++) {
                        querys[i] = queryList.get(i);
                    }
                }
                query.where(querys).orderBy(new OrderImpl(fldRecdId, false));
            }
            return null;
        }, pageable);

        if (page == null || page.getContent() == null || page.getContent().size() == 0) {
            return new ArrayList<ForumLib>();
        } else {
            return page.getContent();
        }
    }

    @Override
    @TargetDataSource(name = "news")
    public Page<ForumLib> findPageList(ForumLib entity, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }

        Page<ForumLib> page = forumLibRepository.findAll((root, query, cb) ->  {
            Path<String> fldRecdId = root.get("fldRecdId");
            Path<Integer> fldFlowFlag = root.get("fldFlowFlag");
            if (entity != null) {
                List<Predicate> queryList = new ArrayList<Predicate>();
                if (StringUtils.isNotEmpty(entity.getFldRecdId())) {
                    queryList.add(cb.greaterThan(fldRecdId, entity.getFldRecdId()));
                }
                if (entity.getFldReadFlag() != null) {
                    queryList.add(cb.equal(fldFlowFlag, entity.getFldReadFlag()));
                }
                Predicate[] querys = new Predicate[queryList.size()];
                if (queryList != null && queryList.size() > 0) {
                    for (int i = 0, len = queryList.size(); i < len; i++) {
                        querys[i] = queryList.get(i);
                    }
                }
                query.where(querys).orderBy(new OrderImpl(fldRecdId, false));
            }
            return null;
        }, pageable);

        return page;
    }

    @Override
    @TargetDataSource(name = "news")
    public void save(List<ForumLib> news) {
        forumLibRepository.save(news);
    }

    @Override
    @TargetDataSource(name = "news")
    public void delete(List<ForumLib> news) {
        forumLibRepository.delete(news);
    }


}
