package com.huishu.service.impl;

import com.huishu.datasource.TargetDataSource;
import com.huishu.entity.NewsLib;
import com.huishu.repository.NewsLibRepository;
import com.huishu.service.NewsLibService;
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
public class NewsLibServiceImpl implements NewsLibService {

    @Autowired
    private NewsLibRepository newsLibRepository;

    @Override
    @TargetDataSource(name = "news")
    public List<NewsLib> findOneHundred(NewsLib news, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<NewsLib> page = newsLibRepository.findAll((root, query, cb) -> {
            Path<String> fldRecdId = root.get("fldRecdId");
            Path<String> biaoShi = root.get("biaoShi");
            if (news != null) {
                List<Predicate> queryList = new ArrayList<Predicate>();
                if (StringUtils.isNotEmpty(news.getFldRecdId())) {
                    queryList.add(cb.greaterThan(fldRecdId, news.getFldRecdId()));
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
                query.where(querys).orderBy(new OrderImpl(fldRecdId, true));
            }
            return null;
        }, pageable);
        if (page == null || page.getContent() == null || page.getContent().size() == 0) {
            return new ArrayList<NewsLib>();
        } else {
            return page.getContent();
        }
    }

    @Override
    @TargetDataSource(name = "news")
    public void save(List<NewsLib> news) {
        newsLibRepository.save(news);
    }

    @Override
    @TargetDataSource(name = "zonghe")
    public List<NewsLib> findOneHundredZonghe(NewsLib news, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<NewsLib> page = newsLibRepository.findAll((root, query, cb) -> {
            Path<String> fldRecdId = root.get("fldRecdId");
            Path<String> biaoShi = root.get("biaoShi");
            if (news != null) {
                List<Predicate> queryList = new ArrayList<Predicate>();
                if (StringUtils.isNotEmpty(news.getFldRecdId())) {
                    queryList.add(cb.greaterThan(fldRecdId, news.getFldRecdId()));
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
                query.where(querys).orderBy(new OrderImpl(fldRecdId, true));
            }
            return null;
        }, pageable);
        if (page == null || page.getContent() == null || page.getContent().size() == 0) {
            return new ArrayList<NewsLib>();
        } else {
            return page.getContent();
        }
    }

    @Override
    @TargetDataSource(name = "zonghe")
    public void saveZonghe(List<NewsLib> news) {
        newsLibRepository.save(news);
    }


    @Override
    @TargetDataSource(name = "policy")
    public List<NewsLib> findOneHundredPolicy(NewsLib news, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<NewsLib> page = newsLibRepository.findAll((root, query, cb) -> {
            Path<String> fldRecdId = root.get("fldRecdId");
            Path<String> biaoShi = root.get("biaoShi");
            if (news != null) {
                List<Predicate> queryList = new ArrayList<Predicate>();
                if (StringUtils.isNotEmpty(news.getFldRecdId())) {
                    queryList.add(cb.greaterThan(fldRecdId, news.getFldRecdId()));
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
                query.where(querys).orderBy(new OrderImpl(fldRecdId, true));
            }
            return null;
        }, pageable);
        if (page == null || page.getContent() == null || page.getContent().size() == 0) {
            return new ArrayList<NewsLib>();
        } else {
            return page.getContent();
        }
    }

    @Override
    @TargetDataSource(name = "policy")
    public void savePolicy(List<NewsLib> news) {
        newsLibRepository.save(news);
    }

    @Override
    @TargetDataSource(name = "news")
    public void delete(List<NewsLib> news) {
        newsLibRepository.delete(news);
    }

    @Override
    @TargetDataSource(name = "zonghe")
    public void deleteZonghe(List<NewsLib> news) {
        newsLibRepository.delete(news);
    }

    @Override
    @TargetDataSource(name = "policy")
    public void deletePolicy(List<NewsLib> news) {
        newsLibRepository.delete(news);
    }

    @Override
    @TargetDataSource(name = "news")
    public void deleteById(String id) {
        newsLibRepository.delete(id);
    }

    @Override
    @TargetDataSource(name = "zonghe")
    public void deleteZongheById(String id) {
        newsLibRepository.delete(id);
    }

    @Override
    @TargetDataSource(name = "policy")
    public void deletePolicyById(String id) {
        newsLibRepository.delete(id);
    }

}
