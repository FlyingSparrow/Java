package com.huishu.service.impl;

import com.huishu.entity.NewsLibBak;
import com.huishu.repository.NewsLibBakRepository;
import com.huishu.service.NewsLibBakService;
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
public class NewsLibBakServiceImpl implements NewsLibBakService {

    @Autowired
    private NewsLibBakRepository newsLibBakRepository;

    @Override
    public List<NewsLibBak> findOneHundred(NewsLibBak news, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<NewsLibBak> page = newsLibBakRepository.findAll((root, query, cb) -> {
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
        }, pageable);
        if (page == null || page.getContent() == null || page.getContent().size() == 0) {
            return new ArrayList<NewsLibBak>();
        } else {
            return page.getContent();
        }
    }

    @Override
    public void save(List<NewsLibBak> news) {
        newsLibBakRepository.save(news);
    }

    @Override
    public long findExist(NewsLibBak bak) {
        return newsLibBakRepository.countByFldUrlAddrAndFldtitle(bak.getFldUrlAddr(), bak.getFldtitle());
    }
}
