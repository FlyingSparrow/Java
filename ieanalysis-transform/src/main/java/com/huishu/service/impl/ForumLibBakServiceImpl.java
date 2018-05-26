package com.huishu.service.impl;

import com.huishu.entity.ForumLibBak;
import com.huishu.repository.ForumLibBakRepository;
import com.huishu.service.ForumLibBakService;
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
public class ForumLibBakServiceImpl implements ForumLibBakService {

    @Autowired
    private ForumLibBakRepository forumLibBakRepository;

    @Override
    public List<ForumLibBak> findOneHundred(ForumLibBak forum, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<ForumLibBak> page = forumLibBakRepository.findAll(new Specification<ForumLibBak>() {
            @Override
            public Predicate toPredicate(Root<ForumLibBak> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Long> id = root.get("id");
                Path<String> biaoshi = root.get("biaoShi");
                if (forum != null) {
                    List<Predicate> queryList = new ArrayList<Predicate>();
                    if (forum.getId() != null) {
                        queryList.add(cb.greaterThan(id, forum.getId()));
                    }
                    if (StringUtils.isNotEmpty(forum.getBiaoShi())) {
                        queryList.add(cb.equal(biaoshi, forum.getBiaoShi()));
                    }
                    Predicate[] querys = new Predicate[queryList.size()];
                    if (queryList != null && queryList.size() > 0) {
                        for (int i = 0, len = queryList.size(); i < len; i++) {
                            querys[i] = queryList.get(i);
                        }
                    }
                    query.where(querys).orderBy(new OrderImpl(id, false));
                }
                return null;
            }
        }, pageable);
        if (page == null || page.getContent() == null || page.getContent().size() == 0) {
            return new ArrayList<ForumLibBak>();
        } else {
            return page.getContent();
        }
    }

    @Override
    public void save(List<ForumLibBak> news) {
        forumLibBakRepository.save(news);
    }

    @Override
    public void delete(List<ForumLibBak> news) {
        forumLibBakRepository.delete(news);
    }

    @Override
    public long findExist(ForumLibBak bak) {
        return forumLibBakRepository.countByFldUrlAddrAndFldtitle(bak.getFldUrlAddr(), bak.getFldtitle());
    }


}
